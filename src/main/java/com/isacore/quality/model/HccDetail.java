package com.isacore.quality.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "hccdetail")
@Table(name = "HCCDETAIL")
public class HccDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "HCCD_ID")
	private Integer idHccd;
	
	//@Column(name = "HCCH_SAPCODE", nullable = false, length = 32)
	//private String sapCode;
	
	@Column(name = "HCCD_NORM_NAME", nullable = false, length = 128)
	private String nameNorm;
	
	@Column(name = "HCCD_PROP_ID", nullable = false, length = 16)
	private String idProperty;
	
	@Column(name = "HCCD_PROP_NAME", nullable = false, length = 1024)
	private String nameProperty;
	
	@Column(name = "HCCD_PROP_TYPE", nullable = false, length = 4)
	private String typeProperty;
	
	@Column(name = "HCCD_PROP_UNIT", nullable = true, length = 8)
	private String unit;
	
	@Column(name = "HCCD_PROP_MIN", nullable = true, columnDefinition = "decimal(8,2)")
	private Double min;
	
	@Column(name = "HCCD_PROP_MAX", nullable = true, columnDefinition = "decimal(8,2)")
	private Double max;
	
	@Column(name = "HCCD_PROP_VIEW", nullable = true, length = 32)
	private String view;
	
	@Column(name = "HCCD_PROP_VIEW_ON_HCC", nullable = true)
	private boolean viewOnHcc;
	
	@Column(name = "HCCD_TEST_RESULT", nullable = true, columnDefinition = "decimal(8,2)")
	private Double result;
	
	@Column(name = "HCCD_TEST_RESULT_VIEW", nullable = true, length = 32)
	private String resultText;
	
	@Column(name = "HCCD_PASS_TEST", nullable = true)
	private boolean passTest;

	public Integer getIdHccd() {
		return idHccd;
	}

	public void setIdHccd(Integer idHccd) {
		this.idHccd = idHccd;
	}

	public String getNameNorm() {
		return nameNorm;
	}

	public void setNameNorm(String nameNorm) {
		this.nameNorm = nameNorm;
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

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Double getMin() {
		return min;
	}

	public void setMin(Double min) {
		this.min = min;
	}

	public Double getMax() {
		return max;
	}

	public void setMax(Double max) {
		this.max = max;
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

	public Double getResult() {
		return result;
	}

	public void setResult(Double result) {
		this.result = result;
	}

	public String getResultText() {
		return resultText;
	}

	public void setResultText(String resultText) {
		this.resultText = resultText;
	}

	public boolean isViewOnHcc() {
		return viewOnHcc;
	}

	public void setViewOnHcc(boolean viewOnHcc) {
		this.viewOnHcc = viewOnHcc;
	}

	public boolean isPassTest() {
		return passTest;
	}

	public void setPassTest(boolean passTest) {
		this.passTest = passTest;
	}
	
}
