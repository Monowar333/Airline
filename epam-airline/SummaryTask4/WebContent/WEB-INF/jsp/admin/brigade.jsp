<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:set var="title" value="brigadeList" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>

<body>
	<table id="main-container">

		<%@ include file="/WEB-INF/jspf/header.jspf"%>

		<tr>

			<td class="content">
				<%-- CONTENT --%> 
					<table id="addupdate" border="3">
							<thead>
								<tr>
									<td>id</td>
									<td><fmt:message key="dispatcher.brigade.idflight" /></td>
									<td><fmt:message key="dispatcher.brigade.namesurname" /></td>
									<td><fmt:message key="dispatcher.brigade.role" /></td>
								</tr>
							</thead>


							<c:forEach var="bean" items="${flightbrigadelist}">

								<tr>
									<td>${bean.id}</td>
									<td>${bean.idFligts}</td>
									<td>${bean.name} ${bean.suname}</td>
									<td>${rolelist.get(bean.role-1).name}</td>
								</tr>

							</c:forEach>
						</table>
			</td>
		</tr>

		<%@ include file="/WEB-INF/jspf/footer.jspf"%>

	</table>

</body>
</html>