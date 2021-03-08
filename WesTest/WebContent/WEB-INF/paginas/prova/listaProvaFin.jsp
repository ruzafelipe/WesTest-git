<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/WEB-INF/paginas/template/cabecalho1.jsp" />

<script>

var qtdPorPagina = 10;
var pagAtual = 0;
var jsonProvas = JSON.parse('${listaProva}');
var tamanho = Object.keys(jsonProvas).length;
var prova;

function paginar(){
	$('table > tbody > tr').remove();
	var tbody = $('table > tbody');
	tamanho = Object.keys(jsonProvas).length;
	for(var x = pagAtual * qtdPorPagina; x < tamanho && x < (pagAtual+1)*qtdPorPagina;x++){
		prova = jsonProvas[x];		
		tbody.append($("<tr id='linha_"+prova.idProva+"'>")
			.append($("<td>").append(prova.titulo))
			.append($("<td>").append(prova.dtCriacao))
			.append($("<td>").append("<a href='corrigir_prova?id="+prova.idProva+"'><span class='glyphicon glyphicon-education' style='margin-left: 22px'></span></a>")));	
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
	<c:if test="${coordenadorLogado != null}">
	<a href="<c:url value="/coordenador/form_prova"/>" class="btn btn-sm animated-button victoria-two" style="color:#000000;">Nova Prova</a>
	<a href="<c:url value="/coordenador/listar_provas"/>" class="btn btn-sm animated-button victoria-two" style="color:#000000;">Criadas</a>
	</c:if>
	<c:if test="${avaliadorLogado != null}">
	<a href="<c:url value="/avaliador/form_prova"/>" class="btn btn-sm animated-button victoria-two" style="color:#000000;">Nova Prova</a>
	<a href="<c:url value="/avaliador/listar_provas"/>" class="btn btn-sm animated-button victoria-two" style="color:#000000;">Criadas</a>
	</c:if>
	</div>
</div>

<div class="row">
	<div class="form col-sm-6 col-sm-offset-3">
		<h1>Minhas provas finalizadas</h1>
		<table class="table table-bordered" title="PROVAS">


			<thead>
				<th rowspan="3">TITULO</th>				
				<th rowspan="2">DATA DE CRIAÇÃO</th>
				<th style="width: 45px;">CORRIGIR</th>


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