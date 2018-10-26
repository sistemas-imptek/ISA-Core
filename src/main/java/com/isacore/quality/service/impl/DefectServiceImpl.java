package com.isacore.quality.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isacore.quality.model.DefectNpc;
import com.isacore.quality.repository.IDefectsRepo;
import com.isacore.quality.service.IDefectNCPService;

@Service
public class DefectServiceImpl implements IDefectNCPService{
	
	@Autowired
	private IDefectsRepo repo;

	@Override
	public List<DefectNpc> findAll() {
		return this.repo.findAll();
	}

	@Override
	public DefectNpc create(DefectNpc defect) {
		return this.repo.save(defect);
	}

	@Override
	public DefectNpc findById(DefectNpc defect) {
		Optional<DefectNpc> o = this.repo.findById(defect.getIdDefect());
		if(o.isPresent())
			return o.get();
		else
			return null;  
	}

	@Override
	public DefectNpc update(DefectNpc defect) {
		return this.repo.save(defect);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

}
