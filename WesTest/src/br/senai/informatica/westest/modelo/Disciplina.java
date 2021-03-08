package br.senai.informatica.westest.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Disciplina {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idDisciplina;
	@Column(unique=true, nullable=false)
	private String descricao;
	@ManyToOne
	private Pessoa coordenador;
	
	public Long getIdDisciplina() {
		return idDisciplina;
	}
	public void setIdDisciplina(Long idDisciplina) {
		this.idDisciplina = idDisciplina;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Pessoa getIdCoordenador() {
		return coordenador;
	}
	public void setIdCoordenador(Pessoa idCoordenador) {
		this.coordenador = idCoordenador;
	}

	
	
	
}
