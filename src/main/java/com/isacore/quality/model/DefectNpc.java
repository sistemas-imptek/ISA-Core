package com.isacore.quality.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "defectnpc")
@Table(name = "DEFECT")
public class DefectNpc {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DEFECT_ID")
	private Integer idDefect;
	
	@Column(name = "DEFECT_DESCRIPTION", nullable = false, length = 4096)
	private String desciption;
	
	@Column(name = "DEFECT_PERCENT", nullable = true, columnDefinition = "decimal(5,3)")
	private Double percent;

	public Integer getIdDefect() {
		return idDefect;
	}

	public void setIdDefect(Integer idDefect) {
		this.idDefect = idDefect;
	}

	public String getDesciption() {
		return desciption;
	}

	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}

	public Double getPercent() {
		return percent;
	}

	public void setPercent(Double percent) {
		this.percent = percent;
	}
	
	
	
}
