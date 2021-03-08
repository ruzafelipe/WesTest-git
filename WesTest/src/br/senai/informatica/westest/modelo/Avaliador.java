package br.senai.informatica.westest.modelo;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue	("Avaliador")
public class Avaliador extends Pessoa {

	@ManyToOne
	private Disciplina disciplina;
	

	public Disciplina getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	
	

}
