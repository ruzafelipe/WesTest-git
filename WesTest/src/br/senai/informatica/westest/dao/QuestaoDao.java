package br.senai.informatica.westest.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.senai.informatica.westest.enums.DificuldadeQuestao;
import br.senai.informatica.westest.enums.TipoQuestao;
import br.senai.informatica.westest.modelo.Pessoa;
import br.senai.informatica.westest.modelo.Questao;

@Repository
public class QuestaoDao implements InterfaceDao<Questao> {

	@PersistenceContext 
	private EntityManager manager;
	
	
	@Override
	public void inserir(Questao objeto) {
		manager.persist(objeto);
		
	}

	@Override
	public void alterar(Questao objeto) {
		manager.merge(objeto);	
	}

	@Override
	public void excluir(Long id) {
		Questao q = manager.find(Questao.class, id);
		manager.remove(q); 
		
	}

	@Override
	public List<Questao> listar() {
		TypedQuery<Questao> query = manager.createQuery("select q from Questao q", Questao.class);
		return query.getResultList();
	}

	@Override
	public Questao buscar(long id) {
		return  manager.find(Questao.class, id);
	}
	
	
	public List<Questao> listarMinhasQuestoes(Long id) {
		TypedQuery<Questao> query = manager.createQuery("select q from Questao q where q.idAvaliador = :id", Questao.class);
		query.setParameter("id", id);
		return query.getResultList();
	}
	
	public List<Questao> listarQuestoesPendentes(Long id) {
		TypedQuery<Questao> query = manager.createQuery("select q from Questao q where q.idCoordenador= :id and q.statusQuestao = 2", Questao.class);
		query.setParameter("id", id);
		return query.getResultList();
	}
	
	public List<Questao> listarQuestoesAprovadasAvaliador(Long id, DificuldadeQuestao dificuldadeQuestao, TipoQuestao tipoQuestao) {
		TypedQuery<Questao> query = manager.createQuery("select q from Questao q where q.idDisciplina= :id and q.statusQuestao = 0 and"
				+ " q.dificuldadeQuestao= :dificuldadeQuestao and q.tipoQuestao = :tipoQuestao", Questao.class);
		query.setParameter("id", id);
		query.setParameter("tipoQuestao", tipoQuestao);		
		query.setParameter("dificuldadeQuestao", dificuldadeQuestao);
		return query.getResultList();
	}
	
	public List<Questao> listarQuestoesAprovadasCoordenador(Long id, DificuldadeQuestao dificuldadeQuestao, TipoQuestao tipoQuestao) {
		TypedQuery<Questao> query = manager.createQuery("select q from Questao q where q.idCoordenador = :id and q.statusQuestao = 0 and"
				+ " q.dificuldadeQuestao= :dificuldadeQuestao and q.tipoQuestao = :tipoQuestao", Questao.class);
		query.setParameter("id", id);
		query.setParameter("tipoQuestao", tipoQuestao);		
		query.setParameter("dificuldadeQuestao", dificuldadeQuestao);
		return query.getResultList();
	}
	
	public List<Questao> gerarProvaAutoAva(String quantidade, Long id) {
		TypedQuery<Questao> query = manager.createQuery("select q from Questao q where q.idDisciplina= :id and q.statusQuestao = 0 ", Questao.class);
		query.setParameter("id", id);		
		try {
			return query.getResultList().subList(0, Integer.parseInt(quantidade));
		} catch (Exception e) {
			return null;
		}
	}
	
	public List<Questao> gerarProvaAutoAva(TipoQuestao tipoQuestao, String quantidade, Long id) {
		TypedQuery<Questao> query = manager.createQuery("select q from Questao q where q.idDisciplina= :id and q.statusQuestao = 0 "
				+ "and q.tipoQuestao = :tipoQuestao  ", Questao.class);
		query.setParameter("id", id);
		query.setParameter("tipoQuestao", tipoQuestao);	
		try {
			return query.getResultList().subList(0, Integer.parseInt(quantidade));
		} catch (Exception e) {
			return null;
		}
	}
	
	public List<Questao> gerarProvaAutoCoord(String quantidade, Long id) {
		TypedQuery<Questao> query = manager.createQuery("select q from Questao q where q.idCoordenador= :id and q.statusQuestao = 0 ", Questao.class);
		query.setParameter("id", id);		
		try {
			return query.getResultList().subList(0, Integer.parseInt(quantidade));
		} catch (Exception e) {
			return null;
		}
	}
	
	public List<Questao> gerarProvaAutoCoord(TipoQuestao tipoQuestao, String quantidade, Long id) {
		TypedQuery<Questao> query = manager.createQuery("select q from Questao q where q.idCoordenador= :id and q.statusQuestao = 0 "
				+ "and q.tipoQuestao = :tipoQuestao  ", Questao.class);
		query.setParameter("id", id);
		query.setParameter("tipoQuestao", tipoQuestao);	
		try {
			return query.getResultList().subList(0, Integer.parseInt(quantidade));
		} catch (Exception e) {
			return null;
		}
	}
	
	
	
	

}
