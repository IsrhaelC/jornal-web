package br.jornal.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name="secao")
public class Secao {
	
	@Id
	@Column(name="secao_id", nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)	
	private Long secao_id;
	
	@Column(name="titulo", nullable=false)
	@NotNull(message="secao.titulo.vazio")
	@Size(min=5, message="{secao.titulo.min}")
	private String titulo;
	
	@Column(name="descricao", nullable=false)
	private String descricao;
	
	@OneToMany(mappedBy="secao", cascade=CascadeType.ALL, targetEntity=Noticia.class, fetch=FetchType.LAZY)
	private List<Noticia> noticias;

	public Long getSecao_id() {
		return secao_id;
	}

	public void setSecao_id(Long secao_id) {
		this.secao_id = secao_id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Noticia> getNoticias() {
		return noticias;
	}

	public void setNoticias(List<Noticia> noticias) {
		this.noticias = noticias;
	}
}
