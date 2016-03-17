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
<meta charset="utf-8">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="/computer-database/dashboard?limit=10&numPage=1">
				Application - Computer Database </a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<h1 id="homeTitle">
				<c:out value="${ cpuPage.totalEntries }" />
				Computers found
			</h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="#" method="GET" class="form-inline">

						<input type="search" id="searchbox" name="search"
							class="form-control" placeholder="Search name" /> <input
							type="submit" id="searchsubmit" value="Filter by name"
							class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer"
						href="/computer-database/addcomputer">Add Computer</a> <a
						class="btn btn-default" id="editComputer" href="#"
						onclick="$.fn.toggleEditMode();">Edit</a>
				</div>
			</div>
		</div>

		<form id="deleteForm" action="#" method="POST">
			<input type="hidden" name="selection" value="">
		</form>

		<div class="container" style="margin-top: 10px;">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<!-- Variable declarations for passing labels as parameters -->
						<!-- Table header for Computer Name -->

						<th class="editMode" style="width: 60px; height: 22px;"><input
							type="checkbox" id="selectall" /> <span
							style="vertical-align: top;"> - <a href="#"
								id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
									class="fa fa-trash-o fa-lg"></i>
							</a>
						</span></th>
						<th>Computer name</th>
						<th>Introduced date</th>
						<!-- Table header for Discontinued Date -->
						<th>Discontinued date</th>
						<!-- Table header for Company -->
						<th>Company</th>

					</tr>
				</thead>
				<!-- Browse attribute computers -->
				<tbody id="results">
					<c:forEach items="${ cpuPage.list }" var="elt">
						<tr>
							<td class="editMode"><input type="checkbox" name="cb"
								class="cb" value="0"></td>
							<td><a href="/computer-database/dashboard" onclick="">${ elt.name }</a></td>
							<td>${ elt.introduced }</td>
							<td>${ elt.discontinued }</td>
							<td>${ elt.name_cpn }</td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</section>

	<footer class="navbar-fixed-bottom">
		<div class="container text-center">
			<ul class="pagination">
				<c:if test="${ cpuPage.pageNumber gt 1 }">
					<li><a
						href="/computer-database/dashboard?numPage=${ cpuPage.pageNumber-1 }"
						aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
					</a></li>
				</c:if>
				<c:if test="${ cpuPage.pageNumber gt 1 }">
					<li><a href="/computer-database/dashboard?numPage=1">1</a></li>
				</c:if>

				<c:if test="${ cpuPage.pageNumber gt 6 }">
					<li><a
						href="/computer-database/dashboard?numPage=${ cpuPage.pageNumber-5 }">${ cpuPage.pageNumber-5 }</a></li>
				</c:if>
				<c:if test="${ cpuPage.pageNumber gt 3 }">
					<li><a
						href="/computer-database/dashboard?numPage=${ cpuPage.pageNumber-2 }">${ cpuPage.pageNumber-2 }</a></li>
				</c:if>
				<c:if test="${ cpuPage.pageNumber gt 2 }">
					<li><a
						href="/computer-database/dashboard?numPage=${ cpuPage.pageNumber-1 }">${ cpuPage.pageNumber-1 }</a></li>
				</c:if>

				<li><a href="#">${ cpuPage.pageNumber }</a></li>

				<c:if
					test="${ cpuPage.pageNumber lt (cpuPage.totalEntries/cpuPage.pageSize)-2 }">
					<li><a
						href="/computer-database/dashboard?numPage=${ cpuPage.pageNumber+1 }">${ cpuPage.pageNumber+1 }</a></li>
				</c:if>
				<c:if
					test="${ cpuPage.pageNumber lt (cpuPage.totalEntries/cpuPage.pageSize)-3 }">
					<li><a
						href="/computer-database/dashboard?numPage=${ cpuPage.pageNumber+2 }">${ cpuPage.pageNumber+2 }</a></li>
				</c:if>
				<c:if
					test="${ cpuPage.pageNumber lt (cpuPage.totalEntries/cpuPage.pageSize)-6 }">
					<li><a
						href="/computer-database/dashboard?numPage=${ cpuPage.pageNumber+5 }">${ cpuPage.pageNumber+5 }</a></li>
				</c:if>

				<c:if
					test="${ cpuPage.pageNumber lt fn:substringBefore((cpuPage.totalEntries/cpuPage.pageSize+1), '.') }">
					<li><a
						href="/computer-database/dashboard?numPage=${ fn:substringBefore((cpuPage.totalEntries/cpuPage.pageSize+1), '.') }">${ fn:substringBefore((cpuPage.totalEntries/cpuPage.pageSize+1), '.') }</a></li>
				</c:if>
				<c:if
					test="${ cpuPage.pageNumber lt fn:substringBefore((cpuPage.totalEntries/cpuPage.pageSize+1), '.') }">
					<li><a
						href="/computer-database/dashboard?numPage=${ cpuPage.pageNumber+1 }"
						aria-label="Next"> <span aria-hidden="true">&raquo;</span>
					</a></li>
				</c:if>
			</ul>

			<div class="btn-group btn-group-sm pull-right" role="group">
				<button type="button" class="btn btn-default" onclick="javascript:window.location='/computer-database/dashboard?limit=10';">10</button>
				<button type="button" class="btn btn-default" onclick="javascript:window.location='/computer-database/dashboard?limit=50';">50</button>
				<button type="button" class="btn btn-default" onclick="javascript:window.location='/computer-database/dashboard?limit=100';">100</button>
			</div>
		</div>
	</footer>
	<script src="../js/jquery.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>
	<script src="../js/dashboard.js"></script>

</body>
</html>