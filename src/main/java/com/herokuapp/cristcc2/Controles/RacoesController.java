package com.herokuapp.cristcc2.Controles;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.herokuapp.cristcc2.Entidades.Racao;
import com.herokuapp.cristcc2.Repository.RacaoRepository;

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
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/edicaoRacoes";
		} else {
			System.out.print("teste 02");
			rr.save(racao);
			
			attributes.addFlashAttribute("mensagem", "Lote de Racao salvo com sucesso!");
			return "redirect:/cadastrarRacoes";
		}
	}
	
	@RequestMapping("/cadastrarRacoes/delete/{codigo}") //@PathVariable Long id, RedirectAttributes redirectAttrs
	public String deletarRacoes(@PathVariable("codigo") Long codigo, RedirectAttributes redirectAttrs) {
		Racao racao = rr.findByCodigo(codigo);
		rr.delete(racao);
		return "redirect:/cadastrarRacoes";
	}
}
