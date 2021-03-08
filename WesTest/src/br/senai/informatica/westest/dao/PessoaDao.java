package br.senai.informatica.westest.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.senai.informatica.westest.modelo.Aluno;
import br.senai.informatica.westest.modelo.Avaliador;
import br.senai.informatica.westest.modelo.Coordenador;
import br.senai.informatica.westest.modelo.Pessoa;

@Repository
public class PessoaDao implements InterfaceDao<Pessoa>{
	
	@PersistenceContext 
	private EntityManager manager;

	@Override
	public void inserir(Pessoa objeto) {
		manager.persist(objeto);
		
	}

	@Override
	public void alterar(Pessoa objeto) {
		manager.merge(objeto);	
	}

	@Override
	public void excluir(Long id) {
		Pessoa p = manager.find(Pessoa.class, id);
		manager.remove(p); 
		
	}

	@Override
	public List<Pessoa> listar() {
		TypedQuery<Pessoa> query = manager.createQuery("select p from Pessoa c", Pessoa.class);
		return query.getResultList();
	}

	@Override
	public Pessoa buscar(long id) {
		return  manager.find(Pessoa.class, id);
	}

	public List<Aluno> listarAlunos(){
		TypedQuery<Aluno> query = manager.createQuery("select a from Aluno a order by a.nome", Aluno.class);
		return query.getResultList();
	}
	
	public List<Avaliador> listarAvaliadores(){
		TypedQuery<Avaliador> query = manager.createQuery("select av from Avaliador av order by av.nome", Avaliador.class);
		return query.getResultList();
	}
	
	public List<Coordenador> listarCoordenadores(){
		TypedQuery<Coordenador> query = manager.createQuery("select c from Coordenador c order by c.nome", Coordenador.class);
		return query.getResultList();
	}
	
	
	
	
	
	
	
	
	public Pessoa logar (String login, String senha){		
		TypedQuery<Pessoa> query = manager.createQuery("select p from Pessoa p where p.login = :login and p.senha = :senha", Pessoa.class);		
		query.setParameter("login", login);
		query.setParameter("senha", senha);		
		if (query.getResultList().size() != 0) {
			return query.getResultList().get(0);
		}else{
			return null;
		}		
	}
	
}
