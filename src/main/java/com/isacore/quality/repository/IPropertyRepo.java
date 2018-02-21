package com.isacore.quality.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isacore.quality.model.Property;

@Repository
public interface IPropertyRepo extends JpaRepository<Property, Integer>{

}
