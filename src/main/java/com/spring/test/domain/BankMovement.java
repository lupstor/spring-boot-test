package com.spring.test.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.*;

@Entity
public class BankMovement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BANK_MOVEMENTS_SEQ")
	@SequenceGenerator(sequenceName = "BANK_MOVEMENTS_SEQ", allocationSize = 1, name = "BANK_MOVEMENTS_SEQ")
	private Long id;
	
	@ManyToOne(optional=false)
    @JoinColumn(name="ACCOUNT_ID",referencedColumnName="ID")
	private Account account;
	
	@ManyToOne(optional=false)
    @JoinColumn(name="BANK_MOVEMENT_TYPE_ID",referencedColumnName="ID")
	private BankMovementType bankMovementType;
	
	private LocalDateTime createDate;
	private BigDecimal amount;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public BankMovementType getBankMovementType() {
		return bankMovementType;
	}
	public void setBankMovementType(BankMovementType bankMovementType) {
		this.bankMovementType = bankMovementType;
	}
	public LocalDateTime getCreateDate() {
		return createDate;
	}
	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
}
