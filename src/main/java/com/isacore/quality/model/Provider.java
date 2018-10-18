package com.isacore.quality.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
	
	@OneToMany(mappedBy = "provider", cascade = {CascadeType.ALL},fetch = FetchType.LAZY, orphanRemoval = true)
	private List<ProdProv> prodProv;

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

	public List<ProdProv> getProdProv() {
		return prodProv;
	}

	public void setProdProv(List<ProdProv> prodProv) {
		this.prodProv = prodProv;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idProvider == null) ? 0 : idProvider.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Provider other = (Provider) obj;
		if (idProvider == null) {
			if (other.idProvider != null)
				return false;
		} else if (!idProvider.equals(other.idProvider))
			return false;
		return true;
	}
	
	
	
}
