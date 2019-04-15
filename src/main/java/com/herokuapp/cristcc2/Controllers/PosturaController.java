package com.herokuapp.cristcc2.Controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.herokuapp.cristcc2.Models.Postura;
import com.herokuapp.cristcc2.Models.TipoAve;
import com.herokuapp.cristcc2.Models.Vacina;
import com.herokuapp.cristcc2.Uteis.Convercoes;
import com.herokuapp.cristcc2.repository.PosturaRepository;
import com.herokuapp.cristcc2.repository.TipoAveRepository;

@Controller
public class PosturaController {

	@Autowired
	private PosturaRepository pr;
	
	@Autowired
	private TipoAveRepository tr;
	
	//Busca lista
	@RequestMapping(value = "/cadastrarPostura", method = RequestMethod.GET)
	public ModelAndView listaPostura() {
		ModelAndView mv = new ModelAndView("postura/cadastrarPostura");
		Iterable<Postura> lista = pr.findAll();
		mv.addObject("listaPostura", lista);
		return mv;
	}
	
	@RequestMapping("/edicaoPostura/novo")
	public String formEdicaoOvos(Model model) {
		model.addAttribute("postura", new Postura());
		Iterable<TipoAve> lista = tr.findAll();
		model.addAttribute("listaAves", lista);
		return "postura/editarPostura";
	}
	
	@RequestMapping(value = "/edicaoPostura/save", method = RequestMethod.POST)
	public String salvarPostura(@Valid Postura postura, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			
			return "redirect:/edicaoPostura";
		} else {
			Convercoes convercoes = new Convercoes();
			postura.setEntrada(convercoes.convertDateUStoDataBR((postura.getEntrada())));
			postura.setSaida(convercoes.convertDateUStoDataBR((postura.getSaida())));
			pr.save(postura);
			
			return "redirect:/cadastrarPostura";
		}
	}
	
	@RequestMapping("/cadastrarPostura/delete/{codigo}") 
	public String deletarPostura(@PathVariable("codigo") Long codigo, RedirectAttributes redirectAttrs) {
		Postura postura = pr.findByCodigo(codigo);
		pr.delete(postura);
		return "redirect:/cadastrarPostura";
	}
	
	//Busca lista
	@RequestMapping(value = "/editarPostura", method = RequestMethod.GET)
	public ModelAndView listaAves() {
		ModelAndView mv = new ModelAndView("postura/cadastrarPostura");
		Iterable<TipoAve> lista = tr.findAll();
		mv.addObject("listaAves", lista);
		return mv;
	}
	
	@RequestMapping("/edicaoPostura/editar/{codigo}")
	public String editarVacinas(@PathVariable Long codigo, Model model) {
		Postura postura = pr.findByCodigo(codigo);

		Convercoes convercoes = new Convercoes();
		postura.setEntrada(convercoes.convertDateBRtoDataUS(postura.getEntrada()));
		postura.setSaida(convercoes.convertDateBRtoDataUS(postura.getSaida()));

		Iterable<TipoAve> lista = tr.findAll();
		model.addAttribute("listaAves", lista);
		
		model.addAttribute("postura", postura);
		
		return "postura/editarPostura";
	}
}
