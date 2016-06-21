<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Listar Seção</title>
</head>
<body>
	<c:import url="../header.jsp"/>
	<div class="main">
		<div class="page-header">
		<h2 class="text-center">Seções</h2>
		</div>
		<div class="container-fluid">
			<div class="row">
				<c:forEach var="s" items="${secoes}">
					<div class="col-md-3 thumbnail text-center wow fadeInUp" data-wow-delay="1s">
						<h4>${s.titulo}</h4>
						<p>${s.descricao}</p>
						<div class="btn-group">
							<a href="alterarSecaoForm?id=${s.secao_id}" class="btn btn-warning">Alterar</a>
							<a href="apagarSecao?id=${s.secao_id}" class="btn btn-danger">Apagar</a>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	<c:import url="../footer.jsp"/>
	<script>
		$(function(){
			new WOW().init();
		});
	</script>

</body>
</html>