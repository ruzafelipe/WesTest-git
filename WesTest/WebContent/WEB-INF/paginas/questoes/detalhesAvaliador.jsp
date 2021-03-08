<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/WEB-INF/paginas/template/cabecalho1.jsp" />

<div class="row">
	<section class="form col-sm-8 col-sm-offset-2">
		<div class="panel panel=default">
			<header class="panelheading">Detalhes da questão...</header>
			<div class="panel-body">
				<form action="validar_questao" method="post">


					<div class="form-group">
						<label>ID:</label> <input type="text"
							class="form-control input-lg" readonly="readonly"
							name="idQuestao" value="${questao.idQuestao }">
					</div>

					<div class="form-group">
						<label>Tipo de Questão:</label> <select id="tipoQuestao"
							class="form-control input-lg" name="tipoQuestao"
							disabled="disabled">
							<c:forEach items="${tipoQuestao }" var="tipo">
								<option value="${tipo }"
									<c:if test="${questao.tipoQuestao.equals(tipo) }">selected</c:if>>
									${tipo.toString()}</option>
							</c:forEach>
						</select>
					</div>

					<div class="form-group">
						<label>Enunciado:</label>
						<textarea class="form-control input-lg" rows="4" name="enunciado"
							cols="50" readonly="readonly">${questao.enunciado}</textarea>

					</div>

					<div class="form-group">
						<label>Dificuldade:</label> <select class="form-control input-lg"
							name="dificuldadeQuestao" disabled="disabled">
							<c:forEach items="${dificuldade }" var="dificuldade">
								<option value="${dificuldade }"
									<c:if test="${questao.dificuldadeQuestao.equals(dificuldade) }">selected</c:if>>
									${dificuldade.toString()}</option>
							</c:forEach>
						</select>
					</div>

					<c:if test="${questao.tipoQuestao == 'OBJETIVA' }">
						<h1>Alternativas</h1>
						<c:forEach var="resposta" items="${respostas }">

							<c:if test="${resposta.resposta == true }">
								<div id="alternativa1" class="" data-toggle="buttons">
									<button class="btn btn-lg btn-success">Correta</button>
									<input type="text" required="required" readonly="readonly"
										value="${resposta.descricao } "
										style="margin-left: 115px; margin-top: -45px; width: 575px"
										class="form-control input-lg" name="descricao"><br>
								</div>
							</c:if>

							<c:if test="${resposta.resposta == false }">
								<div id="alternativa1" class="" data-toggle="buttons">
									<button class="btn btn-lg btn-danger">Incorreta</button>
									<input type="text" required="required" readonly="readonly"
										value="${resposta.descricao } "
										style="margin-left: 115px; margin-top: -45px; width: 575px"
										class="form-control input-lg" name="descricao"><br>
								</div>
							</c:if>



						</c:forEach>
					</c:if>
					<c:if test="${questao.tipoQuestao == 'DISSERTATIVA' }">
					<h1>Resposta esperada</h1>
						<div class="form-group">
							<textarea class="form-control input-lg" cols="50" rows="4"
								name="descricao" readonly="readonly">${resposta.descricao}</textarea>
						</div>
					</c:if>
					</br>
					<c:if test="${questao.statusQuestao != 'ENCAMINHADA' }">
					
					<div class="form-group">
					<label>Comentário</label>
					<textarea class="form-control input-lg" cols="50" rows="4" readonly="readonly"
						id="comentario" name="comentario">${questao.comentario }</textarea>
					</div>
					</br>
					</c:if>
					

					<div class="row" align="center">

						<a href="<c:url value="/avaliador/listar_questoes"/>"
							class="btn btn-lg btn-info">Voltar</a>

					</div>
				</form>
			</div>
		</div>
	</section>
</div>