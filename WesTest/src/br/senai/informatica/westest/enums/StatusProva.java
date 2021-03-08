package br.senai.informatica.westest.enums;

public enum StatusProva {
	
	CRIADA ("Criada"), APLICADA ("Aplicada"), FINALIZADA("Finzalizada"), CORRIGIDA ("Corrigida");
	
	private String status;
	
	private StatusProva(String status){
		this.status = status;
	}
	
	
	
	public String toString(){
		return status;
	}
	

}
