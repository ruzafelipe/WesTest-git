package br.senai.informatica.westest.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.senai.informatica.westest.dao.DisciplinaDao;
import br.senai.informatica.westest.dao.InterfaceDao;
import br.senai.informatica.westest.dao.ProvaDao;
import br.senai.informatica.westest.enums.DificuldadeQuestao;
import br.senai.informatica.westest.enums.StatusProva;
import br.senai.informatica.westest.modelo.Avaliador;
import br.senai.informatica.westest.modelo.Coordenador;
import br.senai.informatica.westest.modelo.Disciplina;
import br.senai.informatica.westest.modelo.Prova;
import br.senai.informatica.westest.modelo.Questao;
import br.senai.informatica.westest.modelo.QuestaoProva;
import br.senai.informatica.westest.modelo.ResultadoProva;
import br.senai.informatica.westest.modelo.Turma;
import br.senai.informatica.westest.modelo.TurmaProva;
import br.senai.informatica.westest.util.ConverterData;

@Transactional
@Controller
public class ProvaController {

	@Autowired
	@Qualifier("provaDao")
	InterfaceDao<Prova> daoProva;
	@Autowired
	@Qualifier("questaoDao")
	InterfaceDao<Questao> daoQuestao;
	@Autowired
	@Qualifier("questaoProvaDao")
	InterfaceDao<QuestaoProva> daoQuesPrv;
	@Autowired
	@Qualifier("disciplinaDao")
	InterfaceDao<Disciplina> daoDisci;
	@Autowired
	@Qualifier("turmaDao")
	InterfaceDao<Turma> daoTurma;
	@Autowired
	@Qualifier("turmaProvaDao")
	InterfaceDao<TurmaProva> daoTurmaProva;
	@Autowired
	@Qualifier("resultadoProvaDao")
	InterfaceDao<ResultadoProva> daoResultProva;

	private Calendar data;
	private QuestaoProva questaoProva;
	private ConverterData converterData;

	@RequestMapping("/avaliador/form_prova")
	public String formProvaAva(Model modelo) {
		modelo.addAttribute("dificuldade", DificuldadeQuestao.values());
		return "/prova/form";
	}
	
	@RequestMapping("/coordenador/form_prova")
	public String formProvaCoord(Model modelo) {
		modelo.addAttribute("dificuldade", DificuldadeQuestao.values());
		return "/prova/form";
	}

	@RequestMapping("/avaliador/enviar_prova")
	public String arquivarProvaAva(Prova prova, int[] questoes, HttpSession session, String titulo) {		
		prova = new Prova();	
		prova.setTitulo(titulo);
		prova.setDtCriacao(data.getInstance());
		prova.setStatusProva(StatusProva.CRIADA);
		List<QuestaoProva> questoesProva = new ArrayList<>();

		for (int i = 0; i < questoes.length; i++) {
			questaoProva = new QuestaoProva();
			questaoProva.setQuestao(daoQuestao.buscar(questoes[i]));
			questoesProva.add(questaoProva);
			daoQuesPrv.inserir(questaoProva);
		}
		Avaliador avali = (Avaliador) session.getAttribute("avaliadorLogado");
		prova.setAvaliador(avali);

		List<Disciplina> disciplinas = new ArrayList<>();
		disciplinas.add(avali.getDisciplina());

		prova.setQuestoesProva(questoesProva);
		prova.setDisciplina(disciplinas);
		daoProva.inserir(prova);
		return "redirect:listar_provas";
	}

	@RequestMapping("/avaliador/listar_provas")
	public String listarProvasAvaliador(Model modelo, HttpSession session) throws JsonProcessingException {
		ProvaDao provaDao = (ProvaDao) daoProva;
		ObjectMapper mapper = new ObjectMapper();
		List<Prova> listaProva;
		Avaliador avali = (Avaliador) session.getAttribute("avaliadorLogado");
		listaProva = provaDao.listarProvasAvaliador(avali.getIdPessoa());
		modelo.addAttribute("listaProva", mapper.writeValueAsString(listaProva));
		System.out.println(listaProva);
		return "/prova/listaProva";
	}
	
	@RequestMapping("/avaliador/listar_provasFin")
	public String listarProvasFinAvaliador(Model modelo, HttpSession session) throws JsonProcessingException {
		ProvaDao provaDao = (ProvaDao) daoProva;
		ObjectMapper mapper = new ObjectMapper();
		List<Prova> listaProva;
		Avaliador avali = (Avaliador) session.getAttribute("avaliadorLogado");
		listaProva = provaDao.listarProvasFinAvaliador(avali.getIdPessoa());
		modelo.addAttribute("listaProva", mapper.writeValueAsString(listaProva));
		System.out.println(listaProva);
		return "/prova/listaProvaFin";
	}
	
	
	
	@RequestMapping("/coordenador/enviar_prova")
	public String arquivarProvaCoord(Prova prova, int[] questoes, HttpSession session) {
		prova.setDtCriacao(data.getInstance());
		prova.setStatusProva(StatusProva.CRIADA);
		List<QuestaoProva> questoesProva = new ArrayList<>();

		for (int i = 0; i < questoes.length; i++) {
			questaoProva = new QuestaoProva();
			questaoProva.setQuestao(daoQuestao.buscar(questoes[i]));
			questoesProva.add(questaoProva);
			daoQuesPrv.inserir(questaoProva);
		}
		Coordenador coord = (Coordenador) session.getAttribute("coordenadorLogado");
		prova.setCoordenador(coord);
		
		DisciplinaDao discDao = (DisciplinaDao) daoDisci;

		List<Disciplina> disciplinas = discDao.listarPorCoord(coord.getIdPessoa());
	

		prova.setQuestoesProva(questoesProva);
		prova.setDisciplina(disciplinas);
		daoProva.inserir(prova);
		return "redirect:listar_provas";
	}
	
	
	@RequestMapping("/coordenador/listar_provas")
	public String listarProvasCoordenador(Model modelo, HttpSession session) throws JsonProcessingException {
		ProvaDao provaDao = (ProvaDao) daoProva;
		ObjectMapper mapper = new ObjectMapper();
		List<Prova> listaProva;
		Coordenador coord = (Coordenador) session.getAttribute("coordenadorLogado");
		listaProva = provaDao.listarProvasCoordenador(coord.getIdPessoa());
		modelo.addAttribute("listaProva", mapper.writeValueAsString(listaProva));
		System.out.println(listaProva);
		return "/prova/listaProva";
	}
	
	@RequestMapping("/coordenador/listar_provasFin")
	public String listarProvasFinCoordenador(Model modelo, HttpSession session) throws JsonProcessingException {
		ProvaDao provaDao = (ProvaDao) daoProva;
		ObjectMapper mapper = new ObjectMapper();
		List<Prova> listaProva;
		Coordenador coord = (Coordenador) session.getAttribute("coordenadorLogado");
		listaProva = provaDao.listarProvasFinCoordenador(coord.getIdPessoa());
		modelo.addAttribute("listaProva", mapper.writeValueAsString(listaProva));
		System.out.println(listaProva);
		return "/prova/listaProvaFin";
	}
	
