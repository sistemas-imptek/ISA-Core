package com.isacore.quality.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "propertylist")
@Table(name = "PROPERTYLIST")
public class PropertyList {

	@Id
	@Column(name = "PROPL_ID", nullable = false, length = 16)
	private String idProperty;

	@Column(name = "PROPL_NAME", nullable = false, length = 1024)
	private String nameProperty;
	
	@Column(name = "PROPL_LINE_APPLICATION", nullable = true, length = 512)
	private String lineApplication;
	
	@Column(name = "PROPL_PERIODICITY", nullable = true, length = 64)
	private String periodicity;
	
	@Column(name = "PROPL_MACHINE", nullable = true, length = 1024)
	private String machine;

	// Propiedad para saber si es Técnica(T) o Visual (V)
	@Column(name = "PROPL_TYPE", nullable = false, length = 4)
	private String typeProperty;
	
	@Column(name = "PROPL_METHOD", nullable = true, length = 512)
	private String method;
	
	@Column(name = "PROPL_LABORATORY", nullable = true, length = 512)
	private String laboratory;
	
	@Column(name = "PROPL_SAMPLING_PLAN", nullable = true, length = 1024)
	private String samplingPlan;


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

	public String getLineApplication() {
		return lineApplication;
	}

	public void setLineApplication(String lineApplication) {
		this.lineApplication = lineApplication;
	}

	public String getPeriodicity() {
		return periodicity;
	}

	public void setPeriodicity(String periodicity) {
		this.periodicity = periodicity;
	}

	public String getMachine() {
		return machine;
	}

	public void setMachine(String machine) {
		this.machine = machine;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getLaboratory() {
		return laboratory;
	}

	public void setLaboratory(String laboratory) {
		this.laboratory = laboratory;
	}

	public String getSamplingPlan() {
		return samplingPlan;
	}

	public void setSamplingPlan(String samplingPlan) {
		this.samplingPlan = samplingPlan;
	}

	@Override
	public String toString() {
		return "PropertyList [idProperty=" + idProperty + ", nameProperty=" + nameProperty + ", normName="
				+ ", lineApplication=" + lineApplication + ", periodicity=" + periodicity + ", machine=" + machine
				+ ", typeProperty=" + typeProperty + ", method=" + method + ", laboratory=" + laboratory
				+ ", samplingPlan=" + samplingPlan + ", typeProduct=" + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idProperty == null) ? 0 : idProperty.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PropertyList other = (PropertyList) obj;
		if (idProperty == null) {
			if (other.idProperty != null)
				return false;
		} else if (!idProperty.equals(other.idProperty))
			return false;
		return true;
	}

	
	
}
