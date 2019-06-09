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

import com.herokuapp.cristcc2.Entidades.Incubatorio;
import com.herokuapp.cristcc2.Entidades.Ovos;
import com.herokuapp.cristcc2.Entidades.Postura;
import com.herokuapp.cristcc2.Entidades.TipoAve;
import com.herokuapp.cristcc2.Relatorios.PdfOvosReportView;
import com.herokuapp.cristcc2.Repository.IncubatorioRepository;
import com.herokuapp.cristcc2.Repository.OvosRepository;
import com.herokuapp.cristcc2.Repository.PosturaRepository;
import com.herokuapp.cristcc2.Repository.TipoAveRepository;
import com.herokuapp.cristcc2.Uteis.Convercoes;

@Controller
public class OvosController {

	@Autowired
	private OvosRepository ovosr;

	@Autowired
	private TipoAveRepository tr;

	@Autowired
	private PosturaRepository pr;

	@Autowired
	private IncubatorioRepository incubatorioRepository;

	private List<Ovos> lista = new ArrayList<>();
	
	private Ovos ovosMen;

	// Salvar
	@RequestMapping(value = "/edicaoOvos/save", method = RequestMethod.POST)
	public String salvarOvos(@Valid Ovos ovos, BindingResult result, RedirectAttributes attributes) {
		ovosMen = ovos;
		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/edicaoOvos";
		} else {
			if (ovos.getQuantidade() < 1) {
				attributes.addFlashAttribute("mensagem", "Quantidade não pode ser menor ou igual a zero.");
				return "redirect:/edicaoOvos/novo";
			}
			
			Convercoes convercoes = new Convercoes();
			ovos.setTipoave(pr.findByCodigo(ovos.getPostura()).getTipoave());
			ovos.setData2(convercoes.convertDateUStoDataBR(ovos.getData()));
			ovosr.save(ovos);
			attributes.addFlashAttribute("mensagem", "Lote de ovos salvo com sucesso!");
			ovosMen = null;
			return "redirect:/cadastrarOvos";
		}
	}

	// Busca lista
	@RequestMapping(value = "/cadastrarOvos", method = RequestMethod.GET)
	public ModelAndView listaOvos() {
		ModelAndView mv = new ModelAndView("ovos/cadastrarOvos");
		lista = ovosr.findAll();
		mv.addObject("listaOvos", lista);
		Iterable<TipoAve> listaTA = tr.findAll();
		mv.addObject("listaAves", listaTA);
		ovosMen = null;
		return mv;
	}

	@RequestMapping("/edicaoOvos/novo")
	public String novoOvos(Model model) {
		if (ovosMen == null)
			ovosMen = new Ovos();
		model.addAttribute("ovos", ovosMen);
		Iterable<TipoAve> lista = tr.findAll();
		model.addAttribute("listaAves", lista);
		Iterable<Postura> lista2 = pr.findAll();
		model.addAttribute("listaPostura", lista2);
		return "ovos/editarOvos";
	}

	@RequestMapping("/cadastrarOvos/delete/{codigo}") // @PathVariable Long id, RedirectAttributes redirectAttrs
	public String deletarOvos(@PathVariable("codigo") Long codigo, RedirectAttributes redirectAttrs) {
		Ovos ovos = ovosr.findByCodigo(codigo);
		List<Incubatorio> incubatorios = incubatorioRepository.findByOvos(ovos.getCodigo());
		if (incubatorios.size() > 0) {
			redirectAttrs.addFlashAttribute("mensagem", "Esse lote esta sendo usado na incubação.");
		} else {
			ovosr.delete(ovos);
			redirectAttrs.addFlashAttribute("mensagem", "Lote deletado com sucesso.");
		}
		return "redirect:/cadastrarOvos";
	}

	@RequestMapping("/edicaoOvos/editar/{codigo}")
	public String editarOvos(@PathVariable Long codigo, Model model) {
		ovosMen = ovosr.findByCodigo(codigo);

		Iterable<TipoAve> lista = tr.findAll();
		model.addAttribute("listaAves", lista);

		model.addAttribute("ovos", ovosMen);

		Iterable<Postura> lista2 = pr.findAll();
		model.addAttribute("listaPostura", lista2);

		return "ovos/editarOvos";
	}

	// Relatório
	@RequestMapping(value = "/gerarPDFOvos", method = RequestMethod.GET)
	public ModelAndView gerarPDFOvos() {
		return new ModelAndView(new PdfOvosReportView(), "ovosList", lista);
	}

	// pesquisar
	@RequestMapping(value = "/cadastrarOvos", method = RequestMethod.POST)
	public String pesquisarOvos(String data2, Model model, @Valid Ovos ovos, BindingResult result,
			RedirectAttributes attributes) {
		lista = retornaLista(ovos, data2);
		model.addAttribute("listaOvos", lista);
		Iterable<TipoAve> listaTA = tr.findAll();
		model.addAttribute("listaAves", listaTA);
		return "ovos/cadastrarOvos";
	}

	private List<Ovos> retornaLista(Ovos ovos, String data2) {
		int key = 0;

		if (ovos.getData().length() > 0 && data2.length() > 0) {
			key += 1;
		}
		if (!ovos.getTipoave().equals("Todos")) {
			key += 2;
		}

		switch (key) {
		case 0:
			lista = ovosr.findAll();
			break;
		case 1:
			lista = ovosr.findByDataBetween(ovos.getData(), data2);
			break;
		case 2:
			lista = ovosr.findByTipoave(ovos.getTipoave());
			break;
		case 3:
			lista = ovosr.findByDataBetweenAndTipoave(ovos.getData(), data2, ovos.getTipoave());
			break;

		}
		return lista;
	}
}
