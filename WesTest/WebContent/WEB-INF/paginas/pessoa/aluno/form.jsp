<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/WEB-INF/paginas/template/cabecalho1.jsp" />

<link href='<c:url value="/resources/fileinput/css/fileinput.css"/>'
	rel="stylesheet" type="text/css">
<script src='<c:url value="/resources/fileinput/js/fileinput.min.js" />'
	type="text/javascript"></script>


<div class="row">
	<section class="form col-sm-6 col-sm-offset-3" >
		<div class="panel panel=default">
			<div class="panelheading">Cadastrando um novo aluno...</div>
			<div class="panel-body">
				<form action="salvar_aluno" method="post" enctype="multipart/form-data">
					<div class="form-group">
						<label>ID:</label> <input type="text"
							class="form-control input-lg" readonly="readonly" name="idPessoa"
							value="${pessoa.idPessoa }">
					</div>


					<div class="form-group">
						<label>Nome:</label> <input type="text" required="required"
							class="form-control input-lg" name="nome" value="${aluno.nome}">
					</div>

						<div class="form-group">
							<label>Login:</label> <input type="text" required="required"
								class="form-control input-lg" name="login"
								value="${aluno.login}">
						</div>

						<div class="form-group">
							<label>Senha:</label> <input type="password" required="true"
								class="form-control input-lg" name="senha"
								value="${aluno.senha }">
								</div>

							<div class="form-group">
								<label>Foto:</label> <input type="file"
									class="file file-loading" 
									data-show-upload="false"
									data-allowed-file-extensions='["jpg","png"]' 
									name="fileFoto">
							</div>					

							<div class="form-group">
								<label>Turma:</label> <select class="form-control input-lg"
									name="turma.idTurma">
									<c:forEach items="${turmas }" var="turma">
										<option value="${turma.idTurma}">${turma.toString()}</option>
									</c:forEach>
								</select>
							</div>
							<button class="buttonSub input-lg col-sm-4 col-sm-offset-4" type="submit">Cadastrar</button>
							</div>
							
							
							</form>
							</div>
							</div>
							</section>
							</div>

<c:import url="/WEB-INF/paginas/template/rodape.jsp" />