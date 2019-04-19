package com.herokuapp.cristcc2.Controles;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.herokuapp.cristcc2.Entidades.Usuario;
import com.herokuapp.cristcc2.Repository.UsuarioRepository;

@Controller
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository ur;
	
	@RequestMapping("/cadastrarUsuario")
	public String formCadastroAves() {
		return "usuario/cadastrarUsuario";
	}
	
	//Salvar
	@RequestMapping(value = "/cadastrarUsuario", method = RequestMethod.POST)
	public String salvarTipoAve(@Valid Usuario usuario, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/cadastrarUsuario";
		} else {
			usuario.setSenha2(usuario.getSenha());
			usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
			ur.save(usuario);
			attributes.addFlashAttribute("mensagem", "Lote de ovos salvo com sucesso!");
			return "redirect:/";
		}
	}
}
