package com.isacore.model;

import java.time.LocalDate;
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
	@Column(name = "EMC_IDMCO", nullable = false)
	private Integer idMCo;
	
	@Column(name = "COM_IDCOMMITTEE", nullable = false)
	private String idCommittee;
	
	@Column(name = "EMP_EMPLOYEE", nullable = false, length = 20)
	private String ciEmployee;
	
	@Column(name = "EMC_STARTDATE", nullable = false)
	private LocalDate startDate;	
	
	@Column(name = "EMC_ENDDATE", nullable = true)
	private LocalDate endDate;
	
	@Column(name = "EMP_STATE", nullable = false)
	private boolean state;

	public Integer getIdMCo() {
		return idMCo;
	}

	public void setIdMCo(Integer idMCo) {
		this.idMCo = idMCo;
	}

	public String getIdCommittee() {
		return idCommittee;
	}

	public void setIdCommittee(String idCommittee) {
		this.idCommittee = idCommittee;
	}

	public String getCiEmployee() {
		return ciEmployee;
	}

	public void setCiEmployee(String ciEmployee) {
		this.ciEmployee = ciEmployee;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	
}
