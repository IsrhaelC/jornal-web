<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Inserir Papel</title>
</head>
<body>
	<c:import url="../header.jsp"/>
	<div class="page-header">
	<h1>Inserir Papel</h1>
	</div>
	<form action="inserirPapel" method="post">
		Papel:<input type="text" name="papel" required="required"/><form:errors path="secao.papel"/><br/>
		<input type="submit" value="Enviar">
	</form>
	<c:import url="../footer.jsp"/>
</body>
</html>