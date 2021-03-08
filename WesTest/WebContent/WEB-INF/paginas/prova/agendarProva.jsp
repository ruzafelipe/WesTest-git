<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/WEB-INF/paginas/template/cabecalho1.jsp" />

<script
	src="<c:url value="/resources/datetimepicker/js/jquery.datetimepicker.full.min.js"/>"></script>

<script
	src="<c:url value="/resources/datetimepicker/js/jquery.datetimepicker.js"/>"></script>
	<script src="<c:url value="/resources/jquery/jquery-mask.js"/>"></script>

<link
	href="<c:url value="/resources/datetimepicker/css/jquery.datetimepicker.css"/>"
	rel="stylesheet">

<script>


$(function() {
	
	$('.tempoProva').mask('00:00');
	$('.notaQuestao').mask('00');
	
	$('#datetimepicker_dtprova').datetimepicker({		
		theme:'dark',		
		format:'d-m-Y H:i',
		lang: 'pt',
		minDate:0,
		step: 15})
		
		
		jQuery.datetimepicker.setLocale('pt');
		
		
});


$(function() {
	$('#tempoQuestao').click(function() {
		if (this.checked) {
			$('.tempoQ').show();
			$('.tempoQ').attr('required');
			$('#tempoProva').attr('disabled', 'disabled');
			$('#tempoProva').val('');
		}else{
			$('.tempoQ').hide();
			$('.tempoQ').removeAttr('required');
			$('#tempoProva').removeAttr('disabled');
			$('#tempoProva').val('');
		}
		
	});
});


var qtdPorPagina = 5;
var pagAtualT = 0;
var pagAtualQ = 0;
var json;
var tamanho;
var turma;
var prova;


function paginarTurmas(){
	
	json = JSON.parse('${turmas}');
	var tbody = $('#tableTurmas');	
	$(tbody).empty();	
	tamanho = Object.keys(json).length;
	for(var x = pagAtualT * qtdPorPagina; x < tamanho && x < (pagAtualT+1)*qtdPorPagina;x++){
		turma = json[x];
		tbody.append($("<tr id='linha_"+turma.idTurma+"'>")
			.append($("<td><input name='turmas' type='checkbox' value='" +turma.idTurma+ "'style='margin-left: 40px;''>"))			
			.append($("<td>").append(turma.descricao)));	
		}
	
	$("#numeracaoT").text("Pagina "+(pagAtualT+1)+" de "+Math.ceil(tamanho/qtdPorPagina));
}

function confBotoesT(){
	$("#proximoT").prop("disabled", tamanho <= qtdPorPagina || pagAtualT > tamanho/qtdPorPagina -1);
	$("#anteriorT").prop("disabled", tamanho <= qtdPorPagina || pagAtualT == 0);
}

$(function(){
	$("#proximoT").click(function(){
		if(pagAtualT < tamanho/qtdPorPagina -1){
			pagAtual++;
			paginarTurmas();
			confBotoesT();
		}
	});
	
	$("#anteriorT").click(function(){
		if(pagAtualT > 0){
			pagAtual--;
			paginarTurmas();
			confBotoesT();
		}
	});	
	paginarTurmas();
	confBotoesT();
});





var questao;
function paginarQuestoes(){
	json = JSON.parse('${questoesProva}');
	var tbody = $('#tableQuestoes');	
	$(tbody).empty();	
	tamanho = Object.keys(json).length;
	for(var x = pagAtualQ * qtdPorPagina; x < tamanho && x < (pagAtualQ+1)*qtdPorPagina;x++){
		questao = json[x];
		tbody.append($("<tr id='linha_"+questao.idQuestaoProva+"'>")
			.append($("<td><input name='notaQuestao' class='notaQuestao' required='required' style='width: 55px;'>"))
			.append($("<td hidden class='tempoQ'><input name='tempoQuestao' placeholder='00:00' class='form-control input tempoProva' style='width: 80px; text-align: center'  />"))
			.append($("<td>").append(questao.questao.enunciado)));	
		}
	
	$("#numeracaoQ").text("Pagina "+(pagAtualQ+1)+" de "+Math.ceil(tamanho/qtdPorPagina));
}

