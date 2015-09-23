package com.sunnyside.api.controller.exception;

public class ResourceNotFoundException extends RestControllerException {

	private static final long serialVersionUID = 6992414634409910031L;
	

	public ResourceNotFoundException(int exceptionCode, String exceptionMessage) {
		super(exceptionCode, exceptionMessage);
	}
	
}
