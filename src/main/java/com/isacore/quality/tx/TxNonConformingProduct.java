package com.isacore.quality.tx;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.isacore.quality.model.NonconformingProduct;
import com.isacore.quality.model.Product;
import com.isacore.quality.service.INonconformingProductService;
import com.isacore.util.Crypto;
import com.isacore.util.WebRequestIsa;
import com.isacore.util.WebResponseIsa;
import com.isacore.util.WebResponseMessage;

@Component
public class TxNonConformingProduct {

	public static final ObjectMapper JSON_MAPPER = new ObjectMapper();

	public static final String TX_NAME_GetAllNCP = "GetAllNCP";
	public static final String TX_CODE_GetAllNCP = "TxQQRgeAlltNCP";
	
	public static final String TX_NAME_GetNCPById = "GetNCPById";
	public static final String TX_CODE_GetNCPById = "TxQQRgetNCPId";
	
	public static final String TX_NAME_SetNCP = "SetNCP";
	public static final String TX_CODE_SetNCP = "TxQQRsetNCP";

	@Autowired
	private INonconformingProductService service;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * TX NAME: TxQQRgetAllNCP Devulve la lista completa de los productos no
	 * conforme almacenados en base de datos
	 * @param wri parámetro de petición de respuesta.
	 * @return wrei respuesta del servidor con la lista de producto no conforme.
	 */
	public ResponseEntity<Object> TxQQRgetAllNCP(WebRequestIsa wri) {

		logger.info("> TX: TxQQRgeAlltNCP");

		WebResponseIsa wrei = new WebResponseIsa();
		wrei.setTransactionName(TX_NAME_GetAllNCP);
		wrei.setTransactionCode(TX_CODE_GetAllNCP);

		List<NonconformingProduct> ncps = this.service.findAll();

		if (ncps.isEmpty() || ncps == null) {
			logger.info("> No existe registros en la base de datos");
			wrei.setMessage(WebResponseMessage.OBJECT_NOT_FOUND);
			return new ResponseEntity<Object>(wrei, HttpStatus.NOT_FOUND);
		} else {
			ncps.forEach((NonconformingProduct x) -> {
				x.getProduct().setPropertyList(null);
				x.getProduct().setFeature(null);
			});

			try {
				// Serializamos la lista a JSON
				String json = JSON_MAPPER.writeValueAsString(ncps);
				// encriptamos el JSON
				String jsonCryp = Crypto.encrypt(json);
				if (jsonCryp.equals(Crypto.ERROR)) {
					logger.error("> error al encriptar");
					wrei.setMessage(WebResponseMessage.ERROR_ENCRYPT);
					return new ResponseEntity<Object>(wrei,HttpStatus.INTERNAL_SERVER_ERROR);
				}else {
					wrei.setMessage(WebResponseMessage.SEARCHING_OK);
					wrei.setStatus(WebResponseMessage.STATUS_OK);
					wrei.setParameters(jsonCryp);
					return new ResponseEntity<Object>(wrei,HttpStatus.OK);
				}
			} catch (IOException e) {
				logger.error("> error al serializar el JSON");
				wrei.setMessage(WebResponseMessage.ERROR_TO_JSON);
				return new ResponseEntity<Object>(wrei,HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}

	/**
	 * TX NAME: TxQQRgetNCPById Devulve un producto no conforme en base a su clave primaria
	 * @param wri parámetro de petición de producto no conforme con su clave primaria.
	 * @return wrei respuesta del servidor el registro de producto no conforme.
	 */
	public ResponseEntity<Object> TxQQRgetNCPById(WebRequestIsa wri){
		logger.info("> TX: TxQQRgetNCPById");
		
		WebResponseIsa wrei = new WebResponseIsa();
		wrei.setTransactionName(TX_NAME_GetNCPById);
		wrei.setTransactionCode(TX_CODE_GetNCPById);
		
		if (wri.getParameters().isEmpty() || wri.getParameters() == null) {
			logger.info("> Objeto vacío");
			wrei.setMessage(WebResponseMessage.WITHOUT_PARAMS);
			return new ResponseEntity<Object>(wrei, HttpStatus.NOT_ACCEPTABLE);
		}else {
			String jsonValue = Crypto.decrypt(wri.getParameters());
			if (jsonValue.equals(Crypto.ERROR)) {
				logger.error("> error al desencryptar");
				wrei.setMessage(WebResponseMessage.ERROR_DECRYPT);
				return new ResponseEntity<Object>(wrei, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				try {
					logger.info("> mapeando json a la clase: " + NonconformingProduct.class);
					ObjectMapper mapper = new ObjectMapper();
					NonconformingProduct ncp = mapper.readValue(jsonValue, NonconformingProduct.class);
					logger.info("> id Nonconforming Product: " + ncp.getIdNCP());
					ncp = this.service.findById(ncp);

					if (ncp == null) {
						logger.info("> Nonconforming Product not found");
						wrei.setMessage(WebResponseMessage.OBJECT_NOT_FOUND);
						return new ResponseEntity<Object>(wrei, HttpStatus.NOT_FOUND);
					} else {
						String json = JSON_MAPPER.writeValueAsString(ncp);
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
					}
				} catch (IOException e) {
					logger.error("> No se ha podido serializar el JSON a la clase: " + NonconformingProduct.class);
					e.printStackTrace();
					wrei.setMessage(WebResponseMessage.ERROR_TO_JSON);
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
			}
		}
	}
	
	
	public ResponseEntity<Object> TxQQRsetNCP(WebRequestIsa wri) {
		logger.info("> TX: TxQQRsetProduct");

		WebResponseIsa wrei = new WebResponseIsa();
		wrei.setTransactionName(TX_NAME_SetNCP);
		wrei.setTransactionCode(TX_CODE_SetNCP);
		
		if (wri.getParameters().isEmpty() || wri.getParameters() == null) {
			logger.info("> Objeto vacío");
			wrei.setMessage(WebResponseMessage.WITHOUT_PARAMS_TO_CREATE_UPDATE);
			return new ResponseEntity<Object>(wrei,HttpStatus.NOT_ACCEPTABLE);
		} else {

			String jsonValue = Crypto.decrypt(wri.getParameters());
			if (jsonValue.equals(Crypto.ERROR)) {
				logger.error("> error al desencryptar");
				wrei.setMessage(WebResponseMessage.ERROR_DECRYPT);
				return new ResponseEntity<Object>(wrei,HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				try {
					logger.info("> mapeando json a la clase: " + NonconformingProduct.class);					
					NonconformingProduct ncp = JSON_MAPPER.readValue(jsonValue, NonconformingProduct.class);
					logger.info("> objeto a guardar: " + ncp.toString());
					NonconformingProduct ncpp = this.service.create(ncp);
					
					String json = JSON_MAPPER.writeValueAsString(ncpp);
					String jsonCryp = Crypto.encrypt(json);
					
					if(jsonCryp.equals(Crypto.ERROR)) {
						logger.error("> error al encryptar");
						wrei.setMessage(WebResponseMessage.ERROR_ENCRYPT);
						return new ResponseEntity<Object>(wrei, HttpStatus.INTERNAL_SERVER_ERROR);
					}else {
						wrei.setMessage(WebResponseMessage.CREATE_UPDATE_OK);
						wrei.setParameters(jsonCryp);
						return new ResponseEntity<Object>(wrei,HttpStatus.OK);
					}
				} catch (IOException e) {
					e.printStackTrace();
					logger.error("> No se ha podido serializar el JSON a la clase: " + NonconformingProduct.class);
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
			}
		}
	}
}
