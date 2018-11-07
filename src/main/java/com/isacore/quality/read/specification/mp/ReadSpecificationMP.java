package com.isacore.quality.read.specification.mp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.isacore.quality.model.Product;
import com.isacore.quality.model.Property;

public class ReadSpecificationMP {

	public static List<Product> read(XSSFSheet sheet){
		
		System.out.println(">> Name sheet:::" + sheet.getSheetName() + ":::");

		Iterator<Row> rowIterator = sheet.iterator();

		Row row;

		row = rowIterator.next();
		row = rowIterator.next();

		CellType cellType;

		List<Product> prooducts = new ArrayList<>();
		
		String checkSapCode = "A";
		
		while (rowIterator.hasNext()) {
			row = rowIterator.next();
			
			Cell cell;

			Product product = new Product();
			product.setTypeProduct("MP");

			List<Property> properties = new ArrayList<>();

			Property p = new Property();
			
			
		}
		
		return null;
	}
	
	
}
