package com.isacore.quality.read.specification.pt;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.isacore.quality.model.Family;
import com.isacore.quality.model.Feature;
import com.isacore.quality.model.LineProduction;
import com.isacore.quality.model.Product;
import com.isacore.quality.model.Property;
import com.isacore.util.PropertyText;

public class ReadSpecificationLaminas {

	public static List<Product> read(XSSFSheet sheet) {

		System.out.println(">> Name sheet:::" + sheet.getSheetName() + ":::");

		Iterator<Row> rowIterator = sheet.iterator();

		Row row;

		row = rowIterator.next();
		row = rowIterator.next();

		CellType cellType;

		List<Product> prooducts = new ArrayList<>();

		while (rowIterator.hasNext()) {
			row = rowIterator.next();

			Cell cell;

			Product product = new Product();
			product.setTypeProduct("PT");

			List<Property> properties = new ArrayList<>();

			Property p = new Property();
			for (int i = 4; i < 137; i++) {

				switch (i) {
				case 4:

					LineProduction lp = new LineProduction();

					// CODISA LÍNEA DE PRODUCCIÓN
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							lp.setIdLineP((int) cell.getNumericCellValue());
							System.out.println(i + "<<< " + lp.getIdLineP() + " | ");
						}
					}

					// LÍNEA DE PRODUCCIÓN
					i++;
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.STRING) {
							lp.setLineName(cell.getStringCellValue());
							System.out.println(i + "<<< " + lp.getLineName() + " | ");
						}
					}

					product.setLineProduction(lp);

					break;

				case 6:// VERSIÓN(Revision)

