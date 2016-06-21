package br.jornal.model;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@Entity(name="usuario")
public class Usuario {
	
	@Id
	@Column(name="usuario_id", nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long usuario_id;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="usuario_papel", joinColumns=@JoinColumn(name="usuario_id", referencedColumnName="usuario_id"),
										inverseJoinColumns=@JoinColumn(name="papel_id", referencedColumnName="papel_id"))
	private List<Papel> papeis;
	
	@OneToMany(mappedBy="usuario", cascade=CascadeType.ALL  ,targetEntity=Classificado.class, fetch=FetchType.LAZY)
	private List<Classificado> classificados;
	
	@OneToMany(mappedBy="usuario", cascade=CascadeType.ALL, targetEntity=Noticia.class, fetch=FetchType.LAZY)
	private List<Noticia> noticias;
	
	@OneToMany(mappedBy="usuario", cascade=CascadeType.ALL, targetEntity=Comentario.class, fetch=FetchType.LAZY)
	private List<Comentario> comentarios;
	
	@NotNull(message="{usuario.nome.vazio}")
	@Size(min=5, message="{usuario.nome.min}")
	@Column(name="nome", nullable=false)
	private String nome;
	
	@Column(name="email", nullable=false)
	private String email;
	
	@Column(name="login", nullable=false)
	private String login;
	
	@Column(name="senha", nullable=false)
	private String senha;
	
	@Column(name="papel_atual")
	private String papelAtual;
	
	public Long getUsuario_id() {
		return usuario_id;
	}
	public void setUsuario_id(Long usuario_id) {
		this.usuario_id = usuario_id;
	}
	public List<Papel> getPapeis() {
		return papeis;
	}
	public void setPapeis(List<Papel> papeis) {
		this.papeis = papeis;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public List<Classificado> getClassificados() {
		return classificados;
	}
	public void setClassificados(List<Classificado> classificados) {
		this.classificados = classificados;
	}
	public List<Noticia> getNoticias() {
		return noticias;
	}
	public void setNoticias(List<Noticia> noticias) {
		this.noticias = noticias;
	}
	public List<Comentario> getComentarios() {
		return comentarios;
	}
	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}
	
	public String getPapelAtual() {
		return papelAtual;
	}
	public void setPapelAtual(String papelAtual) {
		this.papelAtual = papelAtual;
	}
	public boolean equals(Object obj){
		if(!(obj instanceof Usuario))
			return false;
		
		Usuario ref = (Usuario) obj;
		if(ref.getUsuario_id() == this.usuario_id)
			return true;
		return false;
	}
}
