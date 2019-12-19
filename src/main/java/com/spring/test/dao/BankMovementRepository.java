package com.spring.test.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.spring.test.domain.BankMovement;
import com.spring.test.dto.BankMovementDto;

public interface BankMovementRepository extends CrudRepository<BankMovement, Long>{
	@Query("SELECT bm.id AS id, bm.createDate AS createDate, a.accountNumber AS accountNumber, bmt.name AS bankMovementType, bm.amount AS amount "
			+ "FROM Customer c "
			+ "INNER JOIN c.accounts a "
			+ "INNER JOIN a.bankMovements bm "
			+ "INNER JOIN bm.bankMovementType bmt "
			+ "WHERE c.id = ?1")
	Iterable<BankMovementDto> fetchBankMovementsByCustomer(Long id);
	
	@Query(value = "SELECT bm.ID AS id, bm.CREATE_DATE AS createDate, a.ACCOUNT_NUMBER AS accountNumber, bmt.NAME AS bankMovementType, bm.AMOUNT AS amount "
			+ "FROM CUSTOMER c "
			+ "INNER JOIN ACCOUNT a ON a.CUSTOMER_ID = c.ID "
			+ "INNER JOIN BANK_MOVEMENT bm ON bm.ACCOUNT_ID = a.ID  "
			+ "INNER JOIN BANK_MOVEMENT_TYPE bmt ON bmt.ID = bm.BANK_MOVEMENT_TYPE_ID "
			+ "WHERE c.ID = ?1", nativeQuery = true)
	Iterable<BankMovementDto> fetchNativeBankMovementsByCustomer(Long id);

}
