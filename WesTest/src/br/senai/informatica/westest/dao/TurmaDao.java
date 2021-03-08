package br.senai.informatica.westest.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import br.senai.informatica.westest.modelo.Turma;

@Repository
public class TurmaDao implements InterfaceDao<Turma>{

	@PersistenceContext 
	private EntityManager manager;
	
	
	@Override
	public void inserir(Turma objeto) {
		manager.persist(objeto);
		
	}

	@Override
	public void alterar(Turma objeto) {
		manager.merge(objeto);
		
	}

	@Override
	public void excluir(Long id) {
		Turma t = manager.find(Turma.class, id);
		manager.remove(t); 
		
	}

	@Override
	public List<Turma> listar() {
		TypedQuery<Turma> query = manager.createQuery("select t from Turma t", Turma.class);
		return query.getResultList();
	}

	@Override
	public Turma buscar(long id) {
		return  manager.find(Turma.class, id);
	}
	
	

}
