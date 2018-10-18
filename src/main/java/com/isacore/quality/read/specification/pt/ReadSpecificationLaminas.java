package com.isacore.quality.read.specification.pt;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.isacore.quality.model.Property;

public class ReadSpecificationLaminas {

	public static List<Property> read(XSSFSheet sheet){
		
		System.out.println(">> Name sheet:::" + sheet.getSheetName() + ":::");
		
		List<Property> listProperties = new ArrayList<>();

		Iterator<Row> rowIterator = sheet.iterator();

		Row row;

		row = rowIterator.next();
		row = rowIterator.next();
		
		CellType cellType;
		
		while(rowIterator.hasNext()) {
			row = rowIterator.next();

			Cell cell;
			
			Property prop = new Property();
			for(int i = 0; i < 136; i++) {
				switch (i) {
				case 5://CODISA LÍNEA DE PRODUCCIÓN
					cell = row.getCell(i);
					if(!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							System.out.println("<<< " + cell.getNumericCellValue() + " | ");
						}
					}
					break;
					
				case 7://VERSIÓN(Revision)
					cell = row.getCell(i);
					if(!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.STRING) {
							System.out.println("<<< " + cell.getStringCellValue() + " | ");
						}
					}
					break;
					
				case 8://CODISA FAMILIA
					cell = row.getCell(i);
					if(!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							System.out.println("<<< " + cell.getNumericCellValue() + " | ");
						}
					}
					break;
					
				case 9://FAMILIA
					cell = row.getCell(i);
					if(!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.STRING) {
							System.out.println("<<< " + cell.getStringCellValue() + " | ");
						}
					}
					break;
					
				case 10://CÓDIGO SAP
					cell = row.getCell(i);
					if(!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.STRING) {
							System.out.println("<<< " + cell.toString() + " | ");
						}
					}
					break;	
					
				case 11://PRODUCTO 
					cell = row.getCell(i);
					if(!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.STRING) {
							System.out.println("<<< " + cell.getStringCellValue() + " | ");
						}
					}
					break;	

				default:
					break;
				}
			}
			
		}
		return null;
	}
	
}
