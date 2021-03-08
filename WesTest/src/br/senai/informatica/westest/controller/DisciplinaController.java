package br.senai.informatica.westest.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

import br.senai.informatica.westest.dao.InterfaceDao;
import br.senai.informatica.westest.dao.PessoaDao;
import br.senai.informatica.westest.modelo.Disciplina;
import br.senai.informatica.westest.modelo.Pessoa;

@Transactional
@Controller
public class DisciplinaController {
	
	@Autowired
	@Qualifier("disciplinaDao")	
	InterfaceDao<Disciplina> daoDis;
	@Autowired
	@Qualifier("pessoaDao")
	InterfaceDao<Pessoa> daoPe;
	
	@RequestMapping("/admin/form_disciplina")
	public String form(Model modelo) {
		PessoaDao peDao = (PessoaDao) daoPe;
		modelo.addAttribute("coords", peDao.listarCoordenadores());
		return "/disciplina/form";
	}
	
	@RequestMapping("/admin/salvar_disciplina")
	public String salvar(Disciplina disci) {
		if (disci.getIdDisciplina() == null) {
			daoDis.inserir(disci);			
		} else {
			daoDis.alterar(disci);			
		}
		return "redirect:listar_disciplinas";
		
	}
	
	@RequestMapping("/admin/listar_disciplinas")
	public String listar (Model modelo){
		List<Disciplina> listaD = daoDis.listar();
		Gson gson = new Gson();
		modelo.addAttribute("listaD", gson.toJson(listaD));
		return "/disciplina/listaDisciplina";
	}
	
	@RequestMapping("/admin/excluir_disciplina")
	public void excluir(Long id, HttpServletResponse response){
		
		daoDis.excluir(id);
		response.setStatus(200);
	}
	
	@RequestMapping ("/admin/alterar_disciplina")
	public String alterar (Long id, Model modelo){
		
		Disciplina disci = daoDis.buscar(id);
		modelo.addAttribute("disci", disci);
		return "forward:form_disciplina";
	}
	

}
