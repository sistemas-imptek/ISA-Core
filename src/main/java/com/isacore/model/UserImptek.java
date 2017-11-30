package com.isacore.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USERIMPTEK")
public class UserImptek {
	
	@Id
	@Column(name = "USER_IDUSER", nullable = false, length = 20)
	private Integer idUser;
	
	@Column(name = "EMP_CIEMPLOYEE", nullable = false, length = 20)
	private Integer idEmployee;
	
	@Column(name = "USER_NICKNAME", nullable = false, length = 32)
	private String nickName;
	
	@Column(name = "USER_PASS", nullable = false, length = 128)
	private String userPass;
	
	@Column(name = "USER_LASTKEYDATECHANGE", nullable = true)
	private LocalDate lastKeyDateChange;
	
	@Column(name = "USER_LASTACCESS", nullable = false)
	private LocalDate lastAccess;
	
	@ManyToOne
	@JoinColumn(name = "ROLE_NAME",insertable = true, updatable = true, nullable = true)
	private Role role;

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public Integer getIdEmployee() {
		return idEmployee;
	}

	public void setIdEmployee(Integer idEmployee) {
		this.idEmployee = idEmployee;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public LocalDate getLastKeyDateChange() {
		return lastKeyDateChange;
	}

	public void setLastKeyDateChange(LocalDate lastKeyDateChange) {
		this.lastKeyDateChange = lastKeyDateChange;
	}

	public LocalDate getLastAccess() {
		return lastAccess;
	}

	public void setLastAccess(LocalDate lastAccess) {
		this.lastAccess = lastAccess;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
	
}
