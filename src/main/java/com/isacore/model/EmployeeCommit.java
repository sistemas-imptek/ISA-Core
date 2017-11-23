package com.isacore.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLOYEECOMMIT")
public class EmployeeCommit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer IdMco;
	
	@Column(name = "EMC_FECINI")
	private Date startDate;	
	@Column(name = "EMC_FECfFIN")
	private Date finishDate;
	@Column(name = "EMP_STATE")
	private boolean state;


}
