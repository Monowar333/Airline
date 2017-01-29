<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>


<c:set var="title" value="claimlist" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<html>

<script>
	function link1(idb) {
		var act = document.getElementById("Action");
		var command = document.getElementById("command");
		var idflight = document.getElementById("idclaim");
		var changestaus = document.getElementById("changestaus");
		var select = document.getElementById("status" + idb);
		act.method = "post";
		command.value = "changestatusclaim";
		changestaus.value = select.value;
		idflight.value = idb;
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
					<input type="hidden" id="idclaim" name="idclaim" value="default" />
					<input type="hidden" id="changestaus" name="changestaus"
						value="default" />
					<c:choose>
						<c:when test="${fn:length(claimlist) == 0}">No such user</c:when>

						<c:otherwise>
							<table id="addupdate" border="3">
								<thead>
									<tr>
										<td>id</td>
										<td><fmt:message key="claimlist.addupdate.adninistrator" /></td>
										<td><fmt:message key="claimlist.addupdate.daspatcher" /></td>
										<td><fmt:message key="claimlist.addupdate.datecreate" /></td>
										<td><fmt:message key="claimlist.addupdate.flight" /></td>
										<td><fmt:message key="claimlist.addupdate.status" /></td>
										<td><fmt:message key="claimlist.addupdate.description" /></td>
									</tr>
								</thead>
								<c:forEach var="bean" items="${claimlist}">
									<c:set var="color" value="#FFFF00" />
									<c:set var="disabled" value="" />
									<c:set var="link" value="change status" />
									<c:if test="${bean.status == 'executed'}">
										<c:set var="color" value="#00FF00" />
										<c:set var="disabled" value="disabled" />
										<c:set var="link" value="" />
									</c:if>
									<c:if test="${bean.status == 'rejected'}">
										<c:set var="color" value="#FF0000" />
										<c:set var="disabled" value="disabled" />
										<c:set var="link" value="" />
									</c:if>
									<tr>
									<tr>
										<td style="background-color:${color}; color:#ffffff">${bean.id}</td>
										<td style="background-color:${color}; color:#ffffff">${bean.nameadministrator}
											${bean.sunameadministrator}</td>
										<td style="background-color:${color}; color:#ffffff">${bean.namedispatcher}
											${bean.sunamedispatcher}</td>
										<td style="background-color:${color}; color:#ffffff">${bean.date}</td>
										<td style="background-color:${color}; color:#ffffff">${bean.number}</td>
										<td style="background-color:${color}; color:#ffffff"><select
											id="status${bean.id}" name="status${bean.id}" ${disabled}>
												<option value=${bean.status}>${bean.status}</option>
												<option value="executed">
													<fmt:message key="claimlist.addupdate.executed" />
												</option>
												<option value="rejected">
													<fmt:message key="claimlist.addupdate.rejected" />
												</option>
										</select></td>
										<td style="background-color:${color}; color:#ffffff">${bean.description}</td>
										<td style="background-color:${color}; color:#ffffff"><a
											href="#" id="${bean.id}" onclick="link1(this.id)">${link}</a></td>
									</tr>

								</c:forEach>
							</table>
						</c:otherwise>
					</c:choose>

					<%-- CONTENT --%>
			</td>
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