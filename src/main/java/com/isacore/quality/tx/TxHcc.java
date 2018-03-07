package com.isacore.quality.tx;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.isacore.quality.model.HccDetail;
import com.isacore.quality.model.HccHead;
import com.isacore.quality.model.Product;
import com.isacore.quality.model.Property;
import com.isacore.quality.model.ReportHeadT;
import com.isacore.quality.report.GenerateReportHccPT;
import com.isacore.quality.service.IHccHeadService;
import com.isacore.quality.service.IProductService;
import com.isacore.quality.service.IReportHeadTService;
import com.isacore.util.Crypto;
import com.isacore.util.WebRequestIsa;
import com.isacore.util.WebResponseIsa;
import com.isacore.util.WebResponseMessage;

@Component
public class TxHcc {

	public static final String TX_NAME_GenerateHcc = "GenerateHcc";
	public static final String TX_CODE_GenerateHcc = "TxQQRgenerateHCC";
	
	public static final String TX_NAME_SetHcc = "Create/UpdateHcc";
	public static final String TX_CODE_SetHcc = "TxQQRsetHCC";

	public static final ObjectMapper JSON_MAPPER = new ObjectMapper();
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IReportHeadTService serviceRH;

	@Autowired
	private IProductService serviceProducto;
	
	@Autowired
	private IHccHeadService serviceHccH;

	/**
	 * Transacción Para generar la estructura de la HCC, vincular los datos de los
	 * Test por el número de lote y código de la propiedad.
	 * 
	 * @return
	 */
	public ResponseEntity<Object> TxQQRgenerateHCC(WebRequestIsa wri) {
		logger.info("> TX: TxQQRgenerateHCC");

		WebResponseIsa wrei = new WebResponseIsa();
		wrei.setTransactionName(TX_NAME_GenerateHcc);
		wrei.setTransactionCode(TX_CODE_GenerateHcc);

		if (wri.getParameters().isEmpty() || wri.getParameters() == null) {
			logger.info("> Objeto vacío");
			wrei.setMessage(WebResponseMessage.WITHOUT_PARAMS);
			wrei.setStatus(WebResponseMessage.STATUS_ERROR);
			return new ResponseEntity<Object>(wrei, HttpStatus.NOT_ACCEPTABLE);
		} else {
			String jsonValue = Crypto.decrypt(wri.getParameters());
			if (jsonValue.equals(Crypto.ERROR)) {
				logger.error("> error al desencryptar");
				wrei.setMessage(WebResponseMessage.ERROR_DECRYPT);
				wrei.setStatus(WebResponseMessage.STATUS_ERROR);
				return new ResponseEntity<Object>(wrei, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				try {
					logger.info("> mapeando json a la clase: " + HccHead.class);
					HccHead hh = JSON_MAPPER.readValue(jsonValue, HccHead.class);
					HccHead hhg = getHccHead(hh);
					if (hhg != null) {
						String json = JSON_MAPPER.writeValueAsString(hhg);
						String jsonCryp = Crypto.encrypt(json);

						if (jsonCryp.equals(Crypto.ERROR)) {
							logger.error("> error al encryptar");
							wrei.setMessage(WebResponseMessage.ERROR_ENCRYPT);
							return new ResponseEntity<Object>(wrei, HttpStatus.INTERNAL_SERVER_ERROR);
						} else {
							wrei.setMessage(WebResponseMessage.SEARCHING_OK);
							wrei.setParameters(jsonCryp);
							return new ResponseEntity<Object>(wrei, HttpStatus.OK);
						}

					} else {
						logger.error("> Error al generar la HCC: " + HccHead.class);
						wrei.setMessage(WebResponseMessage.ERROR_GENERATE_HCC);
						wrei.setStatus(WebResponseMessage.STATUS_ERROR);
						return new ResponseEntity<Object>(wrei, HttpStatus.INTERNAL_SERVER_ERROR);
					}
				} catch (IOException e) {
					logger.error("> No se ha podido serializar el JSON a la clase: " + HccHead.class);
					wrei.setMessage(WebResponseMessage.ERROR_TO_JSON);
					wrei.setStatus(WebResponseMessage.STATUS_ERROR);
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
			}
		}
	}
	
	public ResponseEntity<Object> TxQQRSetHCC(WebRequestIsa wri) {
		logger.info("> TX: TxQQRSetHCC");
		
		WebResponseIsa wrei = new WebResponseIsa();
		wrei.setTransactionName(TX_NAME_SetHcc);
		wrei.setTransactionCode(TX_CODE_SetHcc);
		
		if (wri.getParameters().isEmpty() || wri.getParameters() == null) {
			logger.info("> Objeto vacío");
			wrei.setMessage(WebResponseMessage.WITHOUT_PARAMS_TO_CREATE_UPDATE);
			return new ResponseEntity<Object>(wrei,HttpStatus.NOT_ACCEPTABLE);
		}else {
			
			String jsonValue = Crypto.decrypt(wri.getParameters());
			if (jsonValue.equals(Crypto.ERROR)) {
				logger.error("> error al desencryptar");
				wrei.setMessage(WebResponseMessage.ERROR_DECRYPT);
				return new ResponseEntity<Object>(wrei,HttpStatus.INTERNAL_SERVER_ERROR);
			}else {
				try {
					logger.info("> mapeando json a la clase: " + HccHead.class);					
					HccHead hh = JSON_MAPPER.readValue(jsonValue, HccHead.class);
					logger.info("> objeto a guardar: " + hh.toString());
					hh.setDateCreate(LocalDate.now());
					HccHead hcc = this.serviceHccH.create(hh);
					if(hcc != null) {
						logger.info(">> Hcc guardada correctamente");
						wrei.setMessage(WebResponseMessage.CREATE_UPDATE_OK);
						wrei.setStatus(WebResponseMessage.STATUS_OK);
						
						String statusReport = GenerateReportHccPT.runReport(hh.getSapCode());
						if(statusReport.equals(GenerateReportHccPT.REPORT_SUCCESS)) {
							logger.info(">> Reporte generado correctamente");
							return new ResponseEntity<Object>(wrei,HttpStatus.OK);
						}else {
							wrei.setMessage("No se ha podido generar el reporte");
							wrei.setStatus(WebResponseMessage.STATUS_ERROR);
							return new ResponseEntity<Object>(wrei,HttpStatus.INTERNAL_SERVER_ERROR);
						}
					}else {
						logger.error(">> Error al guardar la Hcc");
						wrei.setMessage("Error al guardar la HCC");
						wrei.setStatus(WebResponseMessage.STATUS_ERROR);
						return new ResponseEntity<Object>(wrei, HttpStatus.INTERNAL_SERVER_ERROR);
					}
				}catch (IOException e) {
					logger.error("> No se ha podido serializar el JSON a la clase: " + HccHead.class);
					wrei.setMessage(WebResponseMessage.ERROR_TO_CLASS);
					wrei.setStatus(WebResponseMessage.STATUS_ERROR);
					e.printStackTrace();
					return new ResponseEntity<Object>(wrei,HttpStatus.BAD_REQUEST);
				}
			}
		}
	}

	private HccHead getHccHead(HccHead hh) {
		logger.info(">>> mthod: getHccHead::::");
		Product p = this.serviceProducto.findOnlyProductById(hh.getProduct());
		if (p != null) {
			ReportHeadT rh = this.serviceRH.findHeadByTypeReport(new ReportHeadT("HCCPT"));

			if (rh != null) {
				HccHead hhg = new HccHead();
				hhg.setProduct(p);
				hhg.setHccNorm(rh.getNorm());
				hhg.setCode(rh.getCode());
				hhg.setReview(rh.getReview());
				hhg.setOf(rh.getOf());
				hhg.setHcchBatch(hh.getHcchBatch());
				hhg = gerenateDetailOfHcc(hhg, hh.getProduct());
				return hhg;
			} else {
				logger.info(">>> mthod: getHccHead::::no se ha el reportHeaderTemplate HCCPT");
				return null;
			}
		} else {
			logger.info(">>> mthod: getHccHead::::no se ha encontrado el producto con id "
					+ hh.getProduct().getIdProduct());
			return null;
		}

	}
	
	private HccHead gerenateDetailOfHcc(HccHead hh, Product p) {

		logger.info(">>>> mthod: gerenateDetailOfHcc::::");
		List<HccDetail> detail = new ArrayList<>();
		
		Product pp = this.serviceProducto.findById(hh.getProduct());
		System.out.println(pp.toString());
		for (Property prop : pp.getPropertyList()) {
			HccDetail hd = new HccDetail();
			hd.setNameNorm(prop.getNormProperty());
			hd.setIdProperty(prop.getIdProperty());
			hd.setNameProperty(prop.getNameProperty());
			hd.setTypeProperty(prop.getTypeProperty());
			hd.setUnit(prop.getUnitProperty());
			hd.setMin(prop.getMinProperty());
			hd.setMax(prop.getMaxProperty());
			hd.setView(prop.getViewProperty());
			hd.setViewOnHcc(prop.isViewPropertyOnHcc());
			hd.generateSpecifications();
			detail.add(hd);
		}

		hh.setDetail(detail);
		System.out.println(hh);
		return hh;
	}
	
	private HccHead mergeHccTests(HccHead hh, String batch) {
		
		
		
		
		return null;
	}

}
