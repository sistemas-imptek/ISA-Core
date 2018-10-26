package com.isacore.quality.service.impl;

import java.util.List;
import java.util.Optional;

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
		return this.repo.findAll();
	}

	@Override
	public Test create(Test obj) {
		return this.repo.save(obj);
	}

	@Override
	public Test findById(Test obj) {
		Optional<Test> test = this.repo.findById(obj.getIdTest());
		if(test.isPresent())
			return test.get();
		else		
			return null; 
	}

	@Override
	public Test update(Test obj) {
		return this.repo.save(obj);
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
