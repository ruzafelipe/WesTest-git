<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/WEB-INF/paginas/template/cabecalho1.jsp" />

<div class="row">
	<section class="form col-sm-4 col-sm-offset-4">
		<div class="panel panel=default">
			<div class="panelheading">Cadastrando uma nova turma...</div>
			<div class="panel-body">
				<form action="salvar_turma" method="post">
					<div class="form-group">
						<label>ID:</label> <input type="text"
							class="form-control input-lg" readonly="readonly" name="idTurma"
							value="${turma.idTurma }">
					</div>


					<div class="form-group">
						<label>Nome da turma:</label> <input type="text" required="required"
							class="form-control input-lg" name="descricao" value="${turma.descricao}">
					</div>
					

					<button class="buttonSub input-lg col-sm-4 col-sm-offset-4" type="submit">Cadastrar</button>
				</form>
			</div>
		</div>
	</section>
</div>


<c:import url="/WEB-INF/paginas/template/rodape.jsp" />