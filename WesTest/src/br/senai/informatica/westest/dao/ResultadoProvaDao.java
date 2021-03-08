package br.senai.informatica.westest.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.senai.informatica.westest.modelo.ResultadoProva;
import br.senai.informatica.westest.modelo.Turma;

@Repository
public class ResultadoProvaDao implements InterfaceDao<ResultadoProva> {
	
	
	@PersistenceContext 
	private EntityManager manager;

	@Override
	public void inserir(ResultadoProva objeto) {
		manager.persist(objeto);
		
	}

	@Override
	public void alterar(ResultadoProva objeto) {
		manager.merge(objeto);
		
	}

	@Override
	public void excluir(Long id) {
		ResultadoProva rp = manager.find(ResultadoProva.class, id);
		manager.remove(rp); 
	}

	@Override
	public List<ResultadoProva> listar() {
		TypedQuery<ResultadoProva> query = manager.createQuery("select rp from ResultadoProva rp", ResultadoProva.class);
		return query.getResultList();
	}

	@Override
	public ResultadoProva buscar(long id) {
		return  manager.find(ResultadoProva.class, id);
	}

}
