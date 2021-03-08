package br.senai.informatica.westest.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class UserInterceptor extends HandlerInterceptorAdapter {	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
	
		String uri = request.getRequestURI();
		if(uri.endsWith("logar") || uri.endsWith("semacesso")){
			return true;
		}else if(uri.contains("admin/") && request.getSession().getAttribute("adminLogado")== null){
			response.sendRedirect("../semacesso");
			return false;
		}else if(uri.contains("aluno/") && request.getSession().getAttribute("alunoLogado")== null){
			response.sendRedirect("../semacesso");
			return false;
		}else if(uri.contains("avaliador/") && request.getSession().getAttribute("avaliadorLogado")== null){
			response.sendRedirect("../semacesso");
			return false;
		}else if(uri.contains("coordenador/") && request.getSession().getAttribute("coordenadorLogado")== null){
			response.sendRedirect("../semacesso");
			return false;
		}
		return true;
	}


}
