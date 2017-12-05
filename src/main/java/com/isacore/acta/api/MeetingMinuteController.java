package com.isacore.acta.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.isacore.acta.model.MeetingMinute;
import com.isacore.acta.service.impl.MeetingMinuteServiceImpl;

@RestController
@RequestMapping(value = "/minute")
public class MeetingMinuteController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private MeetingMinuteServiceImpl minuteService;
	
	@RequestMapping(value = "/findAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MeetingMinute>> findAll(){
		
		List<MeetingMinute> minutes = this.minuteService.findAll();
		
		this.logger.info("> obtiene la lista de actas");
		
		if(minutes == null || minutes.isEmpty())
			return new ResponseEntity<List<MeetingMinute>>(HttpStatus.NO_CONTENT);
		else 
			return new ResponseEntity<List<MeetingMinute>>(minutes,HttpStatus.OK);
	}
}
