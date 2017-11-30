package com.isacore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isacore.model.Role;

@Repository
public interface IRoleRepo extends JpaRepository<Role, String>{

}
