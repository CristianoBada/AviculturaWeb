package com.herokuapp.cristcc2.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

	@RequestMapping(method =  RequestMethod.GET, path = "/entrar")
	public String entrar( ) {
		return "entrar";
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/")
	public String index() {
		return "index";
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/home")
	public String indexHome() {
		return "index";
	}
}
