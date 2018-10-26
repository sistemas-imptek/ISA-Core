package com.isacore.quality.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isacore.quality.model.Feature;

public interface IFeatureRepo extends JpaRepository<Feature, Integer>{

}
