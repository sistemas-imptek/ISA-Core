package com.isacore.service;

import java.util.List;

import com.isacore.model.Role;

public interface IRoleService {

	List<Role> findAll();
	
	Role create(Role role);
	
	Role findById(String idRole);
	
	Role update(Role role);
	
	void delete(String idRole);
	
}
