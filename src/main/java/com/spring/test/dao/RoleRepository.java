package com.spring.test.dao;
import org.springframework.data.repository.CrudRepository;

import com.spring.test.domain.Account;
import com.spring.test.domain.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{
	
}
