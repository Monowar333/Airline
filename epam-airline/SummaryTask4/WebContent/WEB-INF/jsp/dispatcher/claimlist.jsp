<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@ include file="/WEB-INF/jspf/head.jspf"%>

<html>
<c:set var="title" value="claimlist" scope="page" />
<body>
	<table id="main-container">

		<%@ include file="/WEB-INF/jspf/header.jspf"%>

		<tr>
			<td class="content">
				<%-- CONTENT --%> <c:choose>
					<c:when test="${fn:length(claimlist) == 0}">No such user</c:when>

					<c:otherwise>
						<table id="addupdate" border="3">
							<thead>
								<tr>
									<td>id</td>
									<td><fmt:message
											key="claimlist.dispatcher.addupdate.adninistrator" /></td>
									<td><fmt:message
											key="claimlist.dispatcher.addupdate.daspatcher" /></td>
									<td><fmt:message
											key="claimlist.dispatcher.addupdate.datecreate" /></td>
									<td><fmt:message
											key="claimlist.dispatcher.addupdate.flight" /></td>
									<td><fmt:message
											key="claimlist.dispatcher.addupdate.status" /></td>
									<td><fmt:message
											key="claimlist.dispatcher.addupdate.description" /></td>
								</tr>
							</thead>


							<c:forEach var="bean" items="${claimlist}">
								<c:set var="color" value="#FFFF00" />
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
									<td style="background-color:${color}; color:#ffffff">${bean.id}</td>
									<td style="background-color:${color}; color:#ffffff">${bean.nameadministrator}
										${bean.sunameadministrator}</td>
									<td style="background-color:${color}; color:#ffffff">${bean.namedispatcher}
										${bean.sunamedispatcher}</td>
									<td style="background-color:${color}; color:#ffffff">${bean.date}</td>
									<td style="background-color:${color}; color:#ffffff">${bean.number}</td>
									<td style="background-color:${color}; color:#ffffff">${bean.status}</td>
									<td style="background-color:${color}; color:#ffffff">${bean.description}</td>
								</tr>

							</c:forEach>
						</table>
					</c:otherwise>
				</c:choose> <%-- CONTENT --%>
			</td>
		</tr>

		<%@ include file="/WEB-INF/jspf/footer.jspf"%>

	</table>

</body>
</html>