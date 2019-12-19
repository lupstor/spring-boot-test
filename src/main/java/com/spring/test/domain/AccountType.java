package com.spring.test.domain;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class AccountType {
	
    
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AT_SEQ")
	@SequenceGenerator(sequenceName = "AT_SEQ", allocationSize = 1, name = "AT_SEQ")
	@NotNull(message = "Tipo de cuenta requerido")
	@Min(value=1, message="Seleccionar tipo de cuenta")
	private Long id;
	private String Name;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	@Override
	public String toString() {
		return "AccountType [Id=" + id + ", Name=" + Name + "]";
	}
	
	
}
