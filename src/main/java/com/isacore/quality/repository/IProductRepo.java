package com.isacore.quality.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.isacore.quality.model.Product;

@Repository
public interface IProductRepo extends JpaRepository<Product, Integer>{

	@Query("select new com.isacore.quality.model.Product(p.idProduct, p.sapCode, p.nameProduct, p.descProduct, p.itcdq, p.familyProduct, p.typeProduct) from product p where p.idProduct = :idP")
	Product findOnlyProductById(@Param("idP") int idP);
	
}
