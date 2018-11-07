package com.isacore.quality.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isacore.quality.model.Provider;
import com.isacore.quality.repository.IProviderRepo;
import com.isacore.quality.service.IProviderService;

@Service
public class ProviderServiceImpl implements IProviderService{
	
	@Autowired
	private IProviderRepo repo;
	

	@Override
	public List<Provider> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Provider create(Provider obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Provider findById(Provider id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Provider update(Provider obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Provider> findByProductId(Integer idP) {
		
		List<Object[]> list = this.repo.findByProductId(idP);
		
		if (list.isEmpty() || list == null)
			return null;
		else {
			List<Provider> listProvider = new ArrayList<>();
			
			list.forEach((Object[] x) -> {
				Provider prov = new Provider();
				prov.setIdProvider((Integer)x[0]);
				prov.setNameProvider((String)x[1]);
				prov.setTypeProvider((String)x[2]);
				listProvider.add(prov);
			});
			return listProvider;
		} 
	}

}
