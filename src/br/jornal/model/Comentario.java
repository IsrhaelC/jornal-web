package br.jornal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name="comentario")
public class Comentario {

	@Id
	@Column(name="comentario_id", nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long comentario_id;

	@Column(name="id_noticia", insertable=false, updatable=false, nullable=false)
	private Long id_noticia;

	@ManyToOne(optional=false)
	@JoinColumn(name="id_noticia", referencedColumnName="noticia_id")
	private Noticia noticia;
	
	@Column(name="id_autor", insertable=false, updatable=false, nullable=false)
	private Long id_autor;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="id_autor", referencedColumnName="usuario_id")
	private Usuario usuario;
	
	@Column(name="texto", nullable=false)
	@NotNull(message="{comentario.texto.vazio}")
	@Size(min=5, message="{comentario.texto.min}")
	private String texto;

	public Long getComentario_id() {
		return comentario_id;
	}

	public void setComentario_id(Long comentario_id) {
		this.comentario_id = comentario_id;
	}

	public Long getId_noticia() {
		return id_noticia;
	}

	public void setId_noticia(Long id_noticia) {
		this.id_noticia = id_noticia;
	}

	public Noticia getNoticia() {
		return noticia;
	}

	public void setNoticia(Noticia noticia) {
		this.noticia = noticia;
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

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
}
