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
	<div class="main">
		<c:if test="${not empty noticias}">
		<div class="page-header">
			<h1>${secao.titulo} <small> ${secao.descricao}</small></h1>
		</div>
		<div class="container-fluid">
			<div class="row">
			
					<c:forEach var="n" items="${noticias}">
						
						<div class="col-md-6 thumbnail news text-center wow fadeInUp" data-wow-delay="1s">
							<h3>${n.titulo}</h3>
							<h5>${n.subtitulo}</h5>
							<p><strong>Por ${n.usuario.nome}</strong></p><p>${n.data_noticia}</p>
							
							<div>
								<a href="lerNoticia?id=${n.noticia_id}" class="btn btn-primary" >Ler mais...</a>
								<c:if test="${usuario_logado.usuario_id == n.id_autor  and usuario_logado.papelAtual == 'Jornalista'}">
									<a href="alterarNoticiaForm?id=${n.noticia_id}" class="btn btn-warning" >Alterar Noticia</a>
									<a href="apagarNoticia?id=${n.noticia_id}" class="btn btn-danger">Remover Noticia</a>
								</c:if>
								<c:if test="${usuario_logado.papelAtual == 'Editor'}">
									<a href="apagarNoticia?id=${n.noticia_id}" class="btn btn-danger">Remover Noticia</a>
								</c:if>
							</div>
							<br/>
							<br/>
						</div>
					</c:forEach>	
			</div>
		</div>
		</c:if>
		<c:if test="${empty noticias}">
			<div class="page-header text-center">
			<h3>Esta seção ainda não possui Noticias cadastradas</h3>
			</div>
		</c:if>
	</div>
	<c:import url="../footer.jsp"/>
	<script>
		$(function(){
			new WOW().init();
		});
	</script>
</body>
</html>