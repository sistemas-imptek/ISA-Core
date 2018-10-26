package com.isacore.quality.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.isacore.localdate.converter.LocalDateTimeConverter;

@Entity(name = "property")
@IdClass(PropertyPK.class)
@Table(name = "PROPERTY")
public class Property {

	/*
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT_ID")
	private Product product;
	*/
	
	@Id
	private Product product;
	
	@Id
	private PropertyList propertyList;

	
	@Column(name = "PROPERTY_MIN", nullable = true, columnDefinition = "decimal(5,2)" )
	private Double minProperty;
	
	@Column(name = "PROPERTY_MAX", nullable = true, columnDefinition = "decimal(5,2)" )
	private Double maxProperty;
	
	@Column(name = "PROPERTY_UNIT", nullable = true, length = 8)
	private String unitProperty;
	
	@Column(name = "PROPERTY_VIEW", nullable = true, columnDefinition = "varchar(Max)")
	private String viewProperty;

	@Column(name = "PROPERTY_VIEW_HCC", nullable = true)
	private boolean viewPropertyOnHcc;
	
	@Column(name = "PROPERTY_UPDATE", nullable = false)
	@Convert(converter = LocalDateTimeConverter.class)
	@JsonSerialize(using = ToStringSerializer.class)
	private LocalDateTime dateUpdate;
	
	@Column(name = "PROPERTY_TYPE", nullable = true, length = 4)
	private String typeProperty;
	
	@Column(name = "PROPERTY_ASUSER", nullable = true, length = 64)
	private String asUser;
	
/*
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
*/
	
	public void setPropertyListId(String idPL) {
		this.propertyList = new PropertyList();
		this.propertyList.setIdProperty(idPL);
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public PropertyList getPropertyList() {
		return propertyList;
	}

	public void setPropertyList(PropertyList propertyList) {
		this.propertyList = propertyList;
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

	public String getViewProperty() {
		return viewProperty;
	}

	public void setViewProperty(String viewProperty) {
		this.viewProperty = viewProperty;
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

	public String getTypeProperty() {
		return typeProperty;
	}

	public void setTypeProperty(String typeProperty) {
		this.typeProperty = typeProperty;
	}
	
	
}
