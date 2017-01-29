<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<html>

<c:set var="title" value="adduser" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<body>
	<table id="main-container">

		<%@ include file="/WEB-INF/jspf/header.jspf"%>

		<tr>
			<td class="content">
				<%-- CONTENT --%>
				<form id="Action" action="controller" method="POST">
					<input type="hidden" id="command" name="command" value="saveuser" />
					<table id="addupdate">
						<tr>
							<td><fmt:message key="adduser.user.name" /></td>
							<td><input name="name" value="${sessionScope.useradd.name}" /></td>
							<td style="color: #FF0000">${sessionScope.useradd.nameError}</td>
						</tr>
						<tr>
							<td><fmt:message key="adduser.user.surname" /></td>
							<td><input name="suname"
								value="${sessionScope.useradd.suname}" /></td>
							<td style="color: #FF0000">${sessionScope.useradd.sunameError}</td>
						</tr>
						<tr>
							<td><fmt:message key="adduser.user.login" /></td>
							<td><input name="login"
								value="${sessionScope.useradd.login}" /></td>
							<td style="color: #FF0000">${sessionScope.useradd.loginError}</td>
						</tr>
						<tr>
							<td><fmt:message key="adduser.user.role" /></td>
							<td><select name="role">
									<option>
										<fmt:message key="adduser.user.chooserole" />
										</opoption>
										<c:forEach var="bean" items="${rolelist}">
											<option value="${bean.id}">
												${bean.name}
												</opoption>
										</c:forEach>
							</select></td>
							<td style="color: #FF0000">${sessionScope.useradd.roleIdError}</td>
						</tr>
						<tr>
							<td><fmt:message key="adduser.user.telephone" /></td>
							<td><input name="telephone"
								value="${sessionScope.useradd.telephone}" /></td>
							<td style="color: #FF0000">${sessionScope.useradd.telephoneError}</td>
						</tr>
						<tr>
							<td><fmt:message key="adduser.user.email" /></td>
							<td><input name="email"
								value="${sessionScope.useradd.email}" /></td>
							<td style="color: #FF0000">${sessionScope.useradd.emailError}</td>
						</tr>
						<tr>
							<td><fmt:message key="adduser.user.dateofbirthday" /></td>
							<td><input name="dateofbirth"
								value="${sessionScope.useradd.dateofbirth}" /></td>
							<td style="color: #FF0000">${sessionScope.useradd.dateofbirthError}</td>
						</tr>
						<tr>
							<td><fmt:message key="adduser.user.choosesex" /></td>
							<td><select name="sex">
									<option value="">
										<fmt:message key="adduser.user.choosesex" />
									</option>
									<option value="male">
										<fmt:message key="adduser.user.male" />
									</option>
									<option value="female">
										<fmt:message key="adduser.user.female" />
									</option>
							</select></td>
							<td style="color: #FF0000">${sessionScope.useradd.sexError}</td>
						</tr>
						<tr>
							<td><input type="submit"
								value="<fmt:message key="adduser.user.saveuser"/>"></td>
						</tr>
					</table>
				</form> <%-- CONTENT --%>
			</td>
		</tr>

		<%@ include file="/WEB-INF/jspf/footer.jspf"%>

	</table>

</body>
</html>