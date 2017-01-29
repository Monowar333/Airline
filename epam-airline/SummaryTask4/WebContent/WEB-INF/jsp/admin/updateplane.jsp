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
					<input type="hidden" id="command" name="command" value=saveupdateplane />
					<table id="addupdate">
						<tr>
							<td><fmt:message key="addplane.model" /></td>
							<td><input name="model" value="${sessionScope.validateupdateplane.model}" /></td>
							<td style="color: #FF0000">${sessionScope.validateupdateplane.modelError}</td>
						</tr>
						<tr>
							<td><fmt:message key="addplane.numberofseats" /></td>
							<td><input name="numberofseats"
								value="${sessionScope.validateupdateplane.numberofseats}" /></td>
							<td style="color: #FF0000">${sessionScope.validateupdateplane.numberofseatsError}</td>
						</tr>
						<tr>
							<td><fmt:message key="addplane.crew" /></td>
							<td><input name="crew"
								value="${sessionScope.validateupdateplane.crew}" /></td>
							<td style="color: #FF0000">${sessionScope.validateupdateplane.crewError}</td>
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