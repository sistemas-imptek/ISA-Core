package com.isacore.quality.service;

import com.isacore.quality.model.NonconformingProduct;
import com.isacore.util.CRUD;

public interface INonconformingProductService extends CRUD<NonconformingProduct>{
	
	public NonconformingProduct consumeMaterialNC(NonconformingProduct ncp, Double quantity) ;
	
}
