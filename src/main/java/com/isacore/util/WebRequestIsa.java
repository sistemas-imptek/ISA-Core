package com.isacore.util;

public class WebRequestIsa {

	private String transactionName;
	
	private String transactionCode;
		
	private String parameters;

	public WebRequestIsa() {}

	public WebRequestIsa(String transactionName, String transactionCode, String parameters) {
		this.transactionName = transactionName;
		this.transactionCode = transactionCode;		
		this.parameters = parameters;
	}

	public String getTransactionName() {
		return transactionName;
	}

	public void setTransactionName(String transactionName) {
		this.transactionName = transactionName;
	}

	public String getTransactionCode() {
		return transactionCode;
	}

	public void setTransactionCode(String transactionCode) {
		this.transactionCode = transactionCode;
	}

	public String getParameters() {
		return parameters;
	}

	public void setParameters(String parameters) {
		this.parameters = parameters;
	}
	
	

}	