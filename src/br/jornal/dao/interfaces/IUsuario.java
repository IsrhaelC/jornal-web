package br.jornal.dao.interfaces;

import java.util.List;

import br.jornal.model.Usuario;

public interface IUsuario {
	
	public void inserir(Usuario usuario);
	public void remover(Usuario usuario);
	public void alterar(Usuario usuario);
	public List<Usuario> listar();
	public Usuario recuperar(Long id);
	public Usuario recuperar(String login);
}
