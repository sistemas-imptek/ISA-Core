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
	
	@Column(name = "TEST_SAP_CODE", nullable = true, length = 32)
	private String sapCode;
	
	@Column(name = "TEST_PROPERTY_CODE", nullable = false, length = 64)
	private String idProperty;
	
	@Column(name = "TEST_BATCH", nullable = false, length = 64)
	private String batchTest;
	
	@Column(name = "TEST_RESULT", nullable = false, columnDefinition = "decimal(10,5)")
	private Double resultTest;

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

	public String getIdProperty() {
		return idProperty;
	}

	public void setIdProperty(String propertyCode) {
		this.idProperty = propertyCode;
	}

	public String getBatchTest() {
		return batchTest;
	}

	public void setBatchTest(String batchTest) {
		this.batchTest = batchTest;
	}

	public Double getResultTest() {
		return resultTest;
	}

	public void setResultTest(Double resultTest) {
		this.resultTest = resultTest;
	}

	@Override
	public String toString() {
		return "Test [idTest=" + idTest + ", sapCode=" + sapCode + ", idProperty=" + idProperty + ", batchTest="
				+ batchTest + ", resultTest=" + resultTest + "]";
	}
	
}
