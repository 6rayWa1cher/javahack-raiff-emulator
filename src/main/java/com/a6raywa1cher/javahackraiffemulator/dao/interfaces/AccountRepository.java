package com.a6raywa1cher.javahackraiffemulator.dao.interfaces;

import com.a6raywa1cher.javahackraiffemulator.models.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {
}
