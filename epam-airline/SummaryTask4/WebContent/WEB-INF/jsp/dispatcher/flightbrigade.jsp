<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.0.0.js"></script>
<script>
	function btn1(idb) {
		var button = document.getElementById(idb);
		var act = document.getElementById("Action");
		var command = document.getElementById("command");

		if (button.id == 'b4') {
			act.method = "get";
			command.value = "addclaim";
		} else if (button.id == 'b5') {
			command.value = "saveflightbrigade";
		}
		act.submit();
	}
</script>
<script>
	function link1(idb) {
		var act = document.getElementById("Action");
		var command = document.getElementById("command");
		var idbrigadeuser = document.getElementById("idbrigadeuser");
		act.method = "post";
		command.value = "deletebrigade";
		idbrigadeuser.value = idb;
		act.submit();
	}
</script>
<script>
	function link2(idb,nameselect) {
		var act = document.getElementById("Action");
		var command = document.getElementById("command");
		var idbrigadeuser = document.getElementById("idbrigadeuser");
		var select = document.getElementById(idb);
		act.method = "post";
		command.value = "updatebrigade";
		idbrigadeuser.value = select.value;
		idflight.value = idb;
		act.submit();
	}
</script>

<c:set var="title" value="flightbrigade" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf"%>

<body>
	<table id="main-container">

		<%@ include file="/WEB-INF/jspf/header.jspf"%>

		<tr>

			<td class="content">
				<%-- CONTENT --%> <c:choose>
					<c:when test="${fn:length(flightbrigadelist) == 0}">No such flight brigade
							<form id="Action" action="controller" method="POST">
							<input type="hidden" id="idbrigadeuser" name="idbrigadeuser" value="" /> 
								<input type="hidden" id="idflight" name="idflight"value="${idflight}" />
								 <input type="hidden" id="command" name="command" value="default" /> 
								<input type="submit" id="b4" value="<fmt:message key="dispatcher.brigade.sendclaim" />" onclick="btn1(this.id)">


							<table id="addupdate">
								<tr>
									<td style="color: #FF0000">${sessionScope.addbrigarerror}</td>
								</tr>
								<tr>
									<td><fmt:message key="dispatcher.brigade.pilot" /></td>
									<td><select name="pilot">
											<option>
												<fmt:message key="dispatcher.brigade.choospilot" />
												</opoption>
												<c:forEach var="bean" items="${pilotlist}">
													<option value="${bean.id}">
														${bean.name} ${bean.suname}
														</opoption>
												</c:forEach>
									</select></td>
								</tr>
								<tr>
									<td><fmt:message key="dispatcher.brigade.radioman" /></td>
									<td><select name="radioman">
											<option>
												<fmt:message key="dispatcher.brigade.chooseradioman" />
												</opoption>
												<c:forEach var="bean" items="${radiomanlist}">
													<option value="${bean.id}">
														${bean.name} ${bean.suname}
														</opoption>
												</c:forEach>
									</select></td>
								</tr>
								<tr>
									<td><fmt:message key="dispatcher.brigade.navigator" /></td>
									<td><select name="navigator">
											<option>
												<fmt:message key="dispatcher.brigade.choosnavigator" />
												</opoption>
												<c:forEach var="bean" items="${navigatortlist}">
													<option value="${bean.id}">
														${bean.name} ${bean.suname}
														</opoption>
												</c:forEach>
									</select></td>
								</tr>
								<tr>
									<td><fmt:message key="dispatcher.brigade.stewardess" /></td>
									<td><select name="stewardess">
											<option>
												<fmt:message key="dispatcher.brigade.choosestewardess" />
												</opoption>
												<c:forEach var="bean" items="${stewaerdesslist}">
													<option value="${bean.id}">
														${bean.name} ${bean.suname}
														</opoption>
												</c:forEach>
									</select></td>
								</tr>
								<tr>
									<td><fmt:message key="dispatcher.brigade.stewardess" /></td>
									<td><select name="stewardess1">
											<option>
												<fmt:message key="dispatcher.brigade.choosestewardess" />
												</opoption>
												<c:forEach var="bean" items="${stewaerdesslist}">
													<option value="${bean.id}">
														${bean.name} ${bean.suname}
														</opoption>
												</c:forEach>
									</select></td>
								</tr>
								<tr>
									<td><fmt:message key="dispatcher.brigade.stewardess" /></td>
									<td><select name="stewardess2">
											<option>
												<fmt:message key="dispatcher.brigade.choosestewardess" />
												</opoption>
												<c:forEach var="bean" items="${stewaerdesslist}">
													<option value="${bean.id}">
														${bean.name} ${bean.suname}
														</opoption>
												</c:forEach>
									</select></td>
								</tr>
								<tr>
									<td><input type="submit" id="b5" value="seve"
										onclick="btn1(this.id)"></td>
								</tr>

							</table>

						</form>
					</c:when>
					<c:otherwise>
						<form id="Action" action="controller" method="POST">
							<input type="hidden" id="command" name="command" value="default" />
							<input type="hidden" id="idbrigadeuser" name="idbrigadeuser" value="" /> 
							<input type="hidden" id="idflight" name="idflight"value="${idflight}" /> 
							<input type="submit" id="b4" value="<fmt:message key="dispatcher.brigade.sendclaim" />" onclick="btn1(this.id)">
						</form>
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
									<td><a href="#" id="${bean.id}" onclick="link1(this.id)">
									<fmt:message key="dispatcher.brigade.deleteuserfrombrigade" /></a></td>
								</tr>

							</c:forEach>
						</table>
						</c:otherwise>
						</c:choose>
			</td>
		</tr>

		<%@ include file="/WEB-INF/jspf/footer.jspf"%>

	</table>

</body>
</html>