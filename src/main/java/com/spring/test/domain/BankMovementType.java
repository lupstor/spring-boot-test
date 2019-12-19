package com.spring.test.domain;

import java.util.Collection;

import javax.persistence.*;

@Entity
public class BankMovementType {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BMT_SEQ")
	@SequenceGenerator(sequenceName = "BMT_SEQ", allocationSize = 1, name = "BMT_SEQ")
	private Long id;
	
	private String name;
	
	@OneToMany(mappedBy="bankMovementType",targetEntity = BankMovement.class,
		       fetch=FetchType.LAZY)
	private Collection<BankMovement> bankMovements;
	
	public BankMovementType() {
	}
	public BankMovementType(String name) {
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
