package com.isacore.quality.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isacore.quality.model.Test;
import com.isacore.quality.repository.ITestRepo;
import com.isacore.quality.service.ITestService;

@Service
public class TestServiceImpl implements ITestService{

	@Autowired
	private ITestRepo repo;
	
	@Override
	public List<Test> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Test create(Test obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Test findById(Test id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Test update(Test obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Test> findByBatch(String batch) {
		return this.repo.findByBatch(batch);
	}

}
