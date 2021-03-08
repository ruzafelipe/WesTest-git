<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/WEB-INF/paginas/template/cabecalho1.jsp" />


<style>
.carousel-inner>.item>img, .carousel-inner>.item>a>img {
	width: 70%;
	margin: auto;
}
</style>

<div class="container">
	<br>
	<div id="myCarousel" class="carousel slide" data-ride="carousel">
		<!-- Indicators -->
		<ol class="carousel-indicators">
			<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
			<li data-target="#myCarousel" data-slide-to="1"></li>
			<li data-target="#myCarousel" data-slide-to="2"></li>
		</ol>

		<!-- Wrapper for slides -->
		<div class="carousel-inner" role="listbox">


			<div class="item active">
				<img src="<c:url value="/resources/images/imgcrr.jpg"/>" alt="WesTest" width="460" height="345">
				<div class="carousel-caption">
					<h3 style="color: #f19230; margin-top: -250px" >WesTest</h3>
					<p style="color: #000000;">Um novo jeito de realizar provas.</p>					
				</div>
			</div>

			<div class="item">
				<img src="<c:url value="/resources/images/imgcrr1.jpg"/>"
					alt="WesTest" width="460" height="345">
				<div class="carousel-caption">
					<h3 style="color: #f19230; margin-top: -300px" >Praticidade</h3>
						<br>
							<br>
					<p style="margin-top: -50px">Crie e realize suas provas de um modo muito mais fácil.</p>
				</div>
			</div>

			<div class="item">
				<img src="<c:url value="/resources/images/imgcrr2.jpg"/>"
					alt="WesTest" width="460" height="345">
				<div class="carousel-caption">
					<h3 style="color: #f19230; margin-top: -300px">Prepare-se</h3>
					<p style="color: #000000;">Simulados disponívies para ajudar seu estudo.</p>
				</div>
			</div>

		</div>

		<!-- Left and right controls -->
		<a class="left carousel-control" href="#myCarousel" role="button"
			data-slide="prev"> <span class="glyphicon glyphicon-chevron-left"
			aria-hidden="true"></span> <span class="sr-only">Previous</span>
		</a> <a class="right carousel-control" href="#myCarousel" role="button"
			data-slide="next"> <span
			class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
			<span class="sr-only">Next</span>
		</a>
	</div>
</div>



<!--  
<img src="<c:url value="/resources/images/index.gif"/>" style="margin-left: 650px; margin-bottom: 70px; height: 750px">


-->


<c:import url="/WEB-INF/paginas/template/rodape.jsp" />