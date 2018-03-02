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

import org.hibernate.annotations.NaturalId;

@Entity(name = "product")
@Table(name = "PRODUCT")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRODUCT_ID")
	private Integer idProduct;
	
	@NaturalId
	@Column(name = "PRODUCT_SAP_CODE", nullable = true, length = 32)
	private String sapCode;
	
	@Column(name = "PRODUCT_NAME", nullable = false, length = 512)
	private String nameProduct;
	
	@Column(name = "PRODUCT_DESCRIPTION", nullable = true, length = 512)
	private String descProduct;
	
	@Column(name = "PRODUCT_ITCDQ", nullable = true, length = 64)
	private String itcdq;
	
	@Column(name = "PRODUCT_FAMILY", nullable = true, length = 1024)
	private String familyProduct;
	
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

	public String getFamilyProduct() {
		return familyProduct;
	}

	public void setFamilyProduct(String familyProduct) {
		this.familyProduct = familyProduct;
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

	public String getItcdq() {
		return itcdq;
	}

	public void setItcdq(String itcdq) {
		this.itcdq = itcdq;
	}

	@Override
	public String toString() {
		return "Product [idProduct=" + idProduct + ", sapCode=" + sapCode + ", nameProduct=" + nameProduct
				+ ", descProduct=" + descProduct + ", propertyList=" + propertyList + "]";
	}	
	
}
