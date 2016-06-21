package br.jornal.dao.interfaces;

import java.util.List;

import br.jornal.model.Noticia;

public interface INoticia {
	
	public void inserir(Noticia noticia);
	public void remover(Noticia noticia);
	public void alterar(Noticia noticia);
	public List<Noticia> listar();
	public Noticia recuperar(Long id);
	public Noticia recuperar(String titulo);
	public List<Noticia> listarUltimas();
}
