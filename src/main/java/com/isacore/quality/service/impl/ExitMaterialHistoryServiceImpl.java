package com.isacore.quality.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isacore.quality.model.ExitMaterialHistory;
import com.isacore.quality.repository.IExitMaterialHistoryRepo;
import com.isacore.quality.service.IExitMaterialHistoryService;

@Service
public class ExitMaterialHistoryServiceImpl implements IExitMaterialHistoryService {

	@Autowired
	private IExitMaterialHistoryRepo repo;
	
	@Override
	public List<ExitMaterialHistory> findAll() {
		
		return this.repo.findAll();
	}

	@Override
	public ExitMaterialHistory create(ExitMaterialHistory obj) {
		
		return this.repo.save(obj);
	}

	@Override
	public ExitMaterialHistory findById(ExitMaterialHistory obj) {
		Optional<ExitMaterialHistory> com = this.repo.findById(obj.getIdEMH());
		if (com.isPresent())
			return com.get();
		else
			return null;
	}

	@Override
	public ExitMaterialHistory update(ExitMaterialHistory obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

	

}
