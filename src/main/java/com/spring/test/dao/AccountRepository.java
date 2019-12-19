package com.spring.test.dao;
import org.springframework.data.repository.CrudRepository;

import com.spring.test.domain.Account;

public interface AccountRepository extends CrudRepository<Account, Long>{
	
	boolean existsByAccountNumber(Long accountNumber);
}
