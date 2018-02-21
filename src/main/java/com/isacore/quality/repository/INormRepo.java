package com.isacore.quality.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.isacore.quality.model.Norm;

@Repository
public interface INormRepo extends JpaRepository<Norm, Integer>{

	@Query("select new com.isacore.quality.model.Norm(n.idNorm, n.nameNorm, n.aplication, n.description, n.kind) from norm n where n.kind = :kindNorm")
	List<Norm> findByKindNorm(@Param("kindNorm") String kindNorm);
	
}
