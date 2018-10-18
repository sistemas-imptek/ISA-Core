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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@Entity(name = "product")
@Table(name = "PRODUCT")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRODUCT_ID")
	private Integer idProduct;
	
	@Column(name = "PRODUCT_SAP_CODE", nullable = true, length = 32)
	private String sapCode;
	
	@Column(name = "PRODUCT_NAME", nullable = false, length = 1024)
	private String nameProduct;
	
	@Column(name = "PRODUCT_GENERIC_NAME", nullable = true, length = 1024)
	private String genericName;
	
	@Column(name = "PRODUCT_DESCRIPTION", nullable = true, length = 512)
	private String descProduct;
	
	@Column(name = "PRODUCT_ITCDQ", nullable = true, length = 64)
	private String itcdq;
	
	@Column(name = "PRODUCT_TYPE", nullable = false, length = 64)
	private String typeProduct;
	
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "FEA_ID", nullable = true)
	private Feature feature;
	
	@OneToMany(mappedBy = "product", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<ProdProv> prodProv;
	
	@OneToMany(mappedBy = "product", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Property> properties;
	
	@ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	@JoinColumn(name = "FAM_ID", nullable = true)
	private Family family;
	
	@ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	@JoinColumn(name = "LP_ID", nullable = true)
	private  LineProduction lineProduction;

	
	public Product() {
		super();
	}

	public Product(Integer idProduct, String sapCode, String nameProduct, String genericName, String descProduct,
			String itcdq, String typeProduct, Feature feature, List<ProdProv> prodProv, List<Property> properties,
			Family family, LineProduction lineProduction) {
		super();
		this.idProduct = idProduct;
		this.sapCode = sapCode;
		this.nameProduct = nameProduct;
		this.genericName = genericName;
		this.descProduct = descProduct;
		this.itcdq = itcdq;
		this.typeProduct = typeProduct;
		this.feature = feature;
		this.prodProv = prodProv;
		this.properties = properties;
		this.family = family;
		this.lineProduction = lineProduction;
	}

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

	public String getItcdq() {
		return itcdq;
	}

	public void setItcdq(String itcdq) {
		this.itcdq = itcdq;
	}

	public Feature getFeature() {
		return feature;
	}

	public void setFeature(Feature feature) {
		this.feature = feature;
	}

	public List<ProdProv> getProdProv() {
		return prodProv;
	}

	public void setProdProv(List<ProdProv> prodProv) {
		this.prodProv = prodProv;
	}

	public Family getFamily() {
		return family;
	}

	public void setFamily(Family family) {
		this.family = family;
	}

	public LineProduction getLineProduction() {
		return lineProduction;
	}

	public void setLineProduction(LineProduction lineProduction) {
		this.lineProduction = lineProduction;
	}	

	public String getGenericName() {
		return genericName;
	}

	public void setGenericName(String genericName) {
		this.genericName = genericName;
	}

	public List<Property> getProperties() {
		return properties;
	}

	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}

	@Override
	public String toString() {
		return "Product [idProduct=" + idProduct + ", sapCode=" + sapCode + ", nameProduct=" + nameProduct
				+ ", genericName=" + genericName + ", descProduct=" + descProduct + ", itcdq=" + itcdq
				+ ", typeProduct=" + typeProduct + ", feature=" + feature + ", prodProv=" + prodProv + ", properties="
				+ properties + ", family=" + family + ", lineProduction=" + lineProduction + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idProduct == null) ? 0 : idProduct.hashCode());
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
		Product other = (Product) obj;
		if (idProduct == null) {
			if (other.idProduct != null)
				return false;
		} else if (!idProduct.equals(other.idProduct))
			return false;
		return true;
	}	
	
	
	
}
