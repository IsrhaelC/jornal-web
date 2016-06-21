<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Inserir Oferta</title>
</head>
<body>
	<c:import url="../header.jsp"/>
	<div class="main">
		<div class="header-page text-center">
		<h1>Classificado</h1>
		</div>
			<div class="container-fluid">
			<div class="row">
			<div class="thumbnail col-md-6 col-md-offset-3">
				<h2 class="text-center"><strong>${classificado.titulo}</strong></h2>
				<img alt="${classificado.titulo}" class="img-thumbnail imgClassificado" height="200" width="200" src="<c:url value="/resources/images/${classificado.titulo}.png"  />" />
				<div class="infClassificado">
					<p><strong>Descrição</strong> ${classificado.texto}</p>
					<p><strong>Preço Inicial</strong> R$ ${classificado.preco}</p>
					<p><strong>Telefone</strong> ${classificado.telefone}</p>
					<p><strong>Data</strong> ${classificado.data_oferta}</p>
					<c:if test="${not empty classificado.melhor_oferta}">
					<p><strong>Melhor Oferta Atual</strong> R$ ${classificado.melhor_oferta}</p>
					<p><strong>Autor da Melhor Oferta</strong> ${autor.nome}</p>
					<p><strong>Data da Melhor Oferta</strong> ${classificado.data_melhor_oferta}</p>
					</c:if>
					<c:if test="${empty classificado.melhor_oferta}">
						<p><strong>Não há ofertas</strong></p>
					</c:if>
					<h4><strong>Por: ${classificado.usuario.nome}</strong></h4>
					<c:if test="${classificado.vendido == 1 and usuario_logado.usuario_id == classificado.id_autor_mo}">
						<div class="alert alert-success" role="alert">
								<p><strong>Parabéns! Você comprou o produto!</strong></p>
						</div>
					</c:if>
				</div>
			</div>
			</div>
			<c:if test="${classificado.id_autor != usuario_logado.usuario_id and not empty usuario_logado and classificado.vendido == 0}">
			<form action="inserirOferta" method="post">
				<div class="form-group col-md-4 col-md-offset-4">
					<input type="hidden" name="id_classificado" value="${classificado.classificado_id}">
					<input type="hidden" name="id_usuario" value="${usuario_logado.usuario_id}"/>
					<label for="ofert">Inserir Oferta</label> 
					<input id="ofert" class="form-control" type="number" step="0.01" min="0" name="oferta" required="required"/>
					<input class="btn btn-primary" type="submit" value="Enviar"/>
				</div>
			</form>
			</c:if>
			<c:if test="${empty usuario_logado}">
				<h5 class="text-center"><strong>Logue para inserir uma oferta nesse classificado</strong></h5>
			</c:if>
			<c:if test="${classificado.vendido == 1}">
				<h5 class="text-center"><strong>O produto foi vendido</strong></h5>
			</c:if>
		</div>
	</div>
	<c:import url="../footer.jsp"/>
</body>
</html>