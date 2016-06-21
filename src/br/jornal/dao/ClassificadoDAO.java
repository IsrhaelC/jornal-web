package br.jornal.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.jornal.dao.interfaces.IClassificado;
import br.jornal.model.Classificado;

@Repository
public class ClassificadoDAO implements IClassificado{
	
	@PersistenceContext
	private EntityManager manager;
	
	public void inserir(Classificado classificado){
		manager.persist(classificado);
	}
	
	public void remover(Classificado classificado){
		manager.remove(classificado);
	}
	
	public void alterar(Classificado classificado){
		manager.merge(classificado);
	}
	
	public List<Classificado> listar(){
		return manager.createQuery("select c from classificado as c", Classificado.class).getResultList();
	}
	
	public Classificado recuperar(Long id){
		return manager.find(Classificado.class, id);
	}
}
