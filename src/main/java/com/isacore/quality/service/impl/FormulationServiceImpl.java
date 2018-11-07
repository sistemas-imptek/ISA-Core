package com.isacore.quality.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isacore.quality.model.Formulation;
import com.isacore.quality.repository.IFormulationRepo;
import com.isacore.quality.service.IFormulationService;

@Service
public class FormulationServiceImpl implements IFormulationService {

	@Autowired
	private IFormulationRepo repo;

	@Override
	public List<Formulation> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Formulation create(Formulation obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Formulation findById(Formulation id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Formulation update(Formulation obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Formulation> findFormulationByProductAndFormType(String idPSap, Integer idF) {
		List<Object[]> response = this.repo.searchFormulationByProductFormuType(idPSap, idF);
		List<Formulation> formulations = new ArrayList<>();

		if (response == null || response.isEmpty())
			return formulations;
		else {
			response.forEach(x -> {
				Formulation f = new Formulation();
				f.setDescription((String) x[0]);
				f.setMultiFactor((Integer)x[1]);
				f.setUnit((String)x[2]);
				f.setValue((x[3]) == null ? null: ((BigDecimal)x[3]).doubleValue());
				formulations.add(f);
			});

			return formulations;
		}
	}

}
