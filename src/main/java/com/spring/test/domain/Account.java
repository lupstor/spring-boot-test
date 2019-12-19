package com.spring.test.domain;

import java.util.Collection;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACCOUNT_SEQ")
	@SequenceGenerator(sequenceName = "ACCOUNT_SEQ", allocationSize = 1, name = "ACCOUNT_SEQ")
	private Long id;

	@Valid
	@ManyToOne(optional = false)
	@JoinColumn(name = "ACCOUNT_TYPE_ID", referencedColumnName = "ID")
	private AccountType accountType;

	@ManyToOne(optional = false)
	@JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "ID")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Customer customer;

    @NotNull(message = "Numero de cuenta es requerido")
	@Min(value=1000, message="Valor minimo permitido es 1000")
	private Long accountNumber;

	@OneToMany(mappedBy = "account", targetEntity = BankMovement.class, fetch = FetchType.LAZY, cascade= CascadeType.REMOVE)
	private Collection<BankMovement> bankMovements;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", accountType=" + accountType + ", customer=" + customer + ", accountNumber="
				+ accountNumber + ", bankMovements=" + bankMovements + "]";
	}

}
