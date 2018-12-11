package com.isacore.quality.service;

import com.isacore.quality.model.ProdProv;
import com.isacore.util.CRUD;

public interface IProdProvService extends CRUD<ProdProv>{

	Integer validateProdProv(Integer idProduct, Integer idProveedor);
	
	int createProdProv(ProdProv pp);

	int updateProdProv(ProdProv pp);
	
}
