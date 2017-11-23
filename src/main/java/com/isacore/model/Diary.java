package com.isacore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "diary")
public class Diary {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Dia_Iddiary;
	
	@Column(name = "Dia_Description", length =128)
	private String descripcion;
	@Column(name = "Dia_Executed")
	private Boolean ejecutado;
	
	
}
