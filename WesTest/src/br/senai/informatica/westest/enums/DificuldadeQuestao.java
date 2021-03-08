package br.senai.informatica.westest.enums;

public enum DificuldadeQuestao {

	ALTA ("Alta"), MEDIA ("Média"), BAIXA ("Baixa");
	
	private String dificuldade;
	
	private DificuldadeQuestao(String dificuldade){
		this.dificuldade = dificuldade;
	}
	
	
	public String toString(){
		return dificuldade;
	}
}
