package com.spring.test.dao;
import org.springframework.data.repository.CrudRepository;

import com.spring.test.domain.Country;

public interface CountryRepository extends CrudRepository<Country, Long>{
	

}
