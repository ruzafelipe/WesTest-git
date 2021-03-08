package br.senai.informatica.westest.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Resposta {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idResposta;
	@ManyToOne	
	private Questao questao;
	@Column(nullable=false)
	private String descricao;
	private boolean resposta;
	
	
	public Long getIdResposta() {
		return idResposta;
	}
	public void setIdResposta(Long idResposta) {
		this.idResposta = idResposta;
	}	
	public Questao getIdQuestao() {
		return questao;
	}
	public void setIdQuestao(Questao idQuestao) {
		this.questao = idQuestao;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public boolean isResposta() {
		return resposta;
	}
	public void setResposta(boolean resposta) {
		this.resposta = resposta;
	}
	
	
	
}
