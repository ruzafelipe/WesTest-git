package br.senai.informatica.westest.restcontroller;



import javax.servlet.ServletContext;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.ServletContextAware;

import br.senai.informatica.westest.dao.PessoaDao;
import br.senai.informatica.westest.modelo.Aluno;
import br.senai.informatica.westest.modelo.Pessoa;
import br.senai.informatica.westest.dao.InterfaceDao;




@Transactional
@RestController
@RequestMapping("/services/pessoa")
public class AlunoRestController implements ServletContextAware {

	private ServletContext context;
	
	@Autowired
	@Qualifier("pessoaDao")
	private InterfaceDao<Pessoa> dao;
	
	
	
	@RequestMapping(value="/logar", method=RequestMethod.POST,
			headers="Accept=application/json;charset=UTF-8")
	public Pessoa logar (@RequestBody String json){
		Pessoa aluno = null;
		System.out.println(json);
		try{
			JSONObject job = new JSONObject(json);				
			
			String login = job.getString("login");
			String senha = job.getString("senha");
			
			aluno = ((PessoaDao)dao).logar(login, senha);
			
			System.out.println("Teste" + aluno.getNome());
			
					}catch(Exception e){
						throw new RuntimeException(e);				
						
		}
		
		
		return aluno;
	}
	
	@RequestMapping(value = "/alterar_senha", method = RequestMethod.POST, headers = "Accept=application/json;charset=UTF-8")
	public String alterar(@RequestBody String json) {
		
		String retorno;
		
		try {
			JSONObject job = new JSONObject(json);	
			
			Aluno pessoa = new Aluno();
			
			if(!job.isNull("idPessoa")){
				pessoa.setIdPessoa(job.getLong("idPessoa"));
			}
			
			pessoa.setLogin(job.getString("login"));
			pessoa.setNome(job.getString("nome"));
			pessoa.setSenha(job.getString("senha"));
			pessoa.setFoto(null);
			
			System.out.println(pessoa.getSenha());
			System.out.println(pessoa.getLogin());
			System.out.println(pessoa.getIdPessoa());
			System.out.println(pessoa.getNome());
			
			dao.alterar(pessoa);

			
			retorno = "Senha do(a)" + pessoa.getIdPessoa() + " alterada com sucesso";			
			
		
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return retorno;
	

	}

	@Override
	public void setServletContext(ServletContext arg0) {
		this.context = arg0;
		
	}


	
	
}
