<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/WEB-INF/paginas/template/cabecalho1.jsp" />


<script>
function excluir(id, x){
	$.post("excluir_pessoa", {"id": id}, function(){
		$("#linha_"+id).hide("slow", function(){
			if (x >-1){
				jsonAlu.splice(x,1)
				//alert("Aluno "+aluno.nome+" excluido com sucesso!");
				alert("Aluno excluido com sucesso!");
			}
			paginar();
		
		});
	});
}


var qtdPorPagina = 10;
var pagAtual = 0;
var jsonAlu = JSON.parse('${listaAlu}');
var tamanho = Object.keys(jsonAlu).length;
var aluno;

function paginar(){
	$('table > tbody > tr').remove();
	var tbody = $('table > tbody');
	tamanho = Object.keys(jsonAlu).length;
	for(var x = pagAtual * qtdPorPagina; x < tamanho && x < (pagAtual+1)*qtdPorPagina;x++){
		aluno = jsonAlu[x];
		tbody.append($("<tr id='linha_"+aluno.idPessoa+"'>")			
			.append($("<td>").append(aluno.nome))			
			.append($("<td>").append(aluno.turma.descricao))
			.append($("<td>").append("<a href='alterar_aluno?id="+aluno.idPessoa+"'><span class='glyphicon glyphicon-pencil'>Alter</span></a>"))
			.append($("<td>").append("<a href='#'onclick='excluir("+aluno.idPessoa+","+x+")'><span class='glyphicon glyphicon-remove'>Remove</span></a>")));	
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

	<a href="<c:url value="/admin/form_aluno"/>" class="btn btn-sm animated-button victoria-two" style="color:#000000;">Novo Aluno</a>
	
	</div>
</div>				


</br>


<div class="row">
<div class="form col-sm-6 col-sm-offset-3">
<h1>Alunos</h1>
		<table class ="table table-bordered" title="TURMAS">
		
			
			<thead>				
				<th rowspan="2">NOME</th>				
				<th rowspan="2">TURMA</th>
		
			
			</thead>
			<tbody>
			
			</tbody>			
						
			</table>	
			
			<div id="comand_pag" align="center">
		<button class="btn btn-default" id="anterior" disabled>&lsaquo;Anterior	</button>
		<span id="numeracao"> </span>
		<button class="btn btn-default" id="proximo" disabled>Pr�ximo&rsaquo;</button>
		</div>
			
			</div>
			</div>	
			</br>		


<c:import url="/WEB-INF/paginas/template/rodape.jsp" />