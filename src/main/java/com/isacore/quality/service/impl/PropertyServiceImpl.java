package com.isacore.quality.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isacore.quality.model.Property;
import com.isacore.quality.repository.IPropertyRepo;
import com.isacore.quality.service.IPropertyService;

@Service
public class PropertyServiceImpl implements IPropertyService{

	@Autowired
	private IPropertyRepo propertyRepo;
	
	@Override
	public List<Property> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Property create(Property prop) {
		return this.propertyRepo.save(prop);
	}

	@Override
	public Property findById(Property id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Property update(Property obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String validateExistProperty(Integer idProduct, String idPropertyList) {
		
		List<Object> list = this.propertyRepo.validateExistProperty(idProduct, idPropertyList);
		
		if(list == null || list.isEmpty()) 
			return null;
		else 
			return (String)list.get(0);
		
	}

	@Override
	public int createProperty(Property p, String user) {

		LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateUpdate = now.format(formatter);
		
		this.propertyRepo.createProperty(p.getProduct().getIdProduct(), p.getPropertyList().getIdProperty(), p.getMinProperty(), p.getMaxProperty(), p.getUnitProperty(), p.getViewProperty(), dateUpdate, p.getTypeProperty(), user);
		return 0;
	}

	@Override
	public int updateProperty(Property p, String user) {
		
		LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateUpdate = now.format(formatter);
        
		this.propertyRepo.updateProperty(p.getMinProperty(), p.getMaxProperty(), p.getUnitProperty(), p.getViewProperty(), dateUpdate, p.getTypeProperty(), user, p.getProduct().getIdProduct(), p.getPropertyList().getIdProperty());
		return 0;
	}

}
