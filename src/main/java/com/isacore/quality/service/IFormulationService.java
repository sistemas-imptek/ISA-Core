package com.isacore.quality.service;

import java.util.List;

import com.isacore.quality.model.Formulation;
import com.isacore.util.CRUD;

public interface IFormulationService extends CRUD<Formulation>{
	
	List<Formulation> findFormulationByProductAndFormType(String idPSap, Integer idF);
	
}
