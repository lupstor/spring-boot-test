package com.spring.test.dao;
import org.springframework.data.repository.CrudRepository;

import com.spring.test.domain.BankMovementType;

public interface BankMovementTypeRepository extends CrudRepository<BankMovementType, Long>{
	

}
