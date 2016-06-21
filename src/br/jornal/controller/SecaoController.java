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

import br.jornal.dao.interfaces.ISecao;
import br.jornal.model.Secao;

@Transactional
@Controller
public class SecaoController {
	
	@Autowired
	@Qualifier(value="secaoDAO")
	private ISecao sDAO;
	
	@RequestMapping("/inserirSecaoForm")
	public String inserirSecaoForm(Model secao){
		List<Secao> secoes = this.sDAO.listar();
		secao.addAttribute("secoes", secoes);
		return "secao/inserir_secao_form";
	}
	
	@RequestMapping("/inserirSecao")
	public String inserirSecao(@Valid Secao secao, BindingResult result){
		if(result.hasFieldErrors("titulo")){
			return "secao/inserir_secao_form";
		}
		this.sDAO.inserir(secao);
		
		return "redirect:listarSecoes";
	}
	
	@RequestMapping("/listarSecoes")
	public String listarSecao(Model model){
		List<Secao> secoes = this.sDAO.listar();
		model.addAttribute("secoes", secoes);
		
		return "secao/listar_secoes";
	}
	
	@RequestMapping("/apagarSecao")
	public String apagarSecao(Long id){
		this.sDAO.remover(this.sDAO.recuperar(id));
		
		return "redirect:listarSecoes";
	}
	
	@RequestMapping("/alterarSecaoForm")
	public String alterarSecaoForm(Long id, Model model, Model secao){
		Secao secao2 = this.sDAO.recuperar(id);
		model.addAttribute("secao", secao2);
		List<Secao> secoes = this.sDAO.listar();
		secao.addAttribute("secoes", secoes);
		
		return "secao/alterar_secao_form";
	}
	
	@RequestMapping("/alterarSecao")
	public String alterarSecao(Secao secao){
		this.sDAO.alterar(secao);
		return "redirect:listarSecoes";
	}
}