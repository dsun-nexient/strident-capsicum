package com.sunnyside.api.controller.exception;

import com.fasterxml.jackson.annotation.JsonView;
import com.sunnyside.api.jsonview.BaseView;

public abstract class RestControllerException extends Exception {

	private static final long serialVersionUID = -6478345062888577257L;
	
	@JsonView(BaseView.class)
	private int exceptionCode;
	@JsonView(BaseView.class)
	private String exceptionMessage; 

	public RestControllerException(int exceptionCode, String exceptionMessage) {
		super();
		this.exceptionCode = exceptionCode;
		this.exceptionMessage = exceptionMessage;
	}
	
	public int getExceptionCode() {
		return exceptionCode;
	}
	
	public void setExceptionCode(int exceptionCode) {
		this.exceptionCode = exceptionCode;
	}
	
	public String getExceptionMessage() {
		return exceptionMessage;
	}
	
	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}
}
