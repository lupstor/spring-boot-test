package com.spring.test.dao;

import org.springframework.data.repository.CrudRepository;

import com.spring.test.domain.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long>{

}
