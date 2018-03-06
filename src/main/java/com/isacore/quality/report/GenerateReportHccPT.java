package com.isacore.quality.report;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import com.isacore.util.ReportConnection;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

public class GenerateReportHccPT {

	public static final String REPORT_SUCCESS = "OK";
	public static final String REPORT_ERROR = "ERROR";
	
	public static String runReport(String idHcc) {
		try {
			
			Path pa = Paths.get("src/main/resources/Reports/HCC_PT/HCC_PT.jasper");
			JasperReport report = (JasperReport) JRLoader.loadObjectFromFile(pa.toString());
			Map<String, Object> param = new HashMap<String, Object>();;
			param.put("CodeHCC", idHcc);
			JasperPrint jp = JasperFillManager.fillReport(report, param,ReportConnection.getConectionISA());
			Path endPath = Paths.get("C:\\CRIMPTEK\\Calidad\\HCC\\PT\\HCC_"+idHcc+".pdf");
			JasperExportManager.exportReportToPdfFile(jp, endPath.toString());
			return REPORT_SUCCESS;
			
		} catch (JRException e) {
			return REPORT_ERROR;
		}
		
	}
	
}
