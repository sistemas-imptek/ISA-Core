package com.isacore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLOYEE")
public class Employee {
	
	@Id
	@Column(name = "EMP_CIEMPLOYEE", length = 20)
	private String ciEmployee;
	
	@Column(name = "EMP_NAME", length = 64, nullable = false)
	private String name;
	
	@Column(name = "EMP_LASTNAME", length = 64, nullable = false)
	private String lastName;
	
	@Column(name = "EMP_EMAIL", length = 64)
	private String email;
	
	@Column(name = "EMP_DATALIFE", length = 16)
	private String codDataLife;
	
	@Column(name = "EMP_STATE", length = 64)
	private String state;
	

	public String getCiEmployee() {
		return ciEmployee;
	}

	public void setCiEmployee(String ciEmployee) {
		this.ciEmployee = ciEmployee;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCodDataLife() {
		return codDataLife;
	}

	public void setCodDataLife(String codDataLife) {
		this.codDataLife = codDataLife;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	

}
