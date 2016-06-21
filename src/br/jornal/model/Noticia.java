package br.jornal.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name="noticia")
public class Noticia {
	
	@Id
	@Column(name="noticia_id", nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long noticia_id;
	
	@Column(name="titulo", nullable=false)
	@NotNull(message="{noticia.titulo.vazio}")
	@Size(min=10, message="{noticia.titulo.min}")
	private String titulo;
	
	@Column(name="subtitulo", nullable=false)
	private String subtitulo;
	
	@Column(name="texto", nullable=false)
	@Lob
	private String texto;

	@Column(name="id_autor", insertable=false, updatable=false, nullable=false)
	private Long id_autor;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="id_autor", referencedColumnName="usuario_id")
	private Usuario usuario;
	
	@Column(name="data_noticia", nullable=false)
	private Date data_noticia;
	
	@Column(name="id_secao", insertable=false, updatable=false, nullable=false)
	private Long id_secao;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="id_secao", referencedColumnName="secao_id")
	private Secao secao;
	
	@OneToMany(mappedBy="noticia", cascade=CascadeType.ALL, targetEntity=Comentario.class, fetch=FetchType.LAZY)
	private List<Comentario> comentarios;

	public Long getNoticia_id() {
		return noticia_id;
	}

	public void setNoticia_id(Long noticia_id) {
		this.noticia_id = noticia_id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getSubtitulo() {
		return subtitulo;
	}

	public void setSubtitulo(String subtitulo) {
		this.subtitulo = subtitulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
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

	public Date getData_noticia() {
		return data_noticia;
	}

	public void setData_noticia(Date data_noticia) {
		this.data_noticia = data_noticia;
	}

	public Long getId_secao() {
		return id_secao;
	}

	public void setId_secao(Long id_secao) {
		this.id_secao = id_secao;
	}

	public Secao getSecao() {
		return secao;
	}

	public void setSecao(Secao secao) {
		this.secao = secao;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}
}
