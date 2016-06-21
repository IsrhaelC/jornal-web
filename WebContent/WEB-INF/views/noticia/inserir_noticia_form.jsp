<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title></title>
</head>
<body>
	<c:import url="../header.jsp"/>
	<div class="main">
		<div class="page-header">
			<h2 class="text-center">Cadastrar Noticia</h2>
		</div>
		<div class="container-fluid">
			<div class="row">
				<form action="inserirNoticia" method="post" enctype="multipart/form-data">
					<div class="form-group col-md-8 col-md-offset-2">
						<label for="title">Titulo</label><form:errors path="noticia.titulo"/><br/> 
						<input class="form-control" id="title" type="text" name="titulo" required="required"/><br/>
						<label for="stitle">Subtitulo</label> 
						<input class="form-control" id="stitle" type="text" name="subtitulo" required="required"/><br/>
						<label for="stext">Texto</label> 
						<textarea class="form-control" id="stext" rows="20" cols="60" name="texto" required="required"></textarea>
						<label for="img">Imagem</label> 
						<input id="img" type="file" name="imagem"/>
						<label for="section">Seção</label>
						<select id="section" class="form-control" name="secaoTitulo">
							<c:forEach var="s" items="${secoes}">
								<option>
									<c:out value="${s.titulo}"></c:out>
								</option>
							</c:forEach>
						</select><br/>
						<input type="hidden" name="login" value="${usuario_logado.login}"/>		
						<input class="btn btn-primary" type="submit" value="Cadastrar" />
					</div>
				</form>
			</div>
		</div>
	</div>
	<c:import url="../footer.jsp"/>
</body>
</html>