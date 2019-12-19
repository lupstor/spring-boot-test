package com.spring.test.domain;

import java.time.LocalDate;
import java.util.Collection;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUSTOMERS_SEQ")
	@SequenceGenerator(sequenceName = "CUSTOMERS_SEQ", allocationSize = 1, name = "CUSTOMERS_SEQ")
	private Long id;
	
    @NotBlank(message = "Nombres son obligatorios")
	private String firstName;
	
    @NotBlank(message = "Apellidos son obligatorios")
	private String lastName;
	
    @NotNull(message = "Pais es requerido")
    @Valid()
	@ManyToOne(optional=false)
    @JoinColumn(name="COUNTRY_ID",referencedColumnName="ID")
	private Country country;
    
    @NotNull(message = "Fecha de nacimiento es obligatoria")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthDate;
    
	@OneToMany(mappedBy="customer",targetEntity = Account.class,
		       fetch=FetchType.EAGER, cascade = CascadeType.REMOVE)
	private Collection<Account> accounts;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public Collection<Account> getAccounts() {
		return accounts;
	}
	public void setAccounts(Collection<Account> accounts) {
		this.accounts = accounts;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", country=" + country
				+ ", birthDate=" + birthDate + ", accounts=" + accounts + "]";
	}
	
	
}
