package com.isacore.acta.service;

import com.isacore.acta.model.UserImptek;

public interface IUserImptekService extends CRUD<UserImptek>{	
	
	UserImptek findByUserImptek(String nickname);
	
}
