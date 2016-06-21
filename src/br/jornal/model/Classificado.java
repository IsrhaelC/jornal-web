package br.jornal.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name="classificado")
public class Classificado {

	@Id
	@Column(name="classificado_id", nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long classificado_id;
	
	@Column(name="titulo", nullable=false)
	@NotNull(message="{classificado.titulo.vazio}")
	@Size(min=8, message="{classificado.titulo.min}")
	private String titulo;
	
	@Column(name="texto", nullable=false)
	private String texto;
	
	@Column(name="preco", nullable=false)
	private Float preco;
	
	@Column(name="telefone", nullable=false)
	private String telefone;
	
	@Column(name="id_autor_mo")
	private Long id_autor_mo;
	
	@Column(name="melhor_oferta")
	private Float melhor_oferta;
	
	@Column(name="data_melhor_oferta")
	private Date data_melhor_oferta;
	
	@Column(name="vendido")
	private Long vendido;
	
	@Column(name="data_oferta", nullable=false)
	private Date data_oferta;
	
	@Column(name="id_autor", insertable=false, updatable=false, nullable=false)
	private Long id_autor;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="id_autor", referencedColumnName="usuario_id")
	private Usuario usuario;

	public Long getClassificado_id() {
		return classificado_id;
	}

	public void setClassificado_id(Long classificado_id) {
		this.classificado_id = classificado_id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Float getPreco() {
		return preco;
	}

	public void setPreco(Float preco) {
		this.preco = preco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Long getId_autor_mo() {
		return id_autor_mo;
	}

	public void setId_autor_mo(Long id_autor_mo) {
		this.id_autor_mo = id_autor_mo;
	}

	public Float getMelhor_oferta() {
		return melhor_oferta;
	}

	public void setMelhor_oferta(Float melhor_oferta) {
		this.melhor_oferta = melhor_oferta;
	}

	public Date getData_melhor_oferta() {
		return data_melhor_oferta;
	}

	public void setData_melhor_oferta(Date data_melhor_oferta) {
		this.data_melhor_oferta = data_melhor_oferta;
	}

	public Long getVendido() {
		return vendido;
	}

	public void setVendido(Long vendido) {
		this.vendido = vendido;
	}

	public Date getData_oferta() {
		return data_oferta;
	}

	public void setData_oferta(Date data_oferta) {
		this.data_oferta = data_oferta;
	}

	public Long getId_autor() {
		return id_autor;
	}

	public void setId_autor(Long id_autor) {
		this.id_autor = id_autor;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
