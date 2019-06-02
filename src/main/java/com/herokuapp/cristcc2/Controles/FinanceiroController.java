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
import com.herokuapp.cristcc2.Entidades.Financeiro;
import com.herokuapp.cristcc2.Entidades.Produtos;
import com.herokuapp.cristcc2.Relatorios.PdfFinanceiroReportView;
import com.herokuapp.cristcc2.Repository.FinanceiroRepository;
import com.herokuapp.cristcc2.Repository.ProdutoRepository;
import com.herokuapp.cristcc2.Uteis.Convercoes;

@Controller
public class FinanceiroController {

	@Autowired
	private FinanceiroRepository fr;

	@Autowired
	private ProdutoRepository pr;
	
	private List<Financeiro> lista = new ArrayList<>();

	// Inicio
	@RequestMapping(value = "/cadastrarFinanceiro", method = RequestMethod.GET)
	public ModelAndView listaPostura() {
		ModelAndView mv = new ModelAndView("financeiro/cadastrarFinanceiro");
		Iterable<Financeiro> lista = fr.findAll();
		mv.addObject("listaFinanceiro", lista);
		Iterable<Produtos> lista2 = pr.findAll();
		mv.addObject("listaProdutos", lista2);
		return mv;
	}

	// Novo
	@RequestMapping("/edicaoFinanceiro/novo")
	public String edicaoFinanceiro(Model model) {
		model.addAttribute("financeiro", new Financeiro());
		Iterable<Produtos> lista = pr.findAll();
		model.addAttribute("listaProdutos", lista);
		model.addAttribute("itemSelecionado", "entrada");
		model.addAttribute("item", "saida");
		return "financeiro/editarFinanceiro";
	}

	// Salvar
	@RequestMapping(value = "/edicaoFinanceiro/save", method = RequestMethod.POST)
	public String salvarvacinas(@Valid Financeiro financeiro, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/edicaoFinanceiro";
		} else {
			Convercoes convercoes = new Convercoes();
			financeiro.setData2(convercoes.convertDateUStoDataBR((financeiro.getData())));
			fr.save(financeiro);
			attributes.addFlashAttribute("mensagem", "Financeiro salvo com sucesso!");
			return "redirect:/cadastrarFinanceiro";
		}
	}

	// Detelar
	@RequestMapping("/cadastrarFinanceiro/delete/{codigo}") // @PathVariable Long id, RedirectAttributes redirectAttrs
	public String deletarPostura(@PathVariable("codigo") Long codigo, RedirectAttributes redirectAttrs) {
		Financeiro fin = fr.findByCodigo(codigo);
		fr.delete(fin);
		return "redirect:/cadastrarFinanceiro";
	}

	// Editar
	@RequestMapping("/edicaoFinanceiro/editar/{codigo}")
	public String editarFinanceiro(@PathVariable Long codigo, Model model) {
		Financeiro fin = fr.findByCodigo(codigo);

		Convercoes convercoes = new Convercoes();
		fin.setData(convercoes.convertDateBRtoDataUS(fin.getData()));

		model.addAttribute("financeiro", fin);
		Iterable<Produtos> lista = pr.findAll();
		model.addAttribute("listaProdutos", lista);

		if (fin.getEntrasaida().equals("entrada")) {
			model.addAttribute("itemSelecionado", "entrada");
			model.addAttribute("item", "saida");
		} else {
			model.addAttribute("itemSelecionado", "saida");
			model.addAttribute("item", "entrada");
		}

		return "financeiro/editarFinanceiro";
	}

	// Relatório
	@RequestMapping(value = "/gerarPDFFinanceiro", method = RequestMethod.GET)
	public ModelAndView gerarPDFFinanceiro() {
		List<Financeiro> list = new ArrayList<>();
		list = Lists.newArrayList(fr.findAll());

		return new ModelAndView(new PdfFinanceiroReportView(), "financeiroList", list);
	}

	// pesquisar
	@RequestMapping(value = "/cadastrarFinanceiro", method = RequestMethod.POST)
	public String pesquisarFinanceiro(String data2, Model model, @Valid Financeiro financeiro,
			BindingResult result, RedirectAttributes attributes) {
		lista = retornaLista(financeiro, data2);
		model.addAttribute("listaFinanceiro", lista);
		Iterable<Produtos> lista2 = pr.findAll();
		model.addAttribute("listaProdutos", lista2);
		return "financeiro/cadastrarFinanceiro";
	}

	private List<Financeiro> retornaLista(Financeiro financeiro, String data2) {
		int key = 0;
		if (!financeiro.getNome().toString().equals("Todos")) {
			key += 1;
		}
		if (financeiro.getData().length() > 0 && data2.length() > 0) {
			key += 2;
		}
		System.out.println(financeiro.getEntrasaida());
		if (!financeiro.getEntrasaida().toString().equals("Todos")) {
			key += 4;
		}
		
		switch (key) {
		case 0:
			lista = fr.findAll();
			break;
		case 1:
			lista = fr.findByNome(financeiro.getNome());
			break;
		case 2:
			lista = fr.findByDataBetween(financeiro.getData(), data2);
			break;
		case 3:
			lista = fr.findByNomeAndDataBetween(financeiro.getNome(), financeiro.getData(), data2);
			break;
		case 4:
			lista = fr.findByEntrasaida(financeiro.getEntrasaida());
			break;
		case 5:
			lista = fr.findByNomeAndEntrasaida(financeiro.getNome(), financeiro.getEntrasaida());
			break;
		case 6:
			lista = fr.findByDataBetweenAndEntrasaida(financeiro.getData(), data2, financeiro.getEntrasaida());
			break;
		case 7:
			lista = fr.findByNomeAndEntrasaidaAndDataBetween(financeiro.getNome(), financeiro.getEntrasaida(), financeiro.getData(), data2);
			break;
		}
		return lista;
	}
}
