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
	<h1>Alterar Papel</h1>
	</div>
	<form action="alterarPapel" method="post">
		<input type="hidden" name="papel_id" value="${papel.papel_id}"/>
		Papel: <input type="text" name="papel" value="${papel.papel}" required="required"/><br/>
		
		<input type="submit" value="Alterar" />
	</form>
	<c:import url="../footer.jsp"/>
</body>
</html>