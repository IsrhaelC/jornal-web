<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Editar Noticia</title>
</head>
<body>		
	<c:import url="../header.jsp"/>
	<div class="main">
		<div class="page-header">
			<h2 class="text-center">Alterar Noticia</h2>
		</div>
		<div class="container-fluid">
			<div class="row">
				<form action="alterarNoticia" method="post">
					<div class="form-group col-md-8 col-md-offset-2">
						<input type="hidden" name="id_noticia" value="${noticia.noticia_id}" required="required"/>
						<label for="title">Titulo</label> 
						<input class="form-control" id="title" type="text" name="tituloNoticia" value="${noticia.titulo}" required="required"/>
						<label for="stitle">Subtitulo</label> 
						<input class="form-control" id="stitle" type="text" name="subtituloNoticia" value="${noticia.subtitulo}" required="required"/>
						<label for="text">Texto</label> 
						<textarea class="form-control" id="text" rows="20" cols="60" name="textoNoticia" required="required">${noticia.texto}</textarea>
								
						<input class="btn btn-primary" type="submit" value="Alterar" />
					</div>
				</form>
			</div>
		</div>
	</div>
	<c:import url="../footer.jsp"/>
</body>
</html>