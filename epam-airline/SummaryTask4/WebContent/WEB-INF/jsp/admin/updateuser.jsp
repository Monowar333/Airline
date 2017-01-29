
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@ include file="/WEB-INF/jspf/head.jspf"%>

<c:set var="title" value="updateuser" scope="page" />
<body>
	<table id="main-container">

		<%@ include file="/WEB-INF/jspf/header.jspf"%>

		<tr>
			<td class="content">
				<%-- CONTENT --%>
				<form id="Action" action="controller" method="POST">
					<input type="hidden" id="command" name="command" value="updateuser" />
					<table id="addupdate">
						<tr>
							<td><fmt:message key="updateuser.addupdate.name" /></td>
							<td><input name="name"
								value="${sessionScope.updateuser.name}" /></td>
							<td style="color: #FF0000">${sessionScope.updateuser.nameError}</td>
						</tr>
						<tr>
							<td><fmt:message key="updateuser.addupdate.surname" /></td>
							<td><input name="suname"
								value="${sessionScope.updateuser.suname}" /></td>
							<td style="color: #FF0000">${sessionScope.updateuser.sunameError}</td>
						</tr>
						<tr>
							<td><fmt:message key="updateuser.addupdate.role" /></td>
							<td><select name="role">
									<option>
										choose role
										</opoption>
										<c:forEach var="bean" items="${rolelist}">
											<option value="${bean.id}">
												${bean.name}
												</opoption>
										</c:forEach>
							</select></td>
							<td style="color: #FF0000">${sessionScope.updateuser.roleIdError}</td>
						</tr>
						<tr>
							<td><fmt:message key="updateuser.addupdate.telephone" /></td>
							<td><input name="telephone"
								value="${sessionScope.updateuser.telephone}" /></td>
							<td style="color: #FF0000">${sessionScope.updateuser.telephoneError}</td>
						</tr>
						<tr>
							<td><fmt:message key="updateuser.addupdate.email" /></td>
							<td><input name="email"
								value="${sessionScope.updateuser.email}" /></td>
							<td style="color: #FF0000">${sessionScope.updateuser.emailError}</td>
						</tr>
						<tr>
							<td><fmt:message key="updateuser.addupdate.dateofbirthday" /></td>
							<td><input name="dateofbirth"
								value="${sessionScope.updateuser.dateofbirth}" /></td>
							<td style="color: #FF0000">${sessionScope.updateuser.dateofbirthError}</td>
						</tr>
						<tr>
							<td><fmt:message key="updateuser.addupdate.choosesex" /></td>
							<td><select name="sex">
									<option value="">
										<fmt:message key="updateuser.addupdate.choosesex" />
									</option>
									<option value="male">
										<fmt:message key="updateuser.addupdate.male" />
									</option>
									<option value="female">
										<fmt:message key="updateuser.addupdate.femele" />
									</option>
							</select></td>
							<td style="color: #FF0000">${sessionScope.updateuser.sexError}</td>
						</tr>
						<tr>
							<td><input type="submit"
								value="<fmt:message key="adduser.user.saveuser"/>"></td>
						</tr>
					</table>
				</form>
</body>
</html>