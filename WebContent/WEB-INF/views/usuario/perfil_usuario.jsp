<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Meu Perfil</title>
</head>
<body>
	<c:import url="../header.jsp"/>
	<div class="main">
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-6 col-md-offset-3 thumbnail text-center perfil">
					<figure> 
						<img alt="${usuario.nome}" height="400" width="400" class="img-circle" src="<c:url value="/resources/images/${usuario.login}.png"  />" />
						<h4><strong>Nome</strong> ${usuario.nome}</h4>
						<p><strong>Email</strong> ${usuario.email}</p>
						<p><strong>Login</strong> ${usuario.login}</p>
						<p><strong>Papeis</strong></p>
						<c:forEach var="p" items="${usuario.papeis}">
							<ul class="list-inline">
								<li><div class="thumbnail">${p.papel}</div> </li>
							</ul>
						</c:forEach>	
					</figure>
					<div class="btn-group">
						<a href="alterarUsuarioForm?id=${usuario.usuario_id}" class="btn btn-warning">Alterar</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<c:import url="../footer.jsp"/>
</body>
</html>