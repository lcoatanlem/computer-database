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
					<div class="label label-default pull-right">id: ${ cpuId }</div>
					<h1>Edit Computer</h1>

					<form action="editComputer" method="POST">
						<input type="hidden" value="0" />
						<fieldset>
							<div class="form-group">
								<label for="computerName">Computer name</label> <input
									type="text" class="form-control" id="computerName"
									value="${ nameInput }">
							</div>
							<div class="form-group">
								<label for="introduced">Introduced date</label>
								<c:choose>
									<c:when test="${ introducedInput == '' }">
										<input type="text" class="form-control" id="introduced"
											placeHolder="Introduced date">
									</c:when>
									<c:otherwise>
										<input type="date" class="form-control" id="introduced"
											value="${ introducedInput }">
									</c:otherwise>
								</c:choose>
							</div>
							<div class="form-group">
								<label for="discontinued">Discontinued date</label>
								<c:choose>
									<c:when test="${ discontinuedInput == '' }">
										<input type="text" class="form-control" id="discontinued"
											placeHolder="Discontinued date">
									</c:when>
									<c:otherwise>
										<input type="date" class="form-control" id="discontinued"
											value="${ discontinuedInput }">
									</c:otherwise>
								</c:choose>
							</div>
							<div class="form-group">
								<label for="companyId">Company</label> <select
									class="form-control" id="companyId" name="company">
									<option value=${ '' }>-------------------</option>
									<c:forEach items="${ companies }" var="company">
										<option value="${company.id}"
											${ company.id == cpnId ? 'selected="selected"' : '' } >${company.name}</option>
									</c:forEach>
								</select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value="Edit" class="btn btn-primary">
							or <a href="dashboard.html" class="btn btn-default">Cancel</a>
						</div>
					</form>
					<script src="js/addComputer.js"></script>
				</div>
			</div>
		</div>
	</section>
</body>
</html>