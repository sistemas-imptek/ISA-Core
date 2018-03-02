package com.isacore.quality.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "norm")
@Table(name = "NORM")
public class Norm {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "NORM_ID")
	private Integer idNorm;
	
	@Column(name = "NORM_NAME", nullable = false, length = 128)
	private String nameNorm;
	
	@Column(name = "NORM_APLICATION", nullable = true, length = 64)
	private String aplication;
	
	@Column(name = "NORM_DESCRIPTION", nullable = true, length = 512)
	private String description;
	
	@Column(name = "NORM_KIND", nullable = false, length = 2)
	private String kind;
	
	@JsonIgnore
	@ManyToMany(cascade = {CascadeType.ALL}, mappedBy = "norms")
	private List<PropertyList> propertyList;
	
	@Transient
	private String kindDesc;

	public Norm() {
		super();
	}

	public Norm(Integer idNorm, String nameNorm, String aplication, String description, String kind) {
		super();
		this.idNorm = idNorm;
		this.nameNorm = nameNorm;
		this.aplication = aplication;
		this.description = description;
		this.kind = kind;
	}

	public Integer getIdNorm() {
		return idNorm;
	}

	public void setIdNorm(Integer idNorm) {
		this.idNorm = idNorm;
	}

	public String getNameNorm() {
		return nameNorm;
	}

	public void setNameNorm(String nameNorm) {
		this.nameNorm = nameNorm;
	}

	public String getAplication() {
		return aplication;
	}

	public void setAplication(String aplication) {
		this.aplication = aplication;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getKindDesc() {
		
		switch (this.kind) {
		case "ES":
			return "Ensayo";
			
		case "PR":
			return "Producto";

		default:
			return "SN";
		}
	}

	public void setKindDesc(String kindDesc) {
		this.kindDesc = kindDesc;
	}

	public List<PropertyList> getPropertyList() {
		return propertyList;
	}

	public void setPropertyList(List<PropertyList> propertyList) {
		this.propertyList = propertyList;
	}

	@Override
	public String toString() {
		return "Norm [idNorm=" + idNorm + ", nameNorm=" + nameNorm + ", aplication=" + aplication + ", description="
				+ description + ", kind=" + kind + ", propertyList=" + propertyList + ", kindDesc=" + kindDesc + "]";
	}

	
}
