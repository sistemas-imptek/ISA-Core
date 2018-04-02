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

public class GenerateReportPentahoPNC extends AbstractReportGenerator{

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private static final String QUERY_NAME = "ReportQuery";
	private Map<String, Object> paramsReport;
	
	@Override
	public MasterReport getReportDefinition() {

		Path pa = Paths.get("com/isacore/quality/report/prpt/PNC03_6.0.1.0-386.prpt");
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
		dataFactory.setQuery(QUERY_NAME, "select ncp.rep_title, ncp.rep_subtitle, ncp.rep_reference, ncp.rep_code, ncp.rep_review, ncp.rep_register, \r\n" + 
				"ncp.ncp_id, p.product_name, ncp.ncp_source, CONCAT(ncp.ncp_nonconforming_amount, ' ', ncp.ncp_unit) as ncp_amountUnit,\r\n" + 
				"ncp.ncp_production_order, ncp.ncp_nonconforming_amount, ncp.ncp_production_date, ncp.ncp_batch, \r\n" + 
				"ncp.ncp_detection_date, a.area_name, round(ncp.ncp_percent_validity*100,2) as ncp_percent_validity,\r\n" + 
				"d.defect_description, ncp.ncp_fivem, o.om_code, t.task_description, round((t.task_percent * 100),2) as task_percent, \r\n" + 
				"ncp.ncp_detail_other, ncp.ncp_final_destination, ncp_additional_remarks, ncp.ncp_u_name, ncp.ncp_job, ncp.ncp_work_area\r\n" + 
				"from dbo.nonconforming_product ncp\r\n" + 
				"inner join dbo.product p on ncp.product_id = p.product_id\r\n" + 
				"inner join dbo.area a on ncp.area_id = a.area_id\r\n" + 
				"inner join dbo.defect d on ncp.defect_id = d.defect_id\r\n" + 
				"inner join dbo.output_method o on ncp.om_id = o.om_id\r\n" + 
				"inner join dbo.task_ncp t on ncp.ncp_id = t.ncp_id\r\n" + 
				"and ncp.ncp_id = ${idNCP}");

		return dataFactory;
	}

	@Override
	public Map<String, Object> getReportParameters() {
		// TODO Auto-generated method stub
		return this.paramsReport;
	}
	
	public void setIdNCP(Integer idNCP) {
		this.paramsReport = new HashMap<String, Object>();
		this.paramsReport.put("idNCO",idNCP);
	}

}
