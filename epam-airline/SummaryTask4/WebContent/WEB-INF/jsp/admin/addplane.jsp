<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<html>

<c:set var="title" value="addplane" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<body>
	<table id="main-container">

		<%@ include file="/WEB-INF/jspf/header.jspf"%>

		<tr>
			<td class="content">
				<%-- CONTENT --%>
				<form id="Action" action="controller" method="POST">
					<input type="hidden" id="command" name="command" value="savenewplane" />
					<table id="addupdate">
						<tr>
							<td><fmt:message key="addplane.model" /></td>
							<td><input name="model" value="${sessionScope.addplanevalidate.model}" /></td>
							<td style="color: #FF0000">${sessionScope.addplanevalidate.modelError}</td>
						</tr>
						<tr>
							<td><fmt:message key="addplane.numberofseats" /></td>
							<td><input name="numberofseats"
								value="${sessionScope.addplanevalidate.numberofseats}" /></td>
							<td style="color: #FF0000">${sessionScope.addplanevalidate.numberofseatsError}</td>
						</tr>
						<tr>
							<td><fmt:message key="addplane.crew" /></td>
							<td><input name="crew"
								value="${sessionScope.addplanevalidate.crew}" /></td>
							<td style="color: #FF0000">${sessionScope.addplanevalidate.crewError}</td>
						</tr>
						<tr>
							<td><input type="submit"
								value="<fmt:message key="addplane.saveplane"/>"></td>
						</tr>
					</table>
				</form> <%-- CONTENT --%>
			</td>
		</tr>

		<%@ include file="/WEB-INF/jspf/footer.jspf"%>

	</table>

</body>
</html>