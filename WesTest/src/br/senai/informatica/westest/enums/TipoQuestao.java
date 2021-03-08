package br.senai.informatica.westest.enums;

public enum TipoQuestao {

	OBJETIVA ("Objetiva"), DISSERTATIVA ("Dissertativa");
	
	private String tipo;
	
	private TipoQuestao (String tipo){
		this.tipo = tipo;
	}
	
	
	public String toString(){
		return tipo;
	}
	
	
}
