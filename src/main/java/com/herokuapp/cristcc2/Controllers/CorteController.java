package com.herokuapp.cristcc2.Controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.herokuapp.cristcc2.Models.Corte;
import com.herokuapp.cristcc2.repository.CorteRepository;


@Controller
public class CorteController {

	@Autowired
	private CorteRepository cr;
	
	//Busca lista
	@RequestMapping(value = "/cadastrarCorte", method = RequestMethod.GET)
	public ModelAndView listaOvos() {
		ModelAndView mv = new ModelAndView("corte/cadastrarCorte");
		Iterable<Corte> lista = cr.findAll();
		mv.addObject("listaCorte", lista);
		return mv;
	}
	
	@RequestMapping("/edicaoCorte")
	public String formEdicaoOvos() {
		return "corte/editarCorte";
	}
	
	//Salvar
	@RequestMapping(value = "/edicaoCorte", method = RequestMethod.POST)
	public String salvarOvos(@Valid Corte corte, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/edicaoCorte";
		} else {
			cr.save(corte);
			attributes.addFlashAttribute("mensagem", "Lote de ovos salvo com sucesso!");
			return "redirect:/cadastrarCorte";
		}
	}
}
