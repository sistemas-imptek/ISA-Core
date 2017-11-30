package com.isacore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.isacore.model.Role;
import com.isacore.service.IRoleService;

@RestController
@RequestMapping(value = "/role")
public class RoleController {

	@Autowired
	private IRoleService roleService;
	
	@RequestMapping(value = "/findAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Role>> findAll(){
		return new ResponseEntity<List<Role>>(this.roleService.findAll(),HttpStatus.OK);
	}
	
}
