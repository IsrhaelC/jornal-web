package br.jornal.controller;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import br.jornal.dao.interfaces.IPapel;
import br.jornal.dao.interfaces.ISecao;
import br.jornal.dao.interfaces.IUsuario;
import br.jornal.model.Papel;
import br.jornal.model.Secao;
import br.jornal.model.Usuario;
import br.jornal.util.JornalFileUtil;

@Transactional
@Controller
public class UsuarioController {
	
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
	private ServletContext servletContext;
	
	@RequestMapping("/inserirUsuarioForm")
	public String inserirUsuarioForm(Model secao){
		List<Secao> secoes = this.sDAO.listar();
		secao.addAttribute("secoes", secoes);
		return "usuario/inserir_usuario_form";
	}
	
	@RequestMapping("/inserirJornalistaForm")
	public String inserirJornalistaForm(Model secao){
		List<Secao> secoes = this.sDAO.listar();
		secao.addAttribute("secoes", secoes);
		return "usuario/inserir_jornalista";
	}
	
	@RequestMapping("/inserirEditorForm")
	public String inserirEditorForm(Model secao){
		List<Secao> secoes = this.sDAO.listar();
		secao.addAttribute("secoes", secoes);
		return "usuario/inserir_editor";
	}
	
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
	
	@RequestMapping("/inserirUsuario")
	public String inserirUsuario(@Valid Usuario usuario, BindingResult result, @RequestParam(value="imagem", required=false) MultipartFile imagem) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		
		if(result.hasFieldErrors("nome")){
			return "usuario/inserir_usuario_form";
		}
		List<Papel> papeis = pDAO.listar();
		Papel pUser = new Papel();
		for(Papel papel : papeis){
			if(papel.getPapel().equals("Leitor"))
				pUser = papel;
		}
		
		List<Papel> papeisUser = new ArrayList<>();
		papeisUser.add(pUser);
		usuario.setPapeis(papeisUser);
		usuario.setLogin(usuario.getLogin().toLowerCase());
		usuario.setSenha(md5(usuario.getSenha()));
		
		String path = servletContext.getRealPath("/")+"WEB-INF/resources/images/"+usuario.getLogin()+".png";
		
		JornalFileUtil.salvarImagem(path, imagem);
		
		this.uDAO.inserir(usuario);
		
