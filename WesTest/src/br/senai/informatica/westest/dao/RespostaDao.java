package br.senai.informatica.westest.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.senai.informatica.westest.modelo.Questao;
import br.senai.informatica.westest.modelo.Resposta;

@Repository
public class RespostaDao implements InterfaceDao<Resposta>{
	
	@PersistenceContext 
	private EntityManager manager;

	@Override
	public void inserir(Resposta objeto) {
		manager.persist(objeto);
		
	}

	@Override
	public void alterar(Resposta objeto) {
		manager.merge(objeto);
		
	}

	@Override
	public void excluir(Long id) {
		
	}

	@Override
	public List<Resposta> listar() {
		TypedQuery<Resposta> query = manager.createQuery("select r from Resposta r", Resposta.class);
		return query.getResultList();
	}

	@Override
	public Resposta buscar(long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Resposta> listarPorQuestao(Long id) {
		TypedQuery<Resposta> query = manager.createQuery("select r from Resposta r where r.questao.idQuestao = :id", Resposta.class);
		query.setParameter("id", id);
		return query.getResultList();
	}
	
	public Resposta buscarPorQuestao(Long id) {
		TypedQuery<Resposta> query = manager.createQuery("select r from Resposta r where r.questao.idQuestao = :id", Resposta.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}

}
