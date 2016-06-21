package br.jornal.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.jornal.dao.interfaces.IUsuario;
import br.jornal.model.Usuario;

@Repository
public class UsuarioDAO implements IUsuario{
	
	@PersistenceContext
	private EntityManager manager;
	
	public void inserir(Usuario usuario){
		manager.persist(usuario);
	}
	
	public void remover(Usuario usuario){	
		manager.remove(usuario);
		
	}
	
	public void alterar(Usuario usuario){
		manager.merge(usuario);
	}
	
	public List<Usuario> listar(){		
		return manager.createQuery("select u from usuario as u", Usuario.class).getResultList(); 
	}
	
	public Usuario recuperar(Long id){
		System.out.println(id);
		return manager.find(Usuario.class, id);
	}

	@Override
	public Usuario recuperar(String login) {
		String hql = "select u from usuario as u where u.login = :param_login";
		Query query = manager.createQuery(hql);
		List<Usuario> usuarios = query.setParameter("param_login", login).getResultList();
		if(usuarios.size() != 0){
			return usuarios.get(0);
		}
		return null;
	}
}
