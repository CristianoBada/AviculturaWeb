package com.herokuapp.cristcc2.Controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.herokuapp.cristcc2.Models.Produtos;
import com.herokuapp.cristcc2.repository.ProdutoRepository;

@Controller
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository pr;
	
	@RequestMapping("/cadastrarProduto")
	public String formCadastroProdutos() {
		return "produto/cadastrarProduto";
	}
	
	//Salvar
	@RequestMapping(value = "/cadastrarProduto", method = RequestMethod.POST)
	public String salvarProduto(@Valid Produtos produto, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/cadastrarProduto";
		} else {
			pr.save(produto);
			attributes.addFlashAttribute("mensagem", "Lote de ovos salvo com sucesso!");
			return "redirect:/";
		}
	}
}
