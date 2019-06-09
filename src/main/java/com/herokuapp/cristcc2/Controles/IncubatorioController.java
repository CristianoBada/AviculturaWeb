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
	
	private List<Incubatorio> lista = new ArrayList<>();
	
	private Incubatorio incubatorioMen;

	// Inicio
	@RequestMapping(value = "/cadastrarIncubatorio", method = RequestMethod.GET)
	public ModelAndView listaIncubatorio() {
		ModelAndView mv = new ModelAndView("incubatorio/cadastrarIncubatorio");
		lista = ir.findAll();
		mv.addObject("listaIncubatorio", lista);
		Iterable<TipoAve> lista2 = tr.findAll();
		mv.addObject("listaAves", lista2);
		incubatorioMen = null;
		return mv;
	}

	// Novo
	@RequestMapping("/edicaoIncubatorio/novo")
	public String novoPostura(Model model) {
		if (incubatorioMen == null)
			incubatorioMen = new Incubatorio();
		model.addAttribute("incubatorio", incubatorioMen);
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
		incubatorioMen = incubatorio;
		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/edicaoIncubatorio/novo";
		} else {
			if (incubatorio.getTemperatura() > 38.0 || incubatorio.getTemperatura() < 36.8 ) {
				attributes.addFlashAttribute("mensagem", "A temperatura tem que ser entre 36,8°C e 38°C. Recomenda-se entre 37,4°C e 37,8°C");
				return "redirect:/edicaoIncubatorio/novo";
			}
			if (incubatorio.getMortalidade() != null && incubatorio.getMortalidade() < 0) {
				attributes.addFlashAttribute("mensagem", "o numero mortalidade não pode ser negativo.");
				return "redirect:/edicaoIncubatorio/novo";
			}
			
			if (incubatorio.getUmidade() != null && incubatorio.getUmidade() != 0 && (incubatorio.getUmidade() < 65 || incubatorio.getUmidade() > 75)) {
				attributes.addFlashAttribute("mensagem", "A umidade relativa do ar deve se manter entre 65% e 75%.");
				return "redirect:/edicaoIncubatorio/novo";
			}
			Convercoes convercoes = new Convercoes();
			incubatorio.setInicio2(convercoes.convertDateUStoDataBR(incubatorio.getInicio()));
			incubatorio.setTipoave(ovr.findByCodigo(incubatorio.getOvos()).getTipoave());
			incubatorio.setTempo(tr.findByNomeAve(incubatorio.getTipoave()).getTempoChocagem());
			ir.save(incubatorio);
			attributes.addFlashAttribute("mensagem", "Lote de ovos salvo com sucesso!");
			incubatorioMen = null;
			return "redirect:/cadastrarIncubatorio";
		}
	}

	// Deletar
	@RequestMapping("/cadastrarIncubatorio/delete/{codigo}") 
	public String deletarIncubatorio(@PathVariable("codigo") Long codigo, RedirectAttributes redirectAttrs) {
		Incubatorio incubatorio = ir.findByCodigo(codigo);
		ir.delete(incubatorio);
		redirectAttrs.addFlashAttribute("mensagem", "Lote deletado com sucesso.");
		return "redirect:/cadastrarIncubatorio";
	}

	// Editar
	@RequestMapping("/edicaoIncubatorio/editar/{codigo}")
	public String editarIncubatorio(@PathVariable Long codigo, Model model) {
		incubatorioMen = ir.findByCodigo(codigo);

		Iterable<TipoAve> lista = tr.findAll();
		model.addAttribute("listaAves", lista);
		
		Iterable<Ovos> lista2 = ovr.findAll();
		model.addAttribute("listaOvos", lista2);

		model.addAttribute("incubatorio", incubatorioMen);

		return "incubatorio/editarIncubatorio";
	}

	// Relatório
	@RequestMapping(value = "/gerarPDFIncubatorio", method = RequestMethod.GET)
	public ModelAndView gerarPDFIncubatorio() {
		List<Incubatorio> list = new ArrayList<>();
		list = Lists.newArrayList(ir.findAll());

		return new ModelAndView(new PdfIncubatorioReportView(), "incubatorioList", list);
	}
	
	// pesquisar
		@RequestMapping(value = "/cadastrarIncubatorio", method = RequestMethod.POST)
		public String pesquisarIncubatorio(String data2, Model model, @Valid Incubatorio incubatorio, BindingResult result,
				RedirectAttributes attributes) {
			lista = retornaLista(incubatorio, data2);
			model.addAttribute("listaIncubatorio", lista);
			Iterable<TipoAve> listaTA = tr.findAll();
			model.addAttribute("listaAves", listaTA);
			return "incubatorio/cadastrarIncubatorio";
		}

		private List<Incubatorio> retornaLista(Incubatorio incubatorio, String data2) {
			int key = 0;

			if (incubatorio.getInicio().length() > 0 && data2.length() > 0) {
				key += 1;
			}
			if (!incubatorio.getTipoave().equals("Todos")) {
				key += 2;
			}
			System.out.println(key);
			switch (key) {
			case 0:
				lista = ir.findAll();
				break;
			case 1:
				lista = ir.findByInicioBetween(incubatorio.getInicio(), data2);
				break;
			case 2:
				lista = ir.findByTipoave(incubatorio.getTipoave());
				break;
			case 3:
				lista = ir.findByInicioBetweenAndTipoave(incubatorio.getInicio(), data2, incubatorio.getTipoave());
				break;

			}
			return lista;
		}
}
