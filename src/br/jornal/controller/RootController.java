package br.jornal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.jornal.dao.interfaces.INoticia;
import br.jornal.dao.interfaces.ISecao;
import br.jornal.model.Noticia;
import br.jornal.model.Secao;

@Transactional
@Controller
public class RootController {
	
	@Autowired
	@Qualifier(value="noticiaDAO")
	private INoticia nDAO;
	
	@Autowired
	@Qualifier(value="secaoDAO")
	private ISecao sDAO;
	
	@RequestMapping("/")
	public String home(Model model, Model secao){
		List<Noticia> noticias = this.nDAO.listarUltimas();
		model.addAttribute("noticias", noticias);
		List<Secao> secoes = this.sDAO.listar();
		secao.addAttribute("secoes", secoes);
		
		return "home";
	}
	
	@RequestMapping("/homePage")
	public String homePage(){
		
		return "redirect:/";
	}
}
