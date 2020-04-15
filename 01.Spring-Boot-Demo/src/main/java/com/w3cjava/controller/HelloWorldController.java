package com.w3cjava.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	@RequestMapping("/hello")
	public String index() {
		return "Hello World";
	}
	
	@RequestMapping("/hello1")
	public String index1() {
		return "Hello World1";
	}	
}
