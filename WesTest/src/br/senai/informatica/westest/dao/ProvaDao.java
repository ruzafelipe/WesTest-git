package br.senai.informatica.westest.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.senai.informatica.westest.modelo.Prova;
import br.senai.informatica.westest.modelo.Questao;

@Repository
public class ProvaDao implements InterfaceDao<Prova> {
	
	
	@PersistenceContext 
	private EntityManager manager;

	@Override
	public void inserir(Prova objeto) {
		manager.persist(objeto);
		
	}

	@Override
	public void alterar(Prova objeto) {
		manager.persist(objeto);
		
	}

	@Override
	public void excluir(Long id) {
		Prova p = manager.find(Prova.class, id);
		manager.remove(p); 
	}

	@Override
	public List<Prova> listar() {
		TypedQuery<Prova> query = manager.createQuery("select p from Prova p", Prova.class);
		return query.getResultList();
	}

	@Override
	public Prova buscar(long id) {
		return  manager.find(Prova.class, id);
	}
	
	
	public List<Prova> listarProvasAvaliador(Long id) {
		TypedQuery<Prova> query = manager.createQuery("select p from Prova p where p.avaliador.idPessoa= :id and p.statusProva = 0", Prova.class);
		query.setParameter("id", id);
		return query.getResultList();
	}
	
	public List<Prova> listarProvasCoordenador(Long id) {
		TypedQuery<Prova> query = manager.createQuery("select p from Prova p where p.coordenador.idPessoa= :id and p.statusProva = 0", Prova.class);
		query.setParameter("id", id);
		return query.getResultList();
	}
	
	public List<Prova> listarProvasFinAvaliador(Long id) {
		TypedQuery<Prova> query = manager.createQuery("select p from Prova p where p.avaliador.idPessoa= :id and p.statusProva = 2", Prova.class);
		query.setParameter("id", id);
		return query.getResultList();
	}
	
	public List<Prova> listarProvasFinCoordenador(Long id) {
		TypedQuery<Prova> query = manager.createQuery("select p from Prova p where p.coordenador.idPessoa= :id and p.statusProva = 2", Prova.class);
		query.setParameter("id", id);
		return query.getResultList();
	}

}
