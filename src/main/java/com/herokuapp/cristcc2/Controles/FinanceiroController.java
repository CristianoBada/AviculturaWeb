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

import com.herokuapp.cristcc2.Entidades.Financeiro;
import com.herokuapp.cristcc2.Entidades.Produtos;
import com.herokuapp.cristcc2.Repository.FinanceiroRepository;
import com.herokuapp.cristcc2.Repository.ProdutoRepository;
import com.herokuapp.cristcc2.Uteis.Convercoes;

@Controller
public class FinanceiroController {

	@Autowired
	private FinanceiroRepository fr;
	
	@Autowired
	private ProdutoRepository pr;
	
	//Inicio
	@RequestMapping(value = "/cadastrarFinanceiro", method = RequestMethod.GET)
	public ModelAndView listaPostura() {
		ModelAndView mv = new ModelAndView("financeiro/cadastrarFinanceiro");
		Iterable<Financeiro> lista = fr.findAll();
		mv.addObject("listaFinanceiro", lista);
		return mv;
	}
	
	//Novo
	@RequestMapping("/edicaoFinanceiro/novo")
	public String edicaoFinanceiro(Model model) {
		model.addAttribute("financeiro", new Financeiro());
		Iterable<Produtos> lista = pr.findAll();
		model.addAttribute("listaProdutos", lista);
		return "financeiro/editarFinanceiro";
	}

	
	//Salvar
	@RequestMapping(value = "/edicaoFinanceiro/save", method = RequestMethod.POST)
	public String salvarvacinas(@Valid Financeiro financeiro, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/edicaoFinanceiro";
		} else {
			Convercoes convercoes = new Convercoes();
			financeiro.setData(convercoes.convertDateUStoDataBR((financeiro.getData())));
			fr.save(financeiro);
			attributes.addFlashAttribute("mensagem", "Financeiro salvo com sucesso!");
			return "redirect:/cadastrarFinanceiro";
		}
	}
	
	//Detelar
	@RequestMapping("/cadastrarFinanceiro/delete/{codigo}") //@PathVariable Long id, RedirectAttributes redirectAttrs
	public String deletarPostura(@PathVariable("codigo") Long codigo, RedirectAttributes redirectAttrs) {
		Financeiro fin = fr.findByCodigo(codigo);
		fr.delete(fin);
		return "redirect:/cadastrarFinanceiro";
	}
	
	//Editar
	@RequestMapping("/edicaoFinanceiro/editar/{codigo}")
	public String editarFinanceiro(@PathVariable Long codigo, Model model) {
		Financeiro fin = fr.findByCodigo(codigo);

		Convercoes convercoes = new Convercoes();
		fin.setData(convercoes.convertDateBRtoDataUS(fin.getData()));

		model.addAttribute("financeiro", fin);
		Iterable<Produtos> lista = pr.findAll();
		model.addAttribute("listaProdutos", lista);
		return "financeiro/editarFinanceiro";
	}
}
