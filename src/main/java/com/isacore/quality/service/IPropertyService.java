package com.isacore.quality.service;

import com.isacore.quality.model.Property;
import com.isacore.util.CRUD;

public interface IPropertyService extends CRUD<Property>{

	String validateExistProperty(Integer idProduct, String idPropertyList);
	
	int createProperty(Property p, String user);
	
	int updateProperty(Property p, String user);
	
}
