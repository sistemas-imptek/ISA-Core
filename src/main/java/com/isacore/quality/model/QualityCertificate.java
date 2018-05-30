package com.isacore.quality.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "qualityCertificate")
@Table(name = "QUALITY_CERTIFICATE")
public class QualityCertificate {

	@Id
	@Column(name = "QC_ORDER", nullable = false, length = 64)
	private String order;
	
	@Column(name = "QC_CLIENT", nullable = false, length = 128)
	private String client;
	
	@Column(name = "QC_SAP_CODE", nullable = false, length = 64)
	private String hccSapCode;
	
	@Column(name = "QC_EMAIL", nullable = false, length = 128)
	private String email;


	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHccSapCode() {
		return hccSapCode;
	}

	public void setHccSapCode(String hccSapCode) {
		this.hccSapCode = hccSapCode;
	}
	
}
