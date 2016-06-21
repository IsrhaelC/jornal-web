<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Inserir Seção</title>
</head>
<body>
	<c:import url="../header.jsp"/>
	<div class="main">
		<div class="page-header">
		<h2 class="text-center">Cadastrar Seção</h2>
		</div>
		<div class="container-fluid">
		<div class="row">
			<form action="inserirSecao" method="post">
				<div class="form-group col-md-4 col-md-offset-4">
				<label for="title">Titulo</label>
				<input class="form-control" id="title" type="text" name="titulo" required="required"/><form:errors path="secao.titulo"/><br/>
				<label for="desc">Descrição</label>
				<input class="form-control" id="desc" type="text" name="descricao" required="required"/><br/>
				
				<input type="submit" class="btn btn-primary" value="Enviar">
				</div>
			</form>
		</div>
		</div>
	</div>
	<c:import url="../footer.jsp"/>
</body>
</html>