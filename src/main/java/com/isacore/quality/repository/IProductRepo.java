package com.isacore.quality.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.isacore.quality.model.Product;

@Repository
public interface IProductRepo extends JpaRepository<Product, Integer>{

	@Query("select new com.isacore.quality.model.Product(p.idProduct, p.sapCode, p.nameProduct, p.descProduct, p.itcdq, p.familyProduct, p.typeProduct) from product p where p.idProduct = :idP")
	Product findOnlyProductById(@Param("idP") Integer idP);
	
	@Query(value = "select p.product_id, p.product_sap_code, p.product_name, p.product_description, p.product_type, prop.property_id, prop.property_name, prop.property_type, prop.property_periodicity, prop.property_norm, prop.property_min, prop.property_max, prop.property_unit, prop.property_view, prop.property_view_hcc from product p inner join property prop on p.product_id = prop.product_id where p.product_id = :idP and prop.property_periodicity = :period", nativeQuery = true)
	List<Object[]> findProductByIdAndPeriod(@Param("idP")Integer idP, @Param("period")String period);
	
	
}
