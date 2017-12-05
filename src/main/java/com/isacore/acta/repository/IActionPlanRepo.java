package com.isacore.acta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isacore.acta.model.ActionPlan;

@Repository
public interface IActionPlanRepo extends JpaRepository<ActionPlan, String>{

}
