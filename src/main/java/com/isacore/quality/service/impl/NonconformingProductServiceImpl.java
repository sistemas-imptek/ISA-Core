package com.isacore.quality.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isacore.quality.model.NonconformingProduct;
import com.isacore.quality.repository.INonconformingProduct;
import com.isacore.quality.service.INonconformingProductService;

@Service
public class NonconformingProductServiceImpl implements INonconformingProductService{

	@Autowired
	private INonconformingProduct repo;
	
	@Override
	public List<NonconformingProduct> findAll() {
		return this.repo.findAll();
	}

	@Override
	public NonconformingProduct create(NonconformingProduct ncp) {
		ncp.setRepCode("PNC-03");
		ncp.setRepReference("MP-PNC.01");
		ncp.setRepRegister("PNC.03");
		ncp.setRepReview("REV.01");
		ncp.setRepSubtitle("Tratamiento del Producto No Conforme");
		ncp.setRepTitle("FORMATO DE REGISTRO");
		return this.repo.save(ncp);
	}

	@Override
	public NonconformingProduct findById(NonconformingProduct ncp) {
		Optional<NonconformingProduct> o = this.repo.findById(ncp.getIdNCP());
		if(o.isPresent())
			return o.get();
		else
			return null;
	}

	@Override
	public NonconformingProduct update(NonconformingProduct ncp) {
		return this.repo.save(ncp);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public NonconformingProduct consumeMaterialNC(NonconformingProduct ncp, Double quantity) {
		NonconformingProduct ncpFound= this.findById(ncp);
		Double stock= ncpFound.getExistingMaterial() - quantity;
		ncpFound.setExistingMaterial(stock);
		return this.repo.save(ncpFound);		
	}

}
