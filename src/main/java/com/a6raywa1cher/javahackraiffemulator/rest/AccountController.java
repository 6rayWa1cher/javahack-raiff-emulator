package com.a6raywa1cher.javahackraiffemulator.rest;

import com.a6raywa1cher.javahackraiffemulator.dao.services.AccountService;
import com.a6raywa1cher.javahackraiffemulator.dao.services.UserService;
import com.a6raywa1cher.javahackraiffemulator.models.Account;
import com.a6raywa1cher.javahackraiffemulator.models.User;
import com.a6raywa1cher.javahackraiffemulator.rest.dto.AccountCreationDTO;
import com.a6raywa1cher.javahackraiffemulator.rest.dto.AddMoneyDTO;
import com.a6raywa1cher.javahackraiffemulator.rest.exception.SafeTransferFlagViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Optional;

@Controller
@RequestMapping("/account")
public class AccountController {
	private AccountService accountService;
	private UserService userService;

	@Autowired
	public AccountController(AccountService accountService, UserService userService) {
		this.accountService = accountService;
		this.userService = userService;
	}

	@PostMapping("")
	public ResponseEntity<Account> createAccount(@RequestBody @Valid AccountCreationDTO dto) {
		Optional<User> optionalUser = userService.getById(dto.getUserId());
		if (optionalUser.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Account account = new Account();
		account.setMoney(BigDecimal.ZERO);
		account.setUser(optionalUser.get());
		account.setFrozenBySafeTransfer(dto.isFrozenBySafeTransfer());
		return ResponseEntity.ok(accountService.save(account));
	}

	@GetMapping("/{accountId}")
	public ResponseEntity<Account> getById(@PathVariable Integer accountId) {
		Optional<Account> optionalAccount = accountService.getById(accountId);
		if (optionalAccount.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(optionalAccount.get());
	}

	@PostMapping("/{accountId}/add")
	public ResponseEntity<Account> addMoney(@PathVariable Integer accountId, @RequestBody @Valid AddMoneyDTO dto) {
		Optional<Account> optionalAccount = accountService.getById(accountId);
		if (optionalAccount.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Account account = optionalAccount.get();
		if (account.getFrozenBySafeTransfer() != dto.getSafeTransfer()) {
			throw new SafeTransferFlagViolationException();
		}
		account.setMoney(optionalAccount.get().getMoney().add(dto.getMoney()));
		return ResponseEntity.ok(accountService.save(optionalAccount.get()));
	}
}
