package com.isacore.quality.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isacore.quality.model.OutputMethod;

@Repository
public interface IOutputMethodRepo extends JpaRepository<OutputMethod, Integer>{

}
