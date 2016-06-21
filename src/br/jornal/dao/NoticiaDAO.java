package br.jornal.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.jornal.dao.interfaces.INoticia;
import br.jornal.model.Noticia;

@Repository
public class NoticiaDAO implements INoticia{
	
	@PersistenceContext
	private EntityManager manager;
	
	public void inserir(Noticia noticia){
		manager.persist(noticia);
	}
	
	public void remover(Noticia noticia){
		manager.remove(noticia);
	}
	
	public void alterar(Noticia noticia){
		manager.merge(noticia);
	}
	
	public List<Noticia> listar(){
		return manager.createQuery("select n from noticia as n", Noticia.class).getResultList();
	}
	
	public List<Noticia> listarUltimas(){
		List<Noticia> noticias =  null;
		Query query = manager.createQuery("from noticia order by noticia_id desc");		
		noticias = query.getResultList();
		return noticias;
	}
	
	public Noticia recuperar(Long id){
		return manager.find(Noticia.class, id);
	}

	@Override
	public Noticia recuperar(String titulo) {
		String hql = "select n from noticia as n where n.titulo = :param_titulo";
		Query query = manager.createQuery(hql);
		List<Noticia> noticias = query.setParameter("param_titulo", titulo).getResultList();
		if(noticias.size() != 0){
			return noticias.get(0);
		}
		return null;
	}
}
