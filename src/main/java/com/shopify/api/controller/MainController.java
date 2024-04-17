package com.shopify.api.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
public class MainController {
	
	@GetMapping
	 public String main() {
		return "Server is started";
		
	}
	
	@GetMapping("/home")
	public String home() {
		return "<h1>Home Page</h1>";
	}

	@GetMapping("/main")
	public String main1() {
		return "<h1>Main Page</h1>";
	}
}
