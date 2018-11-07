package com.isacore.quality.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.isacore.quality.model.Family;
import com.isacore.quality.model.Feature;
import com.isacore.quality.model.LineProduction;
import com.isacore.quality.model.Product;
import com.isacore.quality.model.Property;
import com.isacore.quality.model.PropertyList;
import com.isacore.quality.repository.IProductRepo;
import com.isacore.quality.repository.IPropertyRepo;
import com.isacore.quality.service.IProductService;

@Service
public class ProductServiceImpl implements IProductService {
	
	@PersistenceContext
	EntityManager entityManager;

	@Autowired
	private IProductRepo repo;
	
	@Autowired
	private IPropertyRepo repoProperty;

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
		Optional<Product> o = this.repo.findById(obj.getIdProduct());
		if(o.isPresent())
			return o.get();
		else
			return null;
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
//		List<Object[]> list = this.repo.findOnlyProductById(p.getIdProduct());
//		if(list.isEmpty() || list == null)
//			return null;
//		else {
//			Product pp = new Product();
//			
//			Object[] o = list.get(0);
//			pp.setIdProduct((Integer)o[0]);
//			pp.setSapCode((String)o[1]);
//			pp.setNameProduct((String)o[2]);
//			pp.setDescProduct((String)o[3]);
//			pp.setItcdq((String)o[4]);
//			
//			
//		}
		
		Query query = entityManager.createNativeQuery(
				"select p.product_id, p.product_sap_code, p.product_name, p.product_description, p.product_itcdq, p.product_type,\r\n" + 
				"f.fam_id, f.fam_name,\r\n" + 
				"lp.lp_id, lp.lp_name\r\n" + 
				"from product p\r\n" + 
				"full join family f on p.fam_id = f.fam_id\r\n" + 
				"full join line_production lp on p.lp_id = lp.lp_id\r\n" + 
				"WHERE P.product_id = ?");
		
		query.setParameter(1, p.getIdProduct());
		List<Object[]> list = query.getResultList();
		
		if(list.isEmpty() || list == null)
			return null; 
		else {
			Object[] o = list.get(0);
			Product pp = new Product();
			pp.setIdProduct((Integer)o[0]);
			pp.setSapCode((String)o[1]);
			pp.setNameProduct((String)o[2]);
			pp.setDescProduct((String)o[3]);
			pp.setItcdq((String)o[4]);
			pp.setTypeProduct((String)o[5]);
			
			if(o[6] != null) {
				Family f = new Family();
				f.setFamilyId((Integer)o[6]);
				f.setFamilyName((String) o[7]);
				pp.setFamily(f);
			}
			
			if(o[8] != null) {
				LineProduction lp = new LineProduction();
				lp.setIdLineP((Integer)o[8]);
				lp.setLineName((String) o[9]);
				pp.setLineProduction(lp);
			}
			
			return pp;
			
		}
		
	}
	
	@Override
	public List<Product> findAllProducts() {
		
		List<Product> products;
		List<Object[]> list = this.repo.findAllProducts();
		
		if (list.isEmpty() || list == null)
			return null;
		else {
			products = new ArrayList<>();
			list.forEach((Object[]x) -> {
				Product pp = new Product();
				
				if(x[0] != null) {
					pp.setIdProduct((Integer)x[0]);
					pp.setSapCode((String)x[1]);
					pp.setNameProduct((String)x[2]);
					pp.setDescProduct((String)x[3]);
					pp.setItcdq((String)x[4]);
					pp.setTypeProduct((String)x[5]);
					
					if(x[6] != null) {
						Family f = new Family();
						f.setFamilyId((Integer)x[6]);
						f.setFamilyName((String) x[7]);
						pp.setFamily(f);
					}
					
					if(x[8] != null) {
						LineProduction lp = new LineProduction();
						lp.setIdLineP((Integer)x[8]);
						lp.setLineName((String) x[9]);
						pp.setLineProduction(lp);
					}
					
					products.add(pp);
				}
				
			});
			return products;
		}
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
				PropertyList pl = new PropertyList();
				
				pl.setIdProperty((String)x[5]);
				pl.setNameProperty((String)x[6]);
				//p.setIdProperty((String)x[5]);
				//p.setNameProperty((String)x[6]);
				p.setTypeProperty((String)x[7]);
				pl.setPeriodicity((String)x[8]);
				//pl.setNormName((String)x[9]);
				//p.setPeriodicityProperty((String)x[8]);
				//p.setNormProperty((String)x[9]);
				p.setPropertyNorm((String)x[9]);
				p.setPropertyList(pl);
				p.setMinProperty((x[10]) == null ? null : ((BigDecimal)x[10]).doubleValue());
				p.setMaxProperty((x[11]) == null ? null : ((BigDecimal)x[11]).doubleValue());
				p.setUnitProperty((String)x[12]);
				p.setViewProperty((String)x[13]);
				p.setViewPropertyOnHcc((Boolean)x[14]);
				listProperty.add(p);
			});
			product.setProperties(listProperty);
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


	@Override
	public void saveProductProperty(List<Product> listProduct, String user) {
		
		LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateUpdate = now.format(formatter);
		
		listProduct.forEach(x -> {

			System.out.println("Producto a guardar----> " + x.getNameProduct());

			Product p;
			List<Property> listProperties = x.getProperties();
			x.setProperties(null);
			p = this.repo.save(x);

			listProperties.forEach(y -> {
				y.setProduct(p);
				y.setDateUpdate(LocalDateTime.now());
				List<Object> propertyDescription = this.repoProperty.validateExistProperty(y.getProduct().getIdProduct(),
						y.getPropertyList().getIdProperty());
				if (propertyDescription == null)
					this.repoProperty.createProperty(y.getProduct().getIdProduct(), 
							y.getPropertyList().getIdProperty(), 
							y.getMinProperty(), 
							y.getMaxProperty(), 
							y.getUnitProperty(), 
							y.getViewProperty(), 
							dateUpdate, 
							y.getTypeProperty(),
							y.getPropertyNorm(),
							user);
				else
					this.repoProperty.updateProperty(y.getMinProperty(), 
							y.getMaxProperty(), 
							y.getUnitProperty(), 
							y.getViewProperty(), 
							dateUpdate, 
							y.getTypeProperty(), 
							y.getPropertyNorm() ,user, 
							y.getProduct().getIdProduct(), 
							y.getPropertyList().getIdProperty());
			});

		});
	}

}
