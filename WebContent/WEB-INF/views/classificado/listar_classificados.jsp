<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Classificados</title>
</head>	
<body>
	<c:import url="../header.jsp"/>
	<div class="main">
		<c:if test="${not empty classificado}">
		<div class="page-header text-center">
		<h1>Classificados</h1>
		</div>
		<div class="container-fluid">
			<div class="row">
				<c:forEach var="c" items="${classificado}">
					<div class="thumbnail classi col-md-4 wow fadeInUp" data-wow-delay="1s">
						<h3 class="text-center"><strong>${c.titulo}</strong></h3>
						<p><strong>Preço</strong> R$ ${c.preco}</p>
						<p><strong>Telefone</strong> ${c.telefone}</p>
						<p><strong>Data</strong> ${c.data_oferta}</p>
						<h4><strong>Por ${c.usuario.nome}</strong></h4>
						<c:if test="${c.vendido == 1}">
							<div class="alert alert-danger" role="alert">
								<p><strong>O produto foi vendido</strong></p>
							</div>
						</c:if>
						<c:if test="${c.vendido == 0}">
							<div class="alert alert-info" role="alert">
								<p><strong>Produto Disponivel</strong></p>
							</div>
						</c:if>
						<div class="btn-group text-center">
							<a class="btn btn-primary" href="inserirOfertaForm?id=${c.classificado_id}" >Ver mais...</a>
							<c:if test="${c.id_autor == usuario_logado.usuario_id}">
								<a class="btn btn-warning" href="alterarClassificadoForm?id=${c.classificado_id}">Alterar</a>
								<a class="btn btn-danger" href="apagarClassificado?id=${c.classificado_id}">Apagar</a>
							</c:if>
							<c:if test="${c.id_autor == usuario_logado.usuario_id and c.vendido == 0 and not empty c.melhor_oferta}">
								<a class="btn btn-success" href="venderClassificado?id=${c.classificado_id}">Vender</a>
							</c:if>
							<c:if test="${usuario_logado.papelAtual == 'Editor' and usuario_logado.usuario_id != c.id_autor}">
								<a class="btn btn-danger" href="apagarClassificado?id=${c.classificado_id}">Apagar</a>
							</c:if>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
		</c:if>
		<c:if test="${empty classificado}">
			<div class="page-header text-center">
				<h1>Classificados</h1>
			</div>
		</c:if>
	</div>
	<c:import url="../footer.jsp"/>
	<script>
		$(function(){
			new WOW().init();
		});
	</script>
</body>
</html>