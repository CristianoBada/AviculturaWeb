package com.herokuapp.cristcc2.Controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.herokuapp.cristcc2.Models.Financeiro;
import com.herokuapp.cristcc2.repository.FinanceiroRepository;

@Controller
public class FinanceiroController {

	@Autowired
	private FinanceiroRepository fr;
	
	//Busca lista
	@RequestMapping(value = "/cadastrarFinanceiro", method = RequestMethod.GET)
	public ModelAndView listaPostura() {
		ModelAndView mv = new ModelAndView("financeiro/cadastrarFinanceiro");
		Iterable<Financeiro> lista = fr.findAll();
		mv.addObject("listaFinanceiro", lista);
		return mv;
	}
	
	@RequestMapping("/edicaoFinanceiro")
	public String formEdicaoOvos() {
		return "financeiro/editarFinanceiro";
	}
	
	@RequestMapping(value = "/edicaoFinanceiro", method = RequestMethod.POST)
	public String salvarvacinas(@Valid Financeiro financeiro, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/edicaoFinanceiro";
		} else {
			fr.save(financeiro);
			attributes.addFlashAttribute("mensagem", "Financeiro salvo com sucesso!");
			return "redirect:/cadastrarFinanceiro";
		}
	}
	
}
