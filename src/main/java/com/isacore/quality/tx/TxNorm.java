package com.isacore.quality.tx;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.isacore.quality.model.Norm;
import com.isacore.quality.service.INormService;
import com.isacore.util.Crypto;
import com.isacore.util.WebRequestIsa;

@Component
public class TxNorm {

	@Autowired
	private INormService normService;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public ResponseEntity<Object> TxQQRgetNorms(WebRequestIsa wri) {
		logger.info("> TX: TxQQRgetNorms");

		if (wri.getParameters().isEmpty() || wri.getParameters() == null) {
			List<Norm> norms = this.normService.findAll();
			if (norms.isEmpty() || norms == null)
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			else
				return new ResponseEntity<Object>(norms, HttpStatus.OK);
		} else {

			String jsonValue = Crypto.decrypt(wri.getParameters());
			if (jsonValue.equals(Crypto.ERROR)) {
				logger.error("> error al desencryptar");
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			} else {
				logger.info("> Parameters: " + jsonValue);
				logger.info("> TX: TxQQRgetNorms --> " + jsonValue);
				List<Norm> norms = this.normService.findByKindNorm(jsonValue);
				if (norms.isEmpty() || norms == null)
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				else
					return new ResponseEntity<Object>(norms, HttpStatus.OK);
			}
		}
	}

}
