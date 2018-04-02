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

public class GenerateReportPentahoHccPT extends AbstractReportGenerator {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private static final String QUERY_NAME = "ReportQuery";
	private Map<String, Object> paramsReport;

	@Override
	public MasterReport getReportDefinition() {

		Path pa = Paths.get("com/isacore/quality/report/prpt/HCC_PT_6.0.1.0-386.prpt");
		final ClassLoader classloader = this.getClass().getClassLoader();
		
		System.out.println("::::::::: " + classloader.toString());
		
		final URL reportDefinitionURL = classloader.getResource(pa.toString());

		final ResourceManager resourceManager = new ResourceManager();
		Resource directly;
		try {
			
			String pa2 = reportDefinitionURL.toString();
			System.out.println("> ISA::::Method:::getReportDefinition:::" + pa2);
			
			directly = resourceManager.createDirectly(reportDefinitionURL, MasterReport.class);

			MasterReport mr = (MasterReport) directly.getResource();
			mr.setQuery(QUERY_NAME);
			return mr;

		} catch (ResourceException e) {
			logger.info("> ISA::::Method:::getReportDefinition");
			return null;
		}
	}

	@Override
	public DataFactory getDataFactory() {

		final SQLReportDataFactory dataFactory = new SQLReportDataFactory(ReportConnection.ConnectionPentaho());
		dataFactory.setQuery(QUERY_NAME, "select p.product_name, \r\n"
				+ "hh.hcch_sapcode, hh.hcch_analysis, hh.hcch_code, hh.hcch_comment, hh.hcch_date, hh.hcch_norm, hh.hcch_batch, \r\n"
				+ "hh.hcch_job, hh.hcch_of, hh.hcch_periodicity, hh.hcch_reference, hh.hcch_review, hh.hcch_u_name, hh.hcch_work_area,\r\n"
				+ "hd.hccd_prop_type, hd.hccd_norm_name, hd.hccd_prop_name, hd.hccd_prop_unit, hd.hccd_specifications, hd.hccd_test_result, hd.hccd_test_result_view, hd.hccd_pass_test\r\n"
				+ "from dbo.product p\r\n" + "inner join dbo.hcchead hh on hh.product_id = p.product_id\r\n"
				+ "inner join dbo.hccdetail hd on hd.hcch_sapcode = hh.hcch_sapcode\r\n"
				+ "where hh.hcch_sapcode = ${CodeHCC}");

		return dataFactory;
	}

	@Override
	public Map<String, Object> getReportParameters() {
		return this.paramsReport;
	}

	public void setIdHcc(String idHCC) {
		this.paramsReport = new HashMap<String, Object>();
		this.paramsReport.put("CodeHCC", idHCC);
	}

}
