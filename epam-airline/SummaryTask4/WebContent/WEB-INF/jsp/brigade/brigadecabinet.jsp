
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@ taglib prefix="ctu" uri="/WEB-INF/customTagsLibrary"%>

<script>
	function link2(idb) {
		var act = document.getElementById("Action");
		var sort = document.getElementById("sort");
		var command = document.getElementById("command");
		command.value = "brigadecabinet";
		sort.value = idb;
		act.method = "get";
		act.submit();
	}
</script>
<html>

<c:set var="title" value="daspatchercabinet" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>

<body>
	<table id="main-container">

		<%@ include file="/WEB-INF/jspf/header.jspf"%>

		<tr>
			<td class="content">
				<%-- CONTENT --%>
				<form id="Action" action="controller" method="GET">

					<input type="hidden" id="command" name="command" value="dispatchercabinet" />
					 <input type="hidden" id="idflight" name="idflight" value="default" />
					  <input type="hidden" id="sort"name="sort" value=""> 
					  <input type="hidden" id="changestaus" name="changestaus" value="default" />
					<ctu:showuser user="${sessionScope.user}" />
				 <c:choose>
					<c:when test="${fn:length(flightlist) == 0}">No such flight</c:when>

					<c:otherwise>

						<table id="flightlist" border="3">
							<thead>
								<tr>
									<td>id <br> <a href="#" id="byid"
										onclick="link2(this.id)"><img src="images/sort.png"
											width="20" height="20" border="0" alt="sort"></a></td>
									<td><fmt:message key="dispatcher.flightlist.number" /><br>
										<a href="#" id="number" onclick="link2(this.id)"><img
											src="images/sort.png" width="20" height="20" border="0"
											alt="sort"></a></td>
									<td><fmt:message key="dispatcher.flightlist.fromcity" /><br>
										<a href="#" id="byfromcity" onclick="link2(this.id)"><img
											src="images/sort.png" width="20" height="20" border="0"
											alt="sort"></a></td>
									<td><fmt:message key="dispatcher.flightlist.fromCountry" /><br>
										<a href="#" id="byfromcountry" onclick="link2(this.id)"><img
											src="images/sort.png" width="20" height="20" border="0"
											alt="sort"></a></td>
									<td><fmt:message
											key="dispatcher.flightlist.fromAirportName" /><br> <a
										href="#" id="byfromname" onclick="link2(this.id)"><img
											src="images/sort.png" width="20" height="20" border="0"
											alt="sort"></a></td>
									<td><fmt:message key="dispatcher.flightlist.fromToCity" /><br>
										<a href="#" id="bywherecity" onclick="link2(this.id)"><img
											src="images/sort.png" width="20" height="20" border="0"
											alt="sort"></a></td>
									<td><fmt:message key="dispatcher.flightlist.fromTocountry" /><br>
										<a href="#" id="bywherecountry" onclick="link2(this.id)"><img
											src="images/sort.png" width="20" height="20" border="0"
											alt="sort"></a></td>
									<td><fmt:message
											key="dispatcher.flightlist.fromToAirportName" /> <br> <a
										href="#" id="bywherename" onclick="link2(this.id)"><img
											src="images/sort.png" width="20" height="20" border="0"
											alt="sort"></a></td>
									<td><fmt:message key="dispatcher.flightlist.departuredate" />
										<br> <a href="#" id="departuredate"
										onclick="link2(this.id)"><img src="images/sort.png"
											width="20" height="20" border="0" alt="sort"></a></td>
									<td><fmt:message key="dispatcher.flightlist.plain" /><br>
										<a href="#" id="plains" onclick="link2(this.id)"><img
											src="images/sort.png" width="20" height="20" border="0"
											alt="sort"></a></td>
									<td><fmt:message key="dispatcher.flightlist.status" /> <br>
										<a href="#" id="status" onclick="link2(this.id)"><img
											src="images/sort.png" width="20" height="20" border="0"
											alt="sort"></a></td>
								</tr>
							</thead>


							<c:forEach var="bean" items="${flightlist}">
								<c:set var="color" value="#00FF00" />
								<c:if test="${bean.status == 'waiting' }">
									<c:set var="color" value="#FFFF00" />
								</c:if>
								<c:if test="${bean.status == 'delayed' || bean.status == 'no_brigade' || bean.status == 'no_plane'}">
									<c:set var="color" value="#FF0000" />
								</c:if>
								<tr>
									<td style="background-color:${color}; color:#ffffff">${bean.id}</td>
									<td style="background-color:${color}; color:#ffffff">${bean.number}</td>
									<td style="background-color:${color}; color:#ffffff">${bean.fromwhenceCity}
									</td>
									<td style="background-color:${color}; color:#ffffff">${bean.fromwhenceCountry}
									</td>
									<td style="background-color:${color}; color:#ffffff">${bean.fromwhenceName}
									</td>
									<td style="background-color:${color}; color:#ffffff">${bean.whereCity}
									</td>
									<td style="background-color:${color}; color:#ffffff">${bean.whereCountry}
									</td>
									<td style="background-color:${color}; color:#ffffff">${bean.whereName}
									</td>
									<td style="background-color:${color}; color:#ffffff">${bean.departuredate}
									</td>
									<td style="background-color:${color}; color:#ffffff">${bean.idplains}
									</td>
									<td style="background-color:${color}; color:#ffffff">${bean.status}</td>						
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