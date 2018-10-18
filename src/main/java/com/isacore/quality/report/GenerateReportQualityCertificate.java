package com.isacore.quality.report;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.pentaho.reporting.engine.classic.core.DataFactory;
import org.pentaho.reporting.engine.classic.core.MasterReport;
import org.pentaho.reporting.engine.classic.core.modules.misc.datafactory.sql.SQLReportDataFactory;
import org.pentaho.reporting.libraries.resourceloader.Resource;
import org.pentaho.reporting.libraries.resourceloader.ResourceException;
import org.pentaho.reporting.libraries.resourceloader.ResourceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.isacore.util.AbstractReportGenerator;
import com.isacore.util.ReportConnection;

public class GenerateReportQualityCertificate extends AbstractReportGenerator{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private static final String QUERY_NAME = "ReportQuery";
	private Map<String, Object> paramsReport;

	@Override
	public MasterReport getReportDefinition() {
		Path pa = Paths.get("com/isacore/quality/report/prpt/QualityCertificate_6.0.1.0-386.prpt");
		final ClassLoader classloader = this.getClass().getClassLoader();
		
		final URL reportDefinitionURL = classloader.getResource(pa.toString());

		final ResourceManager resourceManager = new ResourceManager();
		Resource directly;
		try {			
			directly = resourceManager.createDirectly(reportDefinitionURL, MasterReport.class);

			MasterReport mr = (MasterReport) directly.getResource();
			mr.setQuery(QUERY_NAME);
			
			logger.info("> ISA::::Method:::getReportDefinition:::::report success");
			return mr;

		} catch (ResourceException e) {
			logger.info("> ISA::::Method:::getReportDefinition");
			return null;
		}
	}

	@Override
	public DataFactory getDataFactory() {
		final SQLReportDataFactory dataFactory = new SQLReportDataFactory(ReportConnection.ConnectionPentaho());
		dataFactory.setQuery(QUERY_NAME, "select \r\n" + 
				"qc.qc_client,qc.qc_order,qc_email,\r\n" + 
				"p.product_name, hh.hcch_sapcode,hh.hcch_norm, hh.hcch_batch, \r\n" + 
				"hd.hccd_prop_type, hd.hccd_norm_name, hd.hccd_prop_name, hd.hccd_prop_unit, hd.hccd_specifications, hd.hccd_test_result, hd.hccd_test_result_view, hd.hccd_pass_test\r\n" + 
				"from dbo.product p\r\n" + 
				"inner join dbo.hcchead hh on hh.product_id = p.product_id\r\n" + 
				"inner join dbo.hccdetail hd on hd.hcch_sapcode = hh.hcch_sapcode\r\n" + 
				"inner join dbo.quality_certificate qc on qc.qc_sap_code = hh.hcch_sapcode\r\n" + 
				"where hh.hcch_sapcode = ${HccSapCode};");

		return dataFactory;
	}

	@Override
	public Map<String, Object> getReportParameters() {
		// TODO Auto-generated method stub
		return this.paramsReport;
	}
	
	public void setIdHccSapCode(String hccSapCode) {
		this.paramsReport = new HashMap<String, Object>();
		this.paramsReport.put("HccSapCode",hccSapCode);
	}

}