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
import com.herokuapp.cristcc2.Entidades.Incubatorio;
import com.herokuapp.cristcc2.Entidades.Ovos;
import com.herokuapp.cristcc2.Entidades.TipoAve;
import com.herokuapp.cristcc2.Relatorios.PdfIncubatorioReportView;
import com.herokuapp.cristcc2.Repository.IncubatorioRepository;
import com.herokuapp.cristcc2.Repository.OvosRepository;
import com.herokuapp.cristcc2.Repository.TipoAveRepository;
import com.herokuapp.cristcc2.Uteis.Convercoes;

@Controller
public class IncubatorioController {

	@Autowired
	private IncubatorioRepository ir;

	@Autowired
	private TipoAveRepository tr;
	
	@Autowired
	private OvosRepository ovr;

	// Inicio
	@RequestMapping(value = "/cadastrarIncubatorio", method = RequestMethod.GET)
	public ModelAndView listaIncubatorio() {
		ModelAndView mv = new ModelAndView("incubatorio/cadastrarIncubatorio");
		Iterable<Incubatorio> lista = ir.findAll();
		mv.addObject("listaIncubatorio", lista);
		return mv;
	}

	// Novo
	@RequestMapping("/edicaoIncubatorio/novo")
	public String novoPostura(Model model) {
		model.addAttribute("incubatorio", new Incubatorio());
		Iterable<TipoAve> lista = tr.findAll();
		model.addAttribute("listaAves", lista);
		Iterable<Ovos> lista2 = ovr.findAll();
		model.addAttribute("listaOvos", lista2);
		return "incubatorio/editarIncubatorio";
	}

	// Salvar
	@RequestMapping(value = "/edicaoIncubatorio/save", method = RequestMethod.POST)
	public String salvarIncubatorio(@Valid Incubatorio incubatorio, BindingResult result,
			RedirectAttributes attributes) {
		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/edicaoIncubatorio";
		} else {
			Convercoes convercoes = new Convercoes();
			incubatorio.setInicio(convercoes.convertDateUStoDataBR(incubatorio.getInicio()));
			ir.save(incubatorio);
			attributes.addFlashAttribute("mensagem", "Lote de ovos salvo com sucesso!");
			return "redirect:/cadastrarIncubatorio";
		}
	}

	// Deletar
	@RequestMapping("/cadastrarIncubatorio/delete/{codigo}") // @PathVariable Long id, RedirectAttributes redirectAttrs
	public String deletarIncubatorio(@PathVariable("codigo") Long codigo, RedirectAttributes redirectAttrs) {
		Incubatorio incubatorio = ir.findByCodigo(codigo);
		ir.delete(incubatorio);
		return "redirect:/cadastrarIncubatorio";
	}

	// Editar
	@RequestMapping("/edicaoIncubatorio/editar/{codigo}")
	public String editarIncubatorio(@PathVariable Long codigo, Model model) {
		Incubatorio incubatorio = ir.findByCodigo(codigo);

		Convercoes convercoes = new Convercoes();
		incubatorio.setInicio(convercoes.convertDateBRtoDataUS(incubatorio.getInicio()));

		Iterable<TipoAve> lista = tr.findAll();
		model.addAttribute("listaAves", lista);
		
		Iterable<Ovos> lista2 = ovr.findAll();
		model.addAttribute("listaOvos", lista2);

		model.addAttribute("incubatorio", incubatorio);

		return "incubatorio/editarIncubatorio";
	}

	// Relatório
	@RequestMapping(value = "/gerarPDFIncubatorio", method = RequestMethod.GET)
	public ModelAndView gerarPDFIncubatorio() {
		List<Incubatorio> list = new ArrayList<>();
		list = Lists.newArrayList(ir.findAll());

		return new ModelAndView(new PdfIncubatorioReportView(), "incubatorioList", list);
	}
}
