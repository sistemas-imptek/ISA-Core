package com.isacore.quality.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isacore.quality.model.ProdProv;
import com.isacore.quality.repository.IProdProvRepo;
import com.isacore.quality.service.IProdProvService;

@Service
public class ProdProvServiceImpl implements IProdProvService{

	@Autowired
	private IProdProvRepo repo;

	@Override
	public List<ProdProv> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProdProv create(ProdProv pp) {
		return this.repo.save(pp);
	}

	@Override
	public ProdProv findById(ProdProv pp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProdProv update(ProdProv pp) {
		return this.repo.save(pp);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}
}
