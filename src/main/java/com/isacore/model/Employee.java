package com.isacore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLOYEE")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Idemployee;
	@Column(name = "EMP_NAME", length = 64, nullable = false)
	private String name;
	@Column(name = "EMP_LASTNAME", length = 64, nullable = false)
	private String lastName;
	@Column(name = "EMP_CIEMPLOYEE", length = 10)
	private String ciEmployee;
	@Column(name = "EMP_EMAIL", length = 64)
	private String email;
	@Column(name = "EMP_STATE", length = 64)
	private String state;
	public Integer getIdemployee() {
		return Idemployee;
	}
	public void setIdemployee(Integer idemployee) {
		Idemployee = idemployee;
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
	public String getCiEmployee() {
		return ciEmployee;
	}
	public void setCiEmployee(String ciEmployee) {
		this.ciEmployee = ciEmployee;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
	
	
	
	
	
	

}
