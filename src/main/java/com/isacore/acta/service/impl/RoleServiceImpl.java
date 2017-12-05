package com.isacore.acta.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isacore.acta.model.Role;
import com.isacore.acta.repository.IRoleRepo;
import com.isacore.acta.service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService{
	
	@Autowired
	private IRoleRepo roleRepo;

	@Override
	public List<Role> findAll() {
		return this.roleRepo.findAll();		
	}

	@Override
	public Role create(Role role) {
		return this.roleRepo.save(role);
	}

	@Override
	public Role findById(String idRole) {
		return this.roleRepo.findOne(idRole);
	}

	@Override
	public Role update(Role role) {
		return this.roleRepo.save(role);
	}

	@Override
	public void delete(String idRole) {
		this.roleRepo.delete(idRole);
		
	}

}