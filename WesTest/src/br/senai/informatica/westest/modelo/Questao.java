package br.senai.informatica.westest.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import br.senai.informatica.westest.enums.DificuldadeQuestao;
import br.senai.informatica.westest.enums.StatusQuestao;
import br.senai.informatica.westest.enums.TipoQuestao;

@Entity
public class Questao {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idQuestao;
	@Column(unique=true, nullable=false)
	private String enunciado;
	private TipoQuestao tipoQuestao;
	private StatusQuestao statusQuestao;
	private Long idDisciplina;
	private DificuldadeQuestao dificuldadeQuestao;
	private int qtdUso;
	private Long idAvaliador;
	private Long idCoordenador;
    private String comentario;
    
	public Long getIdQuestao() {
		return idQuestao;
	}
	public void setIdQuestao(Long idQuestao) {
		this.idQuestao = idQuestao;
	}
	public String getEnunciado() {
		return enunciado;
	}
	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}
	public TipoQuestao getTipoQuestao() {
		return tipoQuestao;
	}
	public void setTipoQuestao(TipoQuestao tipoQuestao) {
		this.tipoQuestao = tipoQuestao;
	}
	public StatusQuestao getStatusQuestao() {
		return statusQuestao;
	}
	public void setStatusQuestao(StatusQuestao statusQuestao) {
		this.statusQuestao = statusQuestao;
	}

	public Long getIdDisciplina() {
		return idDisciplina;
	}
	public void setIdDisciplina(Long idDisciplina) {
		this.idDisciplina = idDisciplina;
	}
	public Long getIdAvaliador() {
		return idAvaliador;
	}
	public void setIdAvaliador(Long idAvaliador) {
		this.idAvaliador = idAvaliador;
	}
	public Long getIdCoordenador() {
		return idCoordenador;
	}
	public void setIdCoordenador(Long idCoordenador) {
		this.idCoordenador = idCoordenador;
	}
	public DificuldadeQuestao getDificuldadeQuestao() {
		return dificuldadeQuestao;
	}
	public void setDificuldadeQuestao(DificuldadeQuestao dificuldadeQuestao) {
		this.dificuldadeQuestao = dificuldadeQuestao;
	}
	public int getQtdUso() {
		return qtdUso;
	}
	public void setQtdUso(int qtdUso) {
		this.qtdUso = qtdUso;
	}

	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
    
    
    
    
    

}
