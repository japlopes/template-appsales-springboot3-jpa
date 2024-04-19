package com.modelmono.appwholesale.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "tb_category")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCateg;
	private String name;
	
	@Transient
	private Set<Product> products = new HashSet<>();
	
	public Category() {
	}

	public Category(Long idCateg, String name) {
		this.idCateg = idCateg;
		this.name = name;
	}

	public Long getIdCateg() {
		return idCateg;
	}

	public void setIdCateg(Long idCateg) {
		this.idCateg = idCateg;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Set<Product> getProducts() {
		return products;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idCateg);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		return Objects.equals(idCateg, other.idCateg);
	}

	
	
	
	
	

}
