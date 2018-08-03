package com.isacore.quality.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isacore.quality.model.Feature;
import com.isacore.quality.model.Product;
import com.isacore.quality.model.Property;
import com.isacore.quality.repository.IProductRepo;
import com.isacore.quality.service.IProductService;

@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	private IProductRepo repo;

	@Override
	public List<Product> findAll() {
		return this.repo.findAll();
	}

	@Override
	public Product create(Product obj) {
		return this.repo.save(obj);
	}

	@Override
	public Product findById(Product obj) {
		return this.repo.findOne(obj.getIdProduct());
	}

	@Override
	public Product update(Product obj) {
		return this.repo.save(obj);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Product findOnlyProductById(Product p) {
		return this.repo.findOnlyProductById(p.getIdProduct());
	}

	@Override
	public Product findProductByIdAndPeriod(Integer idP, String period) {

		List<Object[]> list = this.repo.findProductByIdAndPeriod(idP, period);
		
		if (list.isEmpty() || list == null)
			return null;
		else {
			Product product = new Product();
			
			Object[] o = list.get(0);
			product.setIdProduct((Integer)o[0]);
			product.setSapCode((String) o[1]);
			product.setNameProduct((String) o[2]);
			product.setDescProduct((String) o[3]);
			product.setTypeProduct((String) o[4]);
			
			List<Property> listProperty = new ArrayList<>();
			list.forEach((Object[] x) -> {
				Property p = new Property();
				p.setIdProperty((String)x[5]);
				p.setNameProperty((String)x[6]);
				p.setTypeProperty((String)x[7]);
				p.setPeriodicityProperty((String)x[8]);
				p.setNormProperty((String)x[9]);
				p.setMinProperty((x[10]) == null ? null : ((BigDecimal)x[10]).doubleValue());
				p.setMaxProperty((x[11]) == null ? null : ((BigDecimal)x[11]).doubleValue());
				p.setUnitProperty((String)x[12]);
				p.setViewProperty((String)x[13]);
				p.setViewPropertyOnHcc((Boolean)x[14]);
				listProperty.add(p);
			});
			product.setPropertyList(listProperty);
			return product;
		}
	}

	@Override
	public Product findProductFeature(Integer idP) {
		List<Object[]> list = this.repo.findProductFeature(idP);
		
		if(list.isEmpty() || list == null)
			return null;
		else {
			
			Product product = new Product();
			
			Object[] o = list.get(0); 
			product.setIdProduct((Integer) o[0]);
			product.setSapCode((String) o[1]);
			product.setNameProduct((String) o[2]);
			
			Feature f = new Feature();
			f.setLength((o[3]) == null ? null : ((BigDecimal)o[3]).doubleValue());
			f.setUnitLength((String) o[4]);
			f.setGrossWeigth((o[5]) == null ? null : ((BigDecimal)o[5]).doubleValue());
			f.setUnitGrossWeigth((String) o[6]);
			f.setNetWeigth((o[7]) == null ? null : ((BigDecimal)o[7]).doubleValue());
			f.setUnitNetWeigth((String) o[8]);
			f.setWeigthArea((o[9]) == null ? null : ((BigDecimal)o[9]).doubleValue());
			f.setUmb((String) o[10]);
			f.setUnitCost((o[11]) == null ? null : ((BigDecimal)o[11]).doubleValue());
			f.setDistributorPrice((o[12]) == null ? null : ((BigDecimal)o[12]).doubleValue());
			
			product.setFeature(f);
			
			return product;
		}
	}

}
