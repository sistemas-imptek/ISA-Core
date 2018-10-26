package com.isacore.quality.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.isacore.quality.model.Test;

@Repository
public interface ITestRepo extends JpaRepository<Test, Integer>{

	@Query("select new com.isacore.quality.model.Test(t.idProperty ,avg(t.resultTest)) from test t where t.batchTest = :batch and t.prommissing = true group by t.idProperty")
	List<Test> findByBatch(@Param("batch") String batch);
	
}
