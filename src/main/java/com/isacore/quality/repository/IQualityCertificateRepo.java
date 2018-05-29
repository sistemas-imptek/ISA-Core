package com.isacore.quality.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isacore.quality.model.QualityCertificate;

@Repository
public interface IQualityCertificateRepo extends JpaRepository<QualityCertificate, Integer>{

}
