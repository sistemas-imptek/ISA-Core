package com.isacore.quality.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.isacore.quality.model.Complaint;
import com.isacore.quality.model.Product;
import com.isacore.quality.model.Provider;
import com.isacore.quality.repository.IComplaintRepo;
import com.isacore.quality.repository.IProductRepo;
import com.isacore.quality.service.IComplaintService;
import com.isacore.quality.service.IProviderService;

import net.bytebuddy.asm.Advice.This;

@Component
public class ComplaintServiceImpl implements IComplaintService {

	@Autowired
	private IComplaintRepo repo;

	@Autowired
	private IProductRepo productRepo;

	@Autowired
	private IProviderService providerService;

	@Override
	public List<Complaint> findAll() {
		List<Complaint> cTmp = this.repo.findAll();
		if (!cTmp.isEmpty()) {
			for (Complaint complaint : cTmp) {
				Optional<Product> p = this.productRepo.findById(complaint.getIdProduct());
				Product aux = new Product();
				aux.setNameProduct(p.get().getNameProduct());
				aux.setUnit(p.get().getUnit());
				complaint.setProduct(aux);
				aux.setProviders(this.providerService.findByProductIdVigente(p.get().getIdProduct()));
				for (Provider provider : aux.getProviders()) {
					if (complaint.getIdProvider() == provider.getIdProvider())
						complaint.setProvider(provider);
				}
			}

		}
		return cTmp;
	}

	@Override
	public Complaint create(Complaint obj) {
		return this.repo.save(obj);
	}

	@Override
	public Complaint findById(Complaint obj) {
		Optional<Complaint> com = this.repo.findById(obj.getIdComplaint());
		if (com.isPresent()) {
			Optional<Product> p = this.productRepo.findById(com.get().getIdProduct());
			Product aux = new Product();
			aux.setNameProduct(p.get().getNameProduct());
			aux.setUnit(p.get().getUnit());
			com.get().setProduct(aux);
			aux.setProviders(this.providerService.findByProductIdVigente(p.get().getIdProduct()));
			for (Provider provider : aux.getProviders()) {
				if (com.get().getIdProvider() == provider.getIdProvider())
					com.get().setProvider(provider);
			}
			return com.get();
		} else {
			return null;
		}

	}

	@Override ////
	public Complaint update(Complaint obj) {
		return this.repo.save(obj);
	}

	@Override
	public void delete(String id) {

	}

	@Override
	public List<Complaint> findAllCustomize() {
		return null;
	}

}
