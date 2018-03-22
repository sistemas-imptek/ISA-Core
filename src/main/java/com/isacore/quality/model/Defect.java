package com.isacore.quality.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "defect")
@Table(name = "DEFECT")
public class Defect {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DEFECT_ID")
	private Integer idDefect;
	
	@Column(name = "DEFECT_DESCRIPTION", nullable = false, length = 4096)
	private String description;
	
	@Column(name = "DEFECT_PERCENT", nullable = true, columnDefinition = "decimal(5,2)")
	private Double percent;

	public Integer getIdDefect() {
		return idDefect;
	}

	public void setIdDefect(Integer idDefect) {
		this.idDefect = idDefect;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPercent() {
		return percent;
	}

	public void setPercent(Double percent) {
		this.percent = percent;
	}
	
}
