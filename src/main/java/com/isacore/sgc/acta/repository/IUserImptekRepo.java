package com.isacore.sgc.acta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.isacore.sgc.acta.model.UserImptek;

@Repository
public interface IUserImptekRepo extends JpaRepository<UserImptek, String>{

	@Query("from userImptek u where u.nickName = :user")
	UserImptek findUserByNickname(@Param("user") String user);
	
	@Query("select new com.isacore.sgc.acta.model.UserImptek(ui.idUser,ui.employee,ui.nickName,ui.userPass,ui.lastKeyDateChange,ui.lastAccess) from userImptek ui where ui.nickName = :user")
	UserImptek findOnlyUserByNickname(@Param("user") String user);
	
}