		return "redirect:loginFormulario";
	}
	
	@RequestMapping("/inserirJornalista")
	public String inserirJornalista(@Valid Usuario usuario, BindingResult result, @RequestParam(value="imagem", required=false) MultipartFile imagem) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		
		if(result.hasFieldErrors("nome")){
			return "usuario/inserir_jornalista";
		}
		List<Papel> papeis = pDAO.listar();
		Papel pUser = new Papel();
		for(Papel papel : papeis){
			if(papel.getPapel().equals("Jornalista"))
				pUser = papel;
		}
		List<Papel> papeisUser = new ArrayList<>();
		papeisUser.add(pUser);
		usuario.setPapeis(papeisUser);
		usuario.setLogin(usuario.getLogin().toLowerCase());
		usuario.setSenha(md5(usuario.getSenha()));
		
		String path = servletContext.getRealPath("/")+"WEB-INF/resources/images/"+usuario.getLogin()+".png";
		
		JornalFileUtil.salvarImagem(path, imagem);
		
		this.uDAO.inserir(usuario);
		
		return "redirect:listarUsuarios";
	}
	
	@RequestMapping("/inserirEditor")
	public String inserirEditor(@Valid Usuario usuario, BindingResult result, @RequestParam(value="imagem", required=false) MultipartFile imagem) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		
		if(result.hasFieldErrors("nome")){
			return "usuario/inserir_editor";
		}
		List<Papel> papeis = pDAO.listar();
		Papel pUser = new Papel();
		for(Papel papel : papeis){
			if(papel.getPapel().equals("Editor"))
				pUser = papel;
		}
		List<Papel> papeisUser = new ArrayList<>();
		papeisUser.add(pUser);
		usuario.setPapeis(papeisUser);
		usuario.setLogin(usuario.getLogin().toLowerCase());
		usuario.setSenha(md5(usuario.getSenha()));
		
		String path = servletContext.getRealPath("/")+"WEB-INF/resources/images/"+usuario.getLogin()+".png";
		
		JornalFileUtil.salvarImagem(path, imagem);
		
		this.uDAO.inserir(usuario);
		
		return "redirect:listarUsuarios";
	}
	
	@RequestMapping("/listarUsuarios")
	public String listarUsuarios(Model model, Model secao){
		List<Usuario> usuarios = this.uDAO.listar();
		model.addAttribute("usuarios", usuarios);
		List<Secao> secoes = this.sDAO.listar();
		secao.addAttribute("secoes", secoes);
		
		return "usuario/listar_usuarios";
	}
	
	@RequestMapping("/apagarUsuario")
	public String apagarUsuario(Long id, Long id_logado){
		
		if(id == id_logado){
			this.uDAO.remover(this.uDAO.recuperar(id));
			return "redirect:logout";
		}
		
		this.uDAO.remover(this.uDAO.recuperar(id));
		
		return "redirect:listarUsuarios";
	}
	
	@RequestMapping("/alterarUsuarioForm")
	public String alterarUsuarioForm(Long id, Model model, Model secao){
		Usuario usuario = this.uDAO.recuperar(id);
		model.addAttribute("usuario", usuario);
		List<Secao> secoes = this.sDAO.listar();
		secao.addAttribute("secoes", secoes);
		return "usuario/alterar_usuario_form";
	}
	
	@RequestMapping("/alterarUsuario")
	public String alterarUsuario(Long idUsuario, String nomeUsuario, String emailUsuario, String loginUsuario, String senhaUsuario){
		Usuario usuario = uDAO.recuperar(idUsuario);
		List<Papel> papeis = usuario.getPapeis();
		usuario.setNome(nomeUsuario);
		usuario.setEmail(emailUsuario);
		usuario.setLogin(loginUsuario.toLowerCase());
		usuario.setSenha(senhaUsuario);
		usuario.setPapelAtual(usuario.getPapelAtual());
		usuario.setPapeis(papeis);
		
		this.uDAO.alterar(usuario);
		return "redirect:listarUsuarios";
	}
	
	@RequestMapping("/adicionarPapelForm")
	public String adicionarPapelForm(Long id, Model model, Model papeis, Model secao){
		Usuario usuario = uDAO.recuperar(id);
		List<Papel> papers = pDAO.listar();
		model.addAttribute("usuario", usuario);
		papeis.addAttribute("papeis", papers);
		List<Secao> secoes = this.sDAO.listar();
		secao.addAttribute("secoes", secoes);
		
		return "usuario/adicionar_papel_form";
	}
	
	@RequestMapping("/adicionarPapel")
	public String adicionarPapel(Long idUsuario, String nomePapel){
		Usuario usuario = uDAO.recuperar(idUsuario);
		List<Papel> papeis = usuario.getPapeis();
		Papel papel = pDAO.recuperar(nomePapel);
		if(!(papeis.contains(papel))){
			papeis.add(papel);
		}
		usuario.setNome(usuario.getNome());
		usuario.setEmail(usuario.getEmail());
		usuario.setLogin(usuario.getLogin());
		usuario.setSenha(usuario.getSenha());
		usuario.setPapelAtual(usuario.getPapelAtual());
		usuario.setPapeis(papeis);
		
		this.uDAO.alterar(usuario);
		return "redirect:listarUsuarios";
	}
	
	@RequestMapping("/removerPapel")
	public String removerPapel(Long id, String papel){
		Usuario usuario = uDAO.recuperar(id);
		List<Papel> papeis = usuario.getPapeis();
		Papel paper = pDAO.recuperar(papel);
		for(Papel p : papeis){
			if(p.getPapel_id() == paper.getPapel_id()){
				papeis.remove(p);
			}
		}
		usuario.setNome(usuario.getNome());
		usuario.setEmail(usuario.getEmail());
		usuario.setLogin(usuario.getLogin());
		usuario.setSenha(usuario.getSenha());
		usuario.setPapelAtual(usuario.getPapelAtual());
		usuario.setPapeis(papeis);
		
		this.uDAO.alterar(usuario);
		return "redirect:listarUsuarios";
	}
	
	@RequestMapping("/perfil")
	public String perfil(Long id, Model model, Model secao){
		Usuario usuario = uDAO.recuperar(id);
		List<Secao> secoes = this.sDAO.listar();
		secao.addAttribute("secoes", secoes);
		model.addAttribute("usuario", usuario);
		
		return "usuario/perfil_usuario";
	}
}
