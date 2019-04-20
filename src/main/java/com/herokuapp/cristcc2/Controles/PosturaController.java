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
import com.herokuapp.cristcc2.Entidades.Postura;
import com.herokuapp.cristcc2.Entidades.TipoAve;
import com.herokuapp.cristcc2.Relatorios.PdfPosturaReportView;
import com.herokuapp.cristcc2.Repository.PosturaRepository;
import com.herokuapp.cristcc2.Repository.TipoAveRepository;
import com.herokuapp.cristcc2.Uteis.Convercoes;

@Controller
public class PosturaController {

	@Autowired
	private PosturaRepository pr;
	
	@Autowired
	private TipoAveRepository tr;
	
	//Inicio
	@RequestMapping(value = "/cadastrarPostura", method = RequestMethod.GET)
	public ModelAndView listaPostura() {
		ModelAndView mv = new ModelAndView("postura/cadastrarPostura");
		Iterable<Postura> lista = pr.findAll();
		mv.addObject("listaPostura", lista);
		return mv;
	}
	
	//Novo
	@RequestMapping("/edicaoPostura/novo")
	public String novoPostura(Model model) {
		model.addAttribute("postura", new Postura());
		Iterable<TipoAve> lista = tr.findAll();
		model.addAttribute("listaAves", lista);
		return "postura/editarPostura";
	}
	
	//Salvar
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
	
	//Deletar
	@RequestMapping("/cadastrarPostura/delete/{codigo}") 
	public String deletarPostura(@PathVariable("codigo") Long codigo, RedirectAttributes redirectAttrs) {
		Postura postura = pr.findByCodigo(codigo);
		pr.delete(postura);
		return "redirect:/cadastrarPostura";
	}
	
	
	//Editar
	@RequestMapping("/edicaoPostura/editar/{codigo}")
	public String editarPostura(@PathVariable Long codigo, Model model) {
		Postura postura = pr.findByCodigo(codigo);

		Convercoes convercoes = new Convercoes();
		postura.setEntrada(convercoes.convertDateBRtoDataUS(postura.getEntrada()));
		postura.setSaida(convercoes.convertDateBRtoDataUS(postura.getSaida()));

		Iterable<TipoAve> lista = tr.findAll();
		model.addAttribute("listaAves", lista);
		
		model.addAttribute("postura", postura);
		
		return "postura/editarPostura";
	}
	
	//Relatório
		@RequestMapping(value = "/gerarPDFPostura", method = RequestMethod.GET)
		public ModelAndView gerarPDFPostura() {
			List<Postura> list = new ArrayList<>();
			list = Lists.newArrayList(pr.findAll());

			return new ModelAndView(new PdfPosturaReportView(), "posturaList", list);
		}
}
