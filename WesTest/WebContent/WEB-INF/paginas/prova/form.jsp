<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/WEB-INF/paginas/template/cabecalho1.jsp" />

<script>
	//buscar questões 
	var json;
	
	$(function() {
		//hang on event of form with id=myform
		$("#buscar").submit(function(e) {

			//prevent Default functionality
			e.preventDefault();

			//get the action-url of the form
			var actionurl = e.currentTarget.action;

			//realiza a request e abre o modal
			$.ajax({
				url : actionurl,
				type : "POST",
				data : $("#buscar").serialize(),
				success : function(data) {
					if (data[0] == null) {
						alerta("Nenhuma questão localizada", "danger");
					} else {
						json = data;				
						paginar();
						$("#resultBuscaQuestoes").modal('show');
					}
				}
			});
		});
		
		$(function() {
			//hang on event of form with id=myform
			$("#provaAutomatica").submit(
					function(e) {

						//prevent Default functionality
						e.preventDefault();

						//get the action-url of the form
						var actionurl = e.currentTarget.action;

						//realiza a request e abre o modal
						$.ajax({
							url : actionurl,
							type : "POST",
							data : $("#provaAutomatica").serialize(),
							success : function(data) {
								$("#selecionadasquestoes").empty();
								json = data;
								$(data).each(
										function(k, v) {
											console.log(v);
											
											$(
													"<option selected='selected' value='"+v.idQuestao+"' >"
															+ v.idQuestao + "</option>")
													.appendTo('#selecionadasquestoes');
										});
								paginar();
								$("#resultBuscaQuestoes").modal('show');

							},
							error : function(data) {
								alerta("Não foi possivel gerar a prova", "danger");
							}
						});
					});
		});
		
		
		$("#proximo").click(function(){
			if(pagAtual < tamanho/qtdPorPagina -1){
				pagAtual++;
				paginar();
				confBotoes();
			}
		});
		
		$("#anterior").click(function(){
			if(pagAtual > 0){
				pagAtual--;
				paginar();
				confBotoes();
			}
		});	
	});
		
		var qtdPorPagina = 10;
		var pagAtual = 0;		
		

		function paginar(){			
			var tamanho = Object.keys(json).length;			
			var questao;	
			var tbody = $('#questoesParaProva');
			tbody.empty();			
			for(var x = pagAtual * qtdPorPagina; x < tamanho && x < (pagAtual+1)*qtdPorPagina;x++){
				questao = json[x];
				
			var btn = '';	
				$("#selecionadasquestoes").each(function() {
					if ( $(this).val() == null) {	
						btn = "<button' class='btn btn-success btn-simple' id='"+questao.idQuestao+"' onclick='addQuestao(this)' >Adicionar</button>";
					}else {
						
						$.each($(this).val(),function(key, value) { 											
							if(value == questao.idQuestao){
							   btn = "<button' class='btn btn-danger' id='"+questao.idQuestao+"' onclick='retirarQuestao(this)' >Remover</button>";
								return false;
							}else{
								btn = "<button' class='btn btn-success btn-simple' id='"+questao.idQuestao+"' onclick='addQuestao(this)'>Adicionar</button>";										
							}
							});	
						
						
					}
					
									
					
				});										
				tbody.append($("<tr id='linha_"+questao.idQuestao+"'>")			
						.append($("<td>").append(btn))
						.append($("<td>").append(questao.enunciado)));			
				
				}			
			
			
			
			
			
			$("#numeracao").text("Pagina "+(pagAtual+1)+" de "+Math.ceil(tamanho/qtdPorPagina));
		}

		function confBotoes(){
			$("#proximo").prop("disabled", tamanho <= qtdPorPagina || pagAtual > tamanho/qtdPorPagina -1);
			$("#anterior").prop("disabled", tamanho <= qtdPorPagina || pagAtual == 0);
		}
		
		function alerta(texto, type) {
			$.notify(texto, {
				type : type,
				placement : {
					from : 'top',
					align : 'right'
				}
			});
		}
		
		
		function addQuestao(questao) {
			var questao = $(questao).attr("id");

			$('#' + questao)
					.replaceWith(
							"<button type='button' class='btn btn-danger btn-simple' id='"+questao+"' onclick='retirarQuestao(this)' >Remover</button>")

			$("#linha_" + questao).clone().appendTo("#questoesSelecionadas");
			
			$("<option selected='selected' value='"+questao+"' >" + questao
							+ "</option>").appendTo('#selecionadasquestoes');

	
		}
		
		function retirarQuestao(questao) {
			var questao = $(questao).attr("id");
			
			$('#' + questao).replaceWith(
					"<button' class='btn btn-success btn-simple' id='"+questao+"' onclick='addQuestao(this)' >Adicionar</button>");
			
			
			
			$("#selecionadasquestoes option[value='" + questao + "']").remove();
			$("#linha_" + questao).remove();
			
			paginar();
			
		}
		
		
		$(function() {
			$('#gerarProvaAutomatica').click(function() {
				if (this.checked) {
					$("#provaManual").hide();
					$("#provaAuto").show();
					$("#tableQuestoes").hide();
				}else{
					$("#provaManual").show();
					$("#provaAuto").hide();
					$("#tableQuestoes").show();
				}
				
			});
		});
		
				function enviarProva() {							
						$("#selecionadasquestoes").each(function() {
															if ($(this).val() == null) {
																alerta(
																		"Selecione ao menos uma questão para criar a prova !",
																		"danger");
															}else if($("#titulo").val() == "") {
																alerta(
																		"Você precisa inserir um titulo !",
																		"danger");
																
															}else {
																$("#enviarProva").submit();
															
														   	}
														});
				};
		

</script>


<div class="row">
	<section class="form col-sm-8 col-sm-offset-2">
		<div class="panel panel=default">
			<header class="panelheading">Cadastrando uma nova prova...</header>
			<div class="panel-body">
				<form action="enviar_prova" method="post" id="enviarProva">
					<div class="form-group">
						<label>Titulo:</label> <input type="text" id="titulo"
							class="form-control input-lg" name="titulo" required="required">
					</div>
					<select name="questoes" multiple="multiple"
						id="selecionadasquestoes" style="display: none;">
					</select>

				</form>
			</div>
		</div>
	</section>
</div>

<div class="col-md-10 ">
	<div class=" pull-right">
			<label class="btn btn-success active">
				<input type="checkbox" autocomplete="off" id="gerarProvaAutomatica">
				Prova Automatica
			</label>
	</div>
</div>

		

<div class="col-md-8 col-md-offset-2" style="margin-top: 10px" id="provaManual">
	<form action="listarQuestoesAprovadas" id="buscar" >
		<h1>Questões para prova</h1>
		<br />
		<div class="col-md-9">
			<div class="col-md-12">
				<div class="col-md-6 col-md-offset-1">
					<label> Tipo de Questão</label> <select name="tipoQuestao"
						id="tipoDaQuestao" class="form-control input-lg"
						style="margin-top: 0px;">
						<option value="objetiva">Objetiva</option>
						<option value="dissertativa">Dissertativa</option>
					</select> <span class="material-input"></span>
				</div>
				<div class="col-md-4 col-md-offset-1">
					<label> Nivel de Dificuldade </label> <select
						id="nivel_dificuldade" name="nivel_dificuldade"
						class="form-control input-lg">
						<c:forEach items="${dificuldade }" var="dificuldade">
							<option value="${dificuldade }">
								${dificuldade.toString()}</option>
						</c:forEach>
					</select>
				</div>			
			</div>
		</div>
		<div class="col-md-3" style="padding-top: 30px; padding-bottom: 15px;">
			<button type="submit" class="btn btn-lg btn-primary">Buscar</button>
		</div>
	</form>
</div>

<div class="col-md-8 col-md-offset-2" style="margin-top: 10px" id="provaAuto" hidden>
	<form action="gerarProvaAutomatica" id="provaAutomatica">
	<h1>Prova Automática</h1>	
	<br />
			<div class="col-md-9">
			<div class="col-md-12">
				<div class="col-md-6 col-md-offset-1">
					<label> Tipo de Questão</label> <select name="tipoQuestao"
						id="tipoQuestao" class="form-control input-lg"
						style="margin-top: 0px;">
						<option value="objetiva">Objetiva</option>
						<option value="dissertativa">Dissertativa</option>
						<option value="mista">Ambas</option>
					</select> <span class="material-input"></span>
				</div>
				<div class="col-md-4 col-md-offset-1">
					<label> Quantidade de questoes </label><input type="number" name="nQuestoes" id="nQuestoes"
					class="form-control input-lg" min="0" max="20" required="required">
				</div>
			</div>
		</div>
		<div class="col-md-3" style="padding-top: 30px; padding-bottom: 15px;">
			<button type="submit" class="btn btn-lg btn-primary">Gerar Prova</button>
		</div>
	</form>
</div>

<br />

<div class="col-md-8 col-md-offset-2" style="margin-top: 10px" id="tableQuestoes">

	<table class="table table-bordered" >


		<thead>
			<th style="width: 45px;">REMOVER</th>
			<th>ENUNCIADO</th>


		</thead>
		<tbody id="questoesSelecionadas">

		</tbody>

	</table>

</div>
<div class="row">
	<div class="form col-sm-4 col-sm-offset-6">

		<button class="buttonSub input-lg col-sm-4 col-sm-offset-4"
			type="button" id="btnCadastrar" onclick="enviarProva()">Arquivar</button>

	</div>
</div>


<div class="modal fade" id="resultBuscaQuestoes" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
	style="display: none;">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true"></button>
			</div>
			<div class="modal-body">

				<div class="col-md-12">
					<div class="card card-plain">
						<div class="card-header">
							<h4 class="title">Questões</h4>
							<p class="category">Lista de questões para sua prova</p>
						</div>
					</div>

					<div class="questoes">

						<table class="table table-bordered">


							<thead>
								<th style="width: 45px;">SELECIONAR</th>
								<th>ENUNCIADO</th>


							</thead>
							<tbody id="questoesParaProva">

							</tbody>

						</table>

					</div>

					<div id="comand_pag" align="center">
						<button class="btn btn-default" id="anterior" disabled>&lsaquo;Anterior
						</button>
						<span id="numeracao"> </span>
						<button class="btn btn-default" id="proximo" disabled>Próximo&rsaquo;</button>
					</div>
				</div>


			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-danger btn-simple"
					data-dismiss="modal">Fechar</button>
			</div>
		</div>
	</div>
</div>












<c:import url="/WEB-INF/paginas/template/rodape.jsp" />