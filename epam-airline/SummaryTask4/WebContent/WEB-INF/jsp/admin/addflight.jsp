<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<html>

<c:set var="title" value="addflight" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>

<body>
	<table id="main-container">

		<%@ include file="/WEB-INF/jspf/header.jspf"%>

		<tr>
			<td class="content">
				<%-- CONTENT --%>
				<form id="Action" action="controller" method="POST">
					<input type="hidden" id="command" name="command" value="saveflight" />
					<table id="addupdate">
						<tr>
							<td><fmt:message key="addflight.addupdate.number" /></td>
							<td><input name="number"
								value="${sessionScope.validateflight.number}" /></td>
							<td style="color: #FF0000">${sessionScope.validateflight.numberError}</td>
						</tr>
						<tr>
							<td><fmt:message key="addflight.addupdate.plain" /></td>
							<td><select name="idplains">
									<option>
										<fmt:message key="addflight.addupdate.chooseplain" />
										</opoption>
										<c:forEach var="bean" items="${plainslist}">
											<option value="${bean.id}">
												${bean.model}
												</opoption>
										</c:forEach>
							</select></td>
							<td style="color: #FF0000">${sessionScope.validateflight.idplainsError}</td>
						</tr>
						<tr>
							<td><fmt:message key="addflight.addupdate.from" /></td>
							<td><select name="fromwhence">
									<option>
										<fmt:message key="addflight.addupdate.chooseairport" />
										</opoption>
										<c:forEach var="bean" items="${airportlist}">
											<option value="${bean.id}">
												${bean.name} ${bean.country}
												</opoption>
										</c:forEach>
							</select></td>
							<td style="color: #FF0000">${sessionScope.validateflight.fromwhenceError}</td>
						</tr>
						<tr>
							<td><fmt:message key="addflight.addupdate.where" /></td>
							<td><select name="where">
									<option>
										<fmt:message key="addflight.addupdate.chooseairport" />
										</opoption>
										<c:forEach var="bean" items="${airportlist}">
											<option value="${bean.id}">
												${bean.name} ${bean.country}
												</opoption>
										</c:forEach>
							</select></td>
							<td style="color: #FF0000">${sessionScope.validateflight.whereError}</td>
						</tr>
						<tr>
							<td><fmt:message key="addflight.addupdate.deoarturedate" /></td>
							<td><input name="departuredate"
								value="${sessionScope.validateflight.departuredate}" /></td>
							<td style="color: #FF0000">${sessionScope.validateflight.departuredateError}</td>
						</tr>

						<tr>
							<td><fmt:message key="addflight.addupdate.choosestatus" /></td>
							<td><select name="status">
									<option value="">
										<fmt:message key="addflight.addupdate.choosestatus" />
									</option>
									<option value="no_brigade">no_brigade</option>
									<option value="waiting">waiting</option>
									<option value="check_in">check_in</option>
									<option value="departed">departed</option>
									<option value="delayed">delayed</option>
									<option value="arrived">arrived</option>
							</select></td>
							<td style="color: #FF0000">${sessionScope.validateflight.statusError}</td>
						</tr>
						<tr>
							<td><input type="submit"
								value="<fmt:message key="addflight.addupdate.saveflight"/>">
							</td>
						</tr>
					</table>
				</form> <%-- CONTENT --%>
			</td>
		</tr>

		<%@ include file="/WEB-INF/jspf/footer.jspf"%>

	</table>

</body>
</html>