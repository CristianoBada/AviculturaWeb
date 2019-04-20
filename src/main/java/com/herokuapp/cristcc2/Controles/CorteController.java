package com.herokuapp.cristcc2.Controles;

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

import com.herokuapp.cristcc2.Entidades.Corte;
import com.herokuapp.cristcc2.Entidades.TipoAve;
import com.herokuapp.cristcc2.Repository.CorteRepository;
import com.herokuapp.cristcc2.Repository.TipoAveRepository;
import com.herokuapp.cristcc2.Uteis.Convercoes;


@Controller
public class CorteController {

	@Autowired
	private CorteRepository cr;
	
	@Autowired
	private TipoAveRepository tr;
	
	//Busca lista
	@RequestMapping(value = "/cadastrarCorte", method = RequestMethod.GET)
	public ModelAndView listaOvos() {
		ModelAndView mv = new ModelAndView("corte/cadastrarCorte");
		Iterable<Corte> lista = cr.findAll();
		mv.addObject("listaCorte", lista);
		return mv;
	}
	
	//Novo
		@RequestMapping("/edicaoCorte/novo")
		public String novoCorte(Model model) {
			model.addAttribute("corte", new Corte());
			Iterable<TipoAve> lista = tr.findAll();
			model.addAttribute("listaAves", lista);
			return "corte/editarCorte";
		}
	
	//Salvar
	@RequestMapping(value = "/edicaoCorte/save", method = RequestMethod.POST)
	public String salvarOvos(@Valid Corte corte, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/edicaoCorte";
		} else {
			Convercoes convercoes = new Convercoes();
			corte.setEntrada(convercoes.convertDateUStoDataBR((corte.getEntrada())));
			corte.setSaida(convercoes.convertDateUStoDataBR((corte.getSaida())));
			cr.save(corte);
			attributes.addFlashAttribute("mensagem", "Lote de ovos salvo com sucesso!");
			return "redirect:/cadastrarCorte";
		}
	}
	
	//Deletar
	@RequestMapping("/cadastrarCorte/delete/{codigo}") //@PathVariable Long id, RedirectAttributes redirectAttrs
	public String deletarCorte(@PathVariable("codigo") Long codigo, RedirectAttributes redirectAttrs) {
		Corte corte = cr.findByCodigo(codigo);
		cr.delete(corte);
		return "redirect:/cadastrarCorte";
	}
	
	//Editar
		@RequestMapping("/edicaoCorte/editar/{codigo}")
		public String editarVacinas(@PathVariable Long codigo, Model model) {
			Corte corte = cr.findByCodigo(codigo);

			Convercoes convercoes = new Convercoes();
			corte.setEntrada(convercoes.convertDateBRtoDataUS(corte.getEntrada()));
			corte.setSaida(convercoes.convertDateBRtoDataUS(corte.getSaida()));

			Iterable<TipoAve> lista = tr.findAll();
			model.addAttribute("listaAves", lista);
			
			model.addAttribute("corte", corte);
			
			return "corte/editarCorte";
		}
}
