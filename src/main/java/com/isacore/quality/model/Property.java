package com.isacore.quality.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "property")
@Table(name = "PROPERTY")
public class Property {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PROPERTY_ID")
	private Integer idProperty;
	/*
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT_ID")
	private Product product;
	*/
	@ManyToOne( fetch = FetchType.EAGER)
	@JoinColumn(name = "NORM_ID", referencedColumnName = "NORM_ID", nullable = false)
	private Norm norm;
	
	@Column(name = "PROPERTY_NAME", nullable = false, length = 1024)
	private String nameProperty;
	
	@Column(name = "PROPERTY_TYPE", nullable = false, length = 1)
	private String typeProperty;
	
	@Column(name = "PROPERTY_MIN", nullable = true, columnDefinition = "decimal(5,2)" )
	private double minProperty;
	
	@Column(name = "PROPERTY_MAX", nullable = true, columnDefinition = "decimal(5,2)" )
	private double maxProperty;
	
	@Column(name = "PROPERTY_UNIT", nullable = true, length = 8)
	private String unitProperty;
	
	@Column(name = "PROPERTY_DESCRIPTION", nullable = true, columnDefinition = "varchar(max)")
	private String descProperty;
	
	@Column(name = "PROPERTY_VIEW", nullable = true, length = 32)
	private String viewProperty;

	
	
	public Integer getIdProperty() {
		return idProperty;
	}

	public void setIdProperty(Integer idProperty) {
		this.idProperty = idProperty;
	}
/*
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
*/
	public Norm getNorm() {
		return norm;
	}

	public void setNorm(Norm norm) {
		this.norm = norm;
	}

	public String getNameProperty() {
		return nameProperty;
	}

	public void setNameProperty(String nameProperty) {
		this.nameProperty = nameProperty;
	}

	public String getTypeProperty() {
		return typeProperty;
	}

	public void setTypeProperty(String typeProperty) {
		this.typeProperty = typeProperty;
	}

	public double getMinProperty() {
		return minProperty;
	}

	public void setMinProperty(double minProperty) {
		this.minProperty = minProperty;
	}

	public double getMaxProperty() {
		return maxProperty;
	}

	public void setMaxProperty(double maxProperty) {
		this.maxProperty = maxProperty;
	}

	public String getUnitProperty() {
		return unitProperty;
	}

	public void setUnitProperty(String unitProperty) {
		this.unitProperty = unitProperty;
	}

	public String getDescProperty() {
		return descProperty;
	}

	public void setDescProperty(String descProperty) {
		this.descProperty = descProperty;
	}

	public String getViewProperty() {
		return viewProperty;
	}

	public void setViewProperty(String viewProperty) {
		this.viewProperty = viewProperty;
	}
	
}
