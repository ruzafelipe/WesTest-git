<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="/WEB-INF/paginas/template/cabecalho1.jsp"></c:import>

<img src="<c:url value="/resources/images/erro.gif"/>">
<h1>DEU RUIM!</h1>
<h5>Erro: ${pageContext.exception }</h5> 
<h5>URI: ${pageContext.errorData.requestURI }</h5>
<h5>Stack Trace</h5>
<c:forEach items="${pageContext.exception.stackTrace }" var="trace">
<h5 style="margin-left: 20px; font-color: red">${ trace}</h5>
</c:forEach>

<c:import url="/WEB-INF/paginas/template/rodape.jsp"></c:import>