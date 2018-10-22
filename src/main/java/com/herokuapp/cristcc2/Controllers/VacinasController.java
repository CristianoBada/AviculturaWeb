package com.herokuapp.cristcc2.Controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.herokuapp.cristcc2.Models.Vacina;
import com.herokuapp.cristcc2.repository.VacinasRepository;

@Controller
public class VacinasController {

	@Autowired
	private VacinasRepository vr;
	
	//Busca lista
	@RequestMapping(value = "/cadastrarVacinas", method = RequestMethod.GET)
	public ModelAndView listaPostura() {
		ModelAndView mv = new ModelAndView("vacinas/cadastrarVacinas");
		Iterable<Vacina> lista = vr.findAll();
		mv.addObject("listaVacinas", lista);
		return mv;
	}
	
	@RequestMapping("/edicaoVacinas")
	public String formEdicaoOvos() {
		return "vacinas/editarVacinas";
	}
	
	@RequestMapping(value = "/edicaoVacinas", method = RequestMethod.POST)
	public String salvarvacinas(@Valid Vacina vacina, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/edicaoPostura";
		} else {
			vr.save(vacina);
			attributes.addFlashAttribute("mensagem", "tratamento salvo com sucesso!");
			return "redirect:/cadastrarVacinas";
		}
	}
}
