package br.jornal.controller;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.jornal.dao.interfaces.INoticia;
import br.jornal.dao.interfaces.IPapel;
import br.jornal.dao.interfaces.ISecao;
import br.jornal.dao.interfaces.IUsuario;
import br.jornal.model.Noticia;
import br.jornal.model.Papel;
import br.jornal.model.Secao;
import br.jornal.model.Usuario;

@Controller
public class LoginController {
	
	@Autowired
	@Qualifier(value="usuarioDAO")
	private IUsuario uDAO;
	
	@Autowired
	@Qualifier(value="papelDAO")
	private IPapel pDAO;
	
	@Autowired
	@Qualifier(value="secaoDAO")
	private ISecao sDAO;
	
	@Autowired
	@Qualifier(value="noticiaDAO")
	private INoticia nDAO;
	
	public String md5(String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		MessageDigest algorithm = MessageDigest.getInstance("MD5");
		byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));
		StringBuilder hexString = new StringBuilder();
		for(byte b : messageDigest){
			hexString.append(String.format("%02X", 0xFF & b));
		}
		String md5_senha = hexString.toString();
		return md5_senha;
	}
	
	@RequestMapping("/loginFormulario")
	public String loginFormulario(Model model, Model secao){
		List<Papel> papeis = pDAO.listar();
		model.addAttribute("papeis", papeis);
		List<Secao> secoes = this.sDAO.listar();
		secao.addAttribute("secoes", secoes);
		return "login_form";
	}
	
	@RequestMapping("/login")
	public String login(Usuario usuario, String papel, HttpSession session, Model model) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		List<Papel> roles = pDAO.listar();
		Papel aux = new Papel();
		for(Papel role : roles){
			if(role.getPapel().equals(papel))
				aux = role;
		}
		Usuario user = uDAO.recuperar(usuario.getLogin());
		if(user!=null){
			List<Papel> role2 = user.getPapeis();
			if (role2.contains(aux)) {
				if(user.getSenha().equals(md5(usuario.getSenha()))){
					user.setPapelAtual(papel);
					session.setAttribute("usuario_logado", user);
					List<Noticia> noticias = nDAO.listar();
					model.addAttribute("noticias", noticias);
					return "redirect:/";
				}
			}
			
		}
		return "redirect:loginFormulario";
	}
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:loginFormulario";
	}
}
