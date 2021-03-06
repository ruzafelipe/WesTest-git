<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/WEB-INF/paginas/template/cabecalho1.jsp" />


<script>
function excluir(id, x){
	$.post("excluir_disciplina", {"id": id}, function(){
		$("#linha_"+id).hide("slow", function(){
			if (x >-1){
				jsonDisci.splice(x,1)
				alert("Disciplina "+disciplina.descricao+" excluida com sucesso!");
			}
			paginar();
		
		});
	});
}


var qtdPorPagina = 10;
var pagAtual = 0;
var jsonDisci = JSON.parse('${listaD}');
var tamanho = Object.keys(jsonDisci).length;
var disci;

function paginar(){
	$('table > tbody > tr').remove();
	var tbody = $('table > tbody');
	tamanho = Object.keys(jsonDisci).length;
	for(var x = pagAtual * qtdPorPagina; x < tamanho && x < (pagAtual+1)*qtdPorPagina;x++){
		disci = jsonDisci[x];
		tbody.append($("<tr id='linha_"+disci.idDisciplina+"'>")			
			.append($("<td>").append(disci.descricao))
			.append($("<td>").append(disci.coordenador.nome))
			.append($("<td>").append("<a href='alterar_disciplina?id="+disci.idDisciplina+"'><span class='glyphicon glyphicon-pencil'>Alter</span></a>"))
			.append($("<td>").append("<a href='#'onclick='excluir("+disci.idDisciplina+","+x+")'><span class='glyphicon glyphicon-remove'>Remove</span></a>")));	
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

	<a href="<c:url value="/admin/form_disciplina"/>" class="btn btn-sm animated-button victoria-two" style="color:#000000;">Nova Disciplina</a>
	
	</div>
</div>
	
			

</br>


<div class="row">
<div class="form col-sm-6 col-sm-offset-3">
<h1>Disciplinas</h1>
		<table class ="table table-bordered" title="DISCIPLINAS">
		
			
			<thead>				
				<th rowspan="2">NOME</th>
				<th rowspan="2">COORDENADOR</th>
		
			
			</thead>
			<tbody>
			 
			</tbody>			
						
			</table>	
			
			<div id="comand_pag" align="center">
		<button class="btn btn-default" id="anterior" disabled>&lsaquo;Anterior	</button>
		<span id="numeracao"> </span>
		<button class="btn btn-default" id="proximo" disabled>Pr?ximo&rsaquo;</button>
		</div>
			
			</div>
			</div>	
			</br>		


<c:import url="/WEB-INF/paginas/template/rodape.jsp" />