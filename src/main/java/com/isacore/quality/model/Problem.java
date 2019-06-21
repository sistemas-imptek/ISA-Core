package com.isacore.quality.model;

import java.beans.Transient;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "problem")
@Table(name = "PROBLEM")
public class Problem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PROBLEM_ID")
	private Integer idProblem;
	
	@Column(name = "PROBLEM_DESCRIPTION", nullable = true, length = 1024)
	private String description;
	
	
	@Column(name = "PROBLEM_PICTURE", nullable = true)
	private String pictureStringB64;

	public Integer getIdProblem() {
		return idProblem;
	}

	public void setIdProblem(Integer idProblem) {
		this.idProblem = idProblem;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPictureStringB64() {
		return pictureStringB64;
	}

	public void setPictureStringB64(String pictureStringB64) {
		this.pictureStringB64 = pictureStringB64;
	}

	
	
	
}
