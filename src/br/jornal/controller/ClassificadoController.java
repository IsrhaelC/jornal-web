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

import br.jornal.dao.interfaces.IClassificado;
import br.jornal.dao.interfaces.ISecao;
import br.jornal.dao.interfaces.IUsuario;
import br.jornal.model.Classificado;
import br.jornal.model.Secao;
import br.jornal.model.Usuario;
import br.jornal.util.JornalFileUtil;

@Transactional
@Controller
public class ClassificadoController {
	
	@Autowired
	@Qualifier(value="classificadoDAO")
	private IClassificado cDAO;
	
	@Autowired
	@Qualifier(value="usuarioDAO")
	private IUsuario uDAO;
	
	@Autowired
	@Qualifier(value="secaoDAO")
	private ISecao sDAO;
	
	@Autowired
	private ServletContext servletContext;
	
	@RequestMapping("/inserirClassificadoForm")
	public String inserirClassificadoForm(Model secao){
		List<Secao> secoes = this.sDAO.listar();
		secao.addAttribute("secoes", secoes);
		return "classificado/inserir_classificado_form";
	}
	
	@RequestMapping("/inserirClassificado")
	public String inserirClassificado(@Valid Classificado classificado, String login, BindingResult result, @RequestParam(value="imagem", required=false) MultipartFile imagem){
		if(result.hasFieldErrors("titulo")){
			return "inserir_classificado_form";
		}
		Usuario usuario = uDAO.recuperar(login);
		Date data = new Date();
		classificado.setData_oferta(data);
		classificado.setUsuario(usuario);
		classificado.setVendido(0L);
		
		String path = servletContext.getRealPath("/") + "WEB-INF/resources/images/" + classificado.getTitulo() + ".png";
		
		JornalFileUtil.salvarImagem(path, imagem);
		
		this.cDAO.inserir(classificado);
		return "redirect:listarClassificados";
	}
	
	@RequestMapping("/listarClassificados")
	public String listarClassificados(Model model, Model secao){
		List<Classificado> classificados = this.cDAO.listar();
		model.addAttribute("classificado", classificados);
		List<Secao> secoes = this.sDAO.listar();
		secao.addAttribute("secoes", secoes);
		
		return "classificado/listar_classificados";
	}
	
	@RequestMapping("/listarClassificadosUser")
	public String listarClassificadosUser(Model model, Model secao, Long id){
		
		Usuario usuario = uDAO.recuperar(id);
		List<Classificado> classificadosTemp = cDAO.listar();
		List<Classificado> classificados = new ArrayList<Classificado>();
		
		for(Classificado classificado : classificadosTemp){
			if(classificado.getId_autor() == usuario.getUsuario_id()){
				classificados.add(classificado);
			}
		}
		
		model.addAttribute("classificado", classificados);
		List<Secao> secoes = this.sDAO.listar();
		secao.addAttribute("secoes", secoes);
		
		return "classificado/listar_classificados";
	}
	
	@RequestMapping("/listarClassificadosOfertas")
	public String listarClassificadosOfertas(Model model, Model secao, Long id){
		
		Usuario usuario = uDAO.recuperar(id);
		List<Classificado> classificadosTemp = cDAO.listar();
		List<Classificado> classificados = new ArrayList<Classificado>();
		
		for(Classificado classificado : classificadosTemp){
			if(classificado.getId_autor_mo() == usuario.getUsuario_id()){
				classificados.add(classificado);
			}
		}
		
		model.addAttribute("classificado", classificados);
		List<Secao> secoes = this.sDAO.listar();
		secao.addAttribute("secoes", secoes);
		
		return "classificado/listar_classificados";
	}
	
	@RequestMapping("/apagarClassificado")
	public String apagarClassificado(Long id){
		this.cDAO.remover(this.cDAO.recuperar(id));
		
		return "redirect:listarClassificados";
	}
	
	@RequestMapping("/alterarClassificadoForm")
	public String alterarClassificadoForm(Long id, Model model, Model secao){
		Classificado classificado = this.cDAO.recuperar(id);
		model.addAttribute("classificado", classificado);
		List<Secao> secoes = this.sDAO.listar();
		secao.addAttribute("secoes", secoes);
		
		return "classificado/alterar_classificado_form";
	}
	
	@RequestMapping("/alterarClassificado")
	public String alterarClassificado(Long id_classificado, String tituloClassificado, String textoClassificado, String precoClassificado, String telefoneClassificado){
		Classificado classificado = cDAO.recuperar(id_classificado);
		Usuario usuario = uDAO.recuperar(classificado.getId_autor());
		
		classificado.setData_oferta(classificado.getData_oferta());
		classificado.setPreco(Float.parseFloat(precoClassificado));
		classificado.setTexto(textoClassificado);
		classificado.setTelefone(telefoneClassificado);
		classificado.setTitulo(tituloClassificado);
		classificado.setUsuario(usuario);
		classificado.setVendido(0l);
		
		this.cDAO.alterar(classificado);
		
		return "redirect:listarClassificados";
	}
	
	@RequestMapping("/inserirOfertaForm")
	public String InserirOfertaForm(Long id, Model model, Model autor, Model secao){
		Classificado classificado = cDAO.recuperar(id);
		Usuario usuario = null;
		if(classificado.getId_autor_mo() != null){
			usuario = uDAO.recuperar(classificado.getId_autor_mo());
		}
		autor.addAttribute("autor", usuario);
		model.addAttribute("classificado", classificado);
		List<Secao> secoes = this.sDAO.listar();
		secao.addAttribute("secoes", secoes);
		return "classificado/inserir_oferta_form";
	}
	
	@RequestMapping("/inserirOferta")
	public String inserirOferta(Long id_classificado, Long id_usuario, String oferta){
		Classificado classificado = cDAO.recuperar(id_classificado);
		Date dataOferta = new Date();
		if(classificado.getMelhor_oferta() == null && classificado.getPreco() < Float.parseFloat(oferta)){
			classificado.setData_melhor_oferta(dataOferta);
			classificado.setMelhor_oferta(Float.parseFloat(oferta));
			classificado.setId_autor_mo(id_usuario);
			cDAO.alterar(classificado);
			return "redirect:listarClassificados";
		}
		if(classificado.getMelhor_oferta() != null){
			if(classificado.getMelhor_oferta() > Float.parseFloat(oferta) || classificado.getPreco() > Float.parseFloat(oferta)){
				return "redirect:inserirOfertaForm?id=" + classificado.getClassificado_id();
			}
			classificado.setData_melhor_oferta(dataOferta);
			classificado.setMelhor_oferta(Float.parseFloat(oferta));
			classificado.setId_autor_mo(id_usuario);
			cDAO.alterar(classificado);
			return "redirect:listarClassificados";
		}
		
		return "redirect:inserirOfertaForm?id=" + classificado.getClassificado_id();
	}
	
	@RequestMapping("/venderClassificado")
	public String venderClassificado(Long id){
		Classificado classificado = cDAO.recuperar(id);
		classificado.setVendido(1l);
		cDAO.alterar(classificado);
		return "redirect:listarClassificados";
	}
}
