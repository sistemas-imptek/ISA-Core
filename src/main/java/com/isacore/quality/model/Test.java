package com.isacore.quality.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "test")
@Table(name = "TEST")
public class Test {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TEST_ID")
	private Integer idTest;
	
	@Column(name = "TEST_SAP_CODE", nullable = false, length = 32)
	private String sapCode;
	
	@Column(name = "TEST_PROPERTY_CODE", nullable = false, length = 64)
	private String propertyCode;
	
	@Column(name = "TEST_BATCH", nullable = false, length = 64)
	private String batchTest;
	
	@Column(name = "TEST_RESULT", nullable = false, columnDefinition = "decimal(8,2)")
	private double resultTest;

	public Integer getIdTest() {
		return idTest;
	}

	public void setIdTest(Integer idTest) {
		this.idTest = idTest;
	}

	public String getSapCode() {
		return sapCode;
	}

	public void setSapCode(String sapCode) {
		this.sapCode = sapCode;
	}

	public String getPropertyCode() {
		return propertyCode;
	}

	public void setPropertyCode(String propertyCode) {
		this.propertyCode = propertyCode;
	}

	public String getBatchTest() {
		return batchTest;
	}

	public void setBatchTest(String batchTest) {
		this.batchTest = batchTest;
	}

	public double getResultTest() {
		return resultTest;
	}

	public void setResultTest(double resultTest) {
		this.resultTest = resultTest;
	}
	
	
}
