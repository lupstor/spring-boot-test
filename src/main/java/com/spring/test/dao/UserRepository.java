package com.spring.test.dao;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.spring.test.domain.Account;
import com.spring.test.domain.Role;
import com.spring.test.domain.User;

public interface UserRepository extends CrudRepository<User, String>{
	Optional<User> findByAuthToken(String authToken);
}
