<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<c:import url="/WEB-INF/paginas/template/cabecalho1.jsp"/>

<div class="row">
	<div class="col-sm-4 col-sm-offset-4">
		<h1><span class="glyphicon glyphicon-remove" style="margin-left: 75px;"></span></h1>
		<h2>Erro ao Logar</h2>	
	</div>
</div>
<script lang="JavaScript">
	setTimeout("document.location = 'index'", 2000)
</script>






<c:import url="/WEB-INF/paginas/template/rodape.jsp"/>