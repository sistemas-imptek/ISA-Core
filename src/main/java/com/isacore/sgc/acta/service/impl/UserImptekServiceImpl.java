package com.isacore.sgc.acta.service.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isacore.localdate.converter.LocalDateTimeConverter;
import com.isacore.sgc.acta.model.Employee;
import com.isacore.sgc.acta.model.UserImptek;
import com.isacore.sgc.acta.repository.IRoleRepo;
import com.isacore.sgc.acta.repository.IUserImptekRepo;
import com.isacore.sgc.acta.service.IUserImptekService;

@Service
public class UserImptekServiceImpl implements IUserImptekService{

	@Autowired
	private IUserImptekRepo repo;
	
	@Autowired
	private IRoleRepo repoRole;
	
	@Override
	public List<UserImptek> findAll() {		
		return this.repo.findAll();
	}

	@Override
	public UserImptek create(UserImptek user) {
		return this.repo.save(user);
	}

	@Override
	public UserImptek findById(UserImptek u) { 
		return this.repo.findOne(u.getIdUser());
	}

	@Override
	public UserImptek update(UserImptek user) {
		return this.repo.save(user);
	}

	@Override
	public void delete(String iduser) {
		this.repo.delete(iduser);
	}

	@Override
	public UserImptek findByUserImptek(String nickname) {
		
		List<Object[]> rowSql = this.repo.findUserByNickname(nickname);
		
		if(!(rowSql.isEmpty() || rowSql == null)) {
			Object[] o = rowSql.get(0);
	 		
			UserImptek ui = new UserImptek();
			ui.setIdUser((String)o[0]);
			LocalDateTimeConverter ldtc = new LocalDateTimeConverter();
			ui.setLastAccess(ldtc.convertToEntityAttribute((Timestamp)o[1]));			
			ui.setLastKeyDateChange(ldtc.convertToEntityAttribute((Timestamp)o[2]));
			ui.setNickName((String)o[3]);
			ui.setUserPass((String)o[4]);
			
			Employee emp = new Employee();
			emp.setCiEmployee((String)o[5]);
			
			ui.setRole(this.repoRole.findOne((String)o[6]));
			
			emp.setName((String)o[8]);
			emp.setLastName((String)o[9]);
			emp.setJob((String)o[10]);
			emp.setState((Boolean)o[11]);
			
			ui.setEmployee(emp);
			
			return ui;

		}
		
		return null;
	}

	@Override
	public UserImptek findOnlyUserByNickname(String nickname) {
		return this.repo.findOnlyUserByNickname(nickname);
	}

	

}