function confBotoesQ(){
	$("#proximoQ").prop("disabled", tamanho <= qtdPorPagina || pagAtualQ > tamanho/qtdPorPagina -1);
	$("#anteriorQ").prop("disabled", tamanho <= qtdPorPagina || pagAtualQ == 0);
}

$(function(){
	$("#proximoQ").click(function(){
		if(pagAtualQ < tamanho/qtdPorPagina -1){
			pagAtual++;
			paginarQuestoes();
			confBotoesQ();
		}
	});
	
	$("#anteriorQ").click(function(){
		if(pagAtualQ > 0){
			pagAtual--;
			paginarQuestoes();
			confBotoesQ();
		}
	});	
	paginarQuestoes();
	confBotoesQ();
});


</script>


<form action="agendar_prova" method="post" id="agendarProva">
<div class="col-md-12 table-bordered" >

<input name="idProva" value="${prova.idProva }" style="display: none;">
<select name="turmas" multiple="multiple"
						id="selecionadasturmas" style="display: none;">
					</select>

	<h1>Agendar prova</h1>	
	<div class="form col-sm-6">
		<h3>Questões da prova</h3>
		<h6>Digite uma valor para a(s) nota(s) da(s) questão(ões) (de 1 a 99)</h6>
		<table class="table table-bordered" title="QUESTÕES" >

		
			<thead>
				<th style="width: 60px;">NOTA</th>
				<th style="width: 60px;" hidden class="tempoQ">TEMPO</th>
				<th rowspan="3">ENUNCIADO</th>


			</thead>
			<tbody id="tableQuestoes">

			</tbody>

		</table>			
		<div id="comand_pagQ" align="center">
			<button class="btn btn-default" id="anteriorQ" disabled>&lsaquo;Anterior
			</button>
			<span id="numeracaoQ"> </span>
			<button class="btn btn-default" id="proximoQ" disabled>Próximo&rsaquo;</button>
		</div>

	</div>
	<div class="col-md-3 ">
			<div class=" pull-right">
				<label class="btn btn-success active"> <input
					type="checkbox" autocomplete="off" id="tempoQuestao">
					Tempo por questão
				</label>
			</div>
		</div>	

</div>
	<br />
		<br />
<div class="col-md-12" style="margin-top: 30px; margin-bottom: 30px;">	
	<div class="col-md-6 table-bordered" >
	<h3>Configurações da prova	</h3>
	<br />
	<br />
	<div class="form-group">
	<label>Data e horário da prova</label>
	<input type="text" id="datetimepicker_dtprova" name="dataProva" class="form-control input" required="required" style="width: 200px;"/>
	
	</div>
	
	<div class="form-group">
	<label>Duração da prova</label>
	<input placeholder="00:00" class="form-control input tempoProva" id="tempoProva" required="required" name="tempoProva" style="width: 80px; "  />
	</div>
	
	</div>
	
	<div class="col-md-6 pull-right table-bordered" style="margin-bottom: 20px">
	<h3>Turmas</h3>
	
	<table class="table table-bordered" title="QUESTÕES">

		
			<thead>
				<th style="width: 60px;">SELECIONAR</th>				
				<th rowspan="3">NOME DA TURMA</th>


			</thead>
			<tbody id="tableTurmas">

			</tbody>

		</table>
		<div id="comand_pagT" align="center" style="">
			<button class="btn btn-default" id="anteriorT" disabled>&lsaquo;Anterior
			</button>
			<span id="numeracaoT"> </span>
			<button class="btn btn-default" id="proximoT" disabled>Próximo&rsaquo;</button>
		</div>
	</div>	
	</div>	
	<button class="buttonSub input-lg col-sm-4 col-sm-offset-4" style="margin-bottom: 50px"
			type="submit" id="btnAgendar" >Agendar</button>
			
	
	</form>
	
	



<c:import url="/WEB-INF/paginas/template/rodape.jsp" />