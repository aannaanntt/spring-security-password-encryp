package com.anant.springsecurity.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

	@GetMapping("/")
	public String showHome() {
		return "home";
	}
	
	
	//add another request mapping/leaders
	
	
	@GetMapping("/leaders")
	public String showLeaders() {
		return "leaders";
	}
	

	@GetMapping("/systems")
	public String showSystems() {
		return "systems";
	}
	
	@GetMapping("/access-denied")
	public String accessDenied() {
		return "access-denied";
	}
}
