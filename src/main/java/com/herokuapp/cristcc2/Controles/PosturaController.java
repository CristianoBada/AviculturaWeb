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

	private List<Postura> lista = new ArrayList<>();

	// Inicio
	@RequestMapping(value = "/cadastrarPostura", method = RequestMethod.GET)
	public ModelAndView listaPostura() {
		ModelAndView mv = new ModelAndView("postura/cadastrarPostura");
		lista = pr.findAll();
		mv.addObject("listaPostura", lista);
		return mv;
	}

	// Novo
	@RequestMapping("/edicaoPostura/novo")
	public String novoPostura(Model model) {
		model.addAttribute("postura", new Postura());
		Iterable<TipoAve> listaTA = tr.findAll();
		model.addAttribute("listaAves", listaTA);
		return "postura/editarPostura";
	}

	// Salvar
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

	// Deletar
	@RequestMapping("/cadastrarPostura/delete/{codigo}")
	public String deletarPostura(@PathVariable("codigo") Long codigo, RedirectAttributes redirectAttrs) {
		Postura postura = pr.findByCodigo(codigo).get(0);
		pr.delete(postura);
		return "redirect:/cadastrarPostura";
	}

	// Editar
	@RequestMapping("/edicaoPostura/editar/{codigo}")
	public String editarPostura(@PathVariable Long codigo, Model model) {
		Postura postura = pr.findByCodigo(codigo).get(0);

		Convercoes convercoes = new Convercoes();
		postura.setEntrada(convercoes.convertDateBRtoDataUS(postura.getEntrada()));
		postura.setSaida(convercoes.convertDateBRtoDataUS(postura.getSaida()));

		Iterable<TipoAve> listaTA = tr.findAll();
		model.addAttribute("listaAves", listaTA);

		model.addAttribute("postura", postura);

		return "postura/editarPostura";
	}

	// Relatório
	@RequestMapping(value = "/gerarPDFPostura", method = RequestMethod.GET)
	public ModelAndView gerarPDFPostura() {
		return new ModelAndView(new PdfPosturaReportView(), "posturaList", lista);
	}

	// pesquisar
	@RequestMapping(value = "/cadastrarPostura", method = RequestMethod.POST)
	public String pesquisarPostura(String data2, String data4, Model model, @Valid Postura postura,
			BindingResult result, RedirectAttributes attributes) {
		lista = retornaLista(postura, data2, data4);
		model.addAttribute("listaPostura", lista);
		return "postura/cadastrarPostura";
	}

	private List<Postura> retornaLista(Postura postura, String data2, String data4) {
		int key = 0;
		if (postura.getCodigo() > 0) {
			key += 1;
		}
		if (postura.getEntrada().length() > 0 && data2.length() > 0) {
			Convercoes convercoes = new Convercoes();
			postura.setEntrada(convercoes.convertDateUStoDataBR(postura.getEntrada()));
			data2 = convercoes.convertDateUStoDataBR(data2);
			key += 2;
		}
		if (postura.getSaida().length() > 0 && data4.length() > 0) {
			Convercoes convercoes = new Convercoes();
			postura.setSaida(convercoes.convertDateUStoDataBR(postura.getSaida()));
			data4 = convercoes.convertDateUStoDataBR(data4);
			key += 4;
		}
		
		switch (key) {
		case 0:
			return pr.findAll();
		case 1:
			return pr.findByCodigo(postura.getCodigo());
		case 2:
			return pr.findByEntradaBetween(postura.getEntrada(), data2);
		case 3:
			return pr.findByCodigoAndEntradaBetween(postura.getCodigo(), postura.getEntrada(), data2);
		case 4:
			return pr.findBySaidaBetween(postura.getSaida(), data4);
		case 5: 
			return pr.findByCodigoAndSaidaBetween(postura.getCodigo(), postura.getSaida(), data4);
		case 6: 
			return pr.findByEntradaBetweenAndSaidaBetween(postura.getEntrada(), data2, postura.getSaida(), data4);
		case 7:
			return pr.findByCodigoAndEntradaBetweenAndSaidaBetween(postura.getCodigo(), postura.getEntrada(), data2, postura.getSaida(), data4);
		}
		return lista;
	}
}
