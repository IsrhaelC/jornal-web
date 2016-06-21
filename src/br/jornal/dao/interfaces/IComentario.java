package br.jornal.dao.interfaces;

import java.util.List;

import br.jornal.model.Comentario;

public interface IComentario {
	
	public void inserir(Comentario comentario);
	public void remover(Comentario comentario);
	public void alterar(Comentario comentario);
	public List<Comentario> listar();
	public Comentario recuperar(Long id);
}
