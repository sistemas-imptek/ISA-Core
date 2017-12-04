package com.isacore.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.isacore.localdate.converter.LocalDateTimeConverter;


@Entity(name = "userImptek")
@Table(name = "USERIMPTEK")
public class UserImptek {
	
	@Id
	@Column(name = "USER_IDUSER", nullable = false, length = 20)
	private String idUser;
	
	@Column(name = "EMP_CIEMPLOYEE", nullable = false, length = 20)
	private String idEmployee;
	
	@Column(name = "USER_NICKNAME", nullable = false, length = 32)
	private String nickName;
	
	@Column(name = "USER_PASS", nullable = false, length = 128)
	private String userPass;
	
	@Column(name = "USER_LASTKEYDATECHANGE", nullable = true)
	@Convert(converter = LocalDateTimeConverter.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy@HH:mm:ss", timezone="America/Bogota")
	private LocalDateTime lastKeyDateChange;
	
	@Column(name = "USER_LASTACCESS", nullable = false)
	@Convert(converter = LocalDateTimeConverter.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy@HH:mm:ss", timezone="America/Bogota")
	private LocalDateTime lastAccess;
	
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "ROLE_NAME",insertable = true, updatable = true, nullable = true)
	private Role role;

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public String getIdEmployee() {
		return idEmployee;
	}

	public void setIdEmployee(String idEmployee) {
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

	public LocalDateTime getLastKeyDateChange() {
		return lastKeyDateChange;
	}

	public void setLastKeyDateChange(LocalDateTime lastKeyDateChange) {
		this.lastKeyDateChange = lastKeyDateChange;
	}

	public LocalDateTime getLastAccess() {
		return lastAccess;
	}

	public void setLastAccess(LocalDateTime lastAccess) {
		this.lastAccess = lastAccess;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
	
}
