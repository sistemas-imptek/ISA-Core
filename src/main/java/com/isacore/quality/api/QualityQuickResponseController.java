package com.isacore.quality.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.isacore.quality.tx.TxNorm;
import com.isacore.quality.tx.TxProduct;
import com.isacore.security.tx.LoginIsa;
import com.isacore.util.WebRequestIsa;

@RestController
@RequestMapping(value = "/qualityQR")
public class QualityQuickResponseController {

	@Autowired
	private TxNorm txNorm;
	
	@Autowired
	private TxProduct txProduct;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "/api", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> txQuickResponse(@RequestBody WebRequestIsa wri){
		
		switch(wri.getTransactionCode()) {
		
		case TxNorm.TX_CODE_GetAllNorms:
			return this.txNorm.TxQQRgetNorms(wri);
			
		case TxNorm.TX_CODE_GetNormById:
			return this.txNorm.TxQQRgetNormById(wri);
			
		case TxNorm.TX_CODE_GetByKindNorm:
			return this.txNorm.TxQQRgetByKindNorm(wri);

		case TxProduct.TX_CODE_GetAllProducts:
			return this.txProduct.TxQQRgetProducts(wri);
			
		case TxProduct.TX_CODE_GetProductById:
			return this.txProduct.TxQQRgetProductById(wri);
			
		case TxProduct.TX_CODE_SetProduct:
			return this.txProduct.TxQQRsetProduct(wri);
		
		default:
			logger.info("> La transacción solicitada no existe: TX-> " + wri.getTransactionCode() + " " + wri.getTransactionName() + LoginIsa.class);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
