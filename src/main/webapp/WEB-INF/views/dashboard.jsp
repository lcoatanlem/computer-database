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
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<mylib:link link="Application - Computer Database"
				target="/computer-database/dashboard" className="navbar-brand"
				limit="10" numPage="1"></mylib:link>
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
							<td><mylib:link link="${ elt.name }"
									target="/computer-database/dashboard"></mylib:link></td>
							<td><c:out value="${ elt.introduced }"></c:out></td>
							<td><c:out value="${ elt.discontinued }"></c:out></td>
							<td><c:out value="${ elt.name_cpn }"></c:out></td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</section>

	<footer class="navbar-fixed-bottom">
		<div class="container text-center">
			<ul class="pagination">
				<c:set var="totalPages"
					value="${ fn:substringBefore((cpuPage.totalEntries/cpuPage.pageSize +1), '.') }" />
				<c:if test="${ (cpuPage.totalEntries mod cpuPage.pageSize) eq 0 }">
					<c:set var="totalPages"
						value="${ fn:substringBefore((cpuPage.totalEntries/cpuPage.pageSize), '.') }" />
				</c:if>

				<c:if test="${ cpuPage.pageNumber gt 1 }">
					<li><a
						href="/computer-database/dashboard?numPage=${ cpuPage.pageNumber-1 }"
						aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
					</a></li>
				</c:if>
				<c:if test="${ cpuPage.pageNumber gt 1 }">
					<li><mylib:link link="1" target="/computer-database/dashboard"
							numPage="1">
						</mylib:link></li>
				</c:if>

				<c:if test="${ cpuPage.pageNumber gt 6 }">
					<li><mylib:link link="${ cpuPage.pageNumber-5 }"
							target="/computer-database/dashboard"
							numPage="${ cpuPage.pageNumber-5 }">
						</mylib:link></li>
				</c:if>
				<c:if test="${ cpuPage.pageNumber gt 3 }">
					<li><mylib:link link="${ cpuPage.pageNumber-2 }"
							target="/computer-database/dashboard"
							numPage="${ cpuPage.pageNumber-2 }">
						</mylib:link></li>
				</c:if>
				<c:if test="${ cpuPage.pageNumber gt 2 }">
					<li><mylib:link link="${ cpuPage.pageNumber-1 }"
							target="/computer-database/dashboard"
							numPage="${ cpuPage.pageNumber-1 }">
						</mylib:link></li>
				</c:if>

				<li><mylib:link link="${ cpuPage.pageNumber }" target="#">
					</mylib:link></li>

				<c:if test="${ cpuPage.pageNumber lt totalPages-2 }">
					<li><mylib:link link="${ cpuPage.pageNumber+1 }"
							target="/computer-database/dashboard"
							numPage="${ cpuPage.pageNumber+1 }">
						</mylib:link></li>
				</c:if>
				<c:if test="${ cpuPage.pageNumber lt totalPages-3 }">
					<li><mylib:link link="${ cpuPage.pageNumber+2 }"
							target="/computer-database/dashboard"
							numPage="${ cpuPage.pageNumber+2 }">
						</mylib:link></li>
				</c:if>
				<c:if test="${ cpuPage.pageNumber lt totalPages-6 }">
					<li><mylib:link link="${ cpuPage.pageNumber+5 }"
							target="/computer-database/dashboard"
							numPage="${ cpuPage.pageNumber+5 }">
						</mylib:link></li>
				</c:if>

				<c:if test="${ cpuPage.pageNumber lt totalPages }">
					<li><mylib:link link="${ totalPages }"
							target="/computer-database/dashboard" numPage="${ totalPages }">
						</mylib:link></li>
				</c:if>
				<c:if test="${ cpuPage.pageNumber lt totalPages }">
					<li><a
						href="/computer-database/dashboard?numPage=${ cpuPage.pageNumber+1 }"
						aria-label="Next"> <span aria-hidden="true">&raquo;</span>
					</a></li>
				</c:if>
			</ul>

			<div class="btn-group btn-group-sm pull-right" role="group">
				<button type="button" class="btn btn-default"
					onclick="javascript:window.location='/computer-database/dashboard?limit=10';">10</button>
				<button type="button" class="btn btn-default"
					onclick="javascript:window.location='/computer-database/dashboard?limit=50';">50</button>
				<button type="button" class="btn btn-default"
					onclick="javascript:window.location='/computer-database/dashboard?limit=100';">100</button>
			</div>
		</div>
	</footer>
	<script src="/js/jquery.min.js"></script>
	<script src="/js/bootstrap.min.js"></script>
	<script src="/js/dashboard.js"></script>

</body>
</html>