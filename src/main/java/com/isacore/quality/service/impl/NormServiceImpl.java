package com.isacore.quality.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isacore.quality.model.Norm;
import com.isacore.quality.repository.INormRepo;
import com.isacore.quality.service.INormService;

@Service
public class NormServiceImpl implements INormService{
	
	@Autowired
	private INormRepo repo;

	@Override
	public List<Norm> findAll() {
		return this.repo.findAll();
	}

	@Override
	public Norm create(Norm obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Norm findById(Norm id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Norm update(Norm obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Norm> findByKindNorm(String kindNorm) {
		return this.repo.findByKindNorm(kindNorm);
	}

}
