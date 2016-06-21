<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title></title>
</head>
<body>
	<c:import url="../header.jsp"/>
	<div class="page-header">
	<h1>Lista de Papeis</h1>
	</div>
	<table border="1">
		<c:forEach var="p" items="${papeis}">
			<tr>
				<td>${p.papel_id}</td>
				<td>${p.papel}</td>
				<td><a href="alterarPapelForm?id=${p.papel_id}">Alterar</a></td>
				<td><a href="apagarPapel?id=${p.papel_id}">Apagar</a></td>
			</tr>
		</c:forEach>
	</table>
	<c:import url="../footer.jsp"/>
</body>
</html>