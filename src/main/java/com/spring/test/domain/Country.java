package com.spring.test.domain;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Country {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COUNTRIES_SEQ")
	@SequenceGenerator(sequenceName = "COUNTRIES_SEQ", allocationSize = 1, name = "COUNTRIES_SEQ")
    @NotNull(message = "Pais es requerido")
	@Min(value=1, message="Seleccionar pais")
	private Long id;
	
	private String name;
	
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
	@Override
	public String toString() {
		return "Country [id=" + id + ", name=" + name + "]";
	}
	
}
