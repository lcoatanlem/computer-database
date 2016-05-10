<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="mylib" tagdir="/WEB-INF/tags/"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<link href="/computer-database-webapp/resources/css/bootstrap.min.css"
	rel="stylesheet" media="screen">
<link href="/computer-database-webapp/resources/css/font-awesome.css"
	rel="stylesheet" media="screen">
<link href="/computer-database-webapp/resources/css/main.css"
	rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<mylib:link link="Application - Computer Database"
				target="/computer-database-webapp/dashboard"
				className="navbar-brand" limit="10" numPage="1"></mylib:link>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<div class="row centered-form">
				<form class="well form-horizontal col-md-offset-3 col-md-5"
					action="./login" method="POST">
					<h1>Log in</h1>
					<c:if test="${param.error ne null}">
						<div class="alert alert-dismissible alert-danger">
							<button type="button" class="close" data-dismiss="alert"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							Invalid username / password. Try again.
						</div>
					</c:if>
					<c:if test="${param.logout ne null}">
						<div class="alert alert-dismissible alert-success">
							<button type="button" class="close" data-dismiss="alert"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							You have been logged out.
						</div>
					</c:if>
					<div class="form-group">
						<div class="col-md-12">
							<input placeholder="Username" class="form-control col-md-12"
								type="text" name="username" required />
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-12">
							<input placeholder="Password" class="form-control"
								type="password" name="password" required />
						</div>
					</div>
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" /> <br />
					<div class="form-group row">
						<div class="col-md-6">
							<input class="btn btn-block btn-lg btn-primary" type="submit"
								value="Sign In" />
						</div>
					</div>
				</form>
			</div>
		</div>
	</section>

	<footer class="navbar-fixed-bottom"> </footer>
	<script src="/computer-database-webapp/resources/js/jquery.min.js"></script>
	<script src="/computer-database-webapp/resources/js/bootstrap.min.js"></script>
	<script src="/computer-database-webapp/resources/js/dashboard.js"></script>

</body>
</html>