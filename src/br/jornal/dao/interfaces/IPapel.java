package br.jornal.dao.interfaces;

import java.util.List;

import br.jornal.model.Papel;

public interface IPapel {
	
	public void inserir(Papel papel);
	public void remover(Papel papel);
	public void alterar(Papel papel);
	public List<Papel> listar();
	public Papel recuperar(Long id);
	public Papel recuperar(String papel);
}
