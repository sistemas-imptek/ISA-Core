package com.isacore.quality.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isacore.quality.model.HccHead;

@Repository
public interface IHccHeadRepo extends JpaRepository<HccHead, String>{

}
