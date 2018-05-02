package com.isacore.quality.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isacore.quality.model.Area;
import com.isacore.quality.repository.IAreasRepo;
import com.isacore.quality.service.IAreasService;

@Service
public class AreaServiceImpl implements IAreasService{

	@Autowired
	private IAreasRepo repo;
	
	@Override
	public List<Area> findAll() {
		return this.repo.findAll();
	}

	@Override
	public Area create(Area area) {
		return this.repo.save(area);
	}

	@Override
	public Area findById(Area area) {
		return this.repo.findOne(area.getIdArea());
	}

	@Override
	public Area update(Area area) {
		return this.repo.save(area);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

}