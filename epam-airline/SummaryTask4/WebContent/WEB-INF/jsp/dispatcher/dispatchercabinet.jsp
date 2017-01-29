
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@ taglib prefix="ctu" uri="/WEB-INF/customTagsLibrary"%>
<script>
	function link1(idb) {
		var act = document.getElementById("Action");
		var command = document.getElementById("command");
		var idflight = document.getElementById("idflight");
		var changestaus = document.getElementById("changestaus");
		var select = document.getElementById("status" + idb);
		act.method = "post";
		command.value = "changestatusflight";
		changestaus.value = select.value;
		idflight.value = idb;
		act.submit();
	}
</script>
<script>
	function link2(idb) {
		var act = document.getElementById("Action");
		var sort = document.getElementById("sort");
		var command = document.getElementById("command");
		command.value = "dispatchercabinet";
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

					<table>
						<tr>
							<td><fmt:message key="ltableFlight.finnby"/></td>
							<td><select name="findBy">
									<option value="">
										<fmt:message key="dispatcher.flightlist.chooseparametr" />
									</option>
									<option value="findBynumber">
										<fmt:message key="dispatcher.flightlist.number" />
									</option>
									<option value="findfromCity">
										<fmt:message key="dispatcher.flightlist.fromcity" />
									</option>
									<option value="findBfromCountry">
										<fmt:message key="dispatcher.flightlist.fromCountry" />
									</option>
									<option value="findByAirportName">
										<fmt:message key="dispatcher.flightlist.fromAirportName" />
									</option>
									<option value="findfromtoCity">
										<fmt:message key="dispatcher.flightlist.fromToCity" />
									</option>
									<option value="findBfromtoCountry">
										<fmt:message key="dispatcher.flightlist.fromTocountry" />
									</option>
									<option value="findByFromtoAirportName">
										<fmt:message key="dispatcher.flightlist.fromToAirportName" />
									</option>
									<option value="findBydeparturedate">
										<fmt:message key="dispatcher.flightlist.departuredate" />
									</option>
							</select> </select></td>
							<td><input name="findvalue" value=""></td>
							<td><input type="submit" name="" value="find"></td>
						</tr>
					</table>
				</form> <c:choose>
					<c:when test="${fn:length(flightlist) == 0}">No such user</c:when>

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
									<td style="background-color:${color}; color:#ffffff"><select
										id="status${bean.id}" name="status${bean.id}">
											<option value=${bean.status}>${bean.status}</option>
											<option value="waiting">waiting</option>
											<option value="check_in">check_in</option>
											<option value="departed">departed</option>
											<option value="delayed">delayed</option>
											<option value="arrived">arrived</option>
									</select></td>
									<td style="background-color:${color}; color:#ffffff"><a
										href="controller?command=watchbrigade&id=${bean.id}"><fmt:message
												key="dispatcher.flightlist.watchbrigade" /></a></td>
									<c:if test="${bean.status != 'no_brigade' && bean.status != 'no_plane'}">
										<td style="background-color:${color}; color:#ffffff"><a
										href="#" id="${bean.id}" onclick="link1(this.id)"><fmt:message
												key="dispatcher.flightlist.changestatusflight" /></a></td>
									</c:if>
									
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