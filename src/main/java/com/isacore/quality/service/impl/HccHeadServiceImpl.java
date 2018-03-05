package com.isacore.quality.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isacore.quality.model.HccHead;
import com.isacore.quality.repository.IHccHeadRepo;
import com.isacore.quality.service.IHccHeadService;

@Service
public class HccHeadServiceImpl implements IHccHeadService{
	
	@Autowired
	private IHccHeadRepo repo;

	@Override
	public List<HccHead> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HccHead create(HccHead hcc) {
		return this.repo.save(hcc);
	}

	@Override
	public HccHead findById(HccHead id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HccHead update(HccHead hcc) {
		return this.repo.save(hcc);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

}