					Feature f = new Feature();
					f.setDateUpdate(LocalDateTime.now());

					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.STRING) {
							f.setReview(cell.getStringCellValue());
							System.out.println(i + "<<< " + f.getReview() + " | ");
						}
					}

					product.setFeature(f);

					break;

				case 7:

					Family family = new Family();

					// CODISA FAMILIA
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							family.setFamilyId((int) cell.getNumericCellValue());
							System.out.println(i + "<<< " + family.getFamilyId() + " | ");
						}
					}

					// FAMILIA
					i++;
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.STRING) {
							family.setFamilyName(cell.getStringCellValue());
							System.out.println(i + "<<< " + family.getFamilyName() + " | ");
						}
					}

					product.setFamily(family);

					break;

				case 10: // TIPO DE MATERIAL
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.STRING) {
							product.getFeature().setMaterialItcdq(cell.getStringCellValue());
							System.out.println(i + "<<< " + product.getFeature().getMaterialItcdq() + " | ");
						}
					}
					break;

				case 11: // GRUPO
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.STRING) {
							product.getFeature().setGroupItcdqCod(cell.getStringCellValue());
							System.out.println(i + "<<< " + product.getFeature().getGroupItcdqCod() + " | ");
						}
					}
					break;

				case 12: // SECUENCIAL
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.STRING) {
							product.getFeature().setNumItcdq(cell.getStringCellValue());
							System.out.println(i + "<<< " + product.getFeature().getNumItcdq() + " | ");
						}
					}
					break;
					
				case 13: // CONCATENAR (itcdq)
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.STRING) {
							product.setItcdq(cell.getStringCellValue());
							System.out.println(i + "<<< " + product.getItcdq() + " | ");
						}
					}
					break;
					
				case 14:// CÓDIGO SAP
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						product.setSapCode(cell.toString());
						System.out.println(i + "<<< " + product.getSapCode() + " | ");
					}
					break;

				case 16:// CODISA PRODUCTO
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							product.setIdProduct((int) cell.getNumericCellValue());
							System.out.println(i + "<<< " + product.getIdProduct() + " | ");
						}
					}
					break;

				case 17:// PRODUCTO
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.STRING) {
							product.setNameProduct(cell.getStringCellValue());
							System.out.println(i + "<<< " + product.getNameProduct() + " | ");
						}
					}
					break;

				case 18:// CODISA TIPO DE FORMULA
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							System.out.println(i + "<<< " + cell.getNumericCellValue() + " | ");
						}
					}
					break;

				// INICIO DE LAS
				// ESPECIFICACIONES*********************************************************

				case 20:// ------PROP_11---------

					p = new Property();
					p.setPropertyListId(PropertyText.PROPERTY_11);
					p.setTypeProperty(PropertyText.PROP_TECHNICAL);
					// LONGITUD MIN, M
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							p.setMinProperty(cell.getNumericCellValue());
							System.out.println(i + "<<< " + p.getMinProperty() + " | ");
						}
					}

					// LONGITUD MÁX. M
					i++;
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							p.setMaxProperty(cell.getNumericCellValue());
							System.out.println(i + "<<< " + p.getMaxProperty() + " | ");
						}
					}

					i++;

					p.setUnitProperty("m");
					properties.add(p);
					break;

				case 23:// ------PROP_6---------

					p = new Property();
					p.setPropertyListId(PropertyText.PROPERTY_6);
					p.setTypeProperty(PropertyText.PROP_TECHNICAL);
					// DENSIDAD MIN., g/mL
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							p.setMinProperty(cell.getNumericCellValue());
							System.out.println(i + "<<< " + p.getMinProperty() + " | ");
						}
					}

					// DENSIDAD MÁX., g/mL
					i++;
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							p.setMaxProperty(cell.getNumericCellValue());
							System.out.println(i + "<<< " + p.getMaxProperty() + " | ");
						}
					}

					// UNIDAD DE DENSIDAD, g/mL
					i++;
					i++;
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.STRING) {
							p.setUnitProperty(cell.getStringCellValue());
							System.out.println(i + "<<< " + p.getUnitProperty() + " | ");
						}
					}

					properties.add(p);
					break;

				case 27:// ------PROP_63---------

					p = new Property();
					p.setPropertyListId(PropertyText.PROPERTY_63);
					p.setTypeProperty(PropertyText.PROP_TECHNICAL);
					// VOLUMEN, m3
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							p.setMinProperty(cell.getNumericCellValue());
							System.out.println(i + "<<< " + p.getMinProperty() + " | ");
						}
					}

					properties.add(p);
					break;

				case 28:// ------PROP_1---------

					p = new Property();
					p.setPropertyListId(PropertyText.PROPERTY_1);
					p.setTypeProperty(PropertyText.PROP_TECHNICAL);
					// Punto de reblandecimiento MIN
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							p.setMinProperty(cell.getNumericCellValue());
							System.out.println(i + "<<< " + p.getMinProperty() + " | ");
						}
					}

					// Punto de reblandecimiento MAX
					i++;
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							p.setMaxProperty(cell.getNumericCellValue());
							System.out.println(i + "<<< " + p.getMaxProperty() + " | ");
						}
					}

					// Unidad Punto de reblandecimiento
					i++;
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.STRING) {
							p.setUnitProperty(cell.getStringCellValue());
							System.out.println(i + "<<< " + p.getUnitProperty() + " | ");
						}
					}

					properties.add(p);
					break;

				case 31:// ------PROP_13---------

					p = new Property();
					p.setPropertyListId(PropertyText.PROPERTY_13);
					p.setTypeProperty(PropertyText.PROP_TECHNICAL);
					// Peso Rollo MIN
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							p.setMinProperty(cell.getNumericCellValue());
							System.out.println(i + "<<< " + p.getMinProperty() + " | ");
						}
					}

					// Peso Rollo MAX
					i++;
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							p.setMaxProperty(cell.getNumericCellValue());
							System.out.println(i + "<<< " + p.getMaxProperty() + " | ");
						}
					}

					// Unidad de Peso
					i++;
					i++;
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.STRING) {
							p.setUnitProperty(cell.getStringCellValue());
							System.out.println("<<< " + p.getUnitProperty() + " | ");
						}
					}
					properties.add(p);
					break;

				case 35:// ------PROP_12---------

					p = new Property();
					p.setPropertyListId(PropertyText.PROPERTY_12);
					p.setTypeProperty(PropertyText.PROP_TECHNICAL);
					// Espesor MIN
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							p.setMinProperty(cell.getNumericCellValue());
							System.out.println("<<< " + p.getMinProperty() + " | ");
						}
					}

					// Espesor MAX
					i++;
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							p.setMaxProperty(cell.getNumericCellValue());
							System.out.println("<<< " + p.getMaxProperty() + " | ");
						}
					}

					// Unidad Espesor
					i++;
					i++;
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.STRING) {
							p.setUnitProperty(cell.getStringCellValue());
							System.out.println("<<< " + p.getUnitProperty() + " | ");
						}
					}
					properties.add(p);
					break;

				case 39:// ------PROP_14---------

					p = new Property();
					p.setPropertyListId(PropertyText.PROPERTY_14);
					p.setTypeProperty(PropertyText.PROP_TECHNICAL);
					// Peso por área MIN
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							p.setMinProperty(cell.getNumericCellValue());
							System.out.println("<<< " + p.getMinProperty() + " | ");
						}
					}

					// Peso por área MAX
					i++;
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							p.setMaxProperty(cell.getNumericCellValue());
							System.out.println("<<< " + p.getMaxProperty() + " | ");
						}
					}

					// Unidad Peso/área
					i++;
					i++;
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.STRING) {
							p.setUnitProperty(cell.getStringCellValue());
							System.out.println("<<< " + p.getUnitProperty() + " | ");
						}
					}
					properties.add(p);
					break;

				case 43:// ------PROP_64---------

					p = new Property();
					p.setPropertyListId(PropertyText.PROPERTY_64);
					p.setTypeProperty(PropertyText.PROP_TECHNICAL);
					// Peso /área mastico min.
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							p.setMinProperty(cell.getNumericCellValue());
							System.out.println("<<< " + p.getMinProperty() + " | ");
						}
					}

					// Peso /área mastico MAX
					i++;
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							p.setMaxProperty(cell.getNumericCellValue());
							System.out.println("<<< " + p.getMaxProperty() + " | ");
						}
					}

					// Unidad Peso/área mástico
					i++;
					i++;
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.STRING) {
							p.setUnitProperty(cell.getStringCellValue());
							System.out.println("<<< " + p.getUnitProperty() + " | ");
						}
					}

					properties.add(p);
					break;

				case 47:// ------PROP_2---------

					p = new Property();
					p.setPropertyListId(PropertyText.PROPERTY_2);
					p.setTypeProperty(PropertyText.PROP_TECHNICAL);
					// Penetración MIN
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							p.setMinProperty(cell.getNumericCellValue());
							System.out.println("<<< " + p.getMinProperty() + " | ");
						}
					}

					// Penetración MAX
					i++;
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							p.setMaxProperty(cell.getNumericCellValue());
							System.out.println("<<< " + p.getMaxProperty() + " | ");
						}
					}

					// Unidad Penteración
					i++;
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.STRING) {
							p.setUnitProperty(cell.getStringCellValue());
							System.out.println("<<< " + p.getUnitProperty() + " | ");
						}
					}

					properties.add(p);
					break;

				case 50:// ------PROP_3---------

					p = new Property();
					p.setPropertyListId(PropertyText.PROPERTY_3);
					p.setTypeProperty(PropertyText.PROP_TECHNICAL);
					// Indice de Penteración MIN
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							p.setMinProperty(cell.getNumericCellValue());
							System.out.println("<<< " + p.getMinProperty() + " | ");
						}
					}

					// Indice de Penteración MAX
					i++;
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							p.setMaxProperty(cell.getNumericCellValue());
							System.out.println("<<< " + p.getMaxProperty() + " | ");
						}
					}

					// Unidad Indice Penteración
					i++;
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.STRING) {
							p.setUnitProperty(cell.getStringCellValue());
							System.out.println("<<< " + p.getUnitProperty() + " | ");
						}
					}

					properties.add(p);
					break;

				case 53:// ------PROP_65---------

					p = new Property();
					p.setPropertyListId(PropertyText.PROPERTY_65);
					p.setTypeProperty(PropertyText.PROP_VISUAL);
					// Defectos Visibles
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.STRING) {
							p.setViewProperty(cell.getStringCellValue());
							System.out.println("<<< " + p.getViewProperty() + " | ");
						}
					}

					properties.add(p);
					break;

				case 54:// ------PROP_4---------

					p = new Property();
					p.setPropertyListId(PropertyText.PROPERTY_4);
					p.setTypeProperty(PropertyText.PROP_TECHNICAL);
					// Perdida por Calentamiento MIN (Mástico)
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							p.setMinProperty(cell.getNumericCellValue());
							System.out.println("<<< " + p.getMinProperty() + " | ");
						}
					}

					// Perdida por Calentamiento MÁX (Mástico)
					i++;
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							p.setMaxProperty(cell.getNumericCellValue());
							System.out.println("<<< " + p.getMaxProperty() + " | ");
						}
					}

					// Unidad Perdida por Calentamiento (Mástico)
					i++;
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.STRING) {
							p.setUnitProperty(cell.getStringCellValue());
							System.out.println("<<< " + p.getUnitProperty() + " | ");
						}
					}
					break;

				case 57:// ------PROP_5---------

					p = new Property();
					p.setPropertyListId(PropertyText.PROPERTY_5);
					p.setTypeProperty(PropertyText.PROP_TECHNICAL);
					// Contenido de cenizas MIN
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							p.setMinProperty(cell.getNumericCellValue());
							System.out.println("<<< " + p.getMinProperty() + " | ");
						}
					}

					// Contenido de cenizas MAX
					i++;
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							p.setMaxProperty(cell.getNumericCellValue());
							System.out.println("<<< " + p.getMaxProperty() + " | ");
						}
					}

					// Unidad Contenido de Cenizas
					i++;
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.STRING) {
							p.setUnitProperty(cell.getStringCellValue());
							System.out.println("<<< " + p.getUnitProperty() + " | ");
						}
					}

					properties.add(p);
					break;

				case 60:// ------PROP_7---------

					p = new Property();
					p.setPropertyListId(PropertyText.PROPERTY_7);
					p.setTypeProperty(PropertyText.PROP_TECHNICAL);
					// Deformación remanente por tracción (comportamiento estático), %, MIN
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							p.setMinProperty(cell.getNumericCellValue());
							System.out.println("<<< " + p.getMinProperty() + " | ");
						}
					}

					// Deformación remanente por tracción (comportamiento estático), %, MÁX
					i++;
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							p.setMaxProperty(cell.getNumericCellValue());
							System.out.println("<<< " + p.getMaxProperty() + " | ");
						}
					}

					// Unidad Deformación remanente por tracción (comportamiento estático)
					i++;
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.STRING) {
							p.setUnitProperty(cell.getStringCellValue());
							System.out.println("<<< " + p.getUnitProperty() + " | ");
						}
					}

					properties.add(p);
					break;

				case 63:// ------PROP_8---------

					p = new Property();
					p.setPropertyListId(PropertyText.PROPERTY_8);
					p.setTypeProperty(PropertyText.PROP_VISUAL);
					// Plegabilidad a bajas temperaturas
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.STRING) {
							p.setViewProperty(cell.getStringCellValue());
							System.out.println("<<< " + p.getViewProperty() + " | ");
						}
					}

					properties.add(p);
					break;

				case 64:// ------PROP_9---------

					p = new Property();
					p.setPropertyListId(PropertyText.PROPERTY_9);
					p.setTypeProperty(PropertyText.PROP_TECHNICAL);
					// Rectitud MIN
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							p.setMinProperty(cell.getNumericCellValue());
							System.out.println("<<< " + p.getMinProperty() + " | ");
						}
					}

					// Rectitud MÁX
					i++;
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							p.setMaxProperty(cell.getNumericCellValue());
							System.out.println("<<< " + p.getMaxProperty() + " | ");
						}
					}

					// Unidad Rectitud
					i++;
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.STRING) {
							p.setUnitProperty(cell.getStringCellValue());
							System.out.println("<<< " + p.getUnitProperty() + " | ");
						}
					}

					properties.add(p);
					break;

				case 67:// ------PROP_10---------

					p = new Property();
					p.setPropertyListId(PropertyText.PROPERTY_10);
					p.setTypeProperty(PropertyText.PROP_TECHNICAL);
					// Ancho MIN
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							p.setMinProperty(cell.getNumericCellValue());
							System.out.println("<<< " + p.getMinProperty() + " | ");
						}
					}

					// Ancho MÁX
					i++;
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							p.setMaxProperty(cell.getNumericCellValue());
							System.out.println("<<< " + p.getMaxProperty() + " | ");
						}
					}

					// Unidad Ancho
					i++;
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.STRING) {
							p.setUnitProperty(cell.getStringCellValue());
							System.out.println("<<< " + p.getUnitProperty() + " | ");
						}
					}

					properties.add(p);
					break;

				case 70:// ------PROP_11---------

					p = new Property();
					p.setPropertyListId(PropertyText.PROPERTY_11);
					p.setTypeProperty(PropertyText.PROP_TECHNICAL);
					// Longitud, (m) MIN
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							p.setMinProperty(cell.getNumericCellValue());
							System.out.println("<<< " + p.getMinProperty() + " | ");
						}
					}

					// Longitud, (m) MÁX
					i++;
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							p.setMaxProperty(cell.getNumericCellValue());
							System.out.println("<<< " + p.getMaxProperty() + " | ");
						}
					}

					// Unidad Longitud, (m)
					i++;
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.STRING) {
							p.setUnitProperty(cell.getStringCellValue());
							System.out.println("<<< " + p.getUnitProperty() + " | ");
						}
					}

					properties.add(p);
					break;

				case 73:// ------PROP_15---------

					p = new Property();
					p.setPropertyListId(PropertyText.PROPERTY_15);
					p.setTypeProperty(PropertyText.PROP_VISUAL);
					// Flexibilidad a 0°C
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.STRING) {
							p.setViewProperty(cell.getStringCellValue());
							System.out.println("<<< " + p.getViewProperty() + " | ");
						}
					}

					properties.add(p);
					break;

				case 74:// ------PROP_16---------

					p = new Property();
					p.setPropertyListId(PropertyText.PROPERTY_16);
					p.setTypeProperty(PropertyText.PROP_TECHNICAL);
					// Perdida por Calentamiento (Lámina), MIN
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							p.setMinProperty(cell.getNumericCellValue());
							System.out.println("<<< " + p.getMinProperty() + " | ");
						}
					}

					// Perdida por Calentamiento (Lámina), MÁX
					i++;
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							p.setMaxProperty(cell.getNumericCellValue());
							System.out.println("<<< " + p.getMaxProperty() + " | ");
						}
					}

					// Unidad Perdida por Calentamiento Lámina
					i++;
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.STRING) {
							p.setUnitProperty(cell.getStringCellValue());
							System.out.println("<<< " + p.getUnitProperty() + " | ");
						}
					}

					properties.add(p);
					break;

				case 77:// ------PROP_17---------

					p = new Property();
					p.setPropertyListId(PropertyText.PROPERTY_17);
					p.setTypeProperty(PropertyText.PROP_TECHNICAL);
					// Resistencia a la Fluencia a elevadas temperaturas MIN
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							p.setMinProperty(cell.getNumericCellValue());
							System.out.println("<<< " + p.getMinProperty() + " | ");
						}
					}

					// Resistencia a la Fluencia a elevadas temperaturas MÁX
					i++;
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							p.setMaxProperty(cell.getNumericCellValue());
							System.out.println("<<< " + p.getMaxProperty() + " | ");
						}
					}

					// Unidad Resistencia a la Fluencia a elevadas temperaturas
					i++;
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.STRING) {
							p.setUnitProperty(cell.getStringCellValue());
							System.out.println("<<< " + p.getUnitProperty() + " | ");
						}
					}

					properties.add(p);
					break;

				case 80:// ------PROP_18---------

					p = new Property();
					p.setPropertyListId(PropertyText.PROPERTY_18);
					p.setTypeProperty(PropertyText.PROP_TECHNICAL);
					// Estabilidad Dimensional MIN
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							p.setMinProperty(cell.getNumericCellValue());
							System.out.println("<<< " + p.getMinProperty() + " | ");
						}
					}

					// Estabilidad Dimensional MÁX
					i++;
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							p.setMaxProperty(cell.getNumericCellValue());
							System.out.println("<<< " + p.getMaxProperty() + " | ");
						}
					}

					// Unidad Estabilidad Dimensional
					i++;
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.STRING) {
							p.setUnitProperty(cell.getStringCellValue());
							System.out.println("<<< " + p.getUnitProperty() + " | ");
						}
					}

					properties.add(p);
					break;

				case 83:// ------PROP_19---------

					p = new Property();
					p.setPropertyListId(PropertyText.PROPERTY_19);
					p.setTypeProperty(PropertyText.PROP_TECHNICAL);
					// Resistencia a Tracción Transversal MIN
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							p.setMinProperty(cell.getNumericCellValue());
							System.out.println("<<< " + p.getMinProperty() + " | ");
						}
					}

					// EResistencia a Tracción Transversal MÁX
					i++;
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							p.setMaxProperty(cell.getNumericCellValue());
							System.out.println("<<< " + p.getMaxProperty() + " | ");
						}
					}

					// Unidad Resistencia a Tracción
					cell = row.getCell(87);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.STRING) {
							p.setUnitProperty(cell.getStringCellValue());
							System.out.println("<<< " + p.getUnitProperty() + " | ");
						}
					}

					properties.add(p);
					break;

				case 85:// ------PROP_20---------

					p = new Property();
					p.setPropertyListId(PropertyText.PROPERTY_20);
					p.setTypeProperty(PropertyText.PROP_TECHNICAL);
					// Resistencia a Tracción Longitudinal MIN
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							p.setMinProperty(cell.getNumericCellValue());
							System.out.println("<<< " + p.getMinProperty() + " | ");
						}
					}

					// Resistencia a Tracción Longitudinal MÁX
					i++;
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							p.setMaxProperty(cell.getNumericCellValue());
							System.out.println("<<< " + p.getMaxProperty() + " | ");
						}
					}

					// Unidad Resistencia a Tracción
					i++;
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.STRING) {
							p.setUnitProperty(cell.getStringCellValue());
							System.out.println("<<< " + p.getUnitProperty() + " | ");
						}
					}

					properties.add(p);
					break;

				case 88:// ------PROP_22---------

					p = new Property();
					p.setPropertyListId(PropertyText.PROPERTY_22);
					p.setTypeProperty(PropertyText.PROP_TECHNICAL);
					// Adhesividad MIN
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							p.setMinProperty(cell.getNumericCellValue());
							System.out.println("<<< " + p.getMinProperty() + " | ");
						}
					}

					// Adhesividad MÁX
					i++;
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							p.setMaxProperty(cell.getNumericCellValue());
							System.out.println("<<< " + p.getMaxProperty() + " | ");
						}
					}

					// Unidad Adhesividad
					i++;
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.STRING) {
							p.setUnitProperty(cell.getStringCellValue());
							System.out.println("<<< " + p.getUnitProperty() + " | ");
						}
					}

					properties.add(p);
					break;

				case 91:// ------PROP_23---------

					p = new Property();
					p.setPropertyListId(PropertyText.PROPERTY_23);
					p.setTypeProperty(PropertyText.PROP_TECHNICAL);
					// Resistencia Cizalla Longitudinal MIN
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							p.setMinProperty(cell.getNumericCellValue());
							System.out.println("<<< " + p.getMinProperty() + " | ");
						}
					}

					// Resistencia Cizalla Longitudinal MÁX
					i++;
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							p.setMaxProperty(cell.getNumericCellValue());
							System.out.println("<<< " + p.getMaxProperty() + " | ");
						}
					}

					// Unidad Resistencia Cizalla
					cell = row.getCell(95);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.STRING) {
							p.setUnitProperty(cell.getStringCellValue());
							System.out.println("<<< " + p.getUnitProperty() + " | ");
						}
					}

					properties.add(p);
					break;

				case 93:// ------PROP_24---------

					p = new Property();
					p.setPropertyListId(PropertyText.PROPERTY_24);
					p.setTypeProperty(PropertyText.PROP_TECHNICAL);
					// Resistencia Cizalla Transversal MIN
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							p.setMinProperty(cell.getNumericCellValue());
							System.out.println("<<< " + p.getMinProperty() + " | ");
						}
					}

					// Resistencia Cizalla Transversal MÁX
					i++;
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							p.setMaxProperty(cell.getNumericCellValue());
							System.out.println("<<< " + p.getMaxProperty() + " | ");
						}
					}

					// Unidad Resistencia Cizalla
					i++;
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.STRING) {
							p.setUnitProperty(cell.getStringCellValue());
							System.out.println("<<< " + p.getUnitProperty() + " | ");
						}
					}
					break;

				case 96:// ------PROP_25---------

					p = new Property();
					p.setPropertyListId(PropertyText.PROPERTY_25);
					p.setTypeProperty(PropertyText.PROP_TECHNICAL);
					// Sujeción de Gránulos MIN
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							p.setMinProperty(cell.getNumericCellValue());
							System.out.println("<<< " + p.getMinProperty() + " | ");
						}
					}

					// Sujeción de Gránulos MÁX
					i++;
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							p.setMaxProperty(cell.getNumericCellValue());
							System.out.println("<<< " + p.getMaxProperty() + " | ");
						}
					}

					// Unidad Sujeción de Gránulos
					i++;
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.STRING) {
							p.setUnitProperty(cell.getStringCellValue());
							System.out.println("<<< " + p.getUnitProperty() + " | ");
						}
					}

					properties.add(p);
					break;

				case 99:// ------PROP_26---------

					p = new Property();
					p.setPropertyListId(PropertyText.PROPERTY_26);
					p.setTypeProperty(PropertyText.PROP_TECHNICAL);
					// Alargamiento a la rotuta Transversal MIN
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							p.setMinProperty(cell.getNumericCellValue());
							System.out.println("<<< " + p.getMinProperty() + " | ");
						}
					}

					// Alargamiento a la rotuta Transversal MÁX
					i++;
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							p.setMaxProperty(cell.getNumericCellValue());
							System.out.println("<<< " + p.getMaxProperty() + " | ");
						}
					}

					// Unidad Alargamiento a la Rotura
					cell = row.getCell(102);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.STRING) {
							p.setUnitProperty(cell.getStringCellValue());
							System.out.println("<<< " + p.getUnitProperty() + " | ");
						}
					}

					properties.add(p);
					break;

				case 101:// ------PROP_27---------

					p = new Property();
					p.setPropertyListId(PropertyText.PROPERTY_27);
					p.setTypeProperty(PropertyText.PROP_TECHNICAL);
					// Alargamiento a la Rotura Longitudinal MIN
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							p.setMinProperty(cell.getNumericCellValue());
							System.out.println("<<< " + p.getMinProperty() + " | ");
						}
					}

					// Alargamiento a la Rotura Longitudinal MÁX
					i++;
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							p.setMaxProperty(cell.getNumericCellValue());
							System.out.println("<<< " + p.getMaxProperty() + " | ");
						}
					}

					// Unidad Alargamiento a la Rotura
					i++;
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.STRING) {
							p.setUnitProperty(cell.getStringCellValue());
							System.out.println("<<< " + p.getUnitProperty() + " | ");
						}
					}

					properties.add(p);
					break;

				case 104:// ------PROP_28---------

					p = new Property();
					p.setPropertyListId(PropertyText.PROPERTY_28);
					p.setTypeProperty(PropertyText.PROP_VISUAL);
					// Estanquidad al agua
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.STRING) {
							p.setViewProperty(cell.getStringCellValue());
							System.out.println("<<< " + p.getViewProperty() + " | ");
						}
					}

					properties.add(p);
					break;

				case 105:// ------PROP_29---------

					p = new Property();
					p.setPropertyListId(PropertyText.PROPERTY_29);
					p.setTypeProperty(PropertyText.PROP_VISUAL);
					// Comportamiento frente al fuego
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.STRING) {
							p.setViewProperty(cell.getStringCellValue());
							System.out.println("<<< " + p.getViewProperty() + " | ");
						}
					}

					properties.add(p);
					break;

				case 106:// ------PROP_30---------

					p = new Property();
					p.setPropertyListId(PropertyText.PROPERTY_30);
					p.setTypeProperty(PropertyText.PROP_VISUAL);
					// Reacción al fuego
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.STRING) {
							p.setViewProperty(cell.getStringCellValue());
							System.out.println("<<< " + p.getViewProperty() + " | ");
						}
					}

					properties.add(p);
					break;

				case 107:// ------PROP_31---------

					p = new Property();
					p.setPropertyListId(PropertyText.PROPERTY_31);
					p.setTypeProperty(PropertyText.PROP_VISUAL);
					// Estanquidad tras alargamiento a bajas temperaturas
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.STRING) {
							p.setViewProperty(cell.getStringCellValue());
							System.out.println("<<< " + p.getViewProperty() + " | ");
						}
					}

					properties.add(p);
					break;

				case 108:// ------PROP_32---------

					p = new Property();
					p.setPropertyListId(PropertyText.PROPERTY_32);
					p.setTypeProperty(PropertyText.PROP_TECHNICAL);
					// Resistencia al pelado del solape MIN
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							p.setMinProperty(cell.getNumericCellValue());
							System.out.println("<<< " + p.getMinProperty() + " | ");
						}
					}

					// Resistencia al pelado del solape MÁX
					i++;
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							p.setMaxProperty(cell.getNumericCellValue());
							System.out.println("<<< " + p.getMaxProperty() + " | ");
						}
					}

					// Unidad Resistencia al pelado del solape
					i++;
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.STRING) {
							p.setUnitProperty(cell.getStringCellValue());
							System.out.println("<<< " + p.getUnitProperty() + " | ");
						}
					}

					properties.add(p);
					break;

				case 111:// ------PROP_33---------

					p = new Property();
					p.setPropertyListId(PropertyText.PROPERTY_33);
					p.setTypeProperty(PropertyText.PROP_VISUAL);
					// Propiedades frente al vapor de agua
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.STRING) {
							p.setViewProperty(cell.getStringCellValue());
							System.out.println("<<< " + p.getUnitProperty() + " | ");
						}
					}

					properties.add(p);
					break;

				case 112:// ------PROP_34---------

					p = new Property();
					p.setPropertyListId(PropertyText.PROPERTY_34);
					p.setTypeProperty(PropertyText.PROP_TECHNICAL);
					// Resistencia al Impacto MIN
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							p.setMinProperty(cell.getNumericCellValue());
							System.out.println("<<< " + p.getMinProperty() + " | ");
						}
					}

					// Resistencia al Impacto MÁX
					i++;
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							p.setMaxProperty(cell.getNumericCellValue());
							System.out.println("<<< " + p.getMaxProperty() + " | ");
						}
					}

					// Unidad Resistencia al Impacto
					i++;
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.STRING) {
							p.setUnitProperty(cell.getStringCellValue());
							System.out.println("<<< " + p.getUnitProperty() + " | ");
						}
					}

					properties.add(p);
					break;

				case 115:// ------PROP_35---------

					p = new Property();
					p.setPropertyListId(PropertyText.PROPERTY_35);
					p.setTypeProperty(PropertyText.PROP_VISUAL);
					// Resistencia a una carga estática
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.STRING) {
							p.setViewProperty(cell.getStringCellValue());
							System.out.println("<<< " + p.getViewProperty() + " | ");
						}
					}

					properties.add(p);
					break;

				case 116:// ------PROP_36---------

					p = new Property();
					p.setPropertyListId(PropertyText.PROPERTY_36);
					p.setTypeProperty(PropertyText.PROP_TECHNICAL);
					// Resistencia al Desgarro (clavo) Longitudinal MIN
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							p.setMinProperty(cell.getNumericCellValue());
							System.out.println("<<< " + p.getMinProperty() + " | ");
						}
					}

					// Resistencia al Desgarro (clavo) Longitudinal MAX
					i++;
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							p.setMaxProperty(cell.getNumericCellValue());
							System.out.println("<<< " + p.getMaxProperty() + " | ");
						}
					}

					// Unidad Resistencia al Desgarro por clavo
					cell = row.getCell(120);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.STRING) {
							p.setUnitProperty(cell.getStringCellValue());
							System.out.println("<<< " + p.getUnitProperty() + " | ");
						}
					}

					properties.add(p);
					break;

				case 118:// ------PROP_37---------

					p = new Property();
					p.setPropertyListId(PropertyText.PROPERTY_37);
					p.setTypeProperty(PropertyText.PROP_TECHNICAL);
					// Resistencia al Desgarro (clavo) Transversal MIN
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							p.setMinProperty(cell.getNumericCellValue());
							System.out.println("<<< " + p.getMinProperty() + " | ");
						}
					}

					// Resistencia al Desgarro (clavo) Transversal MAX
					i++;
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							p.setMaxProperty(cell.getNumericCellValue());
							System.out.println("<<< " + p.getMaxProperty() + " | ");
						}
					}

					// Unidad Resistencia al Desgarro por clavo
					i++;
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.STRING) {
							p.setUnitProperty(cell.getStringCellValue());
							System.out.println("<<< " + p.getUnitProperty() + " | ");
						}
					}

					properties.add(p);
					break;

				case 121:// ------PROP_38---------

					p = new Property();
					p.setPropertyListId(PropertyText.PROPERTY_38);
					p.setTypeProperty(PropertyText.PROP_VISUAL);
					// Resistencia a la penetración de raices
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.STRING) {
							p.setViewProperty(cell.getStringCellValue());
							System.out.println("<<< " + p.getViewProperty() + " | ");
						}
					}

					properties.add(p);
					break;

				case 122:// ------PROP_55---------

					p = new Property();
					p.setPropertyListId(PropertyText.PROPERTY_55);
					p.setTypeProperty(PropertyText.PROP_TECHNICAL);
					// Ductilidad MIN.
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							p.setMinProperty(cell.getNumericCellValue());
							System.out.println("<<< " + p.getMinProperty() + " | ");
						}
					}

					// Ductilidad MAX
					i++;
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							p.setMaxProperty(cell.getNumericCellValue());
							System.out.println("<<< " + p.getMaxProperty() + " | ");
						}
					}

					// Unidad de Ductilidad
					i++;
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.STRING) {
							p.setUnitProperty(cell.getStringCellValue());
							System.out.println("<<< " + p.getUnitProperty() + " | ");
						}
					}

					properties.add(p);
					break;

				case 125:// ------PROP_40---------

					p = new Property();
					p.setPropertyListId(PropertyText.PROPERTY_40);
					p.setTypeProperty(PropertyText.PROP_VISUAL);
					// Flexibilidad a baja temperatura, °C
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.STRING) {
							p.setViewProperty(cell.getStringCellValue());
							System.out.println("<<< " + p.getViewProperty() + " | ");
						}
					}

					properties.add(p);
					break;

				case 126:// ------PROP_66---------

					p = new Property();
					p.setPropertyListId(PropertyText.PROPERTY_66);
					p.setTypeProperty(PropertyText.PROP_TECHNICAL);
					// Punto de Inflamación Min.
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							p.setMinProperty(cell.getNumericCellValue());
							System.out.println("<<< " + p.getMinProperty() + " | ");
						}
					}

					// Punto de Inflamación Max.
					i++;
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							p.setMaxProperty(cell.getNumericCellValue());
							System.out.println("<<< " + p.getMaxProperty() + " | ");
						}
					}

					// Unidad de Punto de Inflamación
					i++;
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.STRING) {
							p.setUnitProperty(cell.getStringCellValue());
							System.out.println("<<< " + p.getUnitProperty() + " | ");
						}
					}

					properties.add(p);
					break;

				case 129:// ------PROP_67---------

					p = new Property();
					p.setPropertyListId(PropertyText.PROPERTY_67);
					p.setTypeProperty(PropertyText.PROP_TECHNICAL);
					// Viscosidad Dinámica 180°C, Spindle SC4-27, RPM 80, Min.
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							p.setMinProperty(cell.getNumericCellValue());
							System.out.println("<<< " + p.getMinProperty() + " | ");
						}
					}

					// Viscosidad Dinámica 180°C, Spindle SC4-27, RPM 80, Max.
					i++;
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							p.setMaxProperty(cell.getNumericCellValue());
							System.out.println("<<< " + p.getMaxProperty() + " | ");
						}
					}

					// Unidad de Viscosidad Dinámica 180°C, Spindle SC4-27, RPM 80
					i++;
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.STRING) {
							p.setUnitProperty(cell.getStringCellValue());
							System.out.println("<<< " + p.getUnitProperty() + " | ");
						}
					}

					properties.add(p);
					break;

				case 132:// ------PROP_68---------

					p = new Property();
					p.setPropertyListId(PropertyText.PROPERTY_68);
					p.setTypeProperty(PropertyText.PROP_VISUAL);
					// Estabilidad de forma bajo cambios ciclicos de temperatura
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.STRING) {
							p.setViewProperty(cell.getStringCellValue());
							System.out.println("<<< " + p.getViewProperty() + " | ");
						}
					}

					properties.add(p);
					break;

				case 133:// ------PROP_69---------

					p = new Property();
					p.setPropertyListId(PropertyText.PROPERTY_69);
					p.setTypeProperty(PropertyText.PROP_VISUAL);
					// Comportamiento al envejecimiento artificial
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.STRING) {
							p.setViewProperty(cell.getStringCellValue());
							System.out.println("<<< " + p.getViewProperty() + " | ");
						}
					}

					properties.add(p);
					break;

				case 134:// ------PROP_62---------

					p = new Property();
					p.setPropertyListId(PropertyText.PROPERTY_62);
					p.setTypeProperty(PropertyText.PROP_TECHNICAL);
					// Fluencia, MIN
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							p.setMinProperty(cell.getNumericCellValue());
							System.out.println("<<< " + p.getMinProperty() + " | ");
						}
					}

					// Fluencia, Máx.
					i++;
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.NUMERIC) {
							p.setMaxProperty(cell.getNumericCellValue());
							System.out.println("<<< " + p.getMaxProperty() + " | ");
						}
					}

					// Unidad Fluencia
					i++;
					cell = row.getCell(i);
					if (!(cell == null)) {
						cellType = cell.getCellTypeEnum();
						if (cellType == CellType.STRING) {
							p.setUnitProperty(cell.getStringCellValue());
							System.out.println("<<< " + p.getUnitProperty() + " | ");
						}
					}

					properties.add(p);
					break;

				default:
					break;
				}
			}

			product.setProperties(properties);
			prooducts.add(product);

		}

		return prooducts;
	}

}
