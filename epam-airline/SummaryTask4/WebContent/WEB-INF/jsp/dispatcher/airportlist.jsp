<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<html>
<%@ include file="/WEB-INF/jspf/head.jspf"%>


<c:set var="title" value="airportlist" scope="page" />

<script>
	function link1(idb) {
		var act = document.getElementById("Action");
		var command = document.getElementById("command");
		var idairport = document.getElementById("idairport");
		var changestaus = document.getElementById("changestaus");
		var select = document.getElementById("status" + idb);
		act.method = "post";
		command.value = "changestatusairport";
		changestaus.value = select.value;
		idairport.value = idb;
		act.submit();
	}
</script>
<body>
	<table id="main-container">

		<%@ include file="/WEB-INF/jspf/header.jspf"%>

		<tr>
			<td class="content">
				<form id="Action" action="controller" method="POST">
					<input type="hidden" id="command" name="command" value="default" />
					<input type="hidden" id="idairport" name="idairport" value="default" />
					<input type="hidden" id="changestaus" name="changestaus"
						value="default" />
				</form>
				<c:choose>
					<c:when test="${fn:length(airportlist) == 0}">No such user</c:when>
					<c:otherwise>
						<table id="addupdate" border="3">
							<thead>
								<tr>
									<td>id</td>
									<td><fmt:message
											key="claimlist.dispatcher.airportlist.addapdate.country" /></td>
									<td><fmt:message
											key="claimlist.dispatcher.airportlist.addapdate.city" /></td>
									<td><fmt:message
											key="claimlist.dispatcher.airportlist.addapdate.airportname" /></td>
									<td><fmt:message
											key="claimlist.dispatcher.airportlist.addapdate.iatacode" /></td>
									<td><fmt:message
											key="claimlist.dispatcher.addupdate.status" /></td>
								</tr>
							</thead>


							<c:forEach var="bean" items="${airportlist}">
								<c:set var="color" value="#FFFF00" />
								<c:if test="${bean.status == 'open'}">
									<c:set var="color" value="#00FF00" />
									<c:set var="disabled" value="disabled" />
									<c:set var="link" value="" />
								</c:if>
								<c:if test="${bean.status == 'close'}">
									<c:set var="color" value="#FF0000" />
									<c:set var="disabled" value="disabled" />
									<c:set var="link" value="" />
								</c:if>
								<tr>
									<td style="background-color:${color}; color:#ffffff">${bean.id}</td>
									<td style="background-color:${color}; color:#ffffff">${bean.country}</td>
									<td style="background-color:${color}; color:#ffffff">${bean.city}</td>
									<td style="background-color:${color}; color:#ffffff">${bean.name}</td>
									<td style="background-color:${color}; color:#ffffff">${bean.iatacode}</td>
									<td style="background-color:${color}; color:#ffffff">
									<select
										id="status${bean.id}" name="status${bean.id}">
											<option value=${bean.status}>${bean.status}</option>
											<option value="open">open</option>
											<option value="close">close</option>
									</select>
									</td>
									<td style="background-color:${color}; color:#ffffff">
									<a href="#" id="${bean.id}" onclick="link1(this.id)"><fmt:message
												key="claimlist.dispatcher.addupdate.statusairport" /></a>
									</td>
				
				
								</tr>

							</c:forEach>
						</table>
							<h1 style="text-align: center;"><a href ="controller?command=listairport&page=previous"> < </a> <a href="controller?command=listairport&page=next"> > </a></h1>
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
	
		<%@ include file="/WEB-INF/jspf/footer.jspf"%>

	</table>

</body>
</html>