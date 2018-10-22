package com.herokuapp.cristcc2.Controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.herokuapp.cristcc2.Models.Incubatorio;
import com.herokuapp.cristcc2.repository.IncubatorioRepository;

@Controller
public class IncubatorioController {

	@Autowired
	private IncubatorioRepository ir;
	
	@RequestMapping(value = "/cadastrarIncubatorio", method = RequestMethod.GET)
	public ModelAndView listaIncubatorio() {
		ModelAndView mv = new ModelAndView("incubatorio/cadastrarIncubatorio");
		Iterable<Incubatorio> lista = ir.findAll();
		mv.addObject("listaIncubatorio", lista);
		return mv;
	}
	
	@RequestMapping("/edicaoIncubatorio")
	public String formEdicaoOvos() {
		return "incubatorio/editarIncubatorio";
	}
	
	//Salvar
	@RequestMapping(value = "/edicaoIncubatorio", method = RequestMethod.POST)
	public String salvarIncubatorio(@Valid Incubatorio incubatorio, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/edicaoIncubatorio";
		} else {
			ir.save(incubatorio);
			attributes.addFlashAttribute("mensagem", "Lote de ovos salvo com sucesso!");
			return "redirect:/cadastrarIncubatorio";
		}
	}
}
