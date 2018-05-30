package com.isacore.quality.tx;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.isacore.quality.model.Product;
import com.isacore.quality.model.QualityCertificate;
import com.isacore.quality.report.GenerateReportQuality;
import com.isacore.quality.service.IQualityCertificateService;
import com.isacore.util.Crypto;
import com.isacore.util.WebRequestIsa;
import com.isacore.util.WebResponseIsa;
import com.isacore.util.WebResponseMessage;

@Component
public class TxGenerateQualityCertificate {

	public static final String Tx_NAME_GenerateQualityCertificate = "GenerateQualityCertificate";
	public static final String Tx_CODE_GenerateQualityCertificate = "TxGQC";
	
	public static final ObjectMapper JSON_MAPPER = new ObjectMapper();
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IQualityCertificateService service;
	
	/**
	 * TX NAME: GenerateQualityCertificate genera un certificado de una hcc en formato PDF
	 * 
	 * @param wri
	 * @return
	 */
	public ResponseEntity<Object> TxGQC(WebRequestIsa wri){
		logger.info("> TX: TxGQC");
		
		WebResponseIsa wrei = new WebResponseIsa();
		wrei.setTransactionName(Tx_NAME_GenerateQualityCertificate);
		wrei.setTransactionCode(Tx_CODE_GenerateQualityCertificate);
		
		if (wri.getParameters().isEmpty() || wri.getParameters() == null) {
			logger.info("> Objeto vacío");
			wrei.setMessage(WebResponseMessage.WITHOUT_PARAMS);
			wrei.setStatus(WebResponseMessage.STATUS_ERROR);
			return new ResponseEntity<Object>(wrei, HttpStatus.NOT_ACCEPTABLE);
		}else {
			String jsonValue = Crypto.decrypt(wri.getParameters());
			if (jsonValue.equals(Crypto.ERROR)) {
				logger.error("> error al desencryptar");
				wrei.setMessage(WebResponseMessage.ERROR_DECRYPT);
				wrei.setStatus(WebResponseMessage.STATUS_ERROR);
				return new ResponseEntity<Object>(wrei,HttpStatus.INTERNAL_SERVER_ERROR);
			}else {
				try {
					logger.info("> mapeando json a la clase: " + QualityCertificate.class);
					QualityCertificate qc = JSON_MAPPER.readValue(jsonValue, QualityCertificate.class);
					logger.info("> objeto a guardar: " + qc.toString());
					QualityCertificate qqc = this.service.create(qc);
					
					String json = JSON_MAPPER.writeValueAsString(qqc);
					String jsonCryp = Crypto.encrypt(json);
					
					if(jsonCryp.equals(Crypto.ERROR)) {
						logger.error("> error al encryptar");
						wrei.setMessage(WebResponseMessage.ERROR_ENCRYPT);
						wrei.setStatus(WebResponseMessage.STATUS_ERROR);
						return new ResponseEntity<Object>(wrei, HttpStatus.INTERNAL_SERVER_ERROR);
					}else {
						
						String statusReport = GenerateReportQuality.runReportPentahoQualityCertificate(qqc.getHccSapCode());
						
						if(statusReport.equals(GenerateReportQuality.REPORT_SUCCESS)) {
							logger.info(">> Reporte generado correctamente");
							wrei.setMessage("Certificado de calidad::" + qqc.getOrder() + "::: creado satisfactoriamente");
							wrei.setStatus(WebResponseMessage.STATUS_OK);
							return new ResponseEntity<Object>(wrei, HttpStatus.OK);
						}else {
							logger.info(">> No se ha podido generar el reporte");
							wrei.setMessage("No se pudo Generar el certificado de calidad::" + qqc.getOrder());
							wrei.setStatus(WebResponseMessage.STATUS_ERROR);
							return new ResponseEntity<Object>(wrei, HttpStatus.INTERNAL_SERVER_ERROR);
						}
					}
				}catch (IOException e) {
					logger.error("> No se ha podido serializar el JSON a la clase: " + Product.class);					
					wrei.setMessage(WebResponseMessage.ERROR_TO_JSON);
					wrei.setStatus(WebResponseMessage.STATUS_ERROR);
					return new ResponseEntity<>(wri,HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
		}
	}
}
