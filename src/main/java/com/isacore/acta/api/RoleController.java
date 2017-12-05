package com.isacore.acta.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.isacore.acta.model.Role;
import com.isacore.acta.service.IRoleService;

@RestController
@RequestMapping(value = "/role")
public class RoleController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IRoleService roleService;
	
	@RequestMapping(value = "/findAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Role>> findAll(){
		return new ResponseEntity<List<Role>>(this.roleService.findAll(),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/findByIdRole", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Role> findByIdRole(@RequestParam(value = "idrole",required = true) String idrole){
		return new ResponseEntity<Role>(this.roleService.findById(idrole),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Role> addRole(@RequestBody Role role){
		
		try {
			this.roleService.create(role);
			this.logger.info("> Create Role -> " + role.getRolName() + " <-");
			return new ResponseEntity<Role>(role, HttpStatus.OK);
		} catch (Exception e) {
			this.logger.error("> Error al crear el Role -> " + role.getRolName() + " <-");
			return new ResponseEntity<Role>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
