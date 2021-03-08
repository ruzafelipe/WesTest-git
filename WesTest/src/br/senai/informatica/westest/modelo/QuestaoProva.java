package br.senai.informatica.westest.modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class QuestaoProva {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idQuestaoProva;
	@ManyToOne(fetch = FetchType.EAGER)
	private Questao questao;	
	private double valorAtribuido;
	private double valorResultante;	
	private Long tempoAtribuido;
	private String tempoAtribuidoString;
	


	public Questao getQuestao() {
		return questao;
	}
	public void setQuestao(Questao questao) {
		this.questao = questao;
	}
	
	public Long getIdQuestaoProva() {
		return idQuestaoProva;
	}
	public void setIdQuestaoProva(Long idQuestaoProva) {
		this.idQuestaoProva = idQuestaoProva;
	}
	
	
	public double getValorAtribuido() {
		return valorAtribuido;
	}
	public void setValorAtribuido(double valorAtribuido) {
		this.valorAtribuido = valorAtribuido;
	}
	public double getValorResultante() {
		return valorResultante;
	}
	public void setValorResultante(double valorResultante) {
		this.valorResultante = valorResultante;
	}
	public String getTempoAtribuidoString() {
		return tempoAtribuidoString;
	}
	public void setTempoAtribuidoString(String tempoAtribuidoString) {
		this.tempoAtribuidoString = tempoAtribuidoString;
	}
	public Long getTempoAtribuido() {
		return tempoAtribuido;
	}
	public void setTempoAtribuido(Long tempoAtribuido) {
		this.tempoAtribuido = tempoAtribuido;
	}
	
	
	

}
