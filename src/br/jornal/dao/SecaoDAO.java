package br.jornal.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.jornal.dao.interfaces.ISecao;
import br.jornal.model.Secao;

@Repository
public class SecaoDAO implements ISecao{
	
	@PersistenceContext
	private EntityManager manager;
	
	public void inserir(Secao secao){
		manager.persist(secao);
	}
	
	public void remover(Secao secao){
		manager.remove(secao);
	}
	
	public void alterar(Secao secao){
		manager.merge(secao);
	}
	
	public List<Secao> listar(){
		return manager.createQuery("select s from secao as s", Secao.class).getResultList();
	}
	
	public Secao recuperar(Long id){
		return manager.find(Secao.class, id);
	}

	@Override
	public Secao recuperar(String titulo) {
		String hql = "select s from secao as s where s.titulo = :param_titulo";
		Query query = manager.createQuery(hql);
		List<Secao> secoes = query.setParameter("param_titulo", titulo).getResultList();
		if(secoes.size() != 0){
			return secoes.get(0);
		}
		return null;
	}
}
