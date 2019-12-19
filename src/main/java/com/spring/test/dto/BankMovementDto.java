package com.spring.test.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface BankMovementDto {
	Long getId();
	LocalDateTime getCreateDate();
	Long getAccountNumber();
	String getBankMovementType();
	BigDecimal getAmount();	
}
