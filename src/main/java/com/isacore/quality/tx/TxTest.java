package com.isacore.quality.tx;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.mapping.Array;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.isacore.quality.dto.ProductDto;
import com.isacore.quality.model.HccHead;
import com.isacore.quality.model.Test;
import com.isacore.quality.read.tests.GeneralReadTest;
import com.isacore.quality.read.tests.TestReadAdhesividad;
import com.isacore.quality.read.tests.TestReadCizalla;
import com.isacore.quality.read.tests.TestReadDesgarreClavo;
import com.isacore.quality.read.tests.TestReadPiedra;
import com.isacore.quality.read.tests.TestReadTraction;
import com.isacore.quality.service.IHccHeadService;
import com.isacore.quality.service.IProductService;
import com.isacore.quality.service.ITestService;
import com.isacore.util.Crypto;
import com.isacore.util.WebRequestIsa;
import com.isacore.util.WebResponseIsa;
import com.isacore.util.WebResponseMessage;

@Component
public class TxTest {
	public static final String TX_NAME_SaveTest = "SaveTest";
	public static final String TX_CODE_SaveTest = "TxQQRsaveTest";

	public static final String TX_NAME_GetTestByBatchNull = "GetTestByBatchNull";
	public static final String TX_CODE_GetTestByBatchNull = "TxQQRgetTestByBatchNull";

	public static final String TX_NAME_ReadTestPF = "ReadTestPF";
	public static final String TX_CODE_ReadTestPF = "TxQQRReadTestPF";

	public static final String TX_NAME_ReadTestPFReblandecimiento = "ReadTestPFPReblandecimiento";
	public static final String TX_CODE_ReadTestPFReblandecimiento = "TxQQRReadTestPFPReblandecimiento";
	
	public static final String TX_NAME_GetTestByBatchDEV = "GetTestByBatchDEV";
	public static final String TX_CODE_GetTestByBatchDEV = "TxQQRgetTestByBatchDEV";
	
	public static final String TX_NAME_GetTestByBatchAndIdProduct = "GetTestByBatchAndIdProduct";
	public static final String TX_CODE_GetTestByBatchAndIdProduct = "TxQQRgetTestByBatchAndIdProduct";

	public static final ObjectMapper JSON_MAPPER = new ObjectMapper();
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ITestService serviceTest;

	@Autowired
	private GeneralReadTest readTest;

	@Autowired
	private IProductService serviceProduct;

