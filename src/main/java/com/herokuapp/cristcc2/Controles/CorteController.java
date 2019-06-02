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
import com.herokuapp.cristcc2.Entidades.TipoAve;
import com.herokuapp.cristcc2.Relatorios.PdfCorteReportView;
import com.herokuapp.cristcc2.Repository.CorteRepository;
import com.herokuapp.cristcc2.Repository.TipoAveRepository;
import com.herokuapp.cristcc2.Uteis.Convercoes;

@Controller
public class CorteController {

	@Autowired
	private CorteRepository cr;

	@Autowired
	private TipoAveRepository tr;
	
	private List<Corte> lista = new ArrayList<>();

	// Busca lista
	@RequestMapping(value = "/cadastrarCorte", method = RequestMethod.GET)
	public ModelAndView listaOvos() {
		ModelAndView mv = new ModelAndView("corte/cadastrarCorte");
		lista = cr.findAll();
		mv.addObject("listaCorte", lista);
		Iterable<TipoAve> listaTA = tr.findAll();
		mv.addObject("listaAves", listaTA);
		return mv;
	}

	// Novo
	@RequestMapping("/edicaoCorte/novo")
	public String novoCorte(Model model) {
		model.addAttribute("corte", new Corte());
		Iterable<TipoAve> lista = tr.findAll();
		model.addAttribute("listaAves", lista);
		return "corte/editarCorte";
	}

	// Salvar
	@RequestMapping(value = "/edicaoCorte/save", method = RequestMethod.POST)
	public String salvarOvos(@Valid Corte corte, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/edicaoCorte";
		} else {
			Convercoes convercoes = new Convercoes();
			corte.setEntrada2(convercoes.convertDateUStoDataBR((corte.getEntrada())));
			corte.setSaida2(convercoes.convertDateUStoDataBR((corte.getSaida())));
			cr.save(corte);
			attributes.addFlashAttribute("mensagem", "Lote de ovos salvo com sucesso!");
			return "redirect:/cadastrarCorte";
		}
	}

	// Deletar
	@RequestMapping("/cadastrarCorte/delete/{codigo}") // @PathVariable Long id, RedirectAttributes redirectAttrs
	public String deletarCorte(@PathVariable("codigo") Long codigo, RedirectAttributes redirectAttrs) {
		Corte corte = cr.findByCodigo(codigo);
		cr.delete(corte);
		return "redirect:/cadastrarCorte";
	}

	// Editar
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

	// Relatório
	@RequestMapping(value = "/gerarPDFCorte", method = RequestMethod.GET)
	public ModelAndView gerarPDFCorte() {
		List<Corte> list = new ArrayList<>();
		list = Lists.newArrayList(cr.findAll());

		return new ModelAndView(new PdfCorteReportView(), "corteList", list);
	}
	
	// pesquisar
		@RequestMapping(value = "/cadastrarCorte", method = RequestMethod.POST)
		public String pesquisarCorte(String data2, String data4, Model model, @Valid Corte corte,
				BindingResult result, RedirectAttributes attributes) {
			lista = retornaLista(corte, data2, data4);
			model.addAttribute("listaCorte", lista);
			Iterable<TipoAve> listaTA = tr.findAll();
			model.addAttribute("listaAves", listaTA);
			return "corte/cadastrarCorte";
		}

		private List<Corte> retornaLista(Corte corte, String data2, String data4) {
			int key = 0;
			if (corte.getEntrada().length() > 0 && data2.length() > 0) {
				key += 1;
			}
			if (corte.getSaida().length() > 0 && data4.length() > 0) {
				key += 2;
			}
			if (!corte.getTipoave().equals("Todos")) {
				key += 4;
			}

			switch (key) {
			case 0:
				lista = cr.findAll();
				break;
			case 1:
				lista = cr.findByEntradaBetween(corte.getEntrada(), data2);
				break;
			case 2:
				lista = cr.findBySaidaBetween(corte.getSaida(), data4);
				break;
			case 3:
				lista = cr.findByEntradaBetweenAndSaidaBetween(corte.getEntrada(), data2, corte.getSaida(), data4);
				break;
			case 4:
				lista = cr.findByTipoave(corte.getTipoave());
				break;
			case 5:
				lista = cr.findByEntradaBetweenAndTipoave(corte.getEntrada(), data2, corte.getTipoave());
				break;
			case 6:
				lista = cr.findBySaidaBetweenAndTipoave(corte.getSaida(), data4, corte.getTipoave());
				break;
			case 7:
				lista = cr.findByEntradaBetweenAndSaidaBetweenAndTipoave(corte.getEntrada(), data2, corte.getSaida(),
						data4, corte.getTipoave());
				break;
			}
			return lista;
		}
}
