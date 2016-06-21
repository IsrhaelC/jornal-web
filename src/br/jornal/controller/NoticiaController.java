package br.jornal.controller;

import java.util.ArrayList;
import java.util.Date;
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

import br.jornal.dao.interfaces.IComentario;
import br.jornal.dao.interfaces.INoticia;
import br.jornal.dao.interfaces.ISecao;
import br.jornal.dao.interfaces.IUsuario;
import br.jornal.model.Comentario;
import br.jornal.model.Noticia;
import br.jornal.model.Secao;
import br.jornal.model.Usuario;
import br.jornal.util.JornalFileUtil;

@Transactional
@Controller
public class NoticiaController {
	
	@Autowired
	@Qualifier(value="noticiaDAO")
	private INoticia nDAO;
	
	@Autowired
	@Qualifier(value="secaoDAO")
	private ISecao sDAO;
	
	@Autowired
	@Qualifier(value="usuarioDAO")
	private IUsuario uDAO;
	
	@Autowired
	@Qualifier(value="comentarioDAO")
	private IComentario cDAO;
	
	@Autowired
	private ServletContext servletContext;
	
	@RequestMapping("/inserirNoticiaForm")
	public String inserirNoticiaForm(Model model){
		List<Secao> secoes = sDAO.listar();
		model.addAttribute("secoes", secoes);
		return "noticia/inserir_noticia_form";
	}
	
	@RequestMapping("/inserirNoticia")
	public String inserirNoticia(@Valid Noticia noticia, String login, String secaoTitulo, BindingResult result, @RequestParam(value="imagem", required=false) MultipartFile imagem){
		if(result.hasFieldErrors("titulo")){
			return "inserir_noticia_form";
		}
		System.out.println(login + secaoTitulo);
		
		Usuario user = uDAO.recuperar(login);
		Secao secao = sDAO.recuperar(secaoTitulo);
		Date data = new Date();
		noticia.setSecao(secao);
		noticia.setUsuario(user);
		noticia.setData_noticia(data);
		
		String path = servletContext.getRealPath("/")+"WEB-INF/resources/images/" + noticia.getTitulo() + ".png";
		
		JornalFileUtil.salvarImagem(path, imagem);
		
		this.nDAO.inserir(noticia);
		return "redirect:listarNoticias";
	}
	
	@RequestMapping("/listarNoticias")
	public String listarNoticias(Model model, Model secao){
		List<Noticia> noticias = this.nDAO.listar();
		model.addAttribute("noticias", noticias);
		List<Secao> secoes = this.sDAO.listar();
		secao.addAttribute("secoes", secoes);
		
		return "noticia/listar_noticias";
	}
	
	@RequestMapping("/listarNoticiaUser")
	public String listarNoticiaUser(Model model, Model secao, Long id){
		List<Noticia> noticias = new ArrayList<Noticia>();
		List<Noticia> noticiasTemp = this.nDAO.listar();
		for(Noticia noticia : noticiasTemp){
			if(noticia.getId_autor() == id){
				noticias.add(noticia);
			}
		}
		
		model.addAttribute("noticias", noticias);
		List<Secao> secoes = this.sDAO.listar();
		secao.addAttribute("secoes", secoes);
		
		return "noticia/listar_noticias";
	}
	
	@RequestMapping("/apagarNoticia")
	public String apagarNoticia(Long id){
		this.nDAO.remover(this.nDAO.recuperar(id));
		
		return "redirect:listarNoticias";
	}
	
	@RequestMapping("/alterarNoticiaForm")
	public String alterarNoticiaForm(Long id, Model model, Model secao){
		Noticia noticia = this.nDAO.recuperar(id);
		model.addAttribute("noticia", noticia);
		List<Secao> secoes = this.sDAO.listar();
		secao.addAttribute("secoes", secoes);
		
		return "noticia/alterar_noticia_form";
	}
	
	@RequestMapping("/alterarNoticia")
	public String alterarNoticia(Long id_noticia, String tituloNoticia, String subtituloNoticia, String textoNoticia){
		
		Noticia noticia = nDAO.recuperar(id_noticia);
		Usuario usuario = uDAO.recuperar(noticia.getId_autor());
		Secao secao = sDAO.recuperar(noticia.getId_secao());
		List<Comentario> comentarios = cDAO.listar();
		List<Comentario> comentarioNoticia = new ArrayList<>();
		for(Comentario comentario : comentarios){
			if(comentario.getId_noticia() == noticia.getNoticia_id()){
				comentarioNoticia.add(comentario);
			}
		}
		noticia.setComentarios(comentarioNoticia);
		noticia.setUsuario(usuario);
		noticia.setSecao(secao);
		noticia.setData_noticia(noticia.getData_noticia());
		noticia.setTitulo(tituloNoticia);
		noticia.setSubtitulo(subtituloNoticia);
		noticia.setTexto(textoNoticia);
		
		this.nDAO.alterar(noticia);
		
		return "redirect:listarNoticias";
	}
	
	@RequestMapping("/lerNoticia")
	public String lerNoticia(Long id, Model model, Model modelComentario, Model secao){
		Noticia noticia = nDAO.recuperar(id);
		List<Comentario> comentarios = cDAO.listar();
		List<Comentario> comentarioNoticia = new ArrayList<>();
		for(Comentario comentario : comentarios){
			if(comentario.getId_noticia() == noticia.getNoticia_id()){
				comentarioNoticia.add(comentario);
			}
		}
		modelComentario.addAttribute("comentarios", comentarioNoticia);
		model.addAttribute("noticia", noticia);
		List<Secao> secoes = this.sDAO.listar();
		secao.addAttribute("secoes", secoes);
		return "noticia/ler_noticia";
	}
	
	@RequestMapping("/noticiaSecao")
	public String noticiaSecao(Long id, Model model, Model secoes, Model secao){
		
		Secao secao2 = sDAO.recuperar(id);
		List<Noticia> noticias = nDAO.listar();
		List<Noticia> noticiasSecao = new ArrayList<Noticia>();
		for(Noticia noticia : noticias){
			if(noticia.getId_secao() ==  id){
				noticiasSecao.add(noticia);
			}
		}
		secoes.addAttribute("secao", secao2);
		model.addAttribute("noticias", noticiasSecao);
		List<Secao> secoes2 = this.sDAO.listar();
		secao.addAttribute("secoes", secoes2);
		
		return "noticia/listar_noticias";
	}
}
