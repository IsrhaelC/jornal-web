<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>${noticia.titulo}</title>
</head>
<body>
	<c:import url="../header.jsp"/>
	<div class="main">
		<div class="page-header">
		<h1>${noticia.titulo} </h1><br/>
		<h3>${noticia.subtitulo}</h3>
		<h5><strong>${noticia.usuario.nome}</strong>, ${noticia.data_noticia}, em ${noticia.secao.titulo}</h5>
		</div>
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-10 col-md-offset-1 text-center">
					<div class="texto">
						<p class="">${noticia.texto}</p>
					</div>
						<img alt="${noticia.titulo}" class="img-size img-responsive img-thumbnail" src="<c:url value="/resources/images/${noticia.titulo}.png"  />" />
				</div>
			</div>
			<br/>
			<br/>
			<div class="row">
				<div class="row coment thumbnail">
				<div class="col-md-4 col-md-offset-4">  
					<h3 class="text-center">Comentários</h3>
						<c:forEach var="t" items="${comentarios}">
						<img alt="${t.usuario.nome}" height="30" width="30" class="img-circle" src="<c:url value="/resources/images/${t.usuario.login}.png"  />" />
							 
							<label>${t.usuario.nome}</label><p>${t.texto}  <c:if test="${usuario_logado.usuario_id == t.usuario.usuario_id}"> <a href="apagarComentario?id=${t.comentario_id}&id_noticia=${noticia.noticia_id}"> &times;</a></c:if></p>
						</c:forEach>
					</div>
				</div>
			</div>
				<div class="row logout">
				<c:if test="${not empty usuario_logado}">
					<form action="inserirComentario" method="post">
						<div class="form-group col-md-4 col-md-offset-4">
							<input class="form-control" type="text" name="texto" required="required"/>
							<input type="hidden" name="login" value="${usuario_logado.login}">
							<input type="hidden" name="titulo" value="${noticia.titulo}">
							<input class="btn btn-default" type="submit" value="Comentar">
						</div>
					</form>
				</c:if>
				<c:if test="${empty usuario_logado}">
					<p class="text-center"><strong>Logue para comentar essa noticia</strong> <a href="loginFormulario">Login</a></p>
				</c:if>
				</div>
		</div>
	</div>
	<c:import url="../footer.jsp"/>
</body>
</html>