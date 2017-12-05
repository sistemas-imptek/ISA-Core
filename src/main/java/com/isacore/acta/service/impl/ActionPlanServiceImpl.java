package com.isacore.acta.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isacore.acta.model.ActionPlan;
import com.isacore.acta.repository.IActionPlanRepo;
import com.isacore.acta.service.IActionPlanService;

@Service
public class ActionPlanServiceImpl implements IActionPlanService{

	@Autowired
	private IActionPlanRepo repo;
	
	@Override
	public List<ActionPlan> findAll() {
		return this.repo.findAll();
	}

	@Override
	public ActionPlan create(ActionPlan obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActionPlan findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActionPlan update(ActionPlan obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

}
