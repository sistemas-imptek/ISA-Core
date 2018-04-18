package com.isacore.quality.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isacore.quality.model.OutputMethod;
import com.isacore.quality.repository.IOutputMethodRepo;
import com.isacore.quality.service.IOutputMethodService;

@Service
public class OutputMethodServiceImpl implements IOutputMethodService{

	@Autowired
	private IOutputMethodRepo repo;
	
	@Override
	public List<OutputMethod> findAll() {
		return this.repo.findAll();
	}

	@Override
	public OutputMethod create(OutputMethod om) {
		return this.repo.save(om);
	}

	@Override
	public OutputMethod findById(OutputMethod om) {
		return this.repo.findOne(om.getIdOM());
	}

	@Override
	public OutputMethod update(OutputMethod om) {
		return this.repo.save(om);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

}
