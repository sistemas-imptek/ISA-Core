package com.isacore.quality.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.isacore.localdate.converter.LocalDateTimeConverter;

@Entity(name = "property")
@Table(name = "PROPERTY")
public class Property {

	@Id
	@Column(name = "PROPERTY_ID", nullable = false, length = 16)
	private String idProperty;
	/*
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT_ID")
	private Product product;
	*/
	@Column(name = "PROPERTY_NAME", nullable = false, length = 1024)
	private String nameProperty;
	
	@Column(name = "PROPERTY_TYPE", nullable = false, length = 1)
	private String typeProperty;
	
	@Column(name = "PROPERTY_NORM", nullable = true, length = 2048)
	private String normProperty;
	
	@Column(name = "PROPERTY_MIN", nullable = true, columnDefinition = "decimal(5,2)" )
	private Double minProperty;
	
	@Column(name = "PROPERTY_MAX", nullable = true, columnDefinition = "decimal(5,2)" )
	private Double maxProperty;
	
	@Column(name = "PROPERTY_UNIT", nullable = true, length = 8)
	private String unitProperty;
	
	@Column(name = "PROPERTY_DESCRIPTION", nullable = true, columnDefinition = "varchar(max)")
	private String descProperty;
	
	@Column(name = "PROPERTY_VIEW", nullable = true, columnDefinition = "varchar(Max)")
	private String viewProperty;

	@Column(name = "PROPERTY_VIEW_HCC", nullable = true)
	private boolean viewPropertyOnHcc;
	
	@Column(name = "MEE_DATEMEETING", nullable = false)
	@Convert(converter = LocalDateTimeConverter.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy@HH:mm:ss", timezone="America/Bogota")
	private LocalDateTime dateUpdate;
	
	@Column(name = "PROPERTY_ASUSER", nullable = false, length = 64)
	private String asUser;
	
	public String getIdProperty() {
		return idProperty;
	}

	public void setIdProperty(String idProperty) {
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

	public Double getMinProperty() {
		return minProperty;
	}

	public void setMinProperty(Double minProperty) {
		this.minProperty = minProperty;
	}

	public Double getMaxProperty() {
		return maxProperty;
	}

	public void setMaxProperty(Double maxProperty) {
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

	public String getNormProperty() {
		return normProperty;
	}

	public void setNormProperty(String normProperty) {
		this.normProperty = normProperty;
	}

	public boolean isViewPropertyOnHcc() {
		return viewPropertyOnHcc;
	}

	public void setViewPropertyOnHcc(boolean viewPropertyOnHcc) {
		this.viewPropertyOnHcc = viewPropertyOnHcc;
	}

	public String getAsUser() {
		return asUser;
	}

	public void setAsUser(String asUser) {
		this.asUser = asUser;
	}

	public LocalDateTime getDateUpdate() {
		return dateUpdate;
	}

	public void setDateUpdate(LocalDateTime dateUpdate) {
		this.dateUpdate = dateUpdate;
	}
	
	
}
