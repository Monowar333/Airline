<%@ page isErrorPage="true" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
	<table id="main-container">

		
			
		<tr>
			<td class="contenterror">
			<%-- CONTENT --%>
				<%-- this way we obtain an information about an exception (if it has been occurred) --%>
				<c:set var="code" value="${requestScope['javax.servlet.error.status_code']}"/>
				<c:set var="message" value="${requestScope['javax.servlet.error.message']}"/>

				
				<c:if test="${not empty code}">
					<h3>Error code: ${code}</h3>
				</c:if>			
				
				<c:if test="${not empty message}">
					<h2 class="error">Page not found </h2> <h3>${message}</h3>
				</c:if>	
				<c:choose>
						<c:when test="${userRole.name == 'administrator' }">
							<a href="controller?command=admincabinet"><fmt:message key="header.jspf.mycabinet"/></a> &nbsp;
						</c:when>
						<c:when test="${userRole.name == 'dispatcher'}">
							<a href="controller?command=dispatchercabinet"><fmt:message key="header.jspf.mycabinet"/></a> &nbsp;
						</c:when>
						<c:when test="${empty user}">
							<a href="startpage"><fmt:message key="header.jspf.startpage"/></a> &nbsp;
						</c:when>
				</c:choose>
			<%-- CONTENT --%>
			</td>
		</tr>


		
	</table>
</body>
</html>