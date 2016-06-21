<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Inicio</title>
	<spring:url value="resources/css/bootstrap.min.css" var="btsMinCss"/>
	<spring:url value="resources/css/style.css" var="styleCss"/>
	<spring:url value="resources/css/home.css" var="homeCss"/>
	<spring:url value="resources/js/bootstrap.js" var="btsMinJs"/>
	<link rel="stylesheet" href="${btsMinCss}"/>
	<link rel="stylesheet" href="${styleCss}"/>
	<link rel="stylesheet" href="${homeCss}"/>
</head>
	<script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
	<script lang="javascript" src="${btsMinJs}"></script>
<body>
	<div class="background-image"></div>
	<!--  Importação do Header -->
	<c:import url="header.jsp"/>
	<div class="main">
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-6 col-xs-12">
					<section class="winter text-center">
						<p id="win">Winter</p>
						<p id="is">is</p>
						<p id="com">Coming</p>
					</section>
				</div>
				<div class="col-md-4 col-md-offset-1 col-xs-12">
					<section class="desc">
						<p>Winter is Coming! E vem trazendo perigos inimagináveis! 
						<strong>Westeros News</strong> possui tudo que você precisa para se preparar para o pior!
						Nossos passarinhos trazem todas as informações que você possa precisar e 
						em nossos classificados você encontrará todos os equipamentos necessários para combater
						os terrríveis Walkers White! Fique atento para não perder a cabeça e...</p>
						<p>Valar Morghulis!</p>
					</section>
				</div>
			</div>
		</div>
	</div>
	<!--  Importação do Footer -->
	<c:import url="footer.jsp"/>
</body>
</html>