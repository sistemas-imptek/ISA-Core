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

	@Override
	public Integer validateProdProv(Integer idProduct, Integer idProveedor) {
		
		List<Object> list = this.repo.validateProdProv(idProduct, idProveedor);
		
		if(list == null || list.isEmpty()) 
			return null;
		else 
			return (Integer)list.get(0);

	}

	@Override
	public int createProdProv(ProdProv pp) {
		this.repo.createProdProv(pp.getProduct().getIdProduct(), pp.getProvider().getIdProvider(), pp.getStatus(), pp.getTypeProv());
		return 0;
	}

	@Override
	public int updateProdProv(ProdProv pp) {
		this.repo.updateProdProv(pp.getStatus(), pp.getTypeProv(),pp.getProduct().getIdProduct(), pp.getProvider().getIdProvider());
		return 0;
	}

	
}
