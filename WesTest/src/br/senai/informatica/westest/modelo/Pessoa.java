package br.senai.informatica.westest.modelo;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "do_tipo")
public class Pessoa {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Long idPessoa;
	@Column(unique=true, nullable=false)
	private String login;
	@Column(unique=true, nullable=false)
	private String nome;
	@Column(nullable=false)
	private String senha;
	@Column(columnDefinition = "mediumblob")
	private byte[] foto;
	
	public Long getIdPessoa() {
		return idPessoa;
	}
	public void setIdPessoa(Long idPessoa) {
		this.idPessoa = idPessoa;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		/*Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		this.senha = encoder.encodePassword(senha, null);	*/
		
		this.senha = senha;
	}
	public byte[] getFoto() {
		return foto;
	}
	public void setFoto(byte[] foto) {
		this.foto = foto;
	}


	
	
	

}
