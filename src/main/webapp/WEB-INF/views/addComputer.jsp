<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.validator.js"></script>

</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand"
				href="/computer-database/dashboard?limit=10&numPage=1">
				Application - Computer Database </a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<h1>Add Computer</h1>
					<form action="addcomputer" method="POST" id="computer">
						<fieldset>
							<div class="form-group">
								<label for="computerName">Computer name</label>
								<c:choose>
									<c:when test="${ empty errors }">
										<input name="name" type="text" class="form-control"
											id="name" placeholder="Computer name"
											value="${fn:escapeXml(cpuDto.name)}">
									</c:when>
									<c:otherwise>
										<input name="name" type="text" class="form-control"
											id="name" placeholder="Computer name"
											value="${fn:escapeXml(cpuDto.name)}">
										<span id="nameError"><b>${ errors['name'] }</b></span>
									</c:otherwise>
								</c:choose>
							</div>
							<div class="form-group">
								<label for="introduced">Introduced date "yyyy-mm-dd"</label>
								<c:choose>
									<c:when test="${ empty errors }">
										<input name="introduced" type="date" class="form-control"
											id="introduced" placeholder="Introduced date"
											value="${ cpuDto.introduced }">
									</c:when>
									<c:otherwise>
										<input name="introduced" type="date" class="form-control"
											id="introduced" placeholder="Introduced date"
											value="${ cpuDto.introduced }">
										<span id="introducedError"><b>${ errors['introduced'] }</b></span>
									</c:otherwise>
								</c:choose>
							</div>
							<div class="form-group">
								<label for="discontinued">Discontinued date "yyyy-mm-dd"</label>
								<c:choose>
									<c:when test="${ empty errors }">
										<input name="discontinued" type="date" class="form-control"
											id="discontinued" placeholder="Discontinued date"
											value="${ cpuDto.discontinued }">
									</c:when>
									<c:otherwise>
										<input name="discontinued" type="date" class="form-control"
											id="discontinued" placeholder="Discontinued date"
											value="${ cpuDto.discontinued }">
										<span id="discontinuedError"><b>${ errors['discontinued'] }</b></span>
									</c:otherwise>
								</c:choose>
							</div>
							<div class="form-group">
								<label for="idCpn">Company</label> <select class="form-control"
									id="idCpn" name="idCpn">
									<option value=${ '' }>-------------------</option>
									<c:forEach items="${ companies }" var="company">
										<option value="${ company.id }"
											${ company.id == cpuDto.idCpn ? 'selected="selected"' : '' }>${ company.name }</option>
									</c:forEach>
								</select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value="Add" class="btn btn-primary">
							<a href="/computer-database/dashboard" class="btn btn-default">Cancel</a>
						</div>
					</form>
					<!-- Desativate to try back-end validation -->
					<script src="js/computer.js"></script>
				</div>
			</div>
		</div>
	</section>
</body>
</html>