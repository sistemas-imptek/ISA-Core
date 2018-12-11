package com.isacore.quality.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity(name = "prodprov")
@IdClass(ProdProvPK.class)
@Table(name = "PROD_PROV")
public class ProdProv {

	@Id
	private Product product;
	
	@Id
	private Provider provider;
	
	@Column(name = "PP_STATUS", nullable = true, length = 32)
	private String status;
	
	@Column(name = "PP_Type", nullable = true, length = 32)
	private String typeProv;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTypeProv() {
		return typeProv;
	}

	public void setTypeProv(String typeProv) {
		this.typeProv = typeProv;
	}

	
}
