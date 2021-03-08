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
import br.senai.informatica.westest.modelo.Turma;


@Transactional
@Controller
public class TurmaController {
	
	@Autowired
	@Qualifier("turmaDao")	
	InterfaceDao<Turma> daoTur;
	
	@RequestMapping("/admin/form_turma")
	public String form() {
		return "/turma/form";
	}
	
	
	@RequestMapping("/admin/salvar_turma")
	public String salvar(Turma turma) {
		if (turma.getIdTurma() == null) {
			daoTur.inserir(turma);			
		} else {
			daoTur.alterar(turma);			
		}
		return "redirect:listar_turmas";
	}
	
	
	@RequestMapping("/admin/listar_turmas")
	public String listar (Model modelo){
		List<Turma> listaT = daoTur.listar();
		Gson gson = new Gson();
		modelo.addAttribute("listaT", gson.toJson(listaT));
		return "/turma/listaTurma";
	}
	
	@RequestMapping("/admin/excluir_turma")
	public void excluir(Long id, HttpServletResponse response){
		
		daoTur.excluir(id);
		response.setStatus(200);
	}
	
	@RequestMapping ("/admin/alterar_turma")
	public String alterar (Long id, Model modelo){
		
		Turma turma = daoTur.buscar(id);
		modelo.addAttribute("turma", turma);
		return "forward:form_turma";
	}

}
