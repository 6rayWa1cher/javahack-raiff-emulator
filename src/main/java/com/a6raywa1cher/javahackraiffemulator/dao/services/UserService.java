package com.a6raywa1cher.javahackraiffemulator.dao.services;

import com.a6raywa1cher.javahackraiffemulator.models.User;

import java.util.Optional;

public interface UserService {
	User save(User user);

	Optional<User> getById(Integer id);

	Optional<User> findByLoginAndPassword(String login, String password);
}
