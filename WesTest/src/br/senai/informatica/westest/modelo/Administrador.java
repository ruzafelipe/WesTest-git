package br.senai.informatica.westest.modelo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue	("Administrador")
public class Administrador extends Pessoa {
	
	
	

}
