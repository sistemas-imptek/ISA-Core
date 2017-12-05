package com.isacore.acta.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.isacore.acta.model.UserImptek;
import com.isacore.acta.service.IUserImptekService;

@RestController
@RequestMapping(value = "/userimptek")
public class UserImptekController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IUserImptekService userImptekService;

	@RequestMapping(value = "/findAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserImptek>> findAll(){
		
		List<UserImptek> users = this.userImptekService.findAll();
		
		if(users == null || users.isEmpty() )
			return new ResponseEntity<List<UserImptek>>(HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<List<UserImptek>>(users,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/findByUser/{user}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<UserImptek> findByUser(@PathVariable("user") String user){
		logger.info("> get User");
		
		UserImptek userI = this.userImptekService.findByUserImptek(user);
		
		if(userI == null)
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<UserImptek>(userI,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/validate", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<UserImptek> validateUser(@RequestParam(value = "user", required = true)String user, @RequestParam(value = "pass")String pass){
		logger.info("> get User");
		
		UserImptek userI = this.userImptekService.findByUserImptek(user);
		
		if(userI == null)
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		else
			if(userI.getUserPass().equals(pass))
				return new ResponseEntity<UserImptek>(userI,HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}
	
	
}
