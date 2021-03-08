<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>WesTest</title>

<script src="<c:url value="/resources/jquery/jquery-1.11.3.min.js"/>"></script>



<script
	src="<c:url value="/resources/bootstrap-3.3.6-dist/js/bootstrap.min.js"/>"></script>
	
<script
	src="<c:url value="/resources/bootstrap-3.3.6-dist/js/bootstrap-notify.js"/>"></script>


<link
	href="<c:url value="/resources/bootstrap-3.3.6-dist/css/bootstrap.min.css"/>"
	rel="stylesheet">

<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/buttons.css"/>">



<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/modal.css"/>">






<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/simple-sidebar.css"/>">




<meta name="viewport" content="width=device-width, shrink-to-fit=no, initial-scale=1">

<script>
$(function(){
	$("#menu-toggle").click(function(e) {
		e.preventDefault();
		$("#wrapper").toggleClass("toggled");
		
	});
	
});



	
</script>


</head>
<body background="<c:url value="/resources/images/background.jpg"/>" style="background-repeat: no-repeat; background-size: 100%"  >


	<div id="wrapper" >

		<!-- Sidebar -->
		<div id="sidebar-wrapper">
			<ul class="sidebar-nav">
				<li class="sidebar-brand"><a href="<c:url value="/index"/> "> WESTEST </a></li>
				<c:if test="${adminLogado != null}">
				<li><a href="<c:url value="/admin/listar_alunos"/>">Alunos</a></li>	
				<li><a href="<c:url value="/admin/listar_avaliadores"/>">Avaliadores</a></li>			
				<li><a href="<c:url value="/admin/listar_coordenadores"/>">Coordenadores</a></li>				
				<li><a href="<c:url value="/admin/listar_disciplinas"/>">Disciplinas</a></li>
				<li><a href="<c:url value="/admin/listar_turmas"/>">Turmas</a></li>
				</c:if>
				<c:if test="${coordenadorLogado != null}">
				<li><a href="<c:url value="/coordenador/listar_provas"/>">Provas</a></li>
				<li><a href="<c:url value="/coordenador/listar_questoes"/>">Questões</a></li>
				</c:if>	
				<c:if test="${avaliadorLogado != null}">
				<li><a href="<c:url value="/avaliador/listar_provas"/>">Provas</a></li>
				<li><a href="<c:url value="/avaliador/listar_questoes"/>">Questões</a></li>
				</c:if>
				
				
				
							
				<c:if test="${alunoLogado == null && avaliadorLogado == null && coordenadorLogado == null && adminLogado == null}">
				<li><a href="#" data-toggle="modal"	data-target="#login-modal">Login</a></li>
				</c:if>
				<c:if test="${alunoLogado != null || avaliadorLogado != null || coordenadorLogado != null || adminLogado != null}">
				<li><a href="<c:url value="/logout"/>">Logout</a></li>
				</c:if>
				<br>
				<br>
				<br>
				<br>
				<br>
				<li><img src="<c:url value="/resources/images/logo2.png"/>" alt="WesTest" 
				width="250" height="220" style="margin-left: -20px"></li>	
			</ul>
		</div>
		<!-- /#sidebar-wrapper -->

		<!-- Page Content -->
		<div id="page-content-wrapper">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-12">
						<a href="#menu-toggle" class="btn btn-default" id="menu-toggle"><span class="glyphicon glyphicon-menu-hamburger"></span></a>
						</div></br>
					</div>
				</div>
			</div>
			
			<div class="modal fade" id="login-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
    	  <div class="modal-dialog">
				<div class="loginmodal-container">
					<h1>Logar</h1><br>
				  <form action="<c:url value="/logar"/>"  method="post">
					<input type="text" required="required" name="login" placeholder="Usuario">
					<input type="password" required="required" name="senha" placeholder="Senha">										
					<input type="submit"  class="login loginmodal-submit" value="Login">
				  </form>					
				  
				</div>
			</div>
		  </div>
			