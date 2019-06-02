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
		Iterable<TipoAve> listaTA = tr.findAll();
		mv.addObject("listaAves", listaTA);
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
			postura.setEntrada2(convercoes.convertDateUStoDataBR((postura.getEntrada())));
			postura.setSaida2(convercoes.convertDateUStoDataBR((postura.getSaida())));
			pr.save(postura);

			return "redirect:/cadastrarPostura";
		}
	}

	// Deletar
	@RequestMapping("/cadastrarPostura/delete/{codigo}")
	public String deletarPostura(@PathVariable("codigo") Long codigo, RedirectAttributes redirectAttrs) {
		Postura postura = pr.findByCodigo(codigo);
		pr.delete(postura);
		return "redirect:/cadastrarPostura";
	}

	// Editar
	@RequestMapping("/edicaoPostura/editar/{codigo}")
	public String editarPostura(@PathVariable Long codigo, Model model) {
		Postura postura = pr.findByCodigo(codigo);
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
		Iterable<TipoAve> listaTA = tr.findAll();
		model.addAttribute("listaAves", listaTA);
		return "postura/cadastrarPostura";
	}

	private List<Postura> retornaLista(Postura postura, String data2, String data4) {
		int key = 0;
		if (postura.getCodigo() > 0) {
			key += 1;
		}
		if (postura.getEntrada().length() > 0 && data2.length() > 0) {
			key += 2;
		}
		if (postura.getSaida().length() > 0 && data4.length() > 0) {
			key += 4;
		}
		if (!postura.getTipoave().equals("Todos")) {
			key += 8;
		}

		switch (key) {
		case 0:
			lista = pr.findAll();
			break;
		case 1:
			lista = new ArrayList<>();
			lista.add(pr.findByCodigo(postura.getCodigo()));
			break;
		case 2:
			lista = pr.findByEntradaBetween(postura.getEntrada(), data2);
			break;
		case 3:
			lista = pr.findByCodigoAndEntradaBetween(postura.getCodigo(), postura.getEntrada(), data2);
			break;
		case 4:
			lista = pr.findBySaidaBetween(postura.getSaida(), data4);
			break;
		case 5:
			lista = pr.findByCodigoAndSaidaBetween(postura.getCodigo(), postura.getSaida(), data4);
			break;
		case 6:
			lista = pr.findByEntradaBetweenAndSaidaBetween(postura.getEntrada(), data2, postura.getSaida(), data4);
			break;
		case 7:
			lista = pr.findByCodigoAndEntradaBetweenAndSaidaBetween(postura.getCodigo(), postura.getEntrada(), data2,
					postura.getSaida(), data4);
			break;
		case 8:
			lista = pr.findByTipoave(postura.getTipoave());
			break;
		case 9:
			lista = pr.findByCodigoAndTipoave(postura.getCodigo(), postura.getTipoave());
			break;
		case 10:
			lista = pr.findByEntradaBetweenAndTipoave(postura.getEntrada(), data2, postura.getTipoave());
			break;
		case 11:
			lista = pr.findByCodigoAndEntradaBetweenAndTipoave(postura.getCodigo(), postura.getEntrada(), data2,
					postura.getTipoave());
			break;
		case 12:
			lista = pr.findBySaidaBetweenAndTipoave(postura.getSaida(), data4, postura.getTipoave());
			break;
		case 13:
			lista = pr.findByCodigoAndSaidaBetweenAndTipoave(postura.getCodigo(), postura.getSaida(), data4,
					postura.getTipoave());
			break;
		case 14:
			lista = pr.findByEntradaBetweenAndSaidaBetweenAndTipoave(postura.getEntrada(), data2, postura.getSaida(),
					data4, postura.getTipoave());
			break;
		case 15:
			lista = pr.findByCodigoAndEntradaBetweenAndSaidaBetweenAndTipoave(postura.getCodigo(), postura.getEntrada(),
					data2, postura.getSaida(), data4, postura.getTipoave());
			break;
		}
		return lista;
	}
}
