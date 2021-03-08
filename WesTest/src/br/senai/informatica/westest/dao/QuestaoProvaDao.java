package br.senai.informatica.westest.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.senai.informatica.westest.modelo.Questao;
import br.senai.informatica.westest.modelo.QuestaoProva;

@Repository
public class QuestaoProvaDao implements InterfaceDao<QuestaoProva> {

	
	@PersistenceContext 
	private EntityManager manager;
	
	
	@Override
	public void inserir(QuestaoProva objeto) {
		manager.persist(objeto);
		
	}

	@Override
	public void alterar(QuestaoProva objeto) {
		manager.merge(objeto);	
		
	}

	@Override
	public void excluir(Long id) {
		QuestaoProva qp = manager.find(QuestaoProva.class, id);
		manager.remove(qp); 
		
	}

	@Override
	public List<QuestaoProva> listar() {
		TypedQuery<QuestaoProva> query = manager.createQuery("select qp from QuestaoProva qp", QuestaoProva.class);
		return query.getResultList();
	}

	@Override
	public QuestaoProva buscar(long id) {
		return  manager.find(QuestaoProva.class, id);
	}

}
