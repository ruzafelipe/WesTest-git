package br.senai.informatica.westest.dao;

import java.util.List;

public interface InterfaceDao<T> {
	
	public void inserir (T objeto);
	public void alterar (T objeto);
	public void excluir (Long id);
	public List<T> listar();
	public T buscar (long id);

}
