package com.isacore.util;

public class WebResponseIsa {
	
	private String transactionName;
	
	private String transactionCode;
	
	private String message;
	
	private String parameters;

	public WebResponseIsa() {}

	public WebResponseIsa(String transactionName, String transactionCode, String parameters) {
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
