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
import com.isacore.quality.model.Product;
import com.isacore.quality.service.IProductService;
import com.isacore.util.Crypto;
import com.isacore.util.WebRequestIsa;
import com.isacore.util.WebResponseIsa;
import com.isacore.util.WebResponseMessage;

@Component
public class TxProduct {

	public static final ObjectMapper JSON_MAPPER = new ObjectMapper();

	public static final String TX_NAME_GetAllProducts = "GetAllProducts";
	public static final String TX_CODE_GetAllProducts = "TxQQRgetProducts";

	public static final String TX_NAME_GetProductById = "GetProductById";
	public static final String TX_CODE_GetProductById = "TxQQRgetProductById";

	@Autowired
	private IProductService service;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * TX NAME: GetAllProducts Obtiene todos los productos de la base de datos
	 * 
	 * @param wri
	 * @return
	 */
	public ResponseEntity<Object> TxQQRgetProducts(WebRequestIsa wri) {
		logger.info("> TX: TxQRgetCommittees");

		WebResponseIsa wrei = new WebResponseIsa();
		wrei.setTransactionName(TX_NAME_GetAllProducts);
		wrei.setTransactionCode(TX_CODE_GetAllProducts);

		List<Product> products = this.service.findAll();

		if (products.isEmpty() || products == null) {
			wrei.setMessage(WebResponseMessage.OBJECT_NOT_FOUND);
			return new ResponseEntity<Object>(wrei, HttpStatus.NOT_FOUND);
		} else {
			try {
				// Serializamos la lista a JSON
				String json = JSON_MAPPER.writeValueAsString(products);
				// encriptamos el JSON
				String jsonCryp = Crypto.encrypt(json);

				if (jsonCryp.equals(Crypto.ERROR)) {
					logger.error("> error al encriptar");
					wrei.setMessage(WebResponseMessage.ERROR_ENCRYPT);
					return new ResponseEntity<Object>(wrei,HttpStatus.INTERNAL_SERVER_ERROR);
				}else {
					wrei.setMessage(WebResponseMessage.SEARCHING_OK);
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
	 * TX NAME: GetProductById Obtiene un producto en base al id solicitado
	 * 
	 * @param wri
	 * @return
	 */
	public ResponseEntity<Object> TxQQRgetProductById(WebRequestIsa wri) {
		logger.info("> TX: TxQQRgetProductById");

		WebResponseIsa wrei = new WebResponseIsa();
		wrei.setTransactionName(TX_NAME_GetProductById);
		wrei.setTransactionCode(TX_CODE_GetProductById);

		if (wri.getParameters().isEmpty() || wri.getParameters() == null) {
			logger.info("> Objeto vacío");
			wrei.setMessage(WebResponseMessage.WITHOUT_PARAMS);
			return new ResponseEntity<Object>(wrei, HttpStatus.NOT_ACCEPTABLE);
		} else {
			String jsonValue = Crypto.decrypt(wri.getParameters());
			if (jsonValue.equals(Crypto.ERROR)) {
				logger.error("> error al desencryptar");
				wrei.setMessage(WebResponseMessage.ERROR_DECRYPT);
				return new ResponseEntity<Object>(wrei, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				try {
					logger.info("> mapeando json a la clase: " + Product.class);
					ObjectMapper mapper = new ObjectMapper();
					Product p = mapper.readValue(jsonValue, Product.class);
					logger.info("> id Product: " + p.getIdProduct());
					p = this.service.findById(p);

					if (p == null) {
						logger.info("> Product not found");
						wrei.setMessage(WebResponseMessage.OBJECT_NOT_FOUND);
						return new ResponseEntity<Object>(wrei, HttpStatus.NOT_FOUND);
					} else {
						String json = JSON_MAPPER.writeValueAsString(p);
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
					logger.error("> No se ha podido serializar el JSON a la clase: " + Product.class);
					wrei.setMessage(WebResponseMessage.ERROR_TO_JSON);
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
			}
		}
	}

	/**
	 * TX NAME: Crea y actualiza el maestro de productos
	 * @param wri
	 *            es el objeto que trae los parámetros para la transacción
	 * @return Rertorna un mensaje de OK si la transacción fue exitosa y un BAD si
	 *         no lo fue
	 */

	public ResponseEntity<Object> TxQQRsetProduct(WebRequestIsa wri) {
		logger.info("> TX: TxQQRsetProduct");

		if (wri.getParameters().isEmpty() || wri.getParameters() == null) {
			logger.info("> Objeto vacío");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {

			String jsonValue = Crypto.decrypt(wri.getParameters());
			if (jsonValue.equals(Crypto.ERROR)) {
				logger.error("> error al desencryptar");
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			} else {
				try {
					logger.info("> mapeando json a la clase: " + Product.class);
					ObjectMapper mapper = new ObjectMapper();
					Product p = mapper.readValue(jsonValue, Product.class);
					logger.info("> objeto a guardar: " + p.toString());
					this.service.create(p);
					return new ResponseEntity<>(HttpStatus.OK);
				} catch (IOException e) {
					logger.error("> No se ha podido serializar el JSON a la clase: " + Product.class);
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
			}
		}
	}
}
