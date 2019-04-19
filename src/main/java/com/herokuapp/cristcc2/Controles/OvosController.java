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

import com.herokuapp.cristcc2.Entidades.Ovos;
import com.herokuapp.cristcc2.Entidades.TipoAve;
import com.herokuapp.cristcc2.Repository.OvosRepository;
import com.herokuapp.cristcc2.Repository.TipoAveRepository;
import com.herokuapp.cristcc2.Uteis.Convercoes;

@Controller
public class OvosController {
	
	@Autowired
	private OvosRepository ovosr;
	
	@Autowired
	private TipoAveRepository tr;
	
	//Salvar
	@RequestMapping(value = "/edicaoOvos/save", method = RequestMethod.POST)
	public String salvarOvos(@Valid Ovos ovos, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/edicaoOvos";
		} else {
			Convercoes convercoes = new Convercoes();
			ovos.setData((convercoes.convertDateUStoDataBR((ovos.getData()))));
			ovosr.save(ovos);
			attributes.addFlashAttribute("mensagem", "Lote de ovos salvo com sucesso!");
			return "redirect:/cadastrarOvos";
		}
	}
	
	//Busca lista
	@RequestMapping(value = "/cadastrarOvos", method = RequestMethod.GET)
	public ModelAndView listaOvos() {
		ModelAndView mv = new ModelAndView("ovos/cadastrarOvos");
		Iterable<Ovos> lista = ovosr.findAll();
		mv.addObject("listaOvos", lista);
		return mv;
	}
	
	@RequestMapping("/edicaoOvos/novo")
	public String novoOvos(Model model) {
		model.addAttribute("ovos", new Ovos());
		Iterable<TipoAve> lista = tr.findAll();
		model.addAttribute("listaAves", lista);
		return "ovos/editarOvos";
	}
	
	@RequestMapping("/cadastrarOvos/delete/{codigo}") //@PathVariable Long id, RedirectAttributes redirectAttrs
	public String deletarOvos(@PathVariable("codigo") Long codigo, RedirectAttributes redirectAttrs) {
		Ovos ovos = ovosr.findByCodigo(codigo);
		ovosr.delete(ovos);
		return "redirect:/cadastrarOvos";
	}
	
	@RequestMapping("/edicaoOvos/editar/{codigo}")
	public String editarOvos(@PathVariable Long codigo, Model model) {
		Ovos ovos = ovosr.findByCodigo(codigo);

		Convercoes convercoes = new Convercoes();
		ovos.setData(convercoes.convertDateBRtoDataUS(ovos.getData()));

		Iterable<TipoAve> lista = tr.findAll();
		model.addAttribute("listaAves", lista);
		
		model.addAttribute("ovos", ovos);
		
		return "ovos/editarOvos";
	}
}
