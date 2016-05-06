<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="target" required="true" type="java.lang.String"
	description="The target of the page"%>
<%@ attribute name="link" required="true" type="java.lang.String"
	description="The link of the page"%>
<%@ attribute name="className" required="false" type="java.lang.String"
	description="The name of the class of the parameter"%>
<%@ attribute name="limit" required="false" type="java.lang.String"
	description="The limit of computers per page"%>
<%@ attribute name="numPage" required="false" type="java.lang.String"
	description="The number of the actual page"%>
<%@ attribute name="id" required="false" type="java.lang.String"
	description="The id of the computer to edit"%>
<%@ attribute name="nameOrder" required="false" type="java.lang.String"
	description="The order of the column name"%>
<%@ attribute name="introducedOrder" required="false"
	type="java.lang.String"
	description="The order of the column introduced"%>
<%@ attribute name="discontinuedOrder" required="false"
	type="java.lang.String"
	description="The order of the column discontinued"%>
<%@ attribute name="companyOrder" required="false"
	type="java.lang.String" description="The order of the column company"%>
<%@ attribute name="search" required="false" type="java.lang.String"
	description="The searching box value"%>


<c:choose>

	<c:when test="${ target != '#' }">
		<c:choose>

			<c:when test="${ not empty id }">
				<a href="${ target }?id=${ id }" class="${ className }"><c:out
						value="${ link }" /></a>
			</c:when>

			<c:otherwise>
				<c:if test="${ not empty limit }">
					<c:set var="vlimit" value="limit=${ limit }" />
				</c:if>
				<c:choose>
					<c:when test="${ empty numPage }">
						<c:set var="vnumPage" value=""></c:set>
					</c:when>
					<c:when test="${ not empty numPage and empty limit }">
						<c:set var="vnumPage" value="numPage=${ numPage }" />
					</c:when>
					<c:otherwise>
						<c:set var="vnumPage" value="&numPage=${ numPage }" />
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${ empty nameOrder }">
						<c:set var="vnameOrder" value=""></c:set>
					</c:when>
					<c:when
						test="${ not empty nameOrder and empty limit and empty numPage}">
						<c:set var="vnameOrder" value="nameOrder=${ nameOrder }" />
					</c:when>
					<c:otherwise>
						<c:set var="vnameOrder" value="&nameOrder=${ nameOrder }" />
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${ empty introducedOrder }">
						<c:set var="vintroducedOrder" value=""></c:set>
					</c:when>
					<c:when
						test="${ not empty introducedOrder and empty limit and empty numPage and empty nameOrder}">
						<c:set var="vintroducedOrder"
							value="introducedOrder=${ introducedOrder }" />
					</c:when>
					<c:otherwise>
						<c:set var="vintroducedOrder"
							value="&introducedOrder=${ introducedOrder }" />
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${ empty discontinuedOrder }">
						<c:set var="vdiscontinuedOrder" value=""></c:set>
					</c:when>
					<c:when
						test="${ not empty discontinuedOrder and empty limit and empty numPage and empty nameOrder and empty introducedOrder}">
						<c:set var="vdiscontinuedOrder"
							value="discontinuedOrder=${ discontinuedOrder }" />
					</c:when>
					<c:otherwise>
						<c:set var="vdiscontinuedOrder"
							value="&discontinuedOrder=${ discontinuedOrder }" />
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${ empty companyOrder }">
						<c:set var="vcompanyOrder" value=""></c:set>
					</c:when>
					<c:when
						test="${ not empty companyOrder and empty limit and empty numPage and empty nameOrder and empty introducedOrder and empty discontinuedOrder}">
						<c:set var="vcompanyOrder" value="companyOrder=${ companyOrder }" />
					</c:when>
					<c:otherwise>
						<c:set var="vcompanyOrder" value="&companyOrder=${ companyOrder }" />
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${ empty search }">
						<c:set var="vsearch" value=""></c:set>
					</c:when>
					<c:when
						test="${ not empty companyOrder and empty limit and empty numPage and empty nameOrder and empty introducedOrder and empty discontinuedOrder and empty companyOrder}">
						<c:set var="vsearch" value="search=${ search }" />
					</c:when>
					<c:otherwise>
						<c:set var="vsearch" value="&search=${ search }" />
					</c:otherwise>
				</c:choose>
				<a
					href="${ target }?${ vlimit }${ vnumPage }${ vnameOrder }${ vintroducedOrder }${ vdiscontinuedOrder }${ vcompanyOrder }${ vsearch }"
					class="${ className }"> <c:out value="${ link }" />
				</a>
			</c:otherwise>

		</c:choose>
	</c:when>

	<c:otherwise>
		<a href="#"><c:out value="${ link }"></c:out></a>
	</c:otherwise>

</c:choose>
