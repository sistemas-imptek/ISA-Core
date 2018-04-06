package com.isacore.quality.read.tests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class GeneralReadTest{

	@Autowired
	private TestReadTraction rTTraction;

	@Autowired
	private TestReadCizalla rTCizalla;

	@Autowired
	private TestReadDesgarreClavo rTDesgarroClavo;

	@Autowired
	private TestReadPiedra rTPiedra;

	@Scheduled(cron = "0 0 0 * * ?")
	public void execute(){
		this.rTTraction.run();

		this.rTCizalla.run();

		this.rTDesgarroClavo.run();

		this.rTPiedra.run();

	}

}
