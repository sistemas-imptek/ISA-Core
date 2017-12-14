package com.isacore.sgc.acta.service;

import com.isacore.sgc.acta.model.UserImptek;

public interface IUserImptekService extends CRUD<UserImptek>{	
	
	UserImptek findByUserImptek(String nickname);
	
}
