package com.sunnyside.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/form")
public class FormController {

	@RequestMapping(method = RequestMethod.GET)
	public String form() {
		return "forms";
	}
	
}
