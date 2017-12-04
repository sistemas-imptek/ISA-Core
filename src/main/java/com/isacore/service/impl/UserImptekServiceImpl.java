package com.isacore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isacore.model.UserImptek;
import com.isacore.repository.IUserImptekRepo;
import com.isacore.service.IUserImptekService;

@Service
public class UserImptekServiceImpl implements IUserImptekService{

	@Autowired
	private IUserImptekRepo repo;
	
	@Override
	public List<UserImptek> findAll() {		
		return this.repo.findAll();
	}

	@Override
	public UserImptek create(UserImptek user) {
		return this.repo.save(user);
	}

	@Override
	public UserImptek findById(String iduser) { 
		return this.repo.findOne(iduser);
	}

	@Override
	public UserImptek update(UserImptek user) {
		return this.repo.save(user);
	}

	@Override
	public void delete(String iduser) {
		this.repo.delete(iduser);
	}

	@Override
	public UserImptek findByUserImptek(String nickname) {
		return this.repo.findUserByNickname(nickname);
	}

	

}
