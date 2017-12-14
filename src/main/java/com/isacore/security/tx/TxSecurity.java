package com.isacore.security.tx;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.isacore.sgc.acta.model.UserImptek;
import com.isacore.sgc.acta.service.IUserImptekService;
import com.isacore.util.Crypto;
import com.isacore.util.WebRequestIsa;

@Component
public class TxSecurity {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IUserImptekService userImptekService;
	
	public ResponseEntity<Object> AAS(WebRequestIsa wri) {
		
		try {
			logger.info("> TX: TxSecurityAAS");
			String jsonValue = Crypto.decrypt(wri.getParameters()); 
			logger.info("> Parameters: " + jsonValue);

			ObjectMapper mapper = new ObjectMapper();
			LoginIsa li = mapper.readValue(jsonValue, LoginIsa.class);
			
			logger.info("> Validando usuario: " + li.getUserName());
			UserImptek userI = this.userImptekService.findByUserImptek(li.getUserName());
			
			if(userI == null)
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			else
				if(li.getPass().equals(userI.getUserPass()))
					return new ResponseEntity<Object>(userI,HttpStatus.OK);
				else
					return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
			
		}catch (UnsupportedEncodingException | NoSuchAlgorithmException | NoSuchPaddingException |InvalidKeyException |
				InvalidAlgorithmParameterException |IllegalBlockSizeException | BadPaddingException e) {
			logger.error("> error al desencryptar: " + LoginIsa.class);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}catch (NullPointerException e) {
			logger.error("> jsonValue is null: " + LoginIsa.class);				
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}catch (IOException e) {
			logger.error("> No se ha podido serializar el JSON a la clase: " + LoginIsa.class);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
}
