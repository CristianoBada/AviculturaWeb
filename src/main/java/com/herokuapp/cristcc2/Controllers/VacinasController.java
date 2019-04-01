package com.herokuapp.cristcc2.Controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.herokuapp.cristcc2.Models.Vacina;
import com.herokuapp.cristcc2.repository.VacinasRepository;

@Controller
public class VacinasController {

	@Autowired
	private VacinasRepository vr;
	
	//Busca lista
	@RequestMapping(value = "/cadastrarVacinas", method = RequestMethod.GET)
	public String cadastrarVacinas(Model model) {
		model.addAttribute("listaVacinas", vr.findAll());
		return "vacinas/cadastrarVacinas";
	}
	
	@RequestMapping("/edicaoVacinas/novo")
	public String formEdicaoOvos(Model model) {
		model.addAttribute("vacina", new Vacina());
		return "vacinas/editarVacinas";
	}
	
	@RequestMapping("/edicaoVacinas/editar/{codigoVacina}")
	public String editarVacinas(@PathVariable Long codigoVacina, Model model){
		model.addAttribute("vacina", vr.findByCodigoVacina(codigoVacina));
		return "vacinas/editarVacinas";		
	}
	
	@RequestMapping( value = "/editarVacinas/save", method = RequestMethod.POST )
	public String save(@Valid Vacina vacina, BindingResult bindingResult, Model model) {
				
		if( bindingResult.hasErrors() ){
			return "vacinas/editarVacinas";
		} else {
			vr.save(vacina);
			return "redirect:/cadastrarVacinas";			
		}

	}
	
	@RequestMapping("/cadastrarVacinas/delete/{codigoVacina}") //@PathVariable Long id, RedirectAttributes redirectAttrs
	public String deletarVacina(@PathVariable("codigoVacina") Long codigoVacina, RedirectAttributes redirectAttrs) {
		Vacina vacina = vr.findByCodigoVacina(codigoVacina);
		vr.delete(vacina);
		return "redirect:/cadastrarVacinas";
	}
	
	@RequestMapping("/cadastrarVacinas/Vacina/criar")
	public String createVacinas(Model model) {
		model.addAttribute("vacina", new Vacina());
		return "vacinas/criarVacinas";
	}
}
