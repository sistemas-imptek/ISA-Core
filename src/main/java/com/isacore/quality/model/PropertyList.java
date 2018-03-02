package com.isacore.quality.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity(name = "propertylist")
@Table(name = "PROPERTYLIST")
public class PropertyList {

	@Id
	@Column(name = "PROPL_ID", nullable = false, length = 16)
	private String idProperty;
	
	@Column(name = "PROPL_NAME" , nullable = false, length = 1024)
	private String nameProperty;
	
	//Propiedad para saber si es Técnica(T) o Visual (V)
	@Column(name = "PROPL_TYPE", nullable = false, length = 4)
	private String typeProperty;
	
	//Propiedad para saber si es de Producto(PR) o Ensayo (TE)
	@Column(name = "PROPL_TYPE2", nullable = false, length = 4)
	private String typeProperty2;
	
	@Transient
	@JsonInclude(Include.NON_NULL)
	private String normText;
	
	@JsonInclude(Include.NON_NULL)
	@ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
	@JoinTable(name = "PROPERTY_NORM",
	joinColumns = {@JoinColumn(name = "PROPL_ID")},
	inverseJoinColumns = {@JoinColumn(name = "NORM_ID")})
	private List<Norm> norms;
	
	public PropertyList() {	}
	
	
	public PropertyList(String idProperty, String nameProperty, String typeProperty, String typeProperty2) {
		super();
		this.idProperty = idProperty;
		this.nameProperty = nameProperty;
		this.typeProperty = typeProperty;
		this.typeProperty2 = typeProperty2;
	}

	public PropertyList(String idProperty, String nameProperty, String typeProperty, String typeProperty2,
			List<Norm> norms, String normText) {
		super();
		this.idProperty = idProperty;
		this.nameProperty = nameProperty;
		this.typeProperty = typeProperty;
		this.typeProperty2 = typeProperty2;
		this.norms = norms;
		this.normText = normText;
	}

	public String getIdProperty() {
		return idProperty;
	}

	public void setIdProperty(String idProperty) {
		this.idProperty = idProperty;
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

	public String getTypeProperty2() {
		return typeProperty2;
	}

	public void setTypeProperty2(String typeProperty2) {
		this.typeProperty2 = typeProperty2;
	}

	public List<Norm> getNorms() {
		return norms;
	}

	public void setNorms(List<Norm> norms) {
		this.norms = norms;
	}
	
	public void listNorms() {
		
		StringBuilder sb = new StringBuilder();
		this.norms.forEach(x -> sb.append(x).append("/"));
		
		this.normText = sb.toString();
	}
	
	public String getNormText() {
		return normText;
	}

	public void setNormText(String normText) {
		this.normText = normText;
	}

	@Override
	public String toString() {
		return "PropertyList [idProperty=" + idProperty + ", nameProperty=" + nameProperty + ", typeProperty="
				+ typeProperty + ", typeProperty2=" + typeProperty2 + ", normText=" + normText + "]";
	}
	
}
