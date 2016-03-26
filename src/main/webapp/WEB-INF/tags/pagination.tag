<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="mylib" tagdir="/WEB-INF/tags"%>

<%@ attribute name="page" required="true"
	type="com.excilys.computerdatabase.pagination.Pagination"
	description="The pagination"%>

<ul class="pagination">
	<c:if test="${ page.cpuPageNumber gt 1 }">
		<li><a
			href="/computer-database/dashboard?numPage=${ page.cpuPageNumber-1 }&limit=${ page.cpuPageSize }"
			aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
		</a></li>
	</c:if>
	<c:if test="${ page.cpuPageNumber gt 1 }">
		<li><mylib:link link="1" target="/computer-database/dashboard"
				numPage="1" limit="${ page.cpuPageSize }">
			</mylib:link></li>
	</c:if>

	<c:if test="${ page.cpuPageNumber gt 6 }">
		<li><mylib:link link="${ page.cpuPageNumber-5 }"
				target="/computer-database/dashboard"
				numPage="${ page.cpuPageNumber-5 }" limit="${ page.cpuPageSize }">
			</mylib:link></li>
	</c:if>
	<c:if test="${ page.cpuPageNumber gt 3 }">
		<li><mylib:link link="${ page.cpuPageNumber-2 }"
				target="/computer-database/dashboard"
				numPage="${ page.cpuPageNumber-2 }" limit="${ page.cpuPageSize }">
			</mylib:link></li>
	</c:if>
	<c:if test="${ page.cpuPageNumber gt 2 }">
		<li><mylib:link link="${ page.cpuPageNumber-1 }"
				target="/computer-database/dashboard"
				numPage="${ page.cpuPageNumber-1 }" limit="${ page.cpuPageSize }">
			</mylib:link></li>
	</c:if>

	<li><mylib:link link="${ page.cpuPageNumber }" target="#"
			limit="${ page.cpuPageSize }">
		</mylib:link></li>

	<c:if test="${ page.cpuPageNumber lt page.cpuNbPages-2 }">
		<li><mylib:link link="${ page.cpuPageNumber+1 }"
				target="/computer-database/dashboard"
				numPage="${ page.cpuPageNumber+1 }" limit="${ page.cpuPageSize }">
			</mylib:link></li>
	</c:if>
	<c:if test="${ page.cpuPageNumber lt page.cpuNbPages-3 }">
		<li><mylib:link link="${ page.cpuPageNumber+2 }"
				target="/computer-database/dashboard"
				numPage="${ page.cpuPageNumber+2 }" limit="${ page.cpuPageSize }">
			</mylib:link></li>
	</c:if>
	<c:if test="${ page.cpuPageNumber lt page.cpuNbPages-6 }">
		<li><mylib:link link="${ page.cpuPageNumber+5 }"
				target="/computer-database/dashboard"
				numPage="${ page.cpuPageNumber+5 }" limit="${ page.cpuPageSize }">
			</mylib:link></li>
	</c:if>
	<c:if test="${ (page.cpuPageNumber-page.cpuNbPages) lt 0}">
		<li><mylib:link link="${ page.cpuNbPages }"
				target="/computer-database/dashboard" numPage="${ page.cpuNbPages }"
				limit="${ page.cpuPageSize }">
			</mylib:link></li>

	</c:if>
	<c:if test="${ (page.cpuPageNumber-page.cpuNbPages) lt 0 }">
		<li><a
			href="/computer-database/dashboard?numPage=${ page.cpuPageNumber+1 }&limit=${ page.cpuPageSize }"
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