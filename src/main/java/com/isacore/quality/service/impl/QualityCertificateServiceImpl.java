package com.isacore.quality.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isacore.quality.model.QualityCertificate;
import com.isacore.quality.repository.IQualityCertificateRepo;
import com.isacore.quality.service.IQualityCertificateService;

@Service
public class QualityCertificateServiceImpl implements IQualityCertificateService{

	@Autowired
	private IQualityCertificateRepo repo;
	
	@Override
	public List<QualityCertificate> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QualityCertificate create(QualityCertificate qc) {
		return this.repo.save(qc);
	}

	@Override
	public QualityCertificate findById(QualityCertificate id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QualityCertificate update(QualityCertificate obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

}
