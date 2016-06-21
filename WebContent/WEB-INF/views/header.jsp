<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
	<spring:url value="resources/css/bootstrap.min.css" var="btsMinCss"/>
	<spring:url value="resources/css/animate.css" var="wowCss"/>
	<spring:url value="resources/css/style.css" var="styleCss"/>
	<spring:url value="resources/js/bootstrap.min.js" var="btsMinJs"/>
	<spring:url value="resources/js/wow.min.js" var="wowJs"/>
	
	<link rel="stylesheet" href="${btsMinCss}"/>
	<link rel="stylesheet" href="${wowCss}"/>
	<link rel="stylesheet" href="${styleCss}"/>
	<link rel="icon" type="image/png" href="resources/images/icon.png" />
	<link href='https://fonts.googleapis.com/css?family=Cinzel+Decorative' rel='stylesheet' type='text/css'>
	<link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
</head>
	<script src="https://code.jquery.com/jquery-2.1.3.min.js" ></script>
	<script lang="javascript" src="${btsMinJs}"></script>
	<script lang="javascript" src="${wowJs}"></script>
<body>

	<nav role="navigation" class="navbar navbar-default navbar-fixed-top">
		<div class="navbar-header">
			<button type="button" data-target="#navbarCollapse" data-toggle="collapse" class="navbar-toggle">
				<span class="sr-only">Menu</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a href="homePage" id="brand" class="navbar-brand">Westeros News</a>
		</div>
		<div id="navbarCollapse" class="collapse navbar-collapse">
			<ul id="navtext" class="nav navbar-nav">
				<li><a href="homePage" >Home</a></li>
				<li role="presentation" class="dropdown">
					<a class="dropdown-toggle" data-toggle="dropdown" href="#" >
					Seções <span class="caret"></span>
					</a>
						<ul class="dropdown-menu">
							<c:forEach var="s" items="${secoes}">
							<li><a href="noticiaSecao?id=${s.secao_id}">${s.titulo}</a></li>
							</c:forEach>
						</ul>
				</li>
				<li><a href="listarClassificados" >Classificados</a></li>
			</ul>
		<div class="navbar-right">
			<c:if test="${not empty usuario_logado}">	
				<c:if test="${usuario_logado.papelAtual == 'Leitor'}">
					<ul class="nav navbar-nav">
						<li><img alt="${usuario_logado.nome}" height="30" width="30" class="img-circle" src="<c:url value="/resources/images/${usuario_logado.login}.png"  />" /></li>
						<li><a href="perfil?id=${usuario_logado.usuario_id}">Meu Perfil</a></li>
						<li role="apresentation" class="dropdown">
						<a class="dropdown-toggle" data-toggle="dropdown" href="#">
						Ações<span class="caret"></span>
						</a>
							<ul class="dropdown-menu">
								<li><a href="listarClassificadosUser?id=${usuario_logado.usuario_id}">Meus Classificados</a></li>
								<li><a href="inserirClassificadoForm">Cadastrar Classificado</a></li>
								<li><a href="listarClassificadosOfertas?id=${usuario_logado.usuario_id}">Minhas Ofertas</a></li>
							</ul>
						</li>
						<li><a href="logout">Logout</a></li>
					</ul>
				</c:if>
				<c:if test="${usuario_logado.papelAtual == 'Jornalista'}">
					<ul class="nav navbar-nav">
						<li><img alt="${usuario_logado.nome}" height="30" width="30" class="img-circle" src="<c:url value="/resources/images/${usuario_logado.login}.png"  />" /></li>
						<li><a href="perfil?id=${usuario_logado.usuario_id}">Meu Perfil</a></li>
						<li role="apresentation" class="dropdown">
						<a class="dropdown-toggle" data-toggle="dropdown" href="#">
						Ações<span class="caret"></span>
						</a>
							<ul class="dropdown-menu">
								<li><a href="inserirNoticiaForm">Inserir Noticia</a></li>
								<li><a href="listarNoticiaUser?id=${usuario_logado.usuario_id}">Minhas Noticias</a></li>
							</ul>
						</li>
						<li><a href="logout">Logout</a></li>
					</ul>
				</c:if>
				<c:if test="${usuario_logado.papelAtual == 'Editor'}">
					<ul class="nav navbar-nav">
						<li><img alt="${usuario_logado.nome}" height="30" width="30" class="img-circle" src="<c:url value="/resources/images/${usuario_logado.login}.png"  />" /></li>
						<li><a href="perfil?id=${usuario_logado.usuario_id}">Meu Perfil</a></li>
						<li role="apresentation" class="dropdown">
						<a class="dropdown-toggle" data-toggle="dropdown" href="#">
						Ações<span class="caret"></span>
						</a>
							<ul class="dropdown-menu">
								<li><a href="inserirJornalistaForm">Cadastrar Jornalista</a></li>
								<li><a href="inserirEditorForm">Cadastrar Editor</a></li>
								<li><a href="inserirSecaoForm">Cadastrar Seção</a></li>
								<li><a href="listarSecoes">Listar Seções</a></li>
								<li><a href="listarUsuarios">Listar Usuarios</a></li>
							</ul>
						</li>
						<li><a href="logout">Logout</a></li>
					</ul>
				</c:if>
			</c:if>
			<c:if test="${empty usuario_logado}">
				<ul class="nav navbar-nav">
					<li><a class="navbar-link" href="loginFormulario">Login</a></li>
					<li><a class="navbar-link" href="inserirUsuarioForm">Cadastre-se já</a></li>
				</ul>
			</c:if>
		</div>
		</div>
	</nav>
	<script>
  		$(function () {
    		$('.dropdown-toggle').dropdown();
  		}); 
	</script>
	
</body>
</html>