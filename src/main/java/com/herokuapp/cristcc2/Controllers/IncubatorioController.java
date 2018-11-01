package com.herokuapp.cristcc2.Controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.herokuapp.cristcc2.Models.Incubatorio;
import com.herokuapp.cristcc2.Models.TipoAve;
import com.herokuapp.cristcc2.repository.IncubatorioRepository;
import com.herokuapp.cristcc2.repository.TipoAveRepository;

@Controller
public class IncubatorioController {

	@Autowired
	private IncubatorioRepository ir;
	
	@Autowired
	private TipoAveRepository tr;
	
	@RequestMapping(value = "/cadastrarIncubatorio", method = RequestMethod.GET)
	public ModelAndView listaIncubatorio() {
		ModelAndView mv = new ModelAndView("incubatorio/cadastrarIncubatorio");
		Iterable<Incubatorio> lista = ir.findAll();
		mv.addObject("listaIncubatorio", lista);
		return mv;
	}
	
	@RequestMapping(value = "/edicaoIncubatorio", method = RequestMethod.GET)
	public ModelAndView formEdicaoOvos() {		
		ModelAndView mv = new ModelAndView("incubatorio/editarIncubatorio");
		Iterable<TipoAve> lista = tr.findAll();
		mv.addObject("listaAves", lista);
		return mv;
	}
	
	//Salvar
	@RequestMapping(value = "/edicaoIncubatorio", method = RequestMethod.POST)
	public String salvarIncubatorio(@Valid Incubatorio incubatorio, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/edicaoIncubatorio";
		} else {
			ir.save(incubatorio);
			attributes.addFlashAttribute("mensagem", "Lote de ovos salvo com sucesso!");
			return "redirect:/cadastrarIncubatorio";
		}
	}
	
	@RequestMapping("/cadastrarIncubatorio/delete/{codigoIncubatorio}") //@PathVariable Long id, RedirectAttributes redirectAttrs
	public String deletarIncubatorio(@PathVariable("codigoIncubatorio") Long codigoIncubatorio, RedirectAttributes redirectAttrs) {
		Incubatorio incubatorio = ir.findByCodigoIncubatorio(codigoIncubatorio);
		ir.delete(incubatorio);
		return "redirect:/cadastrarIncubatorio";
	}
	
}
