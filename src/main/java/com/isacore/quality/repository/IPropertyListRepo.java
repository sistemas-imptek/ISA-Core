package com.isacore.quality.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.isacore.quality.model.PropertyList;

@Repository
public interface IPropertyListRepo extends JpaRepository<PropertyList, String>{

	@Query("select new com.isacore.quality.model.PropertyList(pl.idProperty, pl.nameProperty, pl.typeProperty, pl.typeProperty2) from propertylist pl")
	List<PropertyList> findAllOnlyProperty();
	
	@Query("select new com.isacore.quality.model.PropertyList(pl.idProperty, pl.nameProperty, pl.typeProperty, pl.typeProperty2) from propertylist pl where pl.idProperty = :idProperty")
	PropertyList findOneOnlyPropertyById(@Param("idProperty") String idProperty);
}