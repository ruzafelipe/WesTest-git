package br.senai.informatica.westest.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.senai.informatica.westest.modelo.Disciplina;

@Repository
public class DisciplinaDao implements InterfaceDao<Disciplina> {

	@PersistenceContext 
	private EntityManager manager;
	
	
	
	@Override
	public void inserir(Disciplina objeto) {
		manager.persist(objeto);
		
	}

	@Override
	public void alterar(Disciplina objeto) {
		manager.merge(objeto);
		
	}

	@Override
	public void excluir(Long id) {
		Disciplina d = manager.find(Disciplina.class, id);
		manager.remove(d); 
		
	}

	@Override
	public List<Disciplina> listar() {
		TypedQuery<Disciplina> query = manager.createQuery("select d from Disciplina d", Disciplina.class);
		return query.getResultList();
	}

	@Override
	public Disciplina buscar(long id) {
		return  manager.find(Disciplina.class, id);
	}
	
	public List<Disciplina> listarPorCoord(Long id) {
		TypedQuery<Disciplina> query = manager.createQuery("select d from Disciplina d where d.coordenador.idPessoa = :id", Disciplina.class);
		query.setParameter("id", id);
		return query.getResultList();
	}


}
