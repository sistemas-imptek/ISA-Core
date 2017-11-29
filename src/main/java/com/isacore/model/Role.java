	package com.isacore.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ROLE")
public class Role {

	@Id
	@Column(name = "ROL_NAME", nullable = false, length = 32)
	private String  rolName;
	
	@Column(name = "USER_IDUSER", nullable = false)
	private String idUserImptek;
	
	@Column(name = "ROL_DESC", nullable = false, length = 128)
	private String rolDescription;
	
	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name = "MENU_ROL", 
	joinColumns = {@JoinColumn(name = "ROL_NAME")}, 
	inverseJoinColumns = {@JoinColumn(name = "MEN_IDMENU")})
	private List<Menu> Menus;

	public String getRolName() {
		return rolName;
	}

	public void setRolName(String rolName) {
		this.rolName = rolName;
	}

	public String getRolDescription() {
		return rolDescription;
	}

	public void setRolDescription(String rolDescription) {
		this.rolDescription = rolDescription;
	}
	
	
	
}
