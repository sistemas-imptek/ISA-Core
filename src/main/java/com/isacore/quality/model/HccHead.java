package com.isacore.quality.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.isacore.localdate.converter.LocalDateConverter;

@Entity(name = "hcchead")
@Table(name = "HCCHEAD")
public class HccHead {

	@Id
	@Column(name = "HCCH_SAPCODE", nullable = false, length = 64)	
	private String sapCode;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID", insertable = false, updatable = false)
	private Product product;
	
	@Column(name = "HCCH_NORM", nullable = false, length = 64)
	private String hccNorm;
	
	@Column(name = "HCCH_DATE", nullable = false)
	@Convert(converter = LocalDateConverter.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy@HH:mm:ss", timezone="America/Bogota")
	private LocalDate dateCreate;
	
	@Column(name = "HCCH_PERIODICITY", nullable = false, length = 32)
	private String periodicity;
	
	@Column(name = "HCCH_CODE", nullable = false, length = 32)
	private String code;
	
	@Column(name = "HCCH_REVIEW", nullable = false, length = 8)
	private String review;
	
	@Column(name = "HCCH_REFERENCE", nullable = false, length = 32)
	private String reference;
	
	@Column(name = "HCCH_OF", nullable = false, length = 32)
	private String of;
	
	@Column(name = "HCCH_BATCH", nullable = false, length = 32)
	private String hcchBatch;
	
	@Column(name = "HCCH_COMMENT", nullable = false, columnDefinition = "varchar(max)")
	private String comment;
	
	@Column(name = "HCCH_ANALYSIS", nullable = false, columnDefinition = "varchar(max)")
	private String analysis;

	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "HCCH_SAPCODE", nullable = false)
	private List<HccDetail> detail;
	
	public HccHead() {}

	public String getSapCode() {
		return sapCode;
	}

	public void setSapCode(String sapCode) {
		this.sapCode = sapCode;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getHccNorm() {
		return hccNorm;
	}

	public void setHccNorm(String hccNorm) {
		this.hccNorm = hccNorm;
	}

	public LocalDate getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(LocalDate dateCreate) {
		this.dateCreate = dateCreate;
	}

	public String getPeriodicity() {
		return periodicity;
	}

	public void setPeriodicity(String periodicity) {
		this.periodicity = periodicity;
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

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getOf() {
		return of;
	}

	public void setOf(String of) {
		this.of = of;
	}

	public String getHcchBatch() {
		return hcchBatch;
	}

	public void setHcchBatch(String hcchBatch) {
		this.hcchBatch = hcchBatch;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getAnalysis() {
		return analysis;
	}

	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}

	public List<HccDetail> getDetail() {
		return detail;
	}

	public void setDetail(List<HccDetail> detail) {
		this.detail = detail;
	}

}
