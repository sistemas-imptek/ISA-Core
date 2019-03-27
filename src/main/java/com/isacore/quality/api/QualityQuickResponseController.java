package com.isacore.quality.api;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.isacore.quality.classes.util.TxRequestQualityUtil;
import com.isacore.quality.model.FormulaList;
import com.isacore.quality.model.Formulation;
import com.isacore.quality.model.Product;
import com.isacore.quality.read.specification.mp.GeneralReadMP;
import com.isacore.quality.read.specification.pt.GeneralReadPT;
import com.isacore.quality.read.tests.GeneralReadTest;
import com.isacore.quality.service.IFormulationService;
import com.isacore.quality.service.impl.FormulationServiceImpl;
import com.isacore.quality.service.impl.ProductServiceImpl;
import com.isacore.quality.tx.TxGenerateQualityCertificate;
import com.isacore.quality.tx.TxHcc;
import com.isacore.quality.tx.TxNonConformingProduct;
import com.isacore.quality.tx.TxNorm;
import com.isacore.quality.tx.TxProduct;
import com.isacore.quality.tx.TxPropertyList;
import com.isacore.quality.tx.TxReadFormulation;
import com.isacore.quality.tx.TxTest;
import com.isacore.sgc.acta.model.Role;
import com.isacore.sgc.acta.service.impl.RoleServiceImpl;
import com.isacore.util.WebRequestIsa;
import com.isacore.util.WebResponseIsa;
import com.isacore.util.WebResponseMessage;

@RestController
@RequestMapping(value = "/qualityQR")
public class QualityQuickResponseController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private WebResponseIsa wrei;
	
	@Autowired
	private TxNorm txNorm;
	
	@Autowired
	private TxProduct txProduct;
	
	@Autowired
	private TxPropertyList txPropertyList;
	
	@Autowired
	private TxHcc txHcc;
	
	@Autowired
	private TxNonConformingProduct txNcp;
	
	@Autowired
	private TxRequestQualityUtil txRQU;
	
	@Autowired
	private TxGenerateQualityCertificate txGQC;
	
	@Autowired
	private TxReadFormulation txReadFormulation;
	
	@Autowired
	private ProductServiceImpl p;
	
	@Autowired
	private GeneralReadPT readPT;
	
	@Autowired
	private GeneralReadMP readMP;
	
	@Autowired
	private RoleServiceImpl roleService;
	
	@Autowired
	private GeneralReadTest readTest;
	
	@Autowired
	private IFormulationService formulationService;
	
	@Autowired
	private TxTest txTest;
	
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
			
		case TxProduct.TX_CODE_GetFeature:
			return this.txProduct.TxQQRgetProductFeature(wri);
			
		case TxProduct.TX_CODE_SetProduct:
			return this.txProduct.TxQQRsetProduct(wri);
			
		case TxPropertyList.TX_CODE_GetAllOnlyPropertyList:
			return this.txPropertyList.TxQQRgetOnlyPL(wri);
			
		case TxHcc.TX_CODE_GetAllHCCTP:
			return this.txHcc.TxQQRgetHCCPT(wri);
		
		case TxHcc.TX_CODE_GenerateHcc:
			return this.txHcc.TxQQRgenerateHCC(wri);
			
		case TxHcc.TX_CODE_SetHcc:
			return this.txHcc.TxQQRSetHCC(wri);
			
		case TxNonConformingProduct.TX_CODE_GetAllNCP:
				return this.txNcp.TxQQRgetAllNCP(wri);
				
		case TxNonConformingProduct.TX_CODE_GetNCPById:
				return this.txNcp.TxQQRgetNCPById(wri);
				
		case TxNonConformingProduct.TX_CODE_SetNCP:
			return this.txNcp.TxQQRsetNCP(wri);
			
		case TxNonConformingProduct.TX_CODE_CloseNCP:	
			return this.txNcp.TxQQRcloseNCP(wri);
			
		case TxRequestQualityUtil.TX_CODE_RNCP:
			return this.txRQU.requestNCP();
			
		case TxGenerateQualityCertificate.Tx_CODE_GenerateQualityCertificate:
			return this.txGQC.TxGQC(wri);
			
		case TxGenerateQualityCertificate.TX_CODE_GetAllClients:
			return this.txGQC.TxQQRGAC(wri);
			
		case TxReadFormulation.TX_CODE_ReadFormulation:
			return this.txReadFormulation.TxQQRReadF(wri);
		
		case TxTest.TX_CODE_SaveTest:
			return this.txTest.TxQQRsaveTest(wri);
		
		case TxTest.TX_CODE_GetTestByBatchNull:
			return this.txTest.TxQQRgetTestByBatchNull(wri);
			
		case TxProduct.TX_CODE_GetProductByIdAndPropertyId:
			return this.txProduct.TxQQRgetProductByIdAndPropertyId(wri);
		
		case TxProduct.TX_CODE_GetProductPropertiesById:
			return this.txProduct.TxQQRgetProductPropertiesById(wri);
			
		
		default:
			logger.info("> La transacción solicitada no existe: TX-> " + wri.getTransactionCode());
			wrei.setTransactionName("Transacción no encontrada");
			wrei.setTransactionCode("TxNotFound");
			wrei.setStatus(WebResponseMessage.STATUS_ERROR);
			wrei.setMessage("Transacción no encontrada");
			return new ResponseEntity<Object>(wrei,HttpStatus.OK);
		}
	}	
	
	@PostMapping(value = "/testRole", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Role testExcel(@RequestBody String id) {
		
		Role r = new Role();
		r.setRolName(id);
		return roleService.findById(r);
		
		
	}
	
	@RequestMapping(value = "/varios", method = RequestMethod.GET)
	public void varios() {
		Product pp = new Product();
		pp.setIdProduct(5);
		p.findOnlyProductById(pp);
	}
	
	@RequestMapping(value = "/readSpecifications", method = RequestMethod.GET)
	public void readSpecifications() {
		
		String user = "oquimbiulco";
		readPT.run(user);
		
		readMP.run(user);
	}
	
	@RequestMapping(value = "/readTestEUniversal", method = RequestMethod.GET)
	public void readTestEU() {
		
		this.readTest.execute();
	}
	
	@GetMapping(value = "/formulaList")
	public List<FormulaList> getFormulaList(@RequestParam("id")Integer idProd){		
		return this.formulationService.getTypeFormulationsListByProduct(idProd);		
	}
	
	@RequestMapping(value = "/readFormula", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Formulation> readFormulations(@RequestParam("idPSap")String idPSap,@RequestParam("idF")Integer idF, @RequestParam("load")Integer load) {
		List<Formulation> formulations = this.formulationService.findFormulationByProductAndFormType(idPSap, idF);
		
		if(formulations == null)
			return formulations = new ArrayList<>();
		else {
			formulations.forEach(x -> x.setAmount(load));
			return formulations;
		}
	}
	
	
}
