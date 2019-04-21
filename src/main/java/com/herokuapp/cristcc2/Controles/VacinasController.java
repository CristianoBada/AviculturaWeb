package com.herokuapp.cristcc2.Controles;

import java.text.ParseException;
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
import com.herokuapp.cristcc2.Entidades.Vacina;
import com.herokuapp.cristcc2.Relatorios.PdfVacinaReportView;
import com.herokuapp.cristcc2.Repository.VacinasRepository;
import com.herokuapp.cristcc2.Uteis.Convercoes;

@Controller
public class VacinasController {

	@Autowired
	private VacinasRepository vr;

	//Iniciar
	@RequestMapping(value = "/cadastrarVacinas", method = RequestMethod.GET)
	public String cadastrarVacinas(Model model) {
		model.addAttribute("listaVacinas", vr.findAll());
		return "vacinas/cadastrarVacinas";
	}

	//pesquisar
	@RequestMapping(value = "/cadastrarVacinas", method = RequestMethod.POST)
	public String pesquisarVacinas(String data2, Model model, @Valid Vacina vacina, BindingResult result,
			RedirectAttributes attributes) {	
		model.addAttribute("listaVacinas", retornaLista(vacina, data2));
		return "vacinas/cadastrarVacinas";
	}
	
	private List<Vacina> retornaLista(Vacina vacina, String data2){
		int key = 0;
		if (vacina.getCodigo() > 0) {
			key += 1;
		}
		if (vacina.getData().length() > 0 && data2.length() > 0) {
			Convercoes convercoes = new Convercoes();
			vacina.setData(convercoes.convertDateUStoDataBR(vacina.getData()));
			data2 = convercoes.convertDateUStoDataBR(data2);
			key += 2;
		}	
		if (vacina.getTipo().length() > 0) {
			key += 4;
		}
		
		List<Vacina> list;
		
		switch (key) {
		case 1:
			list = vr.findByCodigo(vacina.getCodigo());
			break;
		case 2:
			list = vr.findByDataBetween(vacina.getData(), data2);
			break;
		case 3:
			list = vr.findByCodigoAndDataBetween(vacina.getCodigo(), vacina.getData(), data2);
			break;
		case 4:
			list = vr.findByTipo(vacina.getTipo());
			break;
		case 5:
			list = vr.findByCodigoAndTipo(vacina.getCodigo(), vacina.getTipo());
			break;
		case 6: 
			list = vr.findByDataBetweenAndTipo(vacina.getData(), data2, vacina.getTipo());
			break;
		case 7:
			list = vr.findByCodigoAndDataBetweenAndTipo(vacina.getCodigo(), vacina.getData(), data2, vacina.getTipo());
			break;
		default:
			list = (List<Vacina>)vr.findAll();
			break;
		}
		return list;
	}

	//Novo
	@RequestMapping("/edicaoVacinas/novo")
	public String edicaoVacina(Model model) {
		model.addAttribute("vacina", new Vacina());
		return "vacinas/editarVacinas";
	}

	//Editar
	@RequestMapping("/edicaoVacinas/editar/{codigo}")
	public String editarVacinas(@PathVariable Long codigo, Model model) {
		Vacina vacina = vr.findByCodigo(codigo).get(0);

		Convercoes convercoes = new Convercoes();
		vacina.setData(convercoes.convertDateBRtoDataUS(vacina.getData()));

		model.addAttribute("vacina", vacina);
		return "vacinas/editarVacinas";
	}

	//Salvar
	@RequestMapping(value = "/edicaoVacinas/save", method = RequestMethod.POST)
	public String salvarVacina(@Valid Vacina vacina, BindingResult bindingResult, Model model) throws ParseException {

		if (bindingResult.hasErrors()) {
			return "vacinas/editarVacinas";
		} else {
			Convercoes convercoes = new Convercoes();
			vacina.setData(convercoes.convertDateUStoDataBR((vacina.getData())));

			vr.save(vacina);
			return "redirect:/cadastrarVacinas";
		}

	}
	
	//Deletar
	@RequestMapping("/cadastrarVacinas/delete/{codigo}") // @PathVariable Long id, RedirectAttributes redirectAttrs
	public String deletarVacina(@PathVariable("codigo") Long codigo, RedirectAttributes redirectAttrs) {
		Vacina vacina = vr.findByCodigo(codigo).get(0);
		vr.delete(vacina);
		return "redirect:/cadastrarVacinas";
	}

	//Relatório
	@RequestMapping(value = "/gerarPDFVacinas", method = RequestMethod.GET)
	public ModelAndView gerarPDFVacinas() {
		List<Vacina> list = new ArrayList<>();
		list = Lists.newArrayList(vr.findAll());

		return new ModelAndView(new PdfVacinaReportView(), "vacinaList", list);
	}
}
