<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/WEB-INF/paginas/template/cabecalho1.jsp" />



<div class="row">
	<section class="form col-sm-6 col-sm-offset-3">
		<div class="panel panel=default">
			<div class="panelheading">Cadastrando uma nova disciplina...</div>
			<div class="panel-body">
				<form action="salvar_disciplina" method="post">


					<div class="form-group">
						<label>ID:</label> <input type="text"
							class="form-control input-lg" readonly="readonly"
							name="idDisciplina" value="${disci.idDisciplina }">
					</div>


					<div class="form-group">
						<label>Nome:</label> <input type="text" required="required"
							class="form-control input-lg" name="descricao"
							value="${disci.descricao}">
					</div>

					<div class="form-group">
						<label>Coordenador:</label> <select class="form-control input-lg"
							name="idCoordenador.idPessoa">
							<c:forEach items="${coords }" var="pessoa">
								<option value="${pessoa.idPessoa}">${pessoa.nome}</option>
							</c:forEach>
						</select>
					</div>


					<button class="buttonSub input-lg col-sm-4 col-sm-offset-4" type="submit">Cadastrar</button>


				</form>
					
			</div>
		</div>
	</section>
</div>



	<c:import url="/WEB-INF/paginas/template/rodape.jsp" />