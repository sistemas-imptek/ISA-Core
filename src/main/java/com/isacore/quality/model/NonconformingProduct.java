package com.isacore.quality.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.isacore.localdate.converter.LocalDateConverter;

@Entity(name = "nonconforming")
@Table(name = "NONCONFORMING_PRODUCT")
public class NonconformingProduct {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "NCP_ID")
	private Integer idNCP;
	
	@Column(name = "NCP_DATE_UPDATE", nullable = false)
	@Convert(converter = LocalDateConverter.class)
	@JsonSerialize(using = ToStringSerializer.class)
	private LocalDateTime dateUpdate;
	
	@Column(name = "NCP_DETECTION_DATE", nullable = false)
	@Convert(converter = LocalDateConverter.class)
	@JsonSerialize(using = ToStringSerializer.class)
	private LocalDate dateDetection;
	
	@Column(name = "NCP_PRODUCTION_DATE", nullable = false)
	@Convert(converter = LocalDateConverter.class)
	@JsonSerialize(using = ToStringSerializer.class)
	private LocalDate dateProduction;
	
	@Column(name = "NCP_YEAR", nullable = false)
	private Integer year;
	
	@Column(name = "NCP_MONTH", nullable = false)
	private Integer month;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID", insertable = false, updatable = false)
	private Product product;
	
	@Column(name = "NCP_BATCH", nullable = false, length = 64)
	private String batch;
	
	@Transient
	private Double daysAntiquities;
	
	@Column(name = "NCP_PRODUCTION_ORDER", nullable = true, length = 64)
	private String orderProduction;
	
	@Column(name = "NCP_HCC_FREE_USE", nullable = true, length = 64)
	private String hccFreeUse;
	
	@Column(name = "NCP_SOURCE", nullable = true, length = 256)
	private String source;
	
	@Column(name = "NCP_AMOUNT_PRODUCED", nullable = false, columnDefinition = "decimal(10,3)")
	private Double amountProduced;
	
	@Column(name = "NCP_NONCONFORMING_AMOUNT", nullable = false, columnDefinition = "decimal(10,3)")
	private Double amountNonConforming;
	
	@Column(name = "NCP_UNIT", nullable = false, length = 16)
	private String unitNCP;
	
	@Column(name = "NCP_MATERIAL_EXIT", nullable = true, columnDefinition = "decimal(10,3)")
	private Double exitMaterial;
	
	@Column(name = "NCP_MATERIAL_BALANCE", nullable = true, columnDefinition = "decimal(10,3)")
	private Double balanceMaterial;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "DEFECT_ID", referencedColumnName = "DEFECT_ID", insertable = true, updatable = false)
	private DefectNpc defect;
	
	@Column(name = "NCP_EXISTS_INVENTORY", nullable = true)
	private Boolean existsInventory;
	
	@Column(name = "NCP_PERCENT_VALIDITY", nullable = true, columnDefinition = "decimal(5,3)")
	private Double validityPercent;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "AREA_ID", referencedColumnName = "AREA_ID", insertable = true, updatable = false)
	private Area area;
	
	@Column(name = "NCP_FIVEM", nullable = false, length = 2048)
	private String fiveM;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "OM_ID", referencedColumnName = "OM_ID", insertable = false, updatable = false)
	private OutputMethod outputMethod;
	
	@Column(name = "NCP_UNIT_COST", nullable = true, columnDefinition = "decimal(5,3)")
	private Double unitCost;
	
	@Column(name = "NCP_TOTAL_COST", nullable = true, columnDefinition = "decimal(10,3)")
	private Double totalCost;
	
	@Column(name = "NCP_PERCENT", nullable = true, columnDefinition = "decimal(5,3)")
	private Double percentPNC;
	
	@Column(name = "NCP_GOAL", nullable = true, columnDefinition = "decimal(5,3)")
	private Double goal;
	
	@Column(name = "NCP_WEIGHT_KG", nullable = true, columnDefinition = "decimal(5,3)")
	private Double weightKg;
	
	@Column(name = "NCP_NOCONFORM_KG", nullable = true, columnDefinition = "decimal(10,3)")
	private Double noconformKg;
	
	@Column(name = "NCP_TOTAL_PRODUCTION_KG", nullable = true, columnDefinition = "decimal(10,3)")
	private Double totalProductionKg;
	
	@Column(name = "NCP_PERCENT_MONTHLY", nullable = true, columnDefinition = "decimal(7,4)")
	private Double percentMonthly;
	
	@Column(name = "NCP_TOTAL_SALES", nullable = true, columnDefinition = "decimal(10,3)")
	private Double totalSales;
	
	@Column(name = "NCP_GOAL_MONTHLY", nullable = true, columnDefinition = "decimal(5,3)")
	private Double goalMonthly;
	
	@Column(name = "NCP_PERCENT_NO_QUALITY", nullable = true, columnDefinition = "decimal(7,4)")
	private Double percentNoQuality;
	
	@Column(name = "NCP_ADDITIONAL_REMARKS", nullable = true)
	private String aditionalRemarks;
	
	@Column(name = "NCP_ASUSER", nullable = true)
	private String asUser;

	public Integer getIdNCP() {
		return idNCP;
	}

	public void setIdNCP(Integer idNCP) {
		this.idNCP = idNCP;
	}

	public LocalDateTime getDateUpdate() {
		return dateUpdate;
	}

	public void setDateUpdate(LocalDateTime dateUpdate) {
		this.dateUpdate = dateUpdate;
	}

	public LocalDate getDateDetection() {
		return dateDetection;
	}

	public void setDateDetection(LocalDate dateDetection) {
		this.dateDetection = dateDetection;
	}

	public LocalDate getDateProduction() {
		return dateProduction;
	}

	public void setDateProduction(LocalDate dateProduction) {
		this.dateProduction = dateProduction;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public String getOrderProduction() {
		return orderProduction;
	}

	public void setOrderProduction(String orderProduction) {
		this.orderProduction = orderProduction;
	}

	public String getHccFreeUse() {
		return hccFreeUse;
	}

	public void setHccFreeUse(String hccFreeUse) {
		this.hccFreeUse = hccFreeUse;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Double getAmountProduced() {
		return amountProduced;
	}

	public void setAmountProduced(Double amountProduced) {
		this.amountProduced = amountProduced;
	}

	public Double getAmountNonConforming() {
		return amountNonConforming;
	}

	public void setAmountNonConforming(Double amountNonConforming) {
		this.amountNonConforming = amountNonConforming;
	}

	public String getUnitNCP() {
		return unitNCP;
	}

	public void setUnitNCP(String unitNCP) {
		this.unitNCP = unitNCP;
	}

	public Double getExitMaterial() {
		return exitMaterial;
	}

	public void setExitMaterial(Double exitMaterial) {
		this.exitMaterial = exitMaterial;
	}

	public Double getBalanceMaterial() {
		return balanceMaterial;
	}

	public void setBalanceMaterial(Double balanceMaterial) {
		this.balanceMaterial = balanceMaterial;
	}

	public Boolean getExistsInventory() {
		return existsInventory;
	}

	public void setExistsInventory(Boolean existsInventory) {
		this.existsInventory = existsInventory;
	}

	public Double getValidityPercent() {
		return validityPercent;
	}

	public void setValidityPercent(Double validityPercent) {
		this.validityPercent = validityPercent;
	}

	public Double getUnitCost() {
		return unitCost;
	}

	public void setUnitCost(Double unitCost) {
		this.unitCost = unitCost;
	}

	public Double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}

	public Double getPercentPNC() {
		return percentPNC;
	}

	public void setPercentPNC(Double percentPNC) {
		this.percentPNC = percentPNC;
	}

	public Double getGoal() {
		return goal;
	}

	public void setGoal(Double goal) {
		this.goal = goal;
	}

	public Double getWeightKg() {
		return weightKg;
	}

	public void setWeightKg(Double weightKg) {
		this.weightKg = weightKg;
	}

	public Double getNoconformKg() {
		return noconformKg;
	}

	public void setNoconformKg(Double noconformKg) {
		this.noconformKg = noconformKg;
	}

	public Double getTotalProductionKg() {
		return totalProductionKg;
	}

	public void setTotalProductionKg(Double totalProductionKg) {
		this.totalProductionKg = totalProductionKg;
	}

	public Double getPercentMonthly() {
		return percentMonthly;
	}

	public void setPercentMonthly(Double percentMonthly) {
		this.percentMonthly = percentMonthly;
	}

	public Double getTotalSales() {
		return totalSales;
	}

	public void setTotalSales(Double totalSales) {
		this.totalSales = totalSales;
	}

	public Double getGoalMonthly() {
		return goalMonthly;
	}

	public void setGoalMonthly(Double goalMonthly) {
		this.goalMonthly = goalMonthly;
	}

	public Double getPercentNoQuality() {
		return percentNoQuality;
	}

	public void setPercentNoQuality(Double percentNoQuality) {
		this.percentNoQuality = percentNoQuality;
	}

	public String getAditionalRemarks() {
		return aditionalRemarks;
	}

	public void setAditionalRemarks(String aditionalRemarks) {
		this.aditionalRemarks = aditionalRemarks;
	}

	public String getAsUser() {
		return asUser;
	}

	public void setAsUser(String asUser) {
		this.asUser = asUser;
	}

	public Double getDaysAntiquities() {
		return daysAntiquities;
	}

	public void setDaysAntiquities(Double daysAntiquities) {
		this.daysAntiquities = daysAntiquities;
	}

	public DefectNpc getDefect() {
		return defect;
	}

	public void setDefect(DefectNpc defect) {
		this.defect = defect;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public String getFiveM() {
		return fiveM;
	}

	public void setFiveM(String fiveM) {
		this.fiveM = fiveM;
	}

	public OutputMethod getOutputMethod() {
		return outputMethod;
	}

	public void setOutputMethod(OutputMethod outputMethod) {
		this.outputMethod = outputMethod;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
}
