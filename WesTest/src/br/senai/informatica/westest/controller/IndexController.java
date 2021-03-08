package br.senai.informatica.westest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping("index")
	public String index() {
		return "index";
	}

	@RequestMapping("semacesso")
	public String semAcesso() {
		return "semacesso";
	}

}
