package com.a6raywa1cher.javahackraiffemulator.rest;

import com.a6raywa1cher.javahackraiffemulator.components.HashingService;
import com.a6raywa1cher.javahackraiffemulator.dao.services.UserService;
import com.a6raywa1cher.javahackraiffemulator.models.Account;
import com.a6raywa1cher.javahackraiffemulator.models.User;
import com.a6raywa1cher.javahackraiffemulator.rest.dto.UserLoginDTO;
import com.a6raywa1cher.javahackraiffemulator.rest.dto.UserRegistrationDTO;
import com.a6raywa1cher.javahackraiffemulator.rest.mirror.AccountMirror;
import com.a6raywa1cher.javahackraiffemulator.rest.mirror.UserMirror;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class UserController {
	private UserService userService;
	private HashingService hashingService;

	@Autowired
	public UserController(UserService userService, HashingService hashingService) {
		this.userService = userService;
		this.hashingService = hashingService;
	}

	@PostMapping("/reg")
	public ResponseEntity<UserMirror> createUser(@RequestBody @Valid UserRegistrationDTO dto) {
		User user = new User();
		user.setLogin(dto.getLogin());
		user.setPassword(hashingService.hash(dto.getPassword()));
		Account account = new Account();
		account.setMoney(BigDecimal.ZERO);
		account.setFrozenBySafeTransfer(false);
		account.setUser(user);
		user.setFavoriteAccount(account);
		user.setAccounts(Collections.singletonList(account));
		return ResponseEntity.ok(UserMirror.convert(userService.save(user)));
	}

	@GetMapping("/{userId}/accounts")
	public ResponseEntity<List<AccountMirror>> getAllAccounts(@PathVariable Integer userId) {
		Optional<User> optionalUser = userService.getById(userId);
		if (optionalUser.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(optionalUser.get().getAccounts().stream()
				.map(AccountMirror::convert)
				.collect(Collectors.toList()));
	}

	@PostMapping("/login")
	public ResponseEntity<UserMirror> loginUser(@RequestBody @Valid UserLoginDTO dto) {
		Optional<User> optionalUser = userService.findByLogin(dto.getLogin());
		if (optionalUser.isEmpty() || !hashingService.compare(dto.getPassword(), optionalUser.get().getPassword())) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(UserMirror.convert(optionalUser.get()));
	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserMirror> getUser(@PathVariable Integer userId) {
		Optional<User> optionalUser = userService.getById(userId);
		if (optionalUser.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(UserMirror.convert(optionalUser.get()));
	}
}
