package com.isacore.quality.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.isacore.quality.model.Formulation;

@Repository
public interface IFormulationRepo extends JpaRepository<Formulation, Integer>{

	@Query(value = "select f.form_description, f.form_multifactor, f.form_unit, f.form_value from formulation f  inner join product p on p.product_id = f.product_id where rtrim(replace(p.product_sap_code,'.0','')) = :idPSap and f.form_id = :idF and not f.form_value = 0", nativeQuery = true)
	List<Object[]> searchFormulationByProductFormuType(@Param("idPSap")String idPSap, @Param("idF")Integer idF);
	
}
