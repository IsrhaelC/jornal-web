<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Alterar Classificado</title>
</head>
<body>
	<c:import url="../header.jsp"/>
	<div class="main">
		<div class="page-header">
		<h2 class="text-center">Atualização de Classificado</h2>
		</div>
		<div class="container-fluid">
			<div class="row">
				<form action="alterarClassificado" method="post">
					<div class="form-group col-md-6 col-md-offset-3">
						<input type="hidden" name="id_classificado" value="${classificado.classificado_id}"/>
						<label for="title">Titulo</label> 
						<input class="form-control" id="title" type="text" name="tituloClassificado" value="${classificado.titulo}"/><br/>
						<label for="desc">Descrição</label>
						<textarea class="form-control" id="desc" rows="5" cols="30" name="textoClassificado">${classificado.texto}</textarea> <br/>
						<label for="price">Preço</label>
						<input class="form-control" id="price" type="number" name="precoClassificado" value="${classificado.preco}"/><br/>
						<label for="phone">Telefone</label>
						<input class="form-control" id="phone" type="text" name="telefoneClassificado" value="${classificado.telefone}"/><br/>
						<input class="btn btn-primary" type="submit" value="Alterar" />
					</div>
				</form>
			</div>
		</div>
	</div>
	<c:import url="../footer.jsp"/>
</body>
</html>