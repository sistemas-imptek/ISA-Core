package com.isacore.quality.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "exit_material_history")
@Table(name = "EXIT_MATERIAL_HISTORY")
public class ExitMaterialHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EMH_ID")
	private Integer idEMH;
	
	@Column(name = "EMH_QUANTITY", nullable = false)
	private Integer quantity;
	
	@Column(name = "EMH_DESCRIPTION", nullable = false, length = 2048)
	private String description;

	public Integer getIdEMH() {
		return idEMH;
	}

	public void setIdEMH(Integer idEMH) {
		this.idEMH = idEMH;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