	/**
	 * TX NAME: SaveTEst guarda los test
	 * 
	 * @param wri
	 * @return
	 */
	public ResponseEntity<Object> TxQQRsaveTest(WebRequestIsa wri) {
		logger.info("> TX: TxQQRgenerateHCC");

		WebResponseIsa wrei = new WebResponseIsa();
		wrei.setTransactionName(TX_NAME_SaveTest);
		wrei.setTransactionCode(TX_CODE_SaveTest);

		if (wri.getParameters().isEmpty() || wri.getParameters() == null) {
			logger.info("> Objeto vacío");
			wrei.setMessage(WebResponseMessage.WITHOUT_PARAMS);
			wrei.setStatus(WebResponseMessage.STATUS_INFO);
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
					logger.info("> mapeando json a la clase: " + Test.class);
					// List<Test> recibedTest = Array.isList(JSON_MAPPER.readValue(jsonValue,
					// Test.class)) ;
					Test[] tsNew = JSON_MAPPER.readValue(jsonValue, Test[].class);
					List<Test> testSaved = new ArrayList<>();
					for (Test d : tsNew) {
						Test t = this.serviceTest.create(d);
						testSaved.add(t);
					}

					if ((tsNew.length == testSaved.size()) & (testSaved != null)) {
						logger.info(">> Test guardado correctamente");
						String json = JSON_MAPPER.writeValueAsString(tsNew);
						String jsonCryp = Crypto.encrypt(json);
						if (jsonCryp.equals(Crypto.ERROR)) {
							logger.error("> error al encryptar");
							wrei.setMessage(WebResponseMessage.ERROR_ENCRYPT);
							wrei.setStatus(WebResponseMessage.STATUS_ERROR);
							return new ResponseEntity<Object>(wrei, HttpStatus.INTERNAL_SERVER_ERROR);
						} else {
							wrei.setParameters(jsonCryp);
							wrei.setMessage(WebResponseMessage.CREATE_UPDATE_OK);
							wrei.setStatus(WebResponseMessage.STATUS_OK);
							return new ResponseEntity<Object>(wrei, HttpStatus.OK);
						}
					} else {
						logger.error(">> El Test no se pudo crear no se a podido crear");
						wrei.setMessage("No se ha podido guardar el reporte");
						wrei.setStatus(WebResponseMessage.STATUS_ERROR);
						return new ResponseEntity<Object>(wrei, HttpStatus.INTERNAL_SERVER_ERROR);
					}
				} catch (IOException e) {
					e.printStackTrace();
					logger.error("> No se ha podido serializar el JSON a la clase: " + Test.class);
					wrei.setMessage(WebResponseMessage.ERROR_TO_CLASS);
					wrei.setStatus(WebResponseMessage.STATUS_ERROR);
					return new ResponseEntity<Object>(wrei, HttpStatus.BAD_REQUEST);
				}
			}
		}
	}

	/**
	 * TX NAME: GetTestByBatchNull recupera los tests en base al codigo de producto
	 * MP y que no tenga Lote
	 * 
	 * @param wri
	 * @return
	 */
	public ResponseEntity<Object> TxQQRgetTestByBatchNull(WebRequestIsa wri) {
		logger.info("> TX: TxQQRgetTestByBatchNull");

		WebResponseIsa wrei = new WebResponseIsa();
		wrei.setTransactionName(TX_NAME_GetTestByBatchNull);
		wrei.setTransactionCode(TX_CODE_GetTestByBatchNull);

		if (wri.getParameters().isEmpty() || wri.getParameters() == null) {
			logger.info("> Objeto vacío");
			wrei.setMessage(WebResponseMessage.WITHOUT_PARAMS);
			wrei.setStatus(WebResponseMessage.STATUS_INFO);
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
					logger.info("> mapeando json a la clase: " + Test.class);
					// List<Test> recibedTest = Array.isList(JSON_MAPPER.readValue(jsonValue,
					// Test.class)) ;
					Test tsNew = JSON_MAPPER.readValue(jsonValue, Test.class);
					List<Test> tests = new ArrayList<>();
					tests = this.serviceTest.findByProductIDBatchNull(tsNew.getIdProduct(), tsNew.getIdProperty());
					if (tests.size() != 0) {
						logger.info(">> Tests obtenidos correctamente");
						String json = JSON_MAPPER.writeValueAsString(tests);
						String jsonCryp = Crypto.encrypt(json);
						if (jsonCryp.equals(Crypto.ERROR)) {
							logger.error("> error al encryptar");
							wrei.setMessage(WebResponseMessage.ERROR_ENCRYPT);
							wrei.setStatus(WebResponseMessage.STATUS_ERROR);
							return new ResponseEntity<Object>(wrei, HttpStatus.INTERNAL_SERVER_ERROR);
						} else {
							wrei.setParameters(jsonCryp);
							wrei.setMessage(WebResponseMessage.SEARCHING_OK);
							wrei.setStatus(WebResponseMessage.STATUS_OK);
							return new ResponseEntity<Object>(wrei, HttpStatus.OK);
						}
					} else {
						logger.error(">> No existen registros");
						wrei.setMessage("No existen registros");
						wrei.setStatus(WebResponseMessage.STATUS_INFO);
						return new ResponseEntity<Object>(wrei, HttpStatus.INTERNAL_SERVER_ERROR);
					}
				} catch (IOException e) {
					e.printStackTrace();
					logger.error("> No se ha podido serializar el JSON a la clase: " + Test.class);
					wrei.setMessage(WebResponseMessage.ERROR_TO_CLASS);
					wrei.setStatus(WebResponseMessage.STATUS_ERROR);
					return new ResponseEntity<Object>(wrei, HttpStatus.BAD_REQUEST);
				}
			}
		}
	}

	/**
	 * TX NAME: TxQQRReadTestPlaneFiles lee los archivos planos del Equipo Universal
	 * 
	 * 
	 * @param wri
	 * @return
	 */
	public ResponseEntity<Object> TxQQRReadTestPlaneFiles(WebRequestIsa wri) {
		logger.info("> TX: " + TX_CODE_ReadTestPF);
		WebResponseIsa wrei = new WebResponseIsa();
		wrei.setTransactionName(TX_NAME_ReadTestPF);
		wrei.setTransactionCode(TX_CODE_ReadTestPF);

		if (wri.getParameters().isEmpty() || wri.getParameters() == null) {
			logger.info("> Objeto vacío");
			wrei.setMessage(WebResponseMessage.WITHOUT_PARAMS);
			wrei.setStatus(WebResponseMessage.STATUS_INFO);
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

					// lee los archivos planos, generados por pruebas del Equipo Universal
					logger.info("> mapeando json a la clase: " + Test.class);
					Test tsNew = JSON_MAPPER.readValue(jsonValue, Test.class);

					switch (tsNew.getIdProperty()) {
					case "PROP_1": // Punto Reblandecimiento
						logger.info("> Leyendo arhivos planos Equipo Punto de Reblandecimiento ");
						this.readTest.executeTReblandecimeinto(tsNew.getBatchTest());
						break;
					default:
						logger.info("> Leyendo arhivos planos Equipo Universal ");
						this.readTest.execute();
						break;
					}
					List<Test> tests = new ArrayList<>();
					tests = this.serviceTest.findByBatchAndPromissingNull(tsNew.getBatchTest());

					if (tests.size() != 0) {
						logger.info(">> Tests obtenidos correctamente");

						for (Test item : tests) {
							ProductDto pd = this.serviceProduct.findProductByIdAndIdProperty(tsNew.getIdProduct(),
									item.getIdProperty());
							if (pd != null) {
								if (pd.getPropertyMax() == null) {
									if (pd.getPropertyMin().doubleValue() <= item.getResultTest()) {
										item.setPrommissing(true);
									}
								} else {
									if ((pd.getPropertyMin().doubleValue() <= item.getResultTest())
											& (pd.getPropertyMax().doubleValue() >= item.getResultTest())) {
										item.setPrommissing(true);
									}
								}
								item.setOwner(tsNew.getOwner());
								item.setIdProduct(pd.getProductId());
								item.setProductName(pd.getProductName());
								item.setSapCode(pd.getProductsapCode());
								item.setComment(tsNew.getComment());
								this.serviceTest.create(item);
							}
						}
						wrei.setMessage(WebResponseMessage.SEARCHING_OK);
						wrei.setStatus(WebResponseMessage.STATUS_OK);
						return new ResponseEntity<Object>(wrei, HttpStatus.OK);

					} else {
						logger.error(">> No existen registros");
						wrei.setMessage("No existen registros");
						wrei.setStatus(WebResponseMessage.STATUS_INFO);
						return new ResponseEntity<Object>(wrei, HttpStatus.INTERNAL_SERVER_ERROR);
					}
				} catch (IOException e) {
					e.printStackTrace();
					logger.error("> No se ha podido serializar el JSON a la clase: " + Test.class);
					wrei.setMessage(WebResponseMessage.ERROR_TO_CLASS);
					wrei.setStatus(WebResponseMessage.STATUS_ERROR);
					return new ResponseEntity<Object>(wrei, HttpStatus.BAD_REQUEST);
				}
			}
		}
	}
	
	/**
	 * TX NAME: GetTestByBatchAndIdProduct recupera los tests en base al codigo de producto
	 * MP y lote
	 * 
	 * @param wri
	 * @return
	 */
	public ResponseEntity<Object> TxQQRgetTestByBatchAndIdProduct(WebRequestIsa wri) {
		logger.info("> TX: TxQQRgetTestByBatchAndIdProduct");

		WebResponseIsa wrei = new WebResponseIsa();
		wrei.setTransactionName(TX_NAME_GetTestByBatchAndIdProduct);
		wrei.setTransactionCode(TX_CODE_GetTestByBatchAndIdProduct);

		if (wri.getParameters().isEmpty() || wri.getParameters() == null) {
			logger.info("> Objeto vacío");
			wrei.setMessage(WebResponseMessage.WITHOUT_PARAMS);
			wrei.setStatus(WebResponseMessage.STATUS_INFO);
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
					logger.info("> mapeando json a la clase: " + Test.class);
					// List<Test> recibedTest = Array.isList(JSON_MAPPER.readValue(jsonValue,
					// Test.class)) ;
					Test tsNew = JSON_MAPPER.readValue(jsonValue, Test.class);
					List<Test> tests = new ArrayList<>();
					tests = this.serviceTest.findByBatchAndIdProduct(tsNew.getBatchTest(),tsNew.getIdProduct());
					if (tests.size() != 0) {
						logger.info(">> Tests obtenidos correctamente");
						String json = JSON_MAPPER.writeValueAsString(tests);
						String jsonCryp = Crypto.encrypt(json);
						if (jsonCryp.equals(Crypto.ERROR)) {
							logger.error("> error al encryptar");
							wrei.setMessage(WebResponseMessage.ERROR_ENCRYPT);
							wrei.setStatus(WebResponseMessage.STATUS_ERROR);
							return new ResponseEntity<Object>(wrei, HttpStatus.INTERNAL_SERVER_ERROR);
						} else {
							wrei.setParameters(jsonCryp);
							wrei.setMessage(WebResponseMessage.SEARCHING_OK);
							wrei.setStatus(WebResponseMessage.STATUS_OK);
							return new ResponseEntity<Object>(wrei, HttpStatus.OK);
						}
					} else {
						logger.error(">> No existen registros");
						wrei.setMessage("No existen registros");
						wrei.setStatus(WebResponseMessage.STATUS_INFO);
						return new ResponseEntity<Object>(wrei, HttpStatus.INTERNAL_SERVER_ERROR);
					}
				} catch (IOException e) {
					e.printStackTrace();
					logger.error("> No se ha podido serializar el JSON a la clase: " + Test.class);
					wrei.setMessage(WebResponseMessage.ERROR_TO_CLASS);
					wrei.setStatus(WebResponseMessage.STATUS_ERROR);
					return new ResponseEntity<Object>(wrei, HttpStatus.BAD_REQUEST);
				}
			}
		}
	}
	
	/**
	 * TX NAME: TxQQRgetTestByBatchDEV recupera los Test de Productos de Desarrollo  q aun no tiene lote
	 * 
	 * 
	 * @param wri
	 * @return
	 */
	
	
	public ResponseEntity<Object> TxQQRgetTestByBatchDEV(WebRequestIsa wri) {
		logger.info("> TX: TxQQRgetTestByBatchDEV");

		WebResponseIsa wrei = new WebResponseIsa();
		wrei.setTransactionName(TX_NAME_GetTestByBatchNull);
		wrei.setTransactionCode(TX_CODE_GetTestByBatchNull);

		if (wri.getParameters().isEmpty() || wri.getParameters() == null) {
			logger.info("> Objeto vacío");
			wrei.setMessage(WebResponseMessage.WITHOUT_PARAMS);
			wrei.setStatus(WebResponseMessage.STATUS_INFO);
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
					logger.info("> mapeando json a la clase: " + Test.class);
					// List<Test> recibedTest = Array.isList(JSON_MAPPER.readValue(jsonValue,
					// Test.class)) ;
					Test tsNew = JSON_MAPPER.readValue(jsonValue, Test.class);
					List<Test> tests = new ArrayList<>();
					tests = this.serviceTest.findByProductIDBatchNull(tsNew.getIdProduct(), tsNew.getIdProperty());
					if (tests.size() != 0) {
						logger.info(">> Tests obtenidos correctamente");
						String json = JSON_MAPPER.writeValueAsString(tests);
						String jsonCryp = Crypto.encrypt(json);
						if (jsonCryp.equals(Crypto.ERROR)) {
							logger.error("> error al encryptar");
							wrei.setMessage(WebResponseMessage.ERROR_ENCRYPT);
							wrei.setStatus(WebResponseMessage.STATUS_ERROR);
							return new ResponseEntity<Object>(wrei, HttpStatus.INTERNAL_SERVER_ERROR);
						} else {
							wrei.setParameters(jsonCryp);
							wrei.setMessage(WebResponseMessage.SEARCHING_OK);
							wrei.setStatus(WebResponseMessage.STATUS_OK);
							return new ResponseEntity<Object>(wrei, HttpStatus.OK);
						}
					} else {
						logger.error(">> No existen registros");
						wrei.setMessage("No existen registros");
						wrei.setStatus(WebResponseMessage.STATUS_INFO);
						return new ResponseEntity<Object>(wrei, HttpStatus.INTERNAL_SERVER_ERROR);
					}
				} catch (IOException e) {
					e.printStackTrace();
					logger.error("> No se ha podido serializar el JSON a la clase: " + Test.class);
					wrei.setMessage(WebResponseMessage.ERROR_TO_CLASS);
					wrei.setStatus(WebResponseMessage.STATUS_ERROR);
					return new ResponseEntity<Object>(wrei, HttpStatus.BAD_REQUEST);
				}
			}
		}
	}

}
