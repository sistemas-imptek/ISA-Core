package com.isacore.quality.service;

import com.isacore.quality.model.QualityCertificate;
import com.isacore.util.CRUD;

public interface IQualityCertificateService extends CRUD<QualityCertificate>{

	Integer findCertificateByPK(String idHcc, Integer idC);

	void createCertificate(QualityCertificate qc);
	
	void updateCount(Integer countC, String idHcc, Integer idC, String clientPrint);
	
}
