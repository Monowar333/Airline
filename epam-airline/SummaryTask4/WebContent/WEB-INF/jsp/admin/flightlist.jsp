<%@page import="org.apache.taglibs.standard.lang.jstl.test.Bean1"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<c:set var="title" value="flightlist" scope="page" />
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
				command.value = "flightlist";
			} else if (($(':input:checked').length) > 0) {
				act.method = "post";
				command.value = "deleteflights";
			}
		} else if (button.id == 'b6') {
			if (($(':input:checked').length) > 1
					|| ($(':input:checked').length) == 0) {
				window.alert('please select just one row to be edited');
				command.value = "flightlist";
				act.method = "get";
			} else if (($(':input:checked').length) == 1) {
				act.method = "get";
				command.value = "updateflight";

			}
		} else if (button.id == 'b7') {
			act.method = "get";
			command.value = "addflight";
		} else if (button.id == 'b9') {
			act.method = "get";
			command.value = "flightlist";
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
					<input type="hidden" id="sort" name="sort" value=""> <input
						type="submit" id="b5"
						value="<fmt:message key="admin.flightlist.button.deleteflights"/>"
						onclick="btn1(this.id)"> <input type="submit" id="b6"
						value="<fmt:message key="admin.flightlist.button.updateflights"/>"
						onclick="btn1(this.id)"> <input type="submit" id="b7"
						value="<fmt:message key="admin.flightlist.button.addnewflight"/>"
						onclick="btn1(this.id)">
					<%-- CONTENT --%>

					<c:choose>
						<c:when test="${fn:length(flightlist) == 0}">No such user</c:when>

						<c:otherwise>
							<table>
								<tr>
									<td><fmt:message key="ltableFlight.finnby"/></td>
									<td><select name="findBy">

											<option value="">
												<fmt:message key="admin.flightlist.chooseparametr" />
											</option>
											<option value="findBynumber">
												<fmt:message key="admin.flightlist.number" />
											</option>
											<option value="findfromCity">
												<fmt:message key="admin.flightlist.fromcity" />
											</option>
											<option value="findBfromCountry">
												<fmt:message key="admin.flightlist.fromCountry" />
											</option>
											<option value="findByAirportName">
												<fmt:message key="admin.flightlist.fromAirportName" />
											</option>
											<option value="findfromtoCity">
												<fmt:message key="admin.flightlist.fromToCity" />
											</option>
											<option value="findBfromtoCountry">
												<fmt:message key="admin.flightlist.fromTocountry" />
											</option>
											<option value="findByFromtoAirportName">
												<fmt:message key="admin.flightlist.fromToAirportName" />
											</option>
											<option value="findBydeparturedate">
												<fmt:message key="admin.flightlist.departuredate" />
											</option>
									</select></td>
									<td><input name="findvalue" value=""></td>
									<td><input type="submit" id="b9" value="find"
										onclick="btn1(this.id)"></td>
								</tr>
							</table>
							<table id="flightlist" border="3">
								<thead>
									<tr>
										<td></td>
										<td>id <br> <a href="#" id="byid"
											onclick="link2(this.id)"><img src="images/sort.png"
												width="20" height="20" border="0" alt="sort"></a></td>
										<td><fmt:message key="admin.flightlist.number" /><br>
											<a href="#" id="number" onclick="link2(this.id)"><img
												src="images/sort.png" width="20" height="20" border="0"
												alt="sort"></a></td>
										<td><fmt:message key="admin.flightlist.fromcity" /><br>
											<a href="#" id="byfromcity" onclick="link2(this.id)"><img
												src="images/sort.png" width="20" height="20" border="0"
												alt="sort"></a></td>
										<td><fmt:message key="admin.flightlist.fromCountry" /><br>
											<a href="#" id="byfromcountry" onclick="link2(this.id)"><img
												src="images/sort.png" width="20" height="20" border="0"
												alt="sort"></a></td>
										<td><fmt:message key="admin.flightlist.fromAirportName" /><br>
											<a href="#" id="byfromname" onclick="link2(this.id)"><img
												src="images/sort.png" width="20" height="20" border="0"
												alt="sort"></a></td>
										<td><fmt:message key="admin.flightlist.fromToCity" /><br>
											<a href="#" id="bywherecity" onclick="link2(this.id)"><img
												src="images/sort.png" width="20" height="20" border="0"
												alt="sort"></a></td>
										<td><fmt:message key="admin.flightlist.fromTocountry" /><br>
											<a href="#" id="bywherecountry" onclick="link2(this.id)"><img
												src="images/sort.png" width="20" height="20" border="0"
												alt="sort"></a></td>
										<td><fmt:message key="admin.flightlist.fromToAirportName" />
											<br> <a href="#" id="bywherename"
											onclick="link2(this.id)"><img src="images/sort.png"
												width="20" height="20" border="0" alt="sort"></a></td>
										<td><fmt:message key="admin.flightlist.departuredate" />
											<br> <a href="#" id="departuredate"
											onclick="link2(this.id)"><img src="images/sort.png"
												width="20" height="20" border="0" alt="sort"></a></td>
										<td><fmt:message key="admin.flightlist.plain" /><br>
											<a href="#" id="plains" onclick="link2(this.id)"><img
												src="images/sort.png" width="20" height="20" border="0"
												alt="sort"></a></td>
										<td><fmt:message key="admin.flightlist.status" /> <br>
											<a href="#" id="status" onclick="link2(this.id)"><img
												src="images/sort.png" width="20" height="20" border="0"
												alt="sort"></a></td>
									</tr>
								</thead>


								<c:forEach var="bean" items="${flightlist}">

									<tr>
										<td><input type="checkbox" name="check"
											value="${bean.id}" /></td>
										<c:set var="color" value="#00FF00" />
										<c:if test="${bean.status == 'waiting'}">
											<c:set var="color" value="#FFFF00" />
										</c:if>
										<c:if test="${bean.status == 'delayed' || bean.status == 'no_brigade' || bean.status == 'no_plane'}">
											<c:set var="color" value="#FF0000" />
										</c:if>
										<td>${bean.id}</td>
										<td>${bean.number}</td>
										<td>${bean.fromwhenceCity}</td>
										<td>${bean.fromwhenceCountry}</td>
										<td>${bean.fromwhenceName}</td>
										<td>${bean.whereCity}</td>
										<td>${bean.whereCountry}</td>
										<td>${bean.whereName}</td>
										<td>${bean.departuredate}</td>
										<td>${bean.idplains}</td>
										<td style="background-color:${color}; color:#ffffff">${bean.status}</td>
										<td style="background-color:${color}; color:#ffffff"><a
										href="controller?command=watchbrigadeadmin&id=${bean.id}"><fmt:message
												key="dispatcher.flightlist.watchbrigade" /></a></td>
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