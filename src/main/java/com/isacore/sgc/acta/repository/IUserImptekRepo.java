package com.isacore.sgc.acta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.isacore.sgc.acta.model.UserImptek;

@Repository
public interface IUserImptekRepo extends JpaRepository<UserImptek, String>{

	@Query(value = "select ui.user_iduser, ui.user_lastaccess, ui.user_lastkeydatechange, ui.user_nickname, ui.user_pass, ui.emp_ciemployee, ui.role_name, em.area_id,em.emp_name, em.emp_lastname, em.emp_job, em.emp_state from userimptek ui inner join employee em on ui.emp_ciemployee = em.emp_ciemployee where ui.user_nickname = :user", nativeQuery = true)
	List<Object[]> findUserByNickname(@Param("user") String user);
	
	@Query(value = "select new com.isacore.sgc.acta.model.UserImptek(ui.idUser,ui.employee,ui.nickName,ui.userPass,ui.lastKeyDateChange,ui.lastAccess) from userImptek ui where ui.nickName = :user", nativeQuery = true)
	UserImptek findOnlyUserByNickname(@Param("user") String user);
	
}
