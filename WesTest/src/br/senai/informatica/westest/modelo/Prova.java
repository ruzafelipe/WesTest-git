package br.senai.informatica.westest.modelo;


import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.senai.informatica.westest.enums.StatusProva;

@Entity
public class Prova {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Long idProva;
	private String titulo;
	@OneToOne
	private Pessoa avaliador;
	@OneToOne
	private Pessoa coordenador;
	@ManyToMany
	private List<Disciplina> disciplina;
	@JsonFormat(pattern="dd/MM/yyyy")
	private Calendar dtCriacao;
	@JsonFormat(pattern="dd/MM/yyyy")
	private Calendar dtAplicacao;
	@JsonFormat(pattern="dd/MM/yyyy")
	private Calendar dtFim;    
	private StatusProva statusProva;
	@ManyToMany(fetch = FetchType.EAGER)
	private List<QuestaoProva> questoesProva;	
	private String tempoProvaString;
	private String codVerificacao; 
			
	

	
	public String getCodVerificacao() {
		return codVerificacao;
	}
	public void setCodVerificacao(String codVerificacao) {
		this.codVerificacao = codVerificacao;
	}
	public String getTempoProvaString() {
		return tempoProvaString;
	}
	public void setTempoProvaString(String tempoProvaString) {
		this.tempoProvaString = tempoProvaString;
	}
	public Pessoa getAvaliador() {
		return avaliador;
	}
	public void setAvaliador(Pessoa avaliador) {
		this.avaliador = avaliador;
	}
	public Pessoa getCoordenador() {
		return coordenador;
	}
	public void setCoordenador(Pessoa coordenador) {
		this.coordenador = coordenador;
	}
	public List<QuestaoProva> getQuestoesProva() {
		return questoesProva;
	}
	public void setQuestoesProva(List<QuestaoProva> questoesProva) {
		this.questoesProva = questoesProva;
	}
	public List<Disciplina> getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(List<Disciplina> disciplina) {
		this.disciplina = disciplina;
	}
	public Long getIdProva() {
		return idProva;
	}
	public void setIdProva(Long idProva) {
		this.idProva = idProva;
	}

	
	
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Pessoa getIdAvaliador() {
		return avaliador;
	}
	public void setIdAvaliador(Pessoa avaliador) {
		this.avaliador = avaliador;
	}
	public Pessoa getIdCoordenador() {
		return coordenador;
	}
	public void setIdCoordenador(Pessoa coordenador) {
		this.coordenador = coordenador;
	}

	public Calendar getDtCriacao() {
		return dtCriacao;
	}
	public void setDtCriacao(Calendar dtCriacao) {
		this.dtCriacao = dtCriacao;
	}
	public Calendar getDtAplicacao() {
		return dtAplicacao;
	}
	public void setDtAplicacao(Calendar dtAplicacao) {
		this.dtAplicacao = dtAplicacao;
	}
	public StatusProva getStatusProva() {
		return statusProva;
	}
	public void setStatusProva(StatusProva statusProva) {
		this.statusProva = statusProva;
	}
	public Calendar getDtFim() {
		return dtFim;
	}
	public void setDtFim(Calendar dtFim) {
		this.dtFim = dtFim;
	}
	
	
	
}
