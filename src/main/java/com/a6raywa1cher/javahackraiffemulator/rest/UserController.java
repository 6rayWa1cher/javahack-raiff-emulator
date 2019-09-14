package com.a6raywa1cher.javahackraiffemulator.rest;

import com.a6raywa1cher.javahackraiffemulator.dao.services.UserService;
import com.a6raywa1cher.javahackraiffemulator.models.Account;
import com.a6raywa1cher.javahackraiffemulator.models.User;
import com.a6raywa1cher.javahackraiffemulator.rest.dto.UserLoginDTO;
import com.a6raywa1cher.javahackraiffemulator.rest.dto.UserRegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {
	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/reg")
	public ResponseEntity<User> createUser(@RequestBody @Valid UserRegistrationDTO dto) {
		User user = new User();
		user.setLogin(dto.getLogin());
		user.setPassword(dto.getPassword());
		return ResponseEntity.ok(userService.save(user));
	}

	@GetMapping("/{userId}/accounts")
	public ResponseEntity<List<Account>> getAllAccounts(@PathVariable Integer userId) {
		Optional<User> optionalUser = userService.getById(userId);
		if (optionalUser.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(optionalUser.get().getAccounts());
	}

	@PostMapping("/login")
	public ResponseEntity<User> loginUser(@RequestBody @Valid UserLoginDTO dto) {
		Optional<User> optionalUser = userService.findByLoginAndPassword(dto.getLogin(), dto.getPassword());
		if (optionalUser.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(optionalUser.get());
	}
}
