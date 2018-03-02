package com.isacore.quality.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "reportheadt")
@Table(name = "REPORTHEADT")
public class ReportHeadT {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "REPORT_ID")
	private Integer idReport;
	
	@Column(name = "REPORT_TYPE", nullable = true, length = 3072)
	private String type;
	
	@Column(name = "REPORT_TITLE", nullable = true, length = 3072)
	private String tittle;
	
	@Column(name = "REPORT_SUBTITLE", nullable = true, length = 3072)
	private String subTitle;
	
	@Column(name = "REPORT_NORM", nullable = true, length = 3072)
	private String norm; 
	
	@Column(name = "REPORT_CODE", nullable = true, length = 3072)
	private String code;
	
	@Column(name = "REPORT_REVIEW", nullable = true, length = 3072)
	private String review;
	
	@Column(name = "REPORT_OF", nullable = true, length = 3072)
	private String of;

	public Integer getIdReport() {
		return idReport;
	}

	public void setIdReport(Integer idReport) {
		this.idReport = idReport;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTittle() {
		return tittle;
	}

	public void setTittle(String tittle) {
		this.tittle = tittle;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getNorm() {
		return norm;
	}

	public void setNorm(String norm) {
		this.norm = norm;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public String getOf() {
		return of;
	}

	public void setOf(String of) {
		this.of = of;
	}
	
}
