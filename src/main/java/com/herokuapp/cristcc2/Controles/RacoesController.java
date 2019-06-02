package com.herokuapp.cristcc2.Controles;

import java.util.ArrayList;
import java.util.List;

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

import com.google.common.collect.Lists;
import com.herokuapp.cristcc2.Entidades.Corte;
import com.herokuapp.cristcc2.Entidades.Postura;
import com.herokuapp.cristcc2.Entidades.Racao;
import com.herokuapp.cristcc2.Relatorios.PdfRacoesReportView;
import com.herokuapp.cristcc2.Repository.CorteRepository;
import com.herokuapp.cristcc2.Repository.PosturaRepository;
import com.herokuapp.cristcc2.Repository.RacaoRepository;
import com.herokuapp.cristcc2.Uteis.Convercoes;

@Controller
public class RacoesController {

	@Autowired
	private RacaoRepository rr;
	
	@Autowired
	private PosturaRepository pr;
	
	@Autowired
	private CorteRepository cr;

	// Inicio
	@RequestMapping(value = "/cadastrarRacoes", method = RequestMethod.GET)
	public ModelAndView listaPostura() {
		ModelAndView mv = new ModelAndView("racoes/cadastrarRacoes");
		Iterable<Racao> lista = rr.findAll();
		mv.addObject("listaRacoes", lista);
		return mv;
	}

	// Novo
	@RequestMapping("/edicaoRacoes/novo")
	public String novaRavoes(Model model) {
		model.addAttribute("racoes", new Racao());
		Iterable<Postura> lista = pr.findAll();
		model.addAttribute("listaPostura", lista);
		Iterable<Corte> lista2 = cr.findAll();
		model.addAttribute("listaCorte", lista2);
		return "racoes/editarRacoes";
	}

	// Editar
	@RequestMapping("/edicaoRacoes/editar/{codigo}")
	public String editarVacinas(@PathVariable Long codigo, Model model) {
		Racao racao = rr.findByCodigo(codigo);
		model.addAttribute("racoes", racao);
		Iterable<Postura> lista = pr.findAll();
		model.addAttribute("listaPostura", lista);
		Iterable<Corte> lista2 = cr.findAll();
		model.addAttribute("listaCorte", lista2);
		return "racoes/editarRacoes";
	}

	@RequestMapping(value = "/edicaoRacoes/save", method = RequestMethod.POST)
	public String salvarvacinas(@Valid Racao racao, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/edicaoRacoes/novo";
		} else {
			racao.setData2(new Convercoes().convertDateUStoDataBR(racao.getData().toString()));
			rr.save(racao);
			attributes.addFlashAttribute("mensagem", "Lote de Racao salvo com sucesso!");
			return "redirect:/cadastrarRacoes";
		}
	}

	// deletar
	@RequestMapping("/cadastrarRacoes/delete/{codigo}") // @PathVariable Long id, RedirectAttributes redirectAttrs
	public String deletarRacoes(@PathVariable("codigo") Long codigo, RedirectAttributes redirectAttrs) {
		Racao racao = rr.findByCodigo(codigo);
		rr.delete(racao);
		return "redirect:/cadastrarRacoes";
	}

	// Relatório
	@RequestMapping(value = "/gerarPDFRacoes", method = RequestMethod.GET)
	public ModelAndView gerarPDFRacoes() {
		List<Racao> list = new ArrayList<>();
		list = Lists.newArrayList(rr.findAll());

		return new ModelAndView(new PdfRacoesReportView(), "racoesList", list);
	}
}
