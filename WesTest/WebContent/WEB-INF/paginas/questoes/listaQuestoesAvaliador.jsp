<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/WEB-INF/paginas/template/cabecalho1.jsp" />

<script>

var qtdPorPagina = 10;
var pagAtual = 0;
var jsonQuestao = JSON.parse('${listaQuestoes}');
var tamanho = Object.keys(jsonQuestao).length;
var questao;

function paginar(){
	$('table > tbody > tr').remove();
	var tbody = $('table > tbody');
	tamanho = Object.keys(jsonQuestao).length;
	for(var x = pagAtual * qtdPorPagina; x < tamanho && x < (pagAtual+1)*qtdPorPagina;x++){
		questao = jsonQuestao[x];
		tbody.append($("<tr id='linha_"+questao.idQuestao+"'>")
			.append($("<td>").append(questao.enunciado))
			.append($("<td>").append(questao.statusQuestao))
			.append($("<td>").append(questao.dificuldadeQuestao))
			.append($("<td>").append("<a href='detalhes_questao?id="+questao.idQuestao+"'><span class='glyphicon glyphicon-info-sign'></span></a>")));	
		}
	
	$("#numeracao").text("Pagina "+(pagAtual+1)+" de "+Math.ceil(tamanho/qtdPorPagina));
}

function confBotoes(){
	$("#proximo").prop("disabled", tamanho <= qtdPorPagina || pagAtual > tamanho/qtdPorPagina -1);
	$("#anterior").prop("disabled", tamanho <= qtdPorPagina || pagAtual == 0);
}

$(function(){
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
	paginar();
	confBotoes();
});


</script>


<div class="row">
<div class="form col-sm-2 col-sm-offset-8">

	<a href="<c:url value="/avaliador/form_questao"/>" class="btn btn-sm animated-button victoria-two" style="color:#000000;">Nova Questão</a>
	
	</div>
</div>

</br>


<div class="row">
	<div class="form col-sm-6 col-sm-offset-3">
		<h1>Minhas Questões</h1>
		<table class="table table-bordered" title="QUESTOES">


			<thead>
				<th rowspan="3">ENUNCIADO</th>
				<th rowspan="2">STATUS</th>
				<th rowspan="2">DIFICULDADE</th>


			</thead>
			<tbody>

			</tbody>

		</table>

		<div id="comand_pag" align="center">
			<button class="btn btn-default" id="anterior" disabled>&lsaquo;Anterior
			</button>
			<span id="numeracao"> </span>
			<button class="btn btn-default" id="proximo" disabled>Próximo&rsaquo;</button>
		</div>

	</div>
</div>
</br>		
<c:import url="/WEB-INF/paginas/template/rodape.jsp" />