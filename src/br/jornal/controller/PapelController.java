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

import br.jornal.dao.interfaces.IPapel;
import br.jornal.dao.interfaces.ISecao;
import br.jornal.model.Papel;

@Transactional
@Controller
public class PapelController {
	
	@Autowired
	@Qualifier(value="papelDAO")
	private IPapel pDAO;
	
	@Autowired
	@Qualifier(value="secaoDAO")
	private ISecao sDAO;
	
	@RequestMapping("/inserirPapelForm")
	public String inserirPapelForm(){
		return "papel/inserir_papel_form";
	}
	
	@RequestMapping("/inserirPapel")
	public String inserirPapel(@Valid Papel papel, BindingResult result){
		if(result.hasFieldErrors("papel")){
			return "inserir_papel_form";
		}
		this.pDAO.inserir(papel);
		
		return "papel/papel_inserido_ok";
	}
	
	@RequestMapping("/listarPapeis")
	public String listarPapeis(Model model){
		List<Papel> papeis = this.pDAO.listar();
		model.addAttribute("papeis", papeis);
		
		return "papel/listar_papeis";
	}
	
	@RequestMapping("/apagarPapel")
	public String apagarPapel(Long id){
		this.pDAO.remover(this.pDAO.recuperar(id));
		
		return "redirect:listarPapeis";
	}
	
	@RequestMapping("/alterarPapelForm")
	public String alterarPapelForm(Long id, Model model){
		Papel papel = this.pDAO.recuperar(id);
		model.addAttribute("papel", papel);
		
		return "papel/alterar_papel_form";		
	}
	
	@RequestMapping("/alterarPapel")
	public String alterarPapel(Papel papel){
		this.pDAO.alterar(papel);
		
		return "redirect:listarPapeis";
	}
}
