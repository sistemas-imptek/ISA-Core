package com.isacore.quality.service;

import com.isacore.quality.model.Product;
import com.isacore.util.CRUD;

public interface IProductService extends CRUD<Product>{

	Product findOnlyProductById (Product p);
	
	Product findProductByIdAndPeriod(Integer idP, String period);
	
}
