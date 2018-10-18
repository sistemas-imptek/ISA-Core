package com.isacore.quality.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isacore.quality.model.ProdProv;
import com.isacore.quality.model.ProdProvPK;

@Repository
public interface IProdProvRepo extends JpaRepository<ProdProv, ProdProvPK>{

}
