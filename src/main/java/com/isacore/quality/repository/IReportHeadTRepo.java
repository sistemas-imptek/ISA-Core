package com.isacore.quality.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.isacore.quality.model.ReportHeadT;

@Repository
public interface IReportHeadTRepo extends JpaRepository<ReportHeadT, Integer> {

	@Query("from reportheadt where type = :typeReport")
	ReportHeadT findHeadByTypeReport(@Param("typeReport") String typeReport);
	
}
