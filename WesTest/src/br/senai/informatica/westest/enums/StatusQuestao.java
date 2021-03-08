package br.senai.informatica.westest.enums;

public enum StatusQuestao {

	APROVADA("Aprovada"), REJEITADA("Rejeitada"), ENCAMINHADA("Encaminhada"), DESATIVADA("Desativada"), SIMULADOS ("Simulados");
	
	private String status;
	
	private StatusQuestao(String status){
		this.status = status;
	}
	
	
	public String toString(){
		
		return status;
		
	}
}
