package com.isacore.quality.service.impl;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isacore.quality.model.HccHead;
import com.isacore.quality.model.ProcessFlow;
import com.isacore.quality.model.Product;
import com.isacore.quality.repository.IHccHeadRepo;
import com.isacore.quality.service.IHccHeadService;

@Service
public class HccHeadServiceImpl implements IHccHeadService {

	@Autowired
	private IHccHeadRepo repo;

	@Override
	public List<HccHead> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HccHead create(HccHead hcc) {
		return this.repo.save(hcc);
	}

	@Override
	public HccHead findById(HccHead hh) {
		Optional<HccHead> hccH = this.repo.findById(hh.getSapCode());
		return hccH.get();
	}

	@Override
	public HccHead update(HccHead hcc) {
		return this.repo.save(hcc);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<HccHead> findOnlyHccHead(String tp) {
		
		List<Object[]> list = this.repo.findOnlyHccHead(tp);

		if(list.isEmpty() || list == null)
			return null;
		else {
			List<HccHead> listhcc = new ArrayList<>();
			
			list.forEach((Object[] x) -> {
				HccHead hh = new HccHead();
				Product p = new Product();
				hh.setSapCode((String)x[0]);
				hh.setHcchBatch((String)x[1]);
				p.setNameProduct((String)x[2]);
				p.setTypeProduct((String)x[3]);
				hh.setProduct(p);
				Instant instant = Instant.ofEpochMilli(((Date) x[4]).getTime());
				hh.setDateCreate(LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate());
				hh.setAnalysis((String)x[5]);
				listhcc.add(hh);
			});
			return listhcc;
		}
	}

	@Override
	public List<HccHead> findOnlyHccHead() {
		List<Object[]> list = this.repo.findOnlyHccHead();

		if(list.isEmpty() || list == null)
			return null;
		else {
			List<HccHead> listhcc = new ArrayList<>();
			
			list.forEach((Object[] x) -> {
				HccHead hh = new HccHead();
				Product p = new Product();
				hh.setSapCode((String)x[0]);
				hh.setHcchBatch((String)x[1]);
				p.setNameProduct((String)x[2]);
				p.setTypeProduct((String)x[3]);
				hh.setProduct(p);
				Instant instant = Instant.ofEpochMilli(((Date) x[4]).getTime());
				hh.setDateCreate(LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate());
				hh.setAnalysis((String)x[5]);
				listhcc.add(hh);
			});
			return listhcc;
		}
	}

}
