package com.isacore.quality.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.isacore.quality.model.ProdProv;
import com.isacore.quality.model.ProdProvPK;

@Repository
public interface IProdProvRepo extends JpaRepository<ProdProv, ProdProvPK>{
	
	@Query(value = "select pp.prov_id from prod_prov pp where pp.product_id = :idProduct and pp.prov_id = :idProveedor", nativeQuery = true)
	List<Object> validateProdProv(@Param("idProduct")Integer idProduct, @Param("idProveedor")Integer idProveedor);

	@Transactional
	@Modifying
	@Query(value = "insert into prod_prov(product_id, prov_id, pp_status, pp_type) values (:idProduct, :idProveedor, :statusP, :typeP)", nativeQuery = true)
	int createProdProv(@Param("idProduct")Integer idProduct, @Param("idProveedor")Integer idProveedor, @Param("statusP")String statusP, @Param("typeP")String typeP);
	
	@Transactional
	@Modifying
	@Query(value = "update prod_prov set pp_status = :statusP, pp_type = :typeP where product_id = :idProduct and prov_id = :idProveedor", nativeQuery = true)
	int updateProdProv(@Param("statusP")String statusP, @Param("typeP")String typeP, @Param("idProduct")Integer idProduct, @Param("idProveedor")Integer idProveedor);
	
}
