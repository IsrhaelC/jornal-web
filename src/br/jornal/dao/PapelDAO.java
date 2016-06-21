package br.jornal.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.jornal.dao.interfaces.IPapel;
import br.jornal.model.Papel;

@Repository
public class PapelDAO implements IPapel{
	
	@PersistenceContext
	private EntityManager manager;
	
	public void inserir(Papel papel){
		manager.persist(papel);
	}
	
	public void remover(Papel papel){
		manager.remove(papel);
	}
	
	public void alterar(Papel papel){
		manager.merge(papel);
	}
	
	public List<Papel> listar(){
		return manager.createQuery("select p from papel as p", Papel.class).getResultList();
	}
	
	public Papel recuperar(Long id){
		return manager.find(Papel.class, id);
	}

	@Override
	public Papel recuperar(String papel) {
		String hql = "select p from papel as p where p.papel = :param_papel";
		Query query = manager.createQuery(hql);
		List<Papel> papeis = query.setParameter("param_papel", papel).getResultList();
		if(papeis.size() != 0){
			return papeis.get(0);
		}
		return null;
	}
	
}
