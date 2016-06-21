<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<spring:url value="resources/css/bootstrap.min.css" var="btsMinCss"/>
	<spring:url value="resources/css/style.css" var="styleCss"/>
	<spring:url value="resources/js/bootstrap.min.js" var="btsMinJs"/>
	<spring:url value="resources/js/bootstrap.min.js" var="btsMinJs"/>
	<link rel="stylesheet" href="${btsMinCss}"/>
	<link rel="stylesheet" href="${styleCss}"/>
	<link href='https://fonts.googleapis.com/css?family=Cinzel+Decorative' rel='stylesheet' type='text/css'>
</head>
<body>
	<div class="navbar navbar-inverse navbar-fixed-bottom">
		<div class="container">
			<p class="navbar-text navbar-right">Site desenvolvido com Spring MVC e Bootstrap por Isrhael Christian
			<a target="_blank" href="https://www.facebook.com/isrhael.christian"><img alt="Facebook" src="resources/images/facebook.png"></a>
			<a target="_blank" href="https://github.com/IsrhaelC"><img alt="GitHub" src="resources/images/github-logo.png"></a>
			<a target="_blank" href="https://www.linkedin.com/in/isrhael-christian-52a267b3?trk=nav_responsive_tab_profile_pic"><img alt="Linkedin" src="resources/images/linkedin.png"></a>
			<a target="_blank" href="https://twitter.com/Isrhael_Souza"><img alt="Twitter" src="resources/images/twitter.png"></a>
			</p>
		</div>
	</div>
</body>
</html>