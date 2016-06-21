<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Alterar Seção</title>
</head>
<body>
	<c:import url="../header.jsp"/>
	<div class="main">
		<div class="page-header">
		<h2 class="text-center">Alterar Seção Seção</h2>
		</div>
		<div class="container-fluid">
		<div class="row">
			<form action="alterarSecao" method="post">
				<div class="form-group col-md-4 col-md-offset-4">
				<input type="hidden" name="secao_id" value="${secao.secao_id}"/>
				<label for="title">Titulo</label>
				<input class="form-control" id="title" type="text" name="titulo" value="${secao.titulo}" required="required"/>
				<label for="desc">Descrição</label>
				<input class="form-control" id="desc" type="text" name="descricao" value="${secao.descricao}" required="required"/><br/>
				
				<input type="submit" class="btn btn-primary" value="Alterar">
				</div>
			</form>
		</div>
		</div>
	</div>
	<c:import url="../footer.jsp"/>
</body>
</html>