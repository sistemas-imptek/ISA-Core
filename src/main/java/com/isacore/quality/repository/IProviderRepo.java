package com.isacore.quality.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.isacore.quality.model.Provider;

@Repository
public interface IProviderRepo extends JpaRepository<Provider, Integer>{

	@Query(value = "select p.prov_id, p.prov_description, p.prov_name, p.prov_sap_code,p.product_id from provider p where p.product_id = :idP", nativeQuery = true)
	List<Object[]> findByProductId(@Param("idP")Integer idP);
	
}
