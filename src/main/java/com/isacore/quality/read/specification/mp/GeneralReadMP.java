package com.isacore.quality.read.specification.mp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.isacore.quality.model.Product;
import com.isacore.quality.read.specification.pt.ReadSpecificationLaminas;
import com.isacore.quality.service.impl.ProductServiceImpl;
import com.isacore.quality.service.impl.PropertyServiceImpl;

@Component
public class GeneralReadMP {

	@Autowired
	private ProductServiceImpl productService;

	@Autowired
	private PropertyServiceImpl propertyService;

	public static final String FILE_NAME = "Especificaciones materias primas V.0.xlsx";

	public static final String PATH_TESTS_TEMPLATE = "C:\\CRIMPTEK\\Calidad\\EspecificacionesFormulaciones\\"
			+ FILE_NAME;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public void run(String user) {

		File excelFile = new File(PATH_TESTS_TEMPLATE);
		if (excelFile.exists()) {
			readEspecification(excelFile, user);
		} else {
			logger.error(">> El directorio:::" + PATH_TESTS_TEMPLATE + ":::no existe");
		}
	}

	public void readEspecification(File f, String user) {

		FileInputStream excelFile;
		XSSFWorkbook workbook = null;

		try {
			excelFile = new FileInputStream(f);

			workbook = new XSSFWorkbook(excelFile);

			List<Product> listProduct;

			listProduct = ReadSpecificationLaminas.read(workbook.getSheet("Especificaciones Técnicas SBS 1"));

		} catch (IOException e) {
			logger.info(">> No se ha podido leer el libro de excel:::" + FILE_NAME);
		} finally {
			try {
				workbook.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			;
		}

	}
}
