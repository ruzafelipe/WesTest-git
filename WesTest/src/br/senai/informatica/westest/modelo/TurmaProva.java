package br.senai.informatica.westest.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class TurmaProva {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idTurmaProva;
	@ManyToOne
	private Prova prova;
	@ManyToOne
	private Turma turma;
	

	
	public Long getIdTurmaProva() {
		return idTurmaProva;
	}
	public void setIdTurmaProva(Long idTurmaProva) {
		this.idTurmaProva = idTurmaProva;
	}
	public Prova getProva() {
		return prova;
	}
	public void setProva(Prova prova) {
		this.prova = prova;
	}
	public Turma getTurma() {
		return turma;
	}
	public void setTurma(Turma turma) {
		this.turma = turma;
	}
	
	
		
	
	

}
