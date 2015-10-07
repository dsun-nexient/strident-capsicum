package com.sunnyside.api.controller.exception;

public class PageNotFoundException extends RestControllerException{

	private static final long serialVersionUID = -8991981702185728959L;
	
	public PageNotFoundException(int exceptionCode, String exceptionMessage) {
		super(exceptionCode, exceptionMessage);
	}



}
