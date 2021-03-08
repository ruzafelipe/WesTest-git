package br.senai.informatica.westest.modelo;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
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

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class ResultadoProva {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idResultadoProva;
	@OneToOne
	private Aluno aluno;
	@OneToOne
	private Prova prova;
	private double nota;
	private double valor;
	private String[] resposta;
	private String[] consideracoes;	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Calendar dtCorreção;
	
	
	public Long getIdResultadoProva() {
		return idResultadoProva;
	}
	public void setIdResultadoProva(Long idResultadoProva) {
		this.idResultadoProva = idResultadoProva;
	}
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	public Prova getProva() {
		return prova;
	}
	public void setProva(Prova prova) {
		this.prova = prova;
	}
	public double getNota() {
		return nota;
	}
	public void setNota(double nota) {
		this.nota = nota;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public String[] getResposta() {
		return resposta;
	}
	public void setResposta(String[] resposta) {
		this.resposta = resposta;
	}
	public String[] getConsideracoes() {
		return consideracoes;
	}
	public void setConsideracoes(String[] consideracoes) {
		this.consideracoes = consideracoes;
	}
	public Calendar getDtCorreção() {
		return dtCorreção;
	}
	public void setDtCorreção(Calendar dtCorreção) {
		this.dtCorreção = dtCorreção;
	}
	
}
