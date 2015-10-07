package com.sunnyside.api.controller.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonView;
import com.sunnyside.api.jsonview.BaseView;

@ResponseBody
@ControllerAdvice
public class ControllerExceptionHandler  {

	@JsonView(BaseView.class)
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResourceNotFoundException handleResourceNotFoundException(ResourceNotFoundException exception) {
		return exception;
	}

	@JsonView(BaseView.class)
	@ExceptionHandler(PageNotFoundException.class)
	public PageNotFoundException handlePageNotFoundException(PageNotFoundException exception) {
		return exception;
	}
}
