package com.isacore.quality.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isacore.quality.model.ProcessTestRequest;

@Repository
public interface IProcessTestRequestRepo extends JpaRepository<ProcessTestRequest, Integer> {

}
