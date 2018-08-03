package com.isacore.quality.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "feature")
@Table(name = "FEATURE")
public class Feature {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FEA_ID")
	private Integer idFeature;
	
	@Column(name = "FEA_LENGTH", nullable = true, columnDefinition = "decimal(7,3)")
	private Double length;
	
	@Column(name = "FEA_LENGTH_UNIT", nullable = true, length = 16)
	private String unitLength;
	
	@Column(name = "FEA_GROSS_WEIGHT", nullable = true, columnDefinition = "decimal(7,3)")
	private Double grossWeigth;
	
	@Column(name = "FEA_GROSS_WEIGHT_UNIT", nullable = true, length = 16)
	private String unitGrossWeigth;
	
	@Column(name = "FEA_NET_WEIGHT", nullable = true, columnDefinition = "decimal(7,3)")
	private Double netWeigth;
	
	@Column(name = "FEA_NET_WEIGHT_UNIT", nullable = true, length = 16)
	private String unitNetWeigth;
	
	@Column(name = "FEA_WEIGHT_AREA", nullable = true, columnDefinition = "decimal(7,3)")
	private Double weigthArea; //siempre en kg/m2
	
	@Column(name = "FEA_UMB", nullable = true, length = 16)
	private String umb;
	
	@Column(name = "FEA_UNIT_COST", nullable = true, columnDefinition = "decimal(7,3)")
	private Double unitCost;
	
	@Column(name = "FEA_DISTRIBUTOR_PRICE", nullable = true, columnDefinition = "decimal(7,3)")
	private Double distributorPrice;
	
	

	public Feature() {
		super();
	}

	public Feature(Integer idFeature, Double length, String unitLength, Double grossWeigth, String unitGrossWeigth,
			Double netWeigth, String unitNetWeigth, Double weigthArea, String umb, Double unitCost,
			Double distributorPrice) {
		super();
		this.idFeature = idFeature;
		this.length = length;
		this.unitLength = unitLength;
		this.grossWeigth = grossWeigth;
		this.unitGrossWeigth = unitGrossWeigth;
		this.netWeigth = netWeigth;
		this.unitNetWeigth = unitNetWeigth;
		this.weigthArea = weigthArea;
		this.umb = umb;
		this.unitCost = unitCost;
		this.distributorPrice = distributorPrice;
	}

	public Integer getIdFeature() {
		return idFeature;
	}

	public void setIdFeature(Integer idFeature) {
		this.idFeature = idFeature;
	}

	public Double getLength() {
		return length;
	}

	public void setLength(Double length) {
		this.length = length;
	}

	public String getUnitLength() {
		return unitLength;
	}

	public void setUnitLength(String unitLength) {
		this.unitLength = unitLength;
	}

	public Double getGrossWeigth() {
		return grossWeigth;
	}

	public void setGrossWeigth(Double grossWeigth) {
		this.grossWeigth = grossWeigth;
	}

	public String getUnitGrossWeigth() {
		return unitGrossWeigth;
	}

	public void setUnitGrossWeigth(String unitGrossWeigth) {
		this.unitGrossWeigth = unitGrossWeigth;
	}

	public Double getNetWeigth() {
		return netWeigth;
	}

	public void setNetWeigth(Double netWeigth) {
		this.netWeigth = netWeigth;
	}

	public String getUnitNetWeigth() {
		return unitNetWeigth;
	}

	public void setUnitNetWeigth(String unitNetWeigth) {
		this.unitNetWeigth = unitNetWeigth;
	}

	public Double getWeigthArea() {
		return weigthArea;
	}

	public void setWeigthArea(Double weigthArea) {
		this.weigthArea = weigthArea;
	}

	public String getUmb() {
		return umb;
	}

	public void setUmb(String umb) {
		this.umb = umb;
	}

	public Double getUnitCost() {
		return unitCost;
	}

	public void setUnitCost(Double unitCost) {
		this.unitCost = unitCost;
	}

	public Double getDistributorPrice() {
		return distributorPrice;
	}

	public void setDistributorPrice(Double distributorPrice) {
		this.distributorPrice = distributorPrice;
	}
	
}
