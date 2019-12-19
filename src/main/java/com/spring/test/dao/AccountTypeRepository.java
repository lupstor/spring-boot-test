package com.spring.test.dao;

import org.springframework.data.repository.CrudRepository;

import com.spring.test.domain.AccountType;

public interface AccountTypeRepository extends CrudRepository<AccountType, Long>{

}
