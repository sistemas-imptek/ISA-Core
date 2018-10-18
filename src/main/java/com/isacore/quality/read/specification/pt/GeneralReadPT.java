package com.isacore.quality.read.specification.pt;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class GeneralReadPT {

	public static final String FILE_NAME = "Especificaciones Técnicas Productos V.0..xlsx";

	public static final String PATH_TESTS_TEMPLATE = "C:\\CRIMPTEK\\Calidad\\EspecificacionesFormulaciones\\" + FILE_NAME;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void run() {
		
		File excelFile = new File(PATH_TESTS_TEMPLATE);
		if (excelFile.exists()) {
			readEspecification(excelFile);
		} else {
			logger.error(">> El directorio:::" + PATH_TESTS_TEMPLATE + ":::no existe");
		}
	}
	
	public void readEspecification(File f) {
		
		FileInputStream excelFile;
		XSSFWorkbook workbook = null;
		
		try {
			excelFile = new FileInputStream(f);
			
			
			workbook = new XSSFWorkbook(excelFile);
			
			ReadNorm.readNorms(workbook.getSheet("Normas Productos"));
			ReadNorm.readSpecificationText(workbook.getSheet("Normas de Ensayo"));
			
			
			
		} catch (IOException e) {
			logger.info(">> No se ha podido leer el libro de excel:::" + FILE_NAME);
		} finally {
			try {
				workbook.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			};
		}
		
	}
	
}
