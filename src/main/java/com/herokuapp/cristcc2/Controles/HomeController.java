package com.herokuapp.cristcc2.Controles;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping("/home")
	public String home(Model model){
		return "index2";
	}
	
	@RequestMapping("/tutorial")
	public String tutorial(Model model) {
		return "tutorial";
	}
}
