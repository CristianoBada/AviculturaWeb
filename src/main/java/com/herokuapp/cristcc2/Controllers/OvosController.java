package com.herokuapp.cristcc2.Controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.herokuapp.cristcc2.Models.Ovos;
import com.herokuapp.cristcc2.repository.OvosRepository;

@Controller
public class OvosController {
	
	@Autowired
	private OvosRepository ovosr;
	
	//Salvar
	@RequestMapping(value = "/edicaoOvos", method = RequestMethod.POST)
	public String salvarOvos(@Valid Ovos ovos, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/edicaoOvos";
		} else {
			ovosr.save(ovos);
			attributes.addFlashAttribute("mensagem", "Lote de ovos salvo com sucesso!");
			return "redirect:/cadastrarOvos";
		}
	}
	
	//Busca lista
	@RequestMapping(value = "/cadastrarOvos", method = RequestMethod.GET)
	public ModelAndView listaOvos() {
		ModelAndView mv = new ModelAndView("ovos/cadastrarOvos");
		Iterable<Ovos> lista = ovosr.findAll();
		mv.addObject("listaOvos", lista);
		return mv;
	}
	
	@RequestMapping("/edicaoOvos")
	public String formEdicaoOvos() {
		return "ovos/editarOvos";
	}
	
	@RequestMapping("/cadastrarOvos/delete/{codigo}") //@PathVariable Long id, RedirectAttributes redirectAttrs
	public String deletarOvos(@PathVariable("codigo") Long codigo, RedirectAttributes redirectAttrs) {
		Ovos ovos = ovosr.findByCodigo(codigo);
		ovosr.delete(ovos);
		return "redirect:/cadastrarOvos";
	}
	
	
}
