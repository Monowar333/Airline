<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<html>

<c:set var="title" value="addclaim" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>

<body>
	<table id="main-container">

		<%@ include file="/WEB-INF/jspf/header.jspf"%>

		<tr>
			<td class="content">
				<%-- CONTENT --%>
				<form id="Action" action="controller" method="POST">
					<input type="hidden" id="idflight" name="idflight"
						value="${idflight}" /> <input type="hidden" id="command"
						name="command" value="savenewclaim" />

					<table id="addupdate">
						<tr>
							<td><fmt:message key="addclaim.addupdate.datecreate" /></td>
							<td><input name="date"
								value="${sessionScope.validatenewclaim.date}"
								disabled="disabled" /></td>
						</tr>
						<tr>
							<td><fmt:message key="addclaim.addupdate.status" /></td>
							<td><input name="status" value="new" disabled="disabled" /></td>
						</tr>
						<tr>
							<td><fmt:message key="addclaim.addupdate.adninistrator" /></td>
							<td><select name="adminlist">
									<option value="">
										choose administrator
										</opoption>
										<c:forEach var="bean" items="${adminlist}">
											<option value="${bean.id}">
												${bean.name} ${bean.suname}
												</opoption>
										</c:forEach>
							</select></td>
							<td style="color: #FF0000">${sessionScope.validatenewclaim.idadministratorError}</td>
						</tr>
						<tr>
							<td><fmt:message key="addclaim.addupdate.description" /></td>
							<td><textarea rows="10" cols="45" name="description"></textarea></td>
							<td style="color: #FF0000">${sessionScope.validatenewclaim.descriptionError}</td>
						</tr>
						<tr>
							<td><input type="submit" id="b5" value="seve"
								onclick="btn1(this.id)"></td>
						</tr>

					</table>

					<%-- CONTENT --%>
			</td>
		</tr>

		<%@ include file="/WEB-INF/jspf/footer.jspf"%>

	</table>

</body>
</html>