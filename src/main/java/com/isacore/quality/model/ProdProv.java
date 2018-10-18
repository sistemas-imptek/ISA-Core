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
	
	@Column(name = "PP_STATUS", nullable = false)
	private boolean status;

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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
	
}
