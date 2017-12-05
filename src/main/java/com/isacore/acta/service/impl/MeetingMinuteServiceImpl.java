package com.isacore.acta.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isacore.acta.model.MeetingMinute;
import com.isacore.acta.repository.IMeetingMinuteRepo;
import com.isacore.acta.service.IMeetingMinuteService;

@Service
public class MeetingMinuteServiceImpl implements IMeetingMinuteService{
	
	@Autowired
	private IMeetingMinuteRepo repo;

	@Override
	public List<MeetingMinute> findAll() {
		return this.repo.findAll();
	}

	@Override
	public MeetingMinute create(MeetingMinute mm) {
		return this.repo.save(mm);
	}

	@Override
	public MeetingMinute findById(String idMinute) {
		return this.findById(idMinute);
	}

	@Override
	public MeetingMinute update(MeetingMinute mm) {
		return this.repo.save(mm);
	}

	@Override
	public void delete(String idMinute) {
		this.repo.delete(idMinute);		
	}

}
