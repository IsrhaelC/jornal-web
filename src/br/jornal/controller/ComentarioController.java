package br.jornal.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import br.jornal.dao.interfaces.IComentario;
import br.jornal.dao.interfaces.INoticia;
import br.jornal.dao.interfaces.ISecao;
import br.jornal.dao.interfaces.IUsuario;
import br.jornal.model.Comentario;
import br.jornal.model.Noticia;
import br.jornal.model.Usuario;

@Transactional
@Controller
public class ComentarioController {
	
	@Autowired
	@Qualifier(value="comentarioDAO")
	private IComentario cDAO;
	
	@Autowired
	@Qualifier(value="noticiaDAO")
	private INoticia nDAO;
	
	@Autowired
	@Qualifier(value="secaoDAO")
	private ISecao sDAO;
	
	@Autowired
	@Qualifier(value="usuarioDAO")
	private IUsuario uDAO;
	
	@RequestMapping("/inserirComentarioForm")
	public String inserirComentarioForm(){
		return "comentario/inserir_comentario_form";
	}
	
	@RequestMapping("/inserirComentario")
	public String inserirComentario(@Valid Comentario comentario, String login, String titulo, BindingResult result){
		if(result.hasFieldErrors("texto")){
			return "inserir_comentario_form";
		}
		Noticia noticia = nDAO.recuperar(titulo);
		Usuario usuario = uDAO.recuperar(login);
		System.out.println(titulo + " " + login);
		comentario.setUsuario(usuario);
		comentario.setNoticia(noticia);
		this.cDAO.inserir(comentario);
		return "redirect:lerNoticia?id=" + noticia.getNoticia_id();
	}
	
	@RequestMapping("/listarComentarios")
	public String listarComentarios(Model model){
		List<Comentario> comentarios = this.cDAO.listar();
		model.addAttribute("comentarios", comentarios);
		
		return "comentario/listar_comentarios";
	}
	
	@RequestMapping("/apagarComentario")
	public String apagarComentario(Long id, Long id_noticia){
		this.cDAO.remover(this.cDAO.recuperar(id));
		
		return "redirect:lerNoticia?id=" + id_noticia;
	}
	
	@RequestMapping("/alterarComentarioForm")
	public String alterarComentarioForm(Long id, Model model){
		Comentario comentario = this.cDAO.recuperar(id);
		model.addAttribute("comentario", comentario);
		
		return "comentario/alterar_comentario_form";
	}
	
	@RequestMapping("/alterarComentario")
	public String alterarComentario(Comentario comentario){
		this.cDAO.alterar(comentario);
		
		return "redirect:listarComentarios";
	}
}
