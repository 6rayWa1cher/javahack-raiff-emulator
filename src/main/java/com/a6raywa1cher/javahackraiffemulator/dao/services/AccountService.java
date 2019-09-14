package com.a6raywa1cher.javahackraiffemulator.dao.services;

import com.a6raywa1cher.javahackraiffemulator.models.Account;

import java.util.Optional;

public interface AccountService {
	Account save(Account account);

	Optional<Account> getById(Integer id);
}
