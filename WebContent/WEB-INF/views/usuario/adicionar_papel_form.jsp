<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Adicionar Papel</title>
</head>
<body>
	<c:import url="../header.jsp"/>
	<div class="main">
		<div class="page-header">
		<h2 class="text-center">Adicionar Papel</h2>
		</div>
	<div class="container-fluid">
	<div class="row">
		<form action="adicionarPapel" method="post">
			<div class="col-md-4 col-md-offset-4">
			<div class="form-group">
				<input type="hidden" name="idUsuario" value="${usuario.usuario_id}"/>
				<h3>Selecione o Papel que deseja adicionar</h3>
				<label for="paper">Papel</label>
				<select class="form-control" id="paper" name="nomePapel">
							<c:forEach var="p" items="${papeis}">
								<option>
									<c:out value="${p.papel}"></c:out>
								</option>
							</c:forEach>
				</select><br/>
				<input type="submit" class="btn btn-primary" value="Adicionar" />
			</div>
			</div>
		</form>
	</div>
	</div>
	</div>
	<c:import url="../footer.jsp"/>	
</body>
</html>