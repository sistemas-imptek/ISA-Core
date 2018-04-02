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
import com.isacore.quality.service.INonconformingProductService;
import com.isacore.util.Crypto;
import com.isacore.util.WebResponseIsa;

@Component
public class TxNonConformingProduct {

	public static final ObjectMapper JSON_MAPPER = new ObjectMapper();

	public static final String TX_NAME_GetAllNCP = "GetAllNCP";
	public static final String TX_CODE_GetAllNCP = "TxQQRgeAlltNCP";

	@Autowired
	private INonconformingProductService service;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public ResponseEntity<Object> TxQQRgetAllNCP() {

		logger.info("> TX: TxQQRgeAlltNCP");

		WebResponseIsa wrei = new WebResponseIsa();
		wrei.setTransactionName(TX_NAME_GetAllNCP);
		wrei.setTransactionCode(TX_CODE_GetAllNCP);

		List<NonconformingProduct> ncps = this.service.findAll();
		
		ncps.forEach((NonconformingProduct x) -> {
			x.getProduct().setPropertyList(null);
			x.getProduct().setFeature(null);
		});

		try {

			String json = JSON_MAPPER.writeValueAsString(ncps);
			
			String jsonCryp = Crypto.encrypt(json);
			
			wrei.setParameters(jsonCryp);
			return new ResponseEntity<Object>(wrei,HttpStatus.OK);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
