package com.isacore.quality.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "product")
@Table(name = "PRODUCT")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRODUCT_ID")
	private Integer idProduct;
	
	@Column(name = "PRODUCT_SAP_CODE", nullable = false, length = 32)
	private String sapCode;
	
	@Column(name = "PRODUCT_NAME", nullable = false, length = 512)
	private String nameProduct;
	
	@Column(name = "PRODUCT_DESCRIPTION", nullable = true, length = 512)
	private String descProduct;
	
	@Column(name = "PRODUCT_TYPE", nullable = false, length = 64)
	private String typeProduct;
	
	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "PRODUCT_ID")
	private List<Property> propertyList;

	
	public Integer getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(Integer idProduct) {
		this.idProduct = idProduct;
	}

	public String getSapCode() {
		return sapCode;
	}

	public void setSapCode(String sapCode) {
		this.sapCode = sapCode;
	}

	public String getNameProduct() {
		return nameProduct;
	}

	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}

	public String getDescProduct() {
		return descProduct;
	}

	public void setDescProduct(String descProduct) {
		this.descProduct = descProduct;
	}

	public String getTypeProduct() {
		return typeProduct;
	}

	public void setTypeProduct(String typeProduct) {
		this.typeProduct = typeProduct;
	}

	public List<Property> getPropertyList() {
		return propertyList;
	}

	public void setPropertyList(List<Property> propertyList) {
		this.propertyList = propertyList;
	}

	@Override
	public String toString() {
		return "Product [idProduct=" + idProduct + ", sapCode=" + sapCode + ", nameProduct=" + nameProduct
				+ ", descProduct=" + descProduct + ", propertyList=" + propertyList + "]";
	}
	
	
	
}
