package com.isacore.quality.classes.util;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.isacore.quality.model.Area;
import com.isacore.quality.model.DefectNpc;
import com.isacore.quality.model.OutputMethod;

@Component
@JsonInclude(Include.NON_NULL)
public class RequestNCP {

	private List<DefectNpc> defects;
	
	private List<OutputMethod> outMethods;
	
	private List<Area> areas;

	public List<DefectNpc> getDefects() {
		return defects;
	}

	public void setDefects(List<DefectNpc> defects) {
		this.defects = defects;
	}

	public List<OutputMethod> getOutMethods() {
		return outMethods;
	}

	public void setOutMethods(List<OutputMethod> outMethods) {
		this.outMethods = outMethods;
	}

	public List<Area> getAreas() {
		return areas;
	}

	public void setAreas(List<Area> areas) {
		this.areas = areas;
	}
	
}
