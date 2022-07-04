package com.greatLearning.studentManagement.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class MainController {
	
	@RequestMapping("/")
	public String displayHomePage(Model theModel) {
		theModel.addAttribute("details","");
		
		return "demo";
	}

}
