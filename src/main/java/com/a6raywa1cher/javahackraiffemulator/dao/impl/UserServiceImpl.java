package com.a6raywa1cher.javahackraiffemulator.dao.impl;

import com.a6raywa1cher.javahackraiffemulator.dao.interfaces.UserRepository;
import com.a6raywa1cher.javahackraiffemulator.dao.services.UserService;
import com.a6raywa1cher.javahackraiffemulator.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
	private UserRepository repository;

	@Autowired
	public UserServiceImpl(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public User save(User user) {
		return repository.save(user);
	}

	@Override
	public Optional<User> getById(Integer id) {
		return repository.findById(id);
	}

	@Override
	public Optional<User> findByLogin(String login) {
		return repository.findByLogin(login);
	}
}
