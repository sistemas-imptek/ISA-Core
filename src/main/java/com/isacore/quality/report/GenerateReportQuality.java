package com.isacore.quality.report;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.pentaho.reporting.engine.classic.core.ReportProcessingException;

import com.isacore.quality.model.Complaint;
import com.isacore.util.AbstractReportGenerator;
import com.isacore.util.ReportConnection;
import com.isacore.util.ReportUtil;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

public class GenerateReportQuality {

	public static final String REPORT_SUCCESS = "OK";
	public static final String REPORT_ERROR = "ERROR";

	public static String runReport(String idHcc) {
		try {

			Path pa = Paths.get("src/main/resources/Reports/HCC_PT/HCC_PT.jasper");
			JasperReport report = (JasperReport) JRLoader.loadObjectFromFile(pa.toString());
			Map<String, Object> param = new HashMap<String, Object>();
			;
			param.put("CodeHCC", idHcc);
			JasperPrint jp = JasperFillManager.fillReport(report, param, ReportConnection.getConectionISA());
			Path endPath = Paths.get("C:\\CRIMPTEK\\Calidad\\HCC\\PT\\HCC_" + idHcc + ".pdf");
			JasperExportManager.exportReportToPdfFile(jp, endPath.toString());
			return REPORT_SUCCESS;

		} catch (JRException e) {
			return REPORT_ERROR;
		}
	}

	public static String runReportPentahoHccPT(String product, String batch, String idHCC, String period) {

		try {
			period = period.equalsIgnoreCase("Diaria") ? "" : period;			
			final File outputFilename = new File("C:/CRIMPTEK/Calidad/HCC/PT/HCC " + product + " " + batch + " " + period + ".pdf") ;
			GenerateReportPentahoHccPT grpPT = new GenerateReportPentahoHccPT();
			grpPT.setIdHcc(idHCC);
			grpPT.generateReport(AbstractReportGenerator.OutputType.PDF, outputFilename);
			
			return REPORT_SUCCESS;
		} catch (ReportProcessingException | IOException e) {
			return REPORT_ERROR;
		}
	}
	
	public static String runReportPentahoHccMP(String product, String batch, String idHCC) {

		try {
			final File outputFilename = new File("C:/CRIMPTEK/Calidad/HCC/MP/HCC " + product + " " + batch + ".pdf");
			GenerateReportPentahoHccMP grpMP = new GenerateReportPentahoHccMP();
			grpMP.setIdHcc(idHCC);
			grpMP.generateReport(AbstractReportGenerator.OutputType.PDF, outputFilename);
			
			return outputFilename.getAbsolutePath();
		} catch (ReportProcessingException | IOException e) {
			return REPORT_ERROR;
		}
	}
	
	public static String runReportPentahoNCP(Integer idNCP) {

		try {
			final File outputFilename = new File("C:/CRIMPTEK/Calidad/PNC/PNC_" + idNCP + ".pdf");
			GenerateReportPentahoPNC grpPNC = new GenerateReportPentahoPNC();
			grpPNC.setIdNCP(idNCP);
			grpPNC.generateReport(AbstractReportGenerator.OutputType.PDF, outputFilename);
			
			return REPORT_SUCCESS;
		} catch (ReportProcessingException | IOException e) {
			return REPORT_ERROR;
		}
	}
	
	public static String runReportPentahoQualityCertificate(String hccSapCode, Integer idCli) {
		try {
			final File outputFilename = new File("C:/CRIMPTEK/Calidad/QualityCertificate/QualityCertificate_" + hccSapCode + ".pdf");
			GenerateReportQualityCertificate grpQC = new GenerateReportQualityCertificate();
			grpQC.setIdHccSapCode(hccSapCode, idCli);
			grpQC.generateReport(AbstractReportGenerator.OutputType.PDF, outputFilename);
			
			return outputFilename.getAbsolutePath();
		} catch (ReportProcessingException | IOException e) {
			e.printStackTrace();
			return REPORT_ERROR;
		}
	}
	
	//Generacion de Reporte Reclamos de Materia Prima.
	public static String runGenerateReportComplaint(Complaint a){
		Map parameters = new HashMap();
		try {
			LocalDateTime fecha1 = LocalDateTime.now();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
			String p= fecha1.format(dtf);
			String nameP=(a.getProduct().getNameProduct()).replaceAll("/", "");
			final File outputFilename = new File("C:/CRIMPTEK/Calidad/ReclamosMP/RMP_" + nameP+"_"+p + ".pdf");
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(Arrays.asList(a));
			String pathReporte = "Complaint.jasper";
			//save pdf
			String ruta = "C:\\CRIMPTEK\\Calidad\\ReportPrpt\\" + pathReporte;
			JasperPrint jasperPrint = JasperFillManager.fillReport(ruta, parameters, beanColDataSource);
			JasperExportManager.exportReportToPdfFile(jasperPrint, outputFilename.getAbsolutePath());
			return outputFilename.getAbsolutePath();
		}catch (JRException e) {
			e.printStackTrace();
			return REPORT_ERROR;
		}
        
    }

}
