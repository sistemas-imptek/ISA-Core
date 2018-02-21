package com.isacore.quality.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isacore.quality.model.Product;
import com.isacore.quality.repository.IProductRepo;
import com.isacore.quality.service.IProductService;

@Service
public class ProductServiceImpl implements IProductService{

	@Autowired
	private IProductRepo repo;
	
	@Override
	public List<Product> findAll() {		
		return this.repo.findAll();
	}

	@Override
	public Product create(Product obj) {
		return this.repo.save(obj);
	}

	@Override
	public Product findById(Product obj) {
		return this.repo.findOne(obj.getIdProduct());
	}

	@Override
	public Product update(Product obj) {
		return this.repo.save(obj);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

}
