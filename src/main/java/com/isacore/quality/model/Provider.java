package com.isacore.quality.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "provider")
@Table(name = "PROVIDER")
public class Provider {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PROV_ID")
	private Integer idProvider;
	
	@Column(name = "PROV_SAP_CODE", nullable = true, length = 64)
	private String sapProviderCode;
	
	@Column(name = "PROV_NAME", nullable = false, length = 2048)
	private String nameProvider;
	
	@Column(name = "PROV_DESCRIPTION", nullable = true, columnDefinition = "varchar(max)")
	private String descProvider;

	public Integer getIdProvider() {
		return idProvider;
	}

	public void setIdProvider(Integer idProvider) {
		this.idProvider = idProvider;
	}

	public String getSapProviderCode() {
		return sapProviderCode;
	}

	public void setSapProviderCode(String sapProviderCode) {
		this.sapProviderCode = sapProviderCode;
	}

	public String getNameProvider() {
		return nameProvider;
	}

	public void setNameProvider(String nameProvider) {
		this.nameProvider = nameProvider;
	}

	public String getDescProvider() {
		return descProvider;
	}

	public void setDescProvider(String descProvider) {
		this.descProvider = descProvider;
	}
	
}
