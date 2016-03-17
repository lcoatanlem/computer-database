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
					<form action="addcomputer" method="POST" id="addcomputer">
						<fieldset>
							<div class="form-group">
								<label for="computerName">Computer name</label> <input
									name="computerName" type="text" class="form-control"
									id="computerName" placeholder="Computer name"
									value="${fn:escapeXml(param.computerName)}">
							</div>
							<div class="form-group">
								<label for="introduced">Introduced date</label> <input
									name="introduced" type="date" class="form-control"
									id="introduced" placeholder="Introduced date">
							</div>
							<div class="form-group">
								<label for="discontinued">Discontinued date</label> <input
									name="discontinued" type="date" class="form-control"
									id="discontinued" placeholder="Discontinued date">
							</div>
							<div class="form-group">
								<label for="companyId">Company</label> <select
									class="form-control" id="companyId" name="company">
									<option value=${ '' }>-------------------</option>
									<c:forEach items="${ companies }" var="company">
										<option value="${company.id}">${company.name}</option>
									</c:forEach>
								</select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value="Add" class="btn btn-primary">
							or <a href="/computer-database/dashboard" class="btn btn-default">Cancel</a>
						</div>
					</form>
					<script>
							$('#addcomputer').validate({
								rules : {
									computerName : {
										required : true,
										minlength : 2,
										noHTML : true
									},
									introduced : {
										dateISO : true
									},
									discontinued : {
										dateISO : true
									}
								},
								messages : {
									computerName : {
										required : "Name is required",
										minlength : "At least two chars"
									},
									introduced : {
										dateISO : "Invalid date format"
									},
									discontinued : {
										dateISO : "Invalid date format"
									}
								},
								error : function(label) {
									$(this).addClass("error");
								}
							});						
					</script>
				</div>
			</div>
		</div>
	</section>
</body>
</html>