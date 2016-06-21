<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Inserir Usuario</title>
</head>
<body>
	<c:import url="../header.jsp"/>
	<div class="main">
		<div class="page-header">
		<h2 class="text-center">Cadastro de Usuário</h2>
		</div>
		<div class="container-fluid">
		<div class="row">	
		<form action="inserirUsuario" method="post" enctype="multipart/form-data">
			<div class="col-md-6 col-md-offset-3" >
			<div class="form-group">
				<label for="nomeu">Nome</label>
				<input id="nomeu" class="form-control" type="text" name="nome" required="required"/><form:errors path="usuario.nome"/><br/>
				<label for="emailu">Email</label>
				<input id="emailu" class="form-control" type="email" name="email" placeholder="seuemail@host.com" required="required"/><br/>
				<label for="loginu">Login</label>
				<input id="loginu" class="form-control" type="text" name="login" required="required"/><br/>
				<label for="senhau">Senha</label>
				<input id="senhau" class="form-control" type="password" name="senha" required="required"/><br/>
				<label for="imagemu">Imagem</label> 
				<input id="imagemu" class="form-control" type="file" name="imagem" required="required"/> <br />
		
				<input type="submit" class="btn btn-primary" value="Enviar" />
			</div>
			</div>
		</form>
		
		</div>
		</div>
	</div>
	<c:import url="../footer.jsp"/>
</body>
</html>