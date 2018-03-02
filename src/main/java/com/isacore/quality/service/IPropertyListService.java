package com.isacore.quality.service;

import java.util.List;

import com.isacore.quality.model.PropertyList;
import com.isacore.util.CRUD;

public interface IPropertyListService extends CRUD<PropertyList> {

	List<PropertyList> findAllOnlyProperty();

	PropertyList findOneOnlyPropertyById(PropertyList pl);

}