	@RequestMapping("/avaliador/agendamento_prova")
	public String agendamentoProva(Model modelo, HttpSession session, Long id) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		Prova prova = daoProva.buscar(id);
		List<QuestaoProva> questoesProva = prova.getQuestoesProva();
		List<Turma> turmas = daoTurma.listar();
		modelo.addAttribute("questoesProva", mapper.writeValueAsString(questoesProva));
		modelo.addAttribute("turmas", mapper.writeValueAsString(turmas));
		modelo.addAttribute("prova", prova);
		return "/prova/agendarProva";
	}
	
	@RequestMapping("/avaliador/agendar_prova")
	public String agendarProva(long idProva, int[] turmas, String dataProva, String tempoProva, String[] tempoQuestao, Long[] notaQuestao) throws ParseException  {
		System.out.println(dataProva);
		
		double valor = 0;
		long tempo = 0;
		Prova prova = daoProva.buscar(idProva);
		List<QuestaoProva> questoesProva = prova.getQuestoesProva();
		for (QuestaoProva questaoProva : questoesProva) {
			questaoProva.setValorAtribuido(notaQuestao[questoesProva.lastIndexOf(questaoProva)]);
			valor += notaQuestao[questoesProva.lastIndexOf(questaoProva)];
			if(tempoProva == null){
				long t = converterData.converterHora(tempoQuestao[questoesProva.lastIndexOf(questaoProva)]);
				tempo += t;
				questaoProva.setTempoAtribuido(t);
				questaoProva.setTempoAtribuidoString(tempoQuestao[questoesProva.lastIndexOf(questaoProva)]);
			}else{
				prova.setTempoProvaString(null);
				prova.setTempoProvaString(tempoProva);				
				questaoProva.setTempoAtribuido(null);
				questaoProva.setTempoAtribuidoString(null);
				
			}
		}
		if(tempoProva == null){
			prova.setDtFim(converterData.somarDataHora(converterData.converterDataEHora(dataProva), tempo));
		}else{
			prova.setDtFim(converterData.somarDataHora(converterData.converterDataEHora(dataProva), converterData.converterHora(tempoProva)));
		}
		
		for (int i = 0; i < turmas.length; i++) {
			TurmaProva tProva = new TurmaProva();
			tProva.setTurma(daoTurma.buscar(turmas[i]));
			tProva.setProva(prova);
			daoTurmaProva.inserir(tProva);
		}
		 Random r = new Random();
		 char[] goodChar = { 'a', 'b', 'c', 'd', 'e', 'f', 'g',
			         'h','i', 'j', 'k','l', 'm', 'n','o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'x','w',
			         'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I','J', 'K','L',
			         'M', 'N','O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z','1',
			         '2', '3', '4', '5', '6', '7', '8', '9','ç',};
			    StringBuffer sb = new StringBuffer();
			    for (int i = 0; i < 7; i++) {
			      sb.append(goodChar[r.nextInt(goodChar.length)]);
			    }
			    prova.setCodVerificacao(sb.toString());
		
		
		ResultadoProva resultadoProva = new ResultadoProva();
		resultadoProva.setValor(valor);
		resultadoProva.setProva(prova);
		daoResultProva.inserir(resultadoProva);
		prova.setDtAplicacao(converterData.converterDataEHora(dataProva));
		prova.setStatusProva(StatusProva.APLICADA);
		daoProva.alterar(prova);		
		return "redirect:listar_provas";
		
		
		
		
	}
	

	@RequestMapping("/coordenador/agendamento_prova")
	public String agendamentoProvaC(Model modelo, HttpSession session, Long id) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		Prova prova = daoProva.buscar(id);
		List<QuestaoProva> questoesProva = prova.getQuestoesProva();
		List<Turma> turmas = daoTurma.listar();
		modelo.addAttribute("questoesProva", mapper.writeValueAsString(questoesProva));
		modelo.addAttribute("turmas", mapper.writeValueAsString(turmas));
		modelo.addAttribute("prova", prova);
		return "/prova/agendarProva";
	}
	
	@RequestMapping("/coordenador/agendar_prova")
	public String agendarProvaC(long idProva, int[] turmas, String dataProva, String tempoProva, String[] tempoQuestao, double[] notaQuestao) throws ParseException  {
		
		
		double valor = 0;
		long tempo = 0;
		Prova prova = daoProva.buscar(idProva);
		List<QuestaoProva> questoesProva = prova.getQuestoesProva();
		for (QuestaoProva questaoProva : questoesProva) {
			questaoProva.setValorAtribuido(notaQuestao[questoesProva.lastIndexOf(questaoProva)]);
			valor += notaQuestao[questoesProva.lastIndexOf(questaoProva)];
			if(tempoProva == null){
				long t = converterData.converterHora(tempoQuestao[questoesProva.lastIndexOf(questaoProva)]);
				tempo += t;
				questaoProva.setTempoAtribuido(t);
				questaoProva.setTempoAtribuidoString(tempoQuestao[questoesProva.lastIndexOf(questaoProva)]);
			}else{
				prova.setTempoProvaString(null);
				prova.setTempoProvaString(tempoProva);				
				questaoProva.setTempoAtribuido(null);
				questaoProva.setTempoAtribuidoString(null);
				
			}
		}

		//EMBARALHAR LISTA
		//Collections.shuffle(questoesProva);
		
		
		
		if(tempoProva == null){
			prova.setDtFim(converterData.somarDataHora(converterData.converterDataEHora(dataProva), tempo));
		}else{
			prova.setDtFim(converterData.somarDataHora(converterData.converterDataEHora(dataProva), converterData.converterHora(tempoProva)));
		}
		
		for (int i = 0; i < turmas.length; i++) {
			TurmaProva tProva = new TurmaProva();
			tProva.setTurma(daoTurma.buscar(turmas[i]));
			tProva.setProva(prova);
			daoTurmaProva.inserir(tProva);
		}
		
		Random r = new Random();
		 char[] goodChar = { 'a', 'b', 'c', 'd', 'e', 'f', 'g',
			         'h','i', 'j', 'k','l', 'm', 'n','o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'x','w',
			         'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I','J', 'K','L',
			         'M', 'N','O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z','1',
			         '2', '3', '4', '5', '6', '7', '8', '9','ç',};
			    StringBuffer sb = new StringBuffer();
			    for (int i = 0; i < 7; i++) {
			      sb.append(goodChar[r.nextInt(goodChar.length)]);
			    }
			    prova.setCodVerificacao(sb.toString());
		
		ResultadoProva resultadoProva = new ResultadoProva();
		resultadoProva.setValor(valor);
		resultadoProva.setProva(prova);
		daoResultProva.inserir(resultadoProva);
		prova.setDtAplicacao(converterData.converterDataEHora(dataProva));
		prova.setStatusProva(StatusProva.APLICADA);
		daoProva.alterar(prova);		
		return "redirect:listar_provas";
	}
}
