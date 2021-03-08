<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/WEB-INF/paginas/template/cabecalho1.jsp" />


<script>
	$(function() {
		alteraDiv = function() {
			if ($('#tipoQuestao').val() == 'OBJETIVA') {
				$("#questaoObjetiva").show();
				$("#questaoDissertativa").hide();
				
				element1 = $("#alternativa1")[0].childNodes[3];
				element1.value = '';
				element1.setAttribute("required", "");
				element1.setAttribute("name", "descricao");
				
				element2 = $("#alternativa2")[0].childNodes[3];
				element2.value = '';
				element2.setAttribute("required", "");
				element2.setAttribute("name", "descricao");
				
				element3 = $("#alternativa3")[0].childNodes[3];
				element3.value = '';
				element3.setAttribute("required", "");
				element3.setAttribute("name", "descricao");
				
				elementText = $("#respostaDisser")[0].childNodes[3];
				elementText.value = '';
				elementText.removeAttribute("required");
				elementText.removeAttribute("name");
			}

			if ($('#tipoQuestao').val() == 'DISSERTATIVA') {
				$("#questaoObjetiva").hide();
				$("#questaoDissertativa").show();
				
				
				element1 = $("#alternativa1")[0].childNodes[3];
				element1.value = '';
				element1.removeAttribute("required");
				element1.removeAttribute("name");
				
				element2 = $("#alternativa2")[0].childNodes[3];
				element2.value = '';
				element2.removeAttribute("required");
				element2.removeAttribute("name");
				
				element3 = $("#alternativa3")[0].childNodes[3];
				element3.value = '';
				element3.removeAttribute("required");
				element3.removeAttribute("name");
				
				element4 = $("#alternativa4")[0].childNodes[3];
				element4.value = '';
				element4.removeAttribute("required");
				element4.removeAttribute("name");
				
				element5 = $("#alternativa5")[0].childNodes[3];
				element5.value = '';
				element5.removeAttribute("required");
				element5.removeAttribute("name");
				
				
				elementText = $("#respostaDisser")[0].childNodes[3];
				elementText.value = '';
				elementText.setAttribute("required", "");
				elementText.setAttribute("name", "descricao");
				
			}

		}

	});

	$(function() {
		alteraQtd = function() {
			if ($('#qtdAlternativas').val() == '3') {
				$("#alternativa4").hide();
				$("#alternativa5").hide();
				element4 = $("#alternativa4")[0].childNodes[3];
				element4.value = '';
				element4.removeAttribute("required");
				element4.removeAttribute("name");
				element5 = $("#alternativa5")[0].childNodes[3];
				element5.value = '';
				element5.removeAttribute("required");
				element5.removeAttribute("name");
			}

			if ($('#qtdAlternativas').val() == '4') {
				$("#alternativa4").show();
				$("#alternativa5").hide();
				element4 = $("#alternativa4")[0].childNodes[3];
				element4.setAttribute("required", "")
				element4.setAttribute("name", "descricao")
				element5 = $("#alternativa5")[0].childNodes[3];
				element5.value = '';
				element5.removeAttribute("required");
				element5.removeAttribute("name");

			}

			if ($('#qtdAlternativas').val() == '5') {
				$("#alternativa4").show();
				$("#alternativa5").show();
				element4 = $("#alternativa4")[0].childNodes[3];
				element4.setAttribute("required", "")
				element4.setAttribute("name", "descricao")
				element5 = $("#alternativa5")[0].childNodes[3];
				element5.setAttribute("required", "")
				element5.setAttribute("name", "descricao")
			
			}

		}

	});
</script>



<div class="row">
	<section class="form col-sm-8 col-sm-offset-2">
		<div class="panel panel=default">
			<header class="panelheading">Cadastrando uma nova questão...</header>
			<div class="panel-body">
				<form action="enviar_questao" method="post">


					<div class="form-group">
						<label>ID:</label> <input type="text"
							class="form-control input-lg" readonly="readonly"
							name="idQuestao" >
					</div>

					<div class="form-group">
						<label>Tipo de Questão:</label> <select id="tipoQuestao"
							class="form-control input-lg" name="tipoQuestao"
							onchange="alteraDiv()">
							<c:forEach items="${tipoQuestao }" var="tipo">
								<option value="${tipo }"
									<c:if test="${questao.tipoQuestao.equals(tipo) }">selected</c:if>>
									${tipo.toString()}</option>
							</c:forEach>
						</select>
					</div>

					<div class="form-group">
						<label>Enunciado:</label>
						<textarea class="form-control input-lg" rows="4" name="enunciado" cols="50"
							required="required" value="${questao.enunciado}"></textarea>

					</div>

					<div class="form-group">
						<label>Dificuldade:</label> <select class="form-control input-lg"
							name="dificuldadeQuestao">
							<c:forEach items="${dificuldade }" var="dificuldade">
								<option value="${dificuldade }"
									<c:if test="${questao.dificuldadeQuestao.equals(dificuldade) }">selected</c:if>>
									${dificuldade.toString()}</option>
							</c:forEach>
						</select>
					</div>

					<div id="questaoObjetiva">
						<div class="form-group">
							<label>Quantidade de alternativas:</label> <select
								class="selectpicker" id="qtdAlternativas" onchange="alteraQtd()">
								<option value="3" selected="selected">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
							</select>
						</div>

						<h2>Alternativas</h2>
						<h4>Digite a alternativa correta e as incorretas de acordo com a ordem</h4>
						<br>

						<div id="alternativa1" class="" data-toggle="buttons">
							<button class="btn btn-lg btn-success">Correta</button>
							<input type="text" required="required"
								style="margin-left: 115px; margin-top: -45px; width: 575px"
								class="form-control input-lg" name="descricao"><br />
						</div>
						<br />
						<div id="alternativa2" class="" data-toggle="buttons">
							<button class="btn btn-lg btn-danger">Incorreta</button>
							<input type="text" required="required"
								style="margin-left: 115px; margin-top: -45px; width: 575px"
								class="form-control input-lg" name="descricao"><br />
						</div>
						<br />

						<div id="alternativa3" class="" data-toggle="buttons">
							<button class="btn btn-lg btn-danger">Incorreta</button>
							<input type="text" required="required"
								style="margin-left: 115px; margin-top: -45px; width: 575px"
								class="form-control input-lg" name="descricao"><br />
						</div>
						<br />

						<div id="alternativa4" class="" data-toggle="buttons"
							style="display: none">
							<button class="btn btn-lg btn-danger">Incorreta</button>
							<input type="text"
								style="margin-left: 115px; margin-top: -45px; width: 575px"
								class="form-control input-lg" ><br />
						</div>
						<br />

						<div id="alternativa5" class="" data-toggle="buttons"
							style="display: none">
							<button class="btn btn-lg btn-danger">Incorreta</button>
							<input type="text"
								style="margin-left: 115px; margin-top: -45px; width: 575px"
								class="form-control input-lg" ><br />
						</div>
						<br />



					</div>
					<br>

					<div id="questaoDissertativa" style="display: none">

						<div id="respostaDisser" class="form-group">
							<label>Resposta esperada:</label>
							<textarea class="form-control input-lg" cols="50" rows="4" name="descricao"></textarea>
						</div>
</div>
					<button class="buttonSub input-lg col-sm-4 col-sm-offset-4"
						type="submit">Cadastrar</button>
				</form>
			</div>
		</div>
	</section>
</div>

<c:import url="/WEB-INF/paginas/template/rodape.jsp" />