package com.a6raywa1cher.javahackraiffemulator.dao.impl;

import com.a6raywa1cher.javahackraiffemulator.dao.interfaces.AccountRepository;
import com.a6raywa1cher.javahackraiffemulator.dao.services.AccountService;
import com.a6raywa1cher.javahackraiffemulator.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
	private AccountRepository repository;

	@Autowired
	public AccountServiceImpl(AccountRepository repository) {
		this.repository = repository;
	}

	@Override
	public Account save(Account account) {
		return repository.save(account);
	}

	@Override
	public Optional<Account> getById(Integer id) {
		return repository.findById(id);
	}
}
