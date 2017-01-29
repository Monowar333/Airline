<%@page import="org.apache.taglibs.standard.lang.jstl.test.Bean1"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<c:set var="title" value="planelist" scope="page" />
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
				command.value = "planelist";
			} else if (($(':input:checked').length) > 0) {
				act.method = "post";
				command.value = "deleteplane";
			}
		} else if (button.id == 'b6') {
			if (($(':input:checked').length) > 1
					|| ($(':input:checked').length) == 0) {
				window.alert('please select just one row to be edited');
				command.value = "planelist";
				act.method = "get";
			} else if (($(':input:checked').length) == 1) {
				act.method = "get";
				command.value = "updateplane";

			}
		} else if (button.id == 'b7') {
			act.method = "get";
			command.value = "addplane";
		} 
		act.submit();
	}
</script>

<script>
	function link2(idb) {
		var act = document.getElementById("Action");
		var sort = document.getElementById("sort");
		var command = document.getElementById("command");
		command.value = "flightlist";
		sort.value = idb;
		act.method = "get";
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
					<input type="submit" id="b5"
						value="<fmt:message key="admin.planelist.button.deleteplane"/>"
						onclick="btn1(this.id)"/>
					 <input type="submit" id="b6" value="<fmt:message key="admin.planelist.button.updateplane"/>" 
						onclick="btn1(this.id)"/> 
						<input type="submit" id="b7" value="<fmt:message key="admin.planelist.button.addnewplane"/>"
						onclick="btn1(this.id)"/>
					<%-- CONTENT --%>

					<c:choose>
						<c:when test="${fn:length(planelist) == 0}">No such planes</c:when>
						<c:otherwise>
								
							<table id="planelist" border="3">
								<thead>
									<tr>
										<td></td>
										<td>id <br> 
										<td><fmt:message key="admin.planelist.model" /><br>
										<td><fmt:message key="admin.planelist.numberofseats" /><br>
										<td><fmt:message key="admin.planelist.crew" /><br>
									</tr>
								</thead>
								<c:forEach var="bean" items="${planelist}">
									<tr>
										<td><input type="checkbox" name="check"
											value="${bean.id}" /></td>
										<c:set var="color" value="#00FF00" />
										<td>${bean.id}</td>
										<td>${bean.model}</td>
										<td>${bean.numberofseats}</td>
										<td>${bean.crew}</td>
									</tr>

								</c:forEach>
							</table>
						</c:otherwise>
					</c:choose>
					<%-- CONTENT --%>

			</form>
	</table>

	<%-- CONTENT --%>
	</td>
	</tr>

	<%@ include file="/WEB-INF/jspf/footer.jspf"%>

	</table>

</body>
</html>