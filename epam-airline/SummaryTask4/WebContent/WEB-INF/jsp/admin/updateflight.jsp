<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@ include file="/WEB-INF/jspf/head.jspf"%>

<c:set var="title" value="updateflight" scope="page" />
<body>
	<table id="main-container">

		<%@ include file="/WEB-INF/jspf/header.jspf"%>

		<tr>
			<td class="content">
				<%-- CONTENT --%>
				<form id="Action" action="controller" method="POST">
					<input type="hidden" id="command" name="command"
						value="saveupdateflight" />
					<table id="addupdate">
						<tr>
							<td><fmt:message key="updateflight.addupdate.number" /></td>
							<td><input name="number"
								value="${sessionScope.validateupdateflight.number}" /></td>
							<td style="color: #FF0000">${sessionScope.validateupdateflight.numberError}</td>
						</tr>
						<tr>
							<td><fmt:message key="updateflight.addupdate.plains" /></td>
							<td><select name="idplains">
									<option>
										${sessionScope.validateupdateflight.idplains}
										</opoption>
										<c:forEach var="bean" items="${plainslist}">
											<option value="${bean.id}">
												${bean.model}
												</opoption>
										</c:forEach>
							</select></td>
							<td style="color: #FF0000">${sessionScope.validateupdateflight.idplainsError}</td>
						</tr>
						<tr>
							<td><fmt:message key="updateflight.addupdate.from" /></td>
							<td><select name="fromwhence">
									<option>
										${sessionScope.validateupdateflight.fromwhence}
										</opoption>
										<c:forEach var="bean" items="${airportlist}">
											<option value="${bean.id}">
												${bean.name} ${bean.country}
												</opoption>
										</c:forEach>
							</select></td>
							<td style="color: #FF0000">${sessionScope.validateupdateflight.fromwhenceError}</td>
						</tr>
						<tr>
							<td><fmt:message key="updateflight.addupdate.where" /></td>
							<td><select name="where">
									<option>
										${sessionScope.validateupdateflight.where }
										</opoption>
										<c:forEach var="bean" items="${airportlist}">
											<option value="${bean.id}">
												${bean.name} ${bean.country}
												</opoption>
										</c:forEach>
							</select></td>
							<td style="color: #FF0000">${sessionScope.validateupdateflight.whereError}</td>
						</tr>
						<tr>
							<td><fmt:message key="updateflight.addupdate.departuredate" /></td>
							<td><input name="departuredate"
								value="${sessionScope.validateupdateflight.departuredate}" /></td>
							<td style="color: #FF0000">${sessionScope.validateupdateflight.departuredateError}</td>
						</tr>

						<tr>
							<td><fmt:message key="updateflight.addupdate.choosestatus" /></td>
							<td><select name="status">
									<option value="">
										<fmt:message key="updateflight.addupdate.choosestatus" />
									</option>
									<option value="no_brigade">no_brigade</option>
									<option value="waiting">waiting</option>
									<option value="check_in">check_in</option>
									<option value="departed">departed</option>
									<option value="delayed">delayed</option>
									<option value="arrived">arrived</option>
							</select></td>
							<td style="color: #FF0000">${sessionScope.validateupdateflight.statusError}</td>
						</tr>
						<tr>
							<td><input type="submit"
								value="<fmt:message key="updateflight.addupdate.saveupdateflight"/>">
							</td>
						</tr>
					</table>
				</form>
	</table>
	<%-- CONTENT --%>
	</td>
	</tr>

	<%@ include file="/WEB-INF/jspf/footer.jspf"%>

	</table>

</body>
</html>