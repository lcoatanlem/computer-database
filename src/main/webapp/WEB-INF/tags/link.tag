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

<c:choose>

	<c:when test="${ target != '#' }">

		<c:choose>
		
			<c:when test="${ not empty id }">
				<a href="${ target }?id=${ id }" class="${ className }"><c:out
						value="${ link }"></c:out></a>
			</c:when>
			
			<c:when test="${ empty numPage }">
			
				<c:choose>
				
					<c:when test="${ empty limit }">
						<a href="${ target }" class="${ className }"><c:out
								value="${ link }"></c:out></a>
					</c:when>
					
					<c:otherwise>
						<a href="${ target }?limit=${ limit }" class="${ className }"><c:out
								value="${ link }"></c:out></a>
					</c:otherwise>
					
				</c:choose>
				
			</c:when>
			
			<c:when test="${ empty limit }">

				<c:choose>
				
					<c:when test="${ empty numPage }">
						<a href="${ target }" class="${ className }"><c:out
								value="${ link }"></c:out></a>
					</c:when>
					
					<c:otherwise>
						<a href="${ target }?numPage=${ numPage }" class="${ className }"><c:out
								value="${ link }"></c:out></a>
					</c:otherwise>
					
				</c:choose>

			</c:when>
			
			<c:otherwise>
				<a href="${ target }?numPage=${ numPage }&limit=${ limit }"
					class="${ className }"><c:out value="${ link }"></c:out></a>
			</c:otherwise>

		</c:choose>

	</c:when>
	
	<c:otherwise>
		<a href="#"><c:out value="${ link }"></c:out></a>
	</c:otherwise>
	
</c:choose>
