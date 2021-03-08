package br.senai.informatica.westest.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import br.senai.informatica.westest.dao.InterfaceDao;
import br.senai.informatica.westest.dao.QuestaoDao;
import br.senai.informatica.westest.dao.RespostaDao;
import br.senai.informatica.westest.enums.DificuldadeQuestao;
import br.senai.informatica.westest.enums.StatusQuestao;
import br.senai.informatica.westest.enums.TipoQuestao;
import br.senai.informatica.westest.modelo.Avaliador;
import br.senai.informatica.westest.modelo.Coordenador;
import br.senai.informatica.westest.modelo.Disciplina;
import br.senai.informatica.westest.modelo.Questao;
import br.senai.informatica.westest.modelo.Resposta;

@Transactional
@Controller
public class QuestaoController {

	@Autowired
	@Qualifier("questaoDao")
	InterfaceDao<Questao> daoQues;
	@Autowired
	@Qualifier("disciplinaDao")
	InterfaceDao<Disciplina> daoDisc;
	@Autowired
	@Qualifier("respostaDao")
	InterfaceDao<Resposta> daoResp;

	@RequestMapping("/avaliador/form_questao")
	public String formAluno(Model modelo) {
		modelo.addAttribute("disciplinas", daoDisc.listar());
		modelo.addAttribute("tipoQuestao", TipoQuestao.values());
		modelo.addAttribute("dificuldade", DificuldadeQuestao.values());
		return "/questoes/form";
	}

	@RequestMapping("/avaliador/enviar_questao")
	public String salvarQuestao(Questao questao, String[] descricao, HttpSession session) {
		if (questao.getIdQuestao() == null) {
			Avaliador avali = (Avaliador) session.getAttribute("avaliadorLogado");
			questao.setIdAvaliador(avali.getIdPessoa());
			questao.setIdDisciplina(avali.getDisciplina().getIdDisciplina());
			questao.setIdCoordenador(avali.getDisciplina().getIdCoordenador().getIdPessoa());
			questao.setStatusQuestao(StatusQuestao.ENCAMINHADA);
			daoQues.inserir(questao);

			Resposta resposta = new Resposta();
			// mandar pra baixo depois da inserção da questão, sear a primeira
			// como correta, e verificar com .equals as vazias
			for (int i = 0; i < descricao.length; i++) {
				if (!descricao[i].equals("")) {
					descricao[i].toString();
					resposta = new Resposta();
					resposta.setIdQuestao(questao);
					resposta.setDescricao(descricao[i]);
					if (descricao[i] == descricao[0]) {
						resposta.setResposta(true);
					}
				}
				daoResp.inserir(resposta);
			}

		} else {
			daoQues.alterar(questao);
		}
		return "redirect:listar_questoes";
	}

	@RequestMapping("/avaliador/listar_questoes")
	public String listarAvaliador(Model modelo, HttpSession session) {
		QuestaoDao quesDao = (QuestaoDao) daoQues;
		Avaliador avali = (Avaliador) session.getAttribute("avaliadorLogado");
		List<Questao> listaQuestoes = quesDao.listarMinhasQuestoes(avali.getIdPessoa());
		Gson gson = new Gson();
		modelo.addAttribute("listaQuestoes", gson.toJson(listaQuestoes));
		return "/questoes/listaQuestoesAvaliador";
	}
	
	@RequestMapping("/avaliador/detalhes_questao")
	public String detalhesQuestaoAva(Model modelo, Long id) {
		modelo.addAttribute("disciplinas", daoDisc.listar());
		modelo.addAttribute("tipoQuestao", TipoQuestao.values());
		modelo.addAttribute("dificuldade", DificuldadeQuestao.values());
		Questao questao = daoQues.buscar(id);		
		modelo.addAttribute("questao", questao);
		RespostaDao resDao = (RespostaDao) daoResp;
		if (questao.getTipoQuestao() == TipoQuestao.OBJETIVA) {			
			List<Resposta> respostas = resDao.listarPorQuestao(id);
			modelo.addAttribute("respostas", respostas);			
		} else {
			Resposta resposta = resDao.buscarPorQuestao(id);
			modelo.addAttribute("resposta", resposta);

		}
		return "/questoes/detalhesAvaliador";
	}
	

	@RequestMapping("/coordenador/listar_questoes")
	public String listarPendentes(Model modelo, HttpSession session) {
		QuestaoDao quesDao = (QuestaoDao) daoQues;
		Coordenador coord = (Coordenador) session.getAttribute("coordenadorLogado");
		List<Questao> listaQuestoes = quesDao.listarQuestoesPendentes(coord.getIdPessoa());
		Gson gson = new Gson();
		modelo.addAttribute("listaQuestoesAprovar", gson.toJson(listaQuestoes));
		return "/questoes/listaQuestoesAprovar";
	}

	@RequestMapping("/coordenador/detalhes_questao")
	public String detalhesQuestao(Model modelo, Long id) {
		modelo.addAttribute("disciplinas", daoDisc.listar());
		modelo.addAttribute("tipoQuestao", TipoQuestao.values());
		modelo.addAttribute("dificuldade", DificuldadeQuestao.values());
		Questao questao = daoQues.buscar(id);		
		modelo.addAttribute("questao", questao);
		RespostaDao resDao = (RespostaDao) daoResp;
		if (questao.getTipoQuestao() == TipoQuestao.OBJETIVA) {			
			List<Resposta> respostas = resDao.listarPorQuestao(id);
			modelo.addAttribute("respostas", respostas);			
		} else {
			Resposta resposta = resDao.buscarPorQuestao(id);
			modelo.addAttribute("resposta", resposta);

		}
		return "/questoes/form_aprovar";
	}
	
