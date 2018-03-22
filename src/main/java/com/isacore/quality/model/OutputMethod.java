package com.isacore.quality.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "outputmehod")
@Table(name = "OUTPUT_METHOD")
public class OutputMethod {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OM_ID")
	private Integer idOM;
	
	@Column(name = "OM_CODE", nullable = false, length = 16)
	private String codeOM;
	
	@Column(name = "OM_DESCRIPTION", nullable = false, length = 3072)
	private String descrption;

	public Integer getIdOM() {
		return idOM;
	}

	public void setIdOM(Integer idOM) {
		this.idOM = idOM;
	}

	public String getCodeOM() {
		return codeOM;
	}

	public void setCodeOM(String codeOM) {
		this.codeOM = codeOM;
	}

	public String getDescrption() {
		return descrption;
	}

	public void setDescrption(String descrption) {
		this.descrption = descrption;
	}
	
}
