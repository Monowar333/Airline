<%@page import="org.apache.taglibs.standard.lang.jstl.test.Bean1"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %> 
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>
<c:set var="title" value="startpage" scope="page" />

<head>
  <script type="text/javascript" src="https://code.jquery.com/jquery-3.0.0.js"></script>       
<script> 

			function link2(idb){
				    var act = document.getElementById("Action");
				    var sort = document.getElementById("sort");
				    sort.value = idb;
				    act.method = "get";
				    act.submit();
			}			
</script>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body>
	<table id="main-container">

		<%@ include file="/WEB-INF/jspf/header.jspf" %>
			
		<tr>
			<td class="content">
			<%-- CONTENT --%>	
							<form id = "Action" action=""  method = "GET">		
							<input type = "hidden" id = "sort" name = "sort" value = "">
							<table>
							<tr>
										<td><fmt:message key="ltableFlight.finnby"/></td>
										<td>
											<select name = "findBy">
											<option value = ""><fmt:message key="ltableFlight.chooseparametr"/></option>
											<option value = "findBynumber"><fmt:message key="ltableFlight.number"/></option>
											<option value = "findfromCity"><fmt:message key="ltableFlight.fromcity"/></option>
											<option value = "findBfromCountry"><fmt:message key="ltableFlight.fromCountry"/></option>
											<option value = "findByAirportName"><fmt:message key="ltableFlight.fromAirportName"/></option>
											<option value = "findfromtoCity"><fmt:message key="ltableFlight.fromToCity"/></option>
											<option value = "findBfromtoCountry"><fmt:message key="ltableFlight.fromTocountry"/></option>
											<option value = "findByFromtoAirportName"><fmt:message key="ltableFlight.fromToAirportName"/></option>
											<option value = "findBydeparturedate"><fmt:message key="ltableFlight.departuredate"/></option>
											</select>
										</td>
										<td>
											<input name = "findvalue" value = "">
										</td>
										<td>
											<input type = "submit" name = "" value = "find">
										</td>
							</tr>	
							</table>
							</form>
							<c:choose>
							<c:when test="${fn:length(flightlist) == 0}">No such user</c:when>
							
							<c:otherwise>
							<table id="flightlist" border="3">
								<thead>
									<tr>
										<td>
										id <br>
										<a href = "#" id = "flight.id" onclick = "link2(this.id)"><img src="images/sort.png" width="20"  height="20" border="0" alt="sort"></a></td>
										<td>
										<fmt:message key="ltableFlight.number"/><br>
										<a href = "#" id = "flight.number" onclick = "link2(this.id)"><img src="images/sort.png" width="20"  height="20" border="0" alt="sort"></a>
										</td>
										<td>
										<fmt:message key="ltableFlight.fromcity"/><br>
										<a href = "#" id = "u.city" onclick = "link2(this.id)"><img src="images/sort.png" width="20"  height="20" border="0" alt="sort"></a></td>
										<td>
										<fmt:message key="ltableFlight.fromCountry"/><br>
										<a href = "#" id = "u.country" onclick = "link2(this.id)"><img src="images/sort.png" width="20"  height="20" border="0" alt="sort"></a></td>
										<td>
										<fmt:message key="ltableFlight.fromAirportName"/><br>
										<a href = "#" id = "u.name" onclick = "link2(this.id)"><img src="images/sort.png" width="20"  height="20" border="0" alt="sort"></a></td>
										<td>
										<fmt:message key="ltableFlight.fromToCity"/><br>
										<a href = "#" id = "r.city" onclick = "link2(this.id)"><img src="images/sort.png" width="20"  height="20" border="0" alt="sort"></a></td>
										<td>
										<fmt:message key="ltableFlight.fromTocountry"/><br>
										<a href = "#" id = "r.country" onclick = "link2(this.id)"><img src="images/sort.png" width="20"  height="20" border="0" alt="sort"></a></td>
										<td>
										<fmt:message key="ltableFlight.fromToAirportName"/> <br>
										<a href = "#" id = "r.name" onclick = "link2(this.id)"><img src="images/sort.png" width="20"  height="20" border="0" alt="sort"></a></td>
										<td>
										<fmt:message key="ltableFlight.departuredate"/> <br>
										<a href = "#" id = "flight.departuredate" onclick = "link2(this.id)"><img src="images/sort.png" width="20"  height="20" border="0" alt="sort"></a>
										</td>
										<td>
										<fmt:message key="ltableFlight.plain"/><br>
										<a href = "#" id = "s.name" onclick = "link2(this.id)"><img src="images/sort.png" width="20"  height="20" border="0" alt="sort"></a>
										</td>
										<td>
										<fmt:message key="flight.status"/> <br>
										<a href = "#" id = "status" onclick = "link2(this.id)"><img src="images/sort.png" width="20"  height="20" border="0" alt="sort"></a>
										</td>
									</tr>
								</thead>
				
				
						<c:forEach var="bean" items="${flightlist}">
									<c:set var="color" value="#00FF00"/>
									  <c:if test="${bean.status == 'waiting'}" >
			                                <c:set var="color" value="#FFFF00"/>
			                           </c:if>
										<c:if test="${bean.status == 'delayed' || bean.status == 'no_brigade' || bean.status == 'no_plane'}">
											<c:set var="color" value="#FF0000" />
										</c:if>
									<tr>
										<td >${bean.id}</td>
										<td >${bean.number}</td>
										<td >${bean.fromwhenceCity} </td>
										<td >${bean.fromwhenceCountry} </td>
										<td >${bean.fromwhenceName} </td>
										<td >${bean.whereCity} </td>
										<td >${bean.whereCountry} </td>
										<td >${bean.whereName} </td>
										<td >${bean.departuredate} </td>
										<td >${bean.idplains} </td>
										<td style="background-color:${color}; color:#ffffff">${bean.status}</td>
									</tr>
				
								</c:forEach>			
							</table>
							</c:otherwise>
							</c:choose>									

			</table>	
				<%-- CONTENT --%>
			</td>
		</tr>
		
		<%@ include file="/WEB-INF/jspf/footer.jspf" %>
		
	</table>
		
</body>
</html>