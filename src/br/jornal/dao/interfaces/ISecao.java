package br.jornal.dao.interfaces;

import java.util.List;

import br.jornal.model.Secao;

public interface ISecao {
	
	public void inserir(Secao secao);
	public void remover(Secao secao);
	public void alterar(Secao secao);
	public List<Secao> listar();
	public Secao recuperar(Long id);
	public Secao recuperar(String titulo);
}
