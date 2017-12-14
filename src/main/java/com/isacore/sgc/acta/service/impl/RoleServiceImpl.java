package com.isacore.sgc.acta.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isacore.sgc.acta.model.Role;
import com.isacore.sgc.acta.repository.IRoleRepo;
import com.isacore.sgc.acta.service.IRoleService;

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
