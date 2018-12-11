package com.isacore.quality.read.tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.isacore.quality.model.Test;
import com.isacore.quality.service.ITestService;

import au.com.bytecode.opencsv.CSVReader;

@Component
public class TestReadDesgarreClavo {

	public static final String RESISTENCIA_DESGARRO_CLAVO_LONGITUDINAL = "PROP_36";
	public static final String RESISTENCIA_DESGARRO_CLAVO_TRANSVERSE = "PROP_37";

	public static final String PATH_TESTS_TRACTION = "C:\\CRIMPTEK\\Calidad\\Tests\\EquipoUniversal\\DesgarreClavo";

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ITestService serviceTest;
	
	public void run() {

		File file = new File(PATH_TESTS_TRACTION);

		if (file.exists()) {
			logger.info(">> Accediendo al directorio:::" + PATH_TESTS_TRACTION + ":::");

			String[] tests = file.list();

			if (tests.length != 0) {
				List<Test> listTests = new ArrayList<>();
				for (int i = 0; i < tests.length; i++)
					readTest(tests[i], listTests);

				listTests.forEach(x -> System.out.println(x.getBatchTest() + "::" + x.getIdProperty() + "::" + x.getResultTest()));

				listTests.forEach(x -> this.serviceTest.create(x));

			} else
				logger.info(">> No existen tests");
		} else
			logger.error(">> El directorio:::" + PATH_TESTS_TRACTION + ":::no existe");

	}

	public void readTest(String fileName, List<Test> listTests) {
		try {

			logger.info(">> ISA::method::readTest:::" + fileName);

			String pathTest = PATH_TESTS_TRACTION + "\\" + fileName;
			// Estraemos el numero de lote
			String[] dataName = fileName.split(" ");
			String batch = dataName[0];
			// leemos el contenido del archivo plano
			CSVReader reader = new CSVReader(new FileReader(pathTest), ';');
			List<String[]> data = reader.readAll();
			reader.close();
			
			String[] longitudinal = data.get(0);
			String[] transverse = data.get(1);
			
			Test t1 = new Test();
			t1.setBatchTest(batch);
			t1.setIdProperty(RESISTENCIA_DESGARRO_CLAVO_LONGITUDINAL);
			t1.setResultTest(Double.parseDouble((String)longitudinal[1]));
			listTests.add(t1);
			
			Test t2 = new Test();
			t2.setBatchTest(batch);
			t2.setIdProperty(RESISTENCIA_DESGARRO_CLAVO_TRANSVERSE);
			t2.setResultTest(Double.parseDouble((String)transverse[1]));
			listTests.add(t2);
			
			File f = new File(pathTest);
			f.delete();
			
		} catch (FileNotFoundException e) {
			logger.error(">> ISA::method::readTest:::FileNotFoundException");
		} catch (IOException e) {
			logger.error(">> ISA::method::readTest:::IOException");
		}
	}
}
