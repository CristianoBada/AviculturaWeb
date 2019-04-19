package com.herokuapp.cristcc2.Controles;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.herokuapp.cristcc2.Entidades.TipoAve;
import com.herokuapp.cristcc2.Repository.TipoAveRepository;

@Controller
public class TipoAveController {

	@Autowired
	private TipoAveRepository tr;
	
	@RequestMapping("/cadastrarNovaAve")
	public String formCadastroAves() {
		return "aves/tipoAvesCadastrar";
	}
	
	//Salvar
		@RequestMapping(value = "/cadastrarNovaAve", method = RequestMethod.POST)
		public String salvarTipoAve(@Valid TipoAve tipoAve, BindingResult result, RedirectAttributes attributes) {
			if (result.hasErrors()) {
				attributes.addFlashAttribute("mensagem", "Verifique os campos!");
				return "redirect:/cadastrarNovaAve";
			} else {
				tr.save(tipoAve);
				attributes.addFlashAttribute("mensagem", "Lote de ovos salvo com sucesso!");
				return "redirect:/";
			}
		}
}
