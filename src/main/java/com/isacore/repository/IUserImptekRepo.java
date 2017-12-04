package com.isacore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.isacore.model.UserImptek;

@Repository
public interface IUserImptekRepo extends JpaRepository<UserImptek, String>{

	@Query("from userImptek u where u.nickName = :user")
	UserImptek findUserByNickname(@Param("user") String user);
	
}
