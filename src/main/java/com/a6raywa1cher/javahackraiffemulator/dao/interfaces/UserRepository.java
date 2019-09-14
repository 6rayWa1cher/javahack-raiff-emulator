package com.a6raywa1cher.javahackraiffemulator.dao.interfaces;

import com.a6raywa1cher.javahackraiffemulator.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	Optional<User> findByLogin(String login);
}
