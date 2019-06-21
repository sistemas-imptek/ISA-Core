package com.isacore.quality.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.isacore.quality.model.Test;

@Repository
public interface ITestRepo extends JpaRepository<Test, Integer> {

	@Query("select new com.isacore.quality.model.Test(t.idProperty ,avg(t.resultTest)) from test t where t.batchTest = :batch and t.prommissing = true group by t.idProperty")
	List<Test> findByBatch(@Param("batch") String batch);

	@Query(value = "select * from test where test_batch = :batch and test_promissing = 0", nativeQuery = true)
	List<Test> findByBatchAndPromissing(@Param("batch") String batch);

	@Query(value = "select * from test where  test_idproduct = :idP and test_property_code=:idProp ", nativeQuery = true)
	List<Test> findByIdProduct(@Param("idP") Integer idP, @Param("idProp") String idProp);

	@Query(value = "Select * from test where test_batch like '%MP%' OR test_batch like '%DEV%'", nativeQuery = true)
	List<Test> findByBatchMP(@Param("batch") String batch);
	
	@Query(value = "select * from test where test_batch = :batch and test_idproduct = :idP", nativeQuery = true)
	List<Test> findByBatchAndIdProduct(@Param("batch") String batch, @Param("idP") Integer idP);

}
