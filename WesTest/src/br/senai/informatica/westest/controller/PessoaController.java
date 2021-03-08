package br.senai.informatica.westest.controller;


import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

import br.senai.informatica.westest.dao.InterfaceDao;
import br.senai.informatica.westest.dao.PessoaDao;
import br.senai.informatica.westest.modelo.Aluno;
import br.senai.informatica.westest.modelo.Avaliador;
import br.senai.informatica.westest.modelo.Coordenador;
import br.senai.informatica.westest.modelo.Disciplina;
import br.senai.informatica.westest.modelo.Pessoa;
import br.senai.informatica.westest.modelo.Turma;

import br.senai.informatica.westest.util.UploadFoto;

@Transactional
@Controller
public class PessoaController {
	
	private UploadFoto upload;

	@Autowired
	@Qualifier("pessoaDao")
	InterfaceDao<Pessoa> daoPe;
	@Autowired
	@Qualifier("turmaDao")
	InterfaceDao<Turma> daoTur;
	@Autowired
	@Qualifier("disciplinaDao")
	InterfaceDao<Disciplina> daoDisc;
	
	
	
	@RequestMapping("/admin/form_aluno")
	public String formAluno(Model modelo) {
		modelo.addAttribute("turmas", daoTur.listar());
		return "/pessoa/aluno/form";
	}

	@RequestMapping("/admin/salvar_aluno")
	public String salvarAlu(Aluno pessoa, MultipartFile fileFoto) {
		if (pessoa.getIdPessoa() == null) {
			pessoa.setFoto(upload.salvarBytes(fileFoto));
			daoPe.inserir(pessoa);	
			
		} else {
			daoPe.alterar(pessoa);			
		}
		return "redirect:listar_alunos";
	}
	
	
	@RequestMapping("/admin/listar_alunos")
	public String listarAlu (Model modelo){
		PessoaDao peDao = (PessoaDao) daoPe;
		List<Aluno> listaAlu = peDao.listarAlunos();
		Gson gson = new Gson();
		modelo.addAttribute("listaAlu", gson.toJson(listaAlu));
		return "/pessoa/aluno/listaAlunos";
	}
	
	@RequestMapping ("/admin/alterar_aluno")
	public String alterarAlu (Long id, Model modelo){		
		Aluno aluno = (Aluno) daoPe.buscar(id);
		modelo.addAttribute("aluno", aluno);
		return "forward:form_aluno";
	}
	
	
	
	
	
	
	@RequestMapping("/admin/form_avaliador")
	public String formAvalaidor(Model modelo) {
		modelo.addAttribute("disci", daoDisc.listar());
		return "/pessoa/avaliador/form";
	}

	@RequestMapping("/admin/salvar_avaliador")
	public String salvarAva(Avaliador pessoa, MultipartFile fileFoto) {
		if (pessoa.getIdPessoa() == null) {
			pessoa.setFoto(upload.salvarBytes(fileFoto));
			daoPe.inserir(pessoa);			
		} else {
			daoPe.alterar(pessoa);			
		}
		return "redirect:listar_avaliadores";
	}
	
	@RequestMapping("/admin/listar_avaliadores")
	public String listarAva (Model modelo){
		PessoaDao peDao = (PessoaDao) daoPe;
		List<Avaliador> listaAva = peDao.listarAvaliadores();
		Gson gson = new Gson();
		modelo.addAttribute("listaAva", gson.toJson(listaAva));
		return "/pessoa/avaliador/listaAvaliadores";
	}
	
	@RequestMapping ("/admin/alterar_avaliador")
	public String alterarAva (Long id, Model modelo){		
		Avaliador ava = (Avaliador) daoPe.buscar(id);
		modelo.addAttribute("avaliador", ava);
		return "forward:form_avaliador";
	}
	
	
	
	
	
	
	@RequestMapping("/admin/form_coordenador")
	public String formCoordenador() {
		return "/pessoa/coordenador/form";
	}

	@RequestMapping("/admin/salvar_coordenador")
	public String salvarCoord(Coordenador pessoa, MultipartFile fileFoto) {
		if (pessoa.getIdPessoa() == null) {
			pessoa.setFoto(upload.salvarBytes(fileFoto));
			daoPe.inserir(pessoa);			
		} else {
			daoPe.alterar(pessoa);			
		}
		return "redirect:listar_coordenadores";
	}

	@RequestMapping("/admin/listar_coordenadores")
	public String listarCoord (Model modelo){
		PessoaDao peDao = (PessoaDao) daoPe;
		List<Coordenador> listaCoord = peDao.listarCoordenadores();
		Gson gson = new Gson();
		modelo.addAttribute("listaCoord", gson.toJson(listaCoord));
		return "/pessoa/coordenador/listaCoordenadores";
	}
	
	@RequestMapping ("/admin/alterar_coordenador")
	public String alterarCoord (Long id, Model modelo){		
		Coordenador coord = (Coordenador) daoPe.buscar(id);
		modelo.addAttribute("coord", coord);
		return "forward:form_avaliador";
	}
	
	
	
	
	
	
	@RequestMapping("/admin/excluir_pessoa")
	public void excluir(Long id, HttpServletResponse response){	
		daoPe.excluir(id);
		response.setStatus(200);
	}
	
	@RequestMapping("/logar")
	public String logar(String login, String senha, HttpSession session) {
		PessoaDao peDao = (PessoaDao) daoPe;
		Pessoa pessoa = peDao.logar(login, senha);
		
		System.out.println(pessoa);
		if (pessoa != null) {
			if (pessoa instanceof Aluno) {
				session.setAttribute("alunoLogado", pessoa);
				return "index";
			} else if (pessoa instanceof Coordenador) {
				session.setAttribute("coordenadorLogado", pessoa);
				return "index";
			} else if(pessoa instanceof Avaliador){
				session.setAttribute("avaliadorLogado", pessoa);
				return "index";
			}else{
				session.setAttribute ("adminLogado", pessoa);
				return "index";
			}
		} else {
			return "errologar";

		}

	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "index";
	}

	
}
