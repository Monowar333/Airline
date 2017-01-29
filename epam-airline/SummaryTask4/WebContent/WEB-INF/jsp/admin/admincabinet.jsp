
<%@page import="org.apache.taglibs.standard.lang.jstl.test.Bean1"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@ taglib prefix="ctu" uri="/WEB-INF/customTagsLibrary"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<c:set var="title" value="admincabinet" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>


<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.0.0.js"></script>
<script>
	function btn1(idb) {
		var button = document.getElementById(idb);
		var act = document.getElementById("Action");
		var command = document.getElementById("command");

		if (button.id == 'b5') {
			if (($(':input:checked').length) == 0) {
				act.method = "get";
				window.alert('please select rows to be deleted');
				command.value = "admincabinet";
			} else if (($(':input:checked').length) > 0) {
				command.value = "deleteuser";
			}
		} else if (button.id == 'b6') {
			if (($(':input:checked').length) > 1
					|| ($(':input:checked').length) == 0) {
				window.alert('please select just one row to be edited');
				command.value = "admincabinet";
				act.method = "get";
			} else if (($(':input:checked').length) == 1) {
				act.method = "get";
				command.value = "updateuser";

			}
		} else if (button.id == 'b7') {
			act.method = "get";
			command.value = "adduser";
		}
		act.submit();
	}
</script>
<body>
	<table id="main-container">

		<%@ include file="/WEB-INF/jspf/header.jspf"%>

		<tr>
			<td class="content">
				<%-- CONTENT --%>
				<form id="Action" action="controller" method="POST">
					<input type="hidden" id="command" name="command" value="default" />
					<ctu:showuser user="${sessionScope.user}" />
					<c:choose>
						<c:when test="${fn:length(userlist) == 0}">No such user</c:when>

						<c:otherwise>
							<input type="submit" id="b5"
								value="<fmt:message key="cabinet.admin.button.deleteusers"/>"
								onclick="btn1(this.id)">
							<input type="submit" id="b6"
								value="<fmt:message key="cabinet.admin.button.ipdateusers"/>"
								onclick="btn1(this.id)">
							<input type="submit" id="b7"
								value="<fmt:message key="cabinet.admin.button.addnewuser"/>"
								onclick="btn1(this.id)">
							<table id="userlist" border="3">
								<thead>
									<tr>
										<td></td>
										<td>id</td>
										<td><fmt:message key="cabinet.admin.users.name" /></td>
										<td><fmt:message key="cabinet.admin.users.telephone" /></td>
										<td><fmt:message key="cabinet.admin.users.email" /></td>
										<td><fmt:message key="cabinet.admin.users.datebirthday" /></td>
										<td><fmt:message key="cabinet.admin.users.sex" /></td>
										<td><fmt:message key="cabinet.admin.users.role" /></td>
									</tr>
								</thead>


								<c:forEach var="bean" items="${userlist}">

									<tr>
										<td><input type="checkbox" name="check"
											value="${bean.id}" /></td>
										<td>${bean.id}</td>
										<td>${bean.name} ${bean.suname}</td>
										<td>${bean.telephone}</td>
										<td>${bean.email}</td>
										<td>${bean.dateofbirth}</td>
										<td>${bean.sex}</td>
										<td>${rolelist.get(bean.roleId-1).name}</td>
									</tr>

								</c:forEach>
							</table>
						</c:otherwise>
					</c:choose>
		</tr>
		</form>
	</table>
	<%-- CONTENT --%>
	</td>
	</tr>

	<%@ include file="/WEB-INF/jspf/footer.jspf"%>

	</table>

</body>
</html>