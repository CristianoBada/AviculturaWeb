package com.herokuapp.cristcc2.Controllers;

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

import com.herokuapp.cristcc2.Models.Incubatorio;
import com.herokuapp.cristcc2.Models.Postura;
import com.herokuapp.cristcc2.Models.TipoAve;
import com.herokuapp.cristcc2.Uteis.Convercoes;
import com.herokuapp.cristcc2.repository.IncubatorioRepository;
import com.herokuapp.cristcc2.repository.TipoAveRepository;

@Controller
public class IncubatorioController {

	@Autowired
	private IncubatorioRepository ir;

	@Autowired
	private TipoAveRepository tr;

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

		model.addAttribute("incubatorio", incubatorio);

		return "incubatorio/editarIncubatorio";
	}
}
