package com.isacore.quality.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isacore.quality.model.PropertyList;
import com.isacore.quality.repository.IPropertyListRepo;
import com.isacore.quality.service.IPropertyListService;

@Service
public class PropertyListServiceImpl implements IPropertyListService{

	@Autowired
	private IPropertyListRepo repo;
	
	@Override
	public List<PropertyList> findAll() {
		return this.repo.findAll();
	}

	@Override
	public PropertyList create(PropertyList obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PropertyList findById(PropertyList id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PropertyList update(PropertyList obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<PropertyList> findAllOnlyProperty() {
		return this.repo.findAllOnlyProperty();
	}
	
	@Override
	public PropertyList findOneOnlyPropertyById(PropertyList pl) {
		return this.repo.findOneOnlyPropertyById(pl.getIdProperty());
	}
}
