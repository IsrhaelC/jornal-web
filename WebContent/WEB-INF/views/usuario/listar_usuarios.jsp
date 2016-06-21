<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Lista de Usuarios</title>
</head>
<body>
	
	<c:import url="../header.jsp"/>
	<div class="main">
		<div class="page-header">
		<h2 class="text-center">Usuários</h2>
		</div>
		<div class="container-fluid">
		<div class="row">	
		<c:forEach var="u" items="${usuarios}">
			<div class="col-md-4 thumbnail text-center wow fadeInUp" data-wow-delay="1s">
				<figure> 
					<img alt="${u.nome}" height="200" width="200" class="img-circle" src="<c:url value="/resources/images/${u.login}.png"  />" />
					<h4><strong>Nome</strong> ${u.nome}</h4>
					<p><strong>Email</strong> ${u.email}</p>
					<p><strong>Login</strong> ${u.login}</p>
					<p><strong>Papeis</strong></p>
					<c:forEach var="p" items="${u.papeis}">
						<ul class="list-inline">
							<li><div class="thumbnail">${p.papel} <c:if test="${fn:length(u.papeis) gt 1}"> <a href="removerPapel?id=${u.usuario_id}&papel=${p.papel}" >&times;</a></c:if></div> </li>
						</ul>
					</c:forEach>	
				</figure>
				<div class="btn-group">
					<a href="alterarUsuarioForm?id=${u.usuario_id}" class="btn btn-warning">Alterar</a>
					<a href="apagarUsuario?id=${u.usuario_id}&id_logado=${usuario_logado.usuario_id}" class="btn btn-danger">Apagar</a>
					<a href="adicionarPapelForm?id=${u.usuario_id}" class="btn btn-primary">Adicionar Papel</a>
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