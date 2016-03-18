<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="mylib" tagdir="/WEB-INF/tags"%>

<%@ attribute name="pageNumber" required="true" type="java.lang.String"
	description="The current page"%>
<%@ attribute name="pageSize" required="true" type="java.lang.String"
	description="The number of elements per page"%>
<%@ attribute name="totalPages" required="true" type="java.lang.String"
	description="The total number of pages"%>

<ul class="pagination">
	<c:if test="${ pageNumber gt 1 }">
		<li><a
			href="/computer-database/dashboard?numPage=${ pageNumber-1 }"
			aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
		</a></li>
	</c:if>
	<c:if test="${ pageNumber gt 1 }">
		<li><mylib:link link="1" target="/computer-database/dashboard"
				numPage="1">
			</mylib:link></li>
	</c:if>

	<c:if test="${ pageNumber gt 6 }">
		<li><mylib:link link="${ pageNumber-5 }"
				target="/computer-database/dashboard"
				numPage="${ pageNumber-5 }">
			</mylib:link></li>
	</c:if>
	<c:if test="${ pageNumber gt 3 }">
		<li><mylib:link link="${ pageNumber-2 }"
				target="/computer-database/dashboard"
				numPage="${ pageNumber-2 }">
			</mylib:link></li>
	</c:if>
	<c:if test="${ pageNumber gt 2 }">
		<li><mylib:link link="${ pageNumber-1 }"
				target="/computer-database/dashboard"
				numPage="${ pageNumber-1 }">
			</mylib:link></li>
	</c:if>

	<li><mylib:link link="${ pageNumber }" target="#">
		</mylib:link></li>

	<c:if test="${ pageNumber lt totalPages-2 }">
		<li><mylib:link link="${ pageNumber+1 }"
				target="/computer-database/dashboard"
				numPage="${ pageNumber+1 }">
			</mylib:link></li>
	</c:if>
	<c:if test="${ pageNumber lt totalPages-3 }">
		<li><mylib:link link="${ pageNumber+2 }"
				target="/computer-database/dashboard"
				numPage="${ pageNumber+2 }">
			</mylib:link></li>
	</c:if>
	<c:if test="${ pageNumber lt totalPages-6 }">
		<li><mylib:link link="${ pageNumber+5 }"
				target="/computer-database/dashboard"
				numPage="${ pageNumber+5 }">
			</mylib:link></li>
	</c:if>

	<c:if test="${ pageNumber lt totalPages }">
		<li><mylib:link link="${ totalPages }"
				target="/computer-database/dashboard" numPage="${ totalPages }">
			</mylib:link></li>
	</c:if>
	<c:if test="${ pageNumber lt totalPages }">
		<li><a
			href="/computer-database/dashboard?numPage=${ pageNumber+1 }"
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