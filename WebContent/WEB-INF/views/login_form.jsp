<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Login</title>
</head>
<body>
	<c:import url="header.jsp"/>
	<div class="main">
		<div class="page-header">
		<h2 class="text-center">Login</h2>
		</div>
		<div class="container-fluid">
			<div class="row">
				<form action="login" method="post">
					<div class="form-group col-md-4 col-md-offset-4 thumb">
					<label for="log">Login</label> 
					<input id="log" class="form-control" type="text" name="login"/><br/>
					<label for="pass">Senha</label> 
					<input id="pass" class="form-control" type="password" name="senha"/><br/>
					<label for="paper">Papel</label> 
					<select id="paper" class="form-control" name="papel">
						<c:forEach var="p" items="${papeis}">
							<option>
								<c:out value="${p.papel}"></c:out>
							</option>
						</c:forEach>
					</select><br/>
					<input class="btn btn-success" type="submit" value="ENVIAR">
					</div>
				</form>
			</div>
		</div>
	</div>
	<c:import url="footer.jsp"/>
</body>
</html>