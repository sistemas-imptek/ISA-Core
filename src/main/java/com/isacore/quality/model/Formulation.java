package com.isacore.quality.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@IdClass(FormulationPK.class)
@Table(name = "formulation")
@JsonInclude(Include.NON_NULL)
public class Formulation {

	@Id
	private Product product;
	
	@Id
	private FormulaList formulationList;
	
	@Id
	private FormulationItem formularionItem;
	
	@Column(name = "FORM_DESCRIPTION", nullable = false, length = 256)
	private String description;
	
	@Column(name = "FORM_VALUE", nullable = false, columnDefinition = ("Decimal(8,6)"))
	private Double value;
	
	@Column(name = "FORM_TYPE", nullable = false, length = 4)
	private String type;
	
	@Column(name = "FORM_VEA", nullable = true, columnDefinition = ("Decimal(10,6)"))
	private Double vea;
	
	@Column(name = "FORM_UNIT", nullable = false, length = 8)
	private String unit;
	
	@Column(name = "FORM_MULTIFACTOR", nullable = false)
	private Integer multiFactor;
	
	@Transient
	private Double amount;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public FormulaList getFormulationList() {
		return formulationList;
	}

	public void setFormulationList(FormulaList formulationList) {
		this.formulationList = formulationList;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getVea() {
		return vea;
	}

	public void setVea(Double vea) {
		this.vea = vea;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Integer getMultiFactor() {
		return multiFactor;
	}

	public void setMultiFactor(Integer multiFactor) {
		this.multiFactor = multiFactor;
	}

	public FormulationItem getFormularionItem() {
		return formularionItem;
	}

	public void setFormularionItem(FormulationItem formularionItem) {
		this.formularionItem = formularionItem;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(int load) {
		this.amount = this.multiFactor * (double)load * this.value;
	}

	
	
}
