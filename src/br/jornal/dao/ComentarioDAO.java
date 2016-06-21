package br.jornal.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.jornal.dao.interfaces.IComentario;
import br.jornal.model.Comentario;

@Repository
public class ComentarioDAO implements IComentario{
	
	@PersistenceContext
	private EntityManager manager;
	
	public void inserir(Comentario comentario){
		manager.persist(comentario);
	}
	
	public void remover(Comentario comentario){
		manager.remove(comentario);
	}
	
	public void alterar(Comentario comentario){
		manager.merge(comentario);
	}
	
	public List<Comentario> listar(){
		return manager.createQuery("select c from comentario as c", Comentario.class).getResultList();
	}
	
	public Comentario recuperar(Long id){
		return manager.find(Comentario.class, id);
	}
}