	@RequestMapping("/coordenador/validar_questao")
	public String validarQuestao(Long code, String comentario, Long id) {
		Questao questao = daoQues.buscar(id);
		questao.setComentario(comentario);
		if(code == 0){		
			questao.setStatusQuestao(StatusQuestao.REJEITADA);			
		}else{
			questao.setStatusQuestao(StatusQuestao.APROVADA);
		}
		daoQues.alterar(questao);
		return "redirect:listar_questoes";
	}
	
	
	@RequestMapping(value = "/avaliador/listarQuestoesAprovadas", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<Questao> listaDeQuestoesAva(HttpSession session, String tipoQuestao, 
			String nivel_dificuldade) {
		
		QuestaoDao quesDao = (QuestaoDao) daoQues;
		Avaliador avali = (Avaliador) session.getAttribute("avaliadorLogado");
		
		TipoQuestao tipoDaQuestao = null;

		if (tipoQuestao.equalsIgnoreCase("objetiva")) {
			tipoDaQuestao = TipoQuestao.OBJETIVA;
		} else {
			tipoDaQuestao = TipoQuestao.DISSERTATIVA;
		}
		
		DificuldadeQuestao dificuldadeQuestao = null;
		
		if(nivel_dificuldade.equalsIgnoreCase("alta")){
			dificuldadeQuestao = DificuldadeQuestao.ALTA;
		}else if(nivel_dificuldade.equalsIgnoreCase("media")){
			dificuldadeQuestao = DificuldadeQuestao.MEDIA;
		}else{
			dificuldadeQuestao = DificuldadeQuestao.BAIXA;
		}	
		
		List<Questao> listaQuestoes = quesDao.listarQuestoesAprovadasAvaliador(avali.getDisciplina().getIdDisciplina(), 
				dificuldadeQuestao, tipoDaQuestao);
		
		return listaQuestoes;
		
	}
	
	@RequestMapping(value = "/avaliador/gerarProvaAutomatica", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseEntity<List<Questao>> gerarProvaAutoAva(HttpSession session, String tipoQuestao, String nQuestoes ) {		
		List<Questao> listaQuestoes;
		QuestaoDao quesDao = (QuestaoDao) daoQues;
		
		
		Avaliador avali = (Avaliador) session.getAttribute("avaliadorLogado");		
		
		
		if (tipoQuestao.equalsIgnoreCase("mista")) {
			listaQuestoes = quesDao.gerarProvaAutoAva(nQuestoes, avali.getDisciplina().getIdDisciplina() );
		} else if (tipoQuestao.equalsIgnoreCase("objetiva")) {
			listaQuestoes = quesDao.gerarProvaAutoAva(TipoQuestao.OBJETIVA, nQuestoes, avali.getDisciplina().getIdDisciplina());
		} else {
			listaQuestoes = quesDao.gerarProvaAutoAva(TipoQuestao.DISSERTATIVA, nQuestoes, avali.getDisciplina().getIdDisciplina());
		}

		if (listaQuestoes != null) {
			return ResponseEntity.ok().body(listaQuestoes);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/coordenador/listarQuestoesAprovadas", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<Questao> listaDeQuestoesCoord(HttpSession session, String tipoQuestao, 
			String nivel_dificuldade) {
		
		QuestaoDao quesDao = (QuestaoDao) daoQues;
		Coordenador coord = (Coordenador) session.getAttribute("coordenadorLogado");
		
		TipoQuestao tipoDaQuestao = null;

		if (tipoQuestao.equalsIgnoreCase("objetiva")) {
			tipoDaQuestao = TipoQuestao.OBJETIVA;
		} else {
			tipoDaQuestao = TipoQuestao.DISSERTATIVA;
		}
		
		DificuldadeQuestao dificuldadeQuestao = null;
		
		if(nivel_dificuldade.equalsIgnoreCase("alta")){
			dificuldadeQuestao = DificuldadeQuestao.ALTA;
		}else if(nivel_dificuldade.equalsIgnoreCase("media")){
			dificuldadeQuestao = DificuldadeQuestao.MEDIA;
		}else{
			dificuldadeQuestao = DificuldadeQuestao.BAIXA;
		}	
		
		List<Questao> listaQuestoes = quesDao.listarQuestoesAprovadasCoordenador(coord.getIdPessoa(), 
				dificuldadeQuestao, tipoDaQuestao);
		
		return listaQuestoes;
		
	}
	
	@RequestMapping(value = "/coordenador/gerarProvaAutomatica", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseEntity<List<Questao>> gerarProvaAutoCoord(HttpSession session, String tipoQuestao, String nQuestoes ) {		
		List<Questao> listaQuestoes;
		QuestaoDao quesDao = (QuestaoDao) daoQues;
		
		
		Coordenador coord = (Coordenador) session.getAttribute("coordenadorLogado");		
		
		
		if (tipoQuestao.equalsIgnoreCase("mista")) {
			listaQuestoes = quesDao.gerarProvaAutoCoord(nQuestoes, coord.getIdPessoa() );
		} else if (tipoQuestao.equalsIgnoreCase("objetiva")) {
			listaQuestoes = quesDao.gerarProvaAutoCoord(TipoQuestao.OBJETIVA, nQuestoes, coord.getIdPessoa());
		} else {
			listaQuestoes = quesDao.gerarProvaAutoCoord(TipoQuestao.DISSERTATIVA, nQuestoes, coord.getIdPessoa());
		}

		if (listaQuestoes != null) {
			return ResponseEntity.ok().body(listaQuestoes);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	
	

}
