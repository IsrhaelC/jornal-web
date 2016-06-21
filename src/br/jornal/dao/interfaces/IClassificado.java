package br.jornal.dao.interfaces;

import java.util.List;

import br.jornal.model.Classificado;

public interface IClassificado {
	
	public void inserir(Classificado classificado);
	public void remover(Classificado classificado);
	public void alterar(Classificado classificado);
	public List<Classificado> listar();
	public Classificado recuperar(Long id);
}
