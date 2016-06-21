<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Inserir Classificado</title>
</head>
<body>
	<c:import url="../header.jsp"/>
	<div class="main">
		<div class="page-header">
		<h2 class="text-center">Cadastro de Classificado</h2>
		</div>
		<div class="container-fluid">
			<div class="row">
				<form action="inserirClassificado" method="post" enctype="multipart/form-data">
					<div class="form-group col-md-6 col-md-offset-3">
						<label for="title">Titulo</label> 
						<input class="form-control" id="title" type="text" name="titulo" required="required"/><br/>
						<label for="desc">Descrição</label>
						<textarea class="form-control" id="desc" rows="5" cols="30" name="texto" required="required"></textarea> <br/>
						<label>Imagem</label> 
						<input class="form-control" type="file" name="imagem" /> <br />
						<label for="price">Preço</label>
						<input class="form-control" id="price" type="number" step="0.01" min="0" name="preco" required="required"/><br/>
						<label for="phone">Telefone</label>
						<input class="form-control" id="phone" type="tel" pattern="[0-9]{2} [0-9]{1} [0-9]{4}-[0-9]{4}" placeholder="99 9 9999-9999" name="telefone" required="required"/><br/>
						<input type="hidden" name="login" value="${usuario_logado.login}">
						<input class="btn btn-primary" type="submit" value="Cadastrar" />
					</div>
				</form>
			</div>
		</div>
	</div>
	<c:import url="../footer.jsp"/>
</body>
</html>