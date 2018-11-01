package com.herokuapp.cristcc2.Controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.herokuapp.cristcc2.Models.Racao;
import com.herokuapp.cristcc2.repository.RacaoRepository;

@Controller
public class RacoesController {
	
	@Autowired
	private RacaoRepository rr;
	
	//Busca lista
	@RequestMapping(value = "/cadastrarRacoes", method = RequestMethod.GET)
	public ModelAndView listaPostura() {
		ModelAndView mv = new ModelAndView("racoes/cadastrarRacoes");
		Iterable<Racao> lista = rr.findAll();
		mv.addObject("listaRacoes", lista);
		return mv;
	}
	
	@RequestMapping("/edicaoRacoes")
	public String formEdicaoOvos() {
		return "racoes/editarRacoes";
	}
	
	@RequestMapping(value = "/edicaoRacoes", method = RequestMethod.POST)
	public String salvarvacinas(@Valid Racao racao, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			System.out.print("teste 01");
			if (racao == null) {
				System.out.print("não criou entidade");
			} else {
				racao.print();
			}
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/edicaoRacoes";
		} else {
			System.out.print("teste 02");
			rr.save(racao);
			
			attributes.addFlashAttribute("mensagem", "Lote de Racao salvo com sucesso!");
			return "redirect:/cadastrarRacoes";
		}
	}
}
