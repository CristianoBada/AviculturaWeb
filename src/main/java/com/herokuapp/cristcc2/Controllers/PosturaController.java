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

import com.herokuapp.cristcc2.Models.Postura;
import com.herokuapp.cristcc2.repository.PosturaRepository;

@Controller
public class PosturaController {

	@Autowired
	private PosturaRepository pr;
	
	//Busca lista
	@RequestMapping(value = "/cadastrarPostura", method = RequestMethod.GET)
	public ModelAndView listaPostura() {
		ModelAndView mv = new ModelAndView("postura/cadastrarPostura");
		Iterable<Postura> lista = pr.findAll();
		mv.addObject("listaPostura", lista);
		return mv;
	}
	
	@RequestMapping("/edicaoPostura")
	public String formEdicaoOvos() {
		return "postura/editarPostura";
	}
	
	@RequestMapping(value = "/edicaoPostura", method = RequestMethod.POST)
	public String salvarPostura(@Valid Postura postura, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/edicaoPostura";
		} else {
			pr.save(postura);
			attributes.addFlashAttribute("mensagem", "lote de postura salvo com sucesso!");
			return "redirect:/cadastrarPostura";
		}
	}
	
	@RequestMapping("/cadastrarPostura/delete/{codigoPostura}") //@PathVariable Long id, RedirectAttributes redirectAttrs
	public String deletarPostura(@PathVariable("codigoPostura") Long codigoPostura, RedirectAttributes redirectAttrs) {
		Postura postura = pr.findByCodigoPostura(codigoPostura);
		pr.delete(postura);
		return "redirect:/cadastrarPostura";
	}
}
