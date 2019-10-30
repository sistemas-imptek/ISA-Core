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
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
	
	@Column(name = "PRODUCT_REVIEW", nullable = true, length = 8)
	private String review;
	
	@Column(name = "PRODUCT_REFERENCE", nullable = true, length = 128)
	private String reference;
	
	//PT MP Vial SemiElaborado
	@Column(name = "PRODUCT_TYPETXT", nullable = true, length = 1024)
	private String typeProductTxt;
	
	@Column(name = "PRODUCT_WAREHOUSE", nullable = true, length = 32)
	private String warehouse;
	
	@Column(name = "PRODUCT_STORE_QUANTITY", nullable = true, length = 512)
	private String storeQuantity;
	
	@Column(name = "PRODUCT_ORIGIN", nullable = true, length = 128)
	private String origin;
	
	@Column(name = "PRODUCT_SPECIFIC_USE", nullable = true, length = 2048)
	private String specificUse;
	
	@Column(name = "PRODUCT_PRESENTATION", nullable = true, length = 512)
	private String presentation;
	
	@Column(name = "PRODUCT_SAP_GROUP", nullable = true, length = 512)
	private String sapGroup;
	
	@Column(name = "PRODUCT__UNIT", nullable = true, length = 16)
	private String unit;
	
	@Column(name = "PRODUCT_REGISTER", nullable = true, length = 4)
	private String register;
	
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "FEA_ID", nullable = true)
	private Feature feature;
	
	@OneToMany(mappedBy = "product", cascade = {CascadeType.DETACH}, fetch = FetchType.LAZY)
	private List<Property> properties;
	
	@OneToMany(mappedBy = "product", cascade = {CascadeType.DETACH}, fetch = FetchType.LAZY)
	private List<NormProduct> norms;
	
	@ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	@JoinColumn(name = "FAM_ID", nullable = true)
	private Family family;
	
	@ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	@JoinColumn(name = "LP_ID", nullable = true)
	private  LineProduction lineProduction;

	@Transient
	private List<ProdProv> providersList;
	
	@Transient
	private List<Provider> providers;
	
	
	public Product() {
		super();
	}

	public Product(Integer idProduct, String sapCode, String nameProduct, String genericName, String descProduct,
			String itcdq, String typeProduct, Feature feature, List<Property> properties,
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

	public String getTypeProductTxt() {
		return typeProductTxt;
	}

	public void setTypeProductTxt(String typeProductTxt) {
		this.typeProductTxt = typeProductTxt;
	}

	public List<ProdProv> getProvidersList() {
		return providersList;
	}

	public void setProvidersList(List<ProdProv> providersList) {
		this.providersList = providersList;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}
	
	public String getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}

	public String getStoreQuantity() {
		return storeQuantity;
	}

	public void setStoreQuantity(String storeQuantity) {
		this.storeQuantity = storeQuantity;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getSpecificUse() {
		return specificUse;
	}

	public void setSpecificUse(String specificUse) {
		this.specificUse = specificUse;
	}

	public String getPresentation() {
		return presentation;
	}

	public void setPresentation(String presentation) {
		this.presentation = presentation;
	}

	public String getSapGroup() {
		return sapGroup;
	}

	public void setSapGroup(String sapGroup) {
		this.sapGroup = sapGroup;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getRegister() {
		return register;
	}

	public void setRegister(String register) {
		this.register = register;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}
	
	

	public List<Provider> getProviders() {
		return providers;
	}

	public void setProviders(List<Provider> providers) {
		this.providers = providers;
	}
	
	

	public List<NormProduct> getNorms() {
		return norms;
	}

	public void setNorm(List<NormProduct> norms) {
		this.norms = norms;
	}

	@JsonIgnore
	public String getFileName() {
		return this.nameProduct.replaceAll("[/:*?'<>]|\"", "-");
	}

	@Override
	public String toString() {
		return "Product [idProduct=" + idProduct + ", sapCode=" + sapCode + ", nameProduct=" + nameProduct
				+ ", genericName=" + genericName + ", descProduct=" + descProduct + ", itcdq=" + itcdq
				+ ", typeProduct=" + typeProduct + ", feature=" + feature + ", properties="
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
