package com.isacore.quality.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isacore.quality.model.Property;
import com.isacore.quality.model.PropertyPK;

@Repository
public interface IPropertyRepo extends JpaRepository<Property, PropertyPK>{

}
