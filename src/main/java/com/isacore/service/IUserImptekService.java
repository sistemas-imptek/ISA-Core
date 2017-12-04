package com.isacore.service;

import com.isacore.model.UserImptek;

public interface IUserImptekService extends CRUD<UserImptek>{	
	
	UserImptek findByUserImptek(String nickname);
	
}
