package br.senai.informatica.westest.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.senai.informatica.westest.modelo.Turma;
import br.senai.informatica.westest.modelo.TurmaProva;

@Repository
public class TurmaProvaDao implements InterfaceDao<TurmaProva> {
	
	@PersistenceContext 
	private EntityManager manager;

	@Override
	public void inserir(TurmaProva objeto) {
		manager.persist(objeto);
		
	}

	@Override
	public void alterar(TurmaProva objeto) {
		manager.merge(objeto);
		
	}

	@Override
	public void excluir(Long id) {
		TurmaProva t = manager.find(TurmaProva.class, id);
		manager.remove(t);
	}

	@Override
	public List<TurmaProva> listar() {
		TypedQuery<TurmaProva> query = manager.createQuery("select tp from TurmaProva tp", TurmaProva.class);
		return query.getResultList();
	}

	@Override
	public TurmaProva buscar(long id) {
		return  manager.find(TurmaProva.class, id);
	}

}
