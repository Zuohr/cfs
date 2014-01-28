<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="html_header.jsp" />
<body>
	<div id="wrap">
<jsp:include page="header.jsp" />

		<div class="container">

			<div class="row row-offcanvas row-offcanvas-right">

				<jsp:include page="cm_nav.jsp" />

				<div class="col-xs-12 col-sm-9">
					<h2>Customer Information</h2>

					<table class="table">

						<tbody>
							<tr>
								<td>name</td>
								<td>
								${requestScope.view_customer.firstname} 
								&nbsp;
								${requestScope.view_customer.lastname}
								</td>
							</tr>
							<tr>
								<td>address</td>
								<td>
								${requestScope.view_customer.addr1}
								<br />
								${requestScope.view_customer.addr2}
								<br />
								${requestScope.view_customer.city }, ${requestScope.view_customer.state }, ${requestScope.view_customer.zip } 
								</td>
							</tr>
							<tr>
								<td>last trading day</td>
								<td>${requestScope.lastTradingDay}</td>
							</tr>
							<tr>
								<td>cash balance</td>
								<td>${requestScope.cash}</td>
							</tr>
							<tr>
								<td></td>
								<td></td>
							</tr>
						</tbody>
					</table>



					<h3>Customer's fund list</h3>

					<table class="table table-striped">
						<thead>
							<tr>
								<th class="header-status"></th>
								
								<th class="header-date">Fund Name</th>
								<th class="header-ac">Position</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach var="pos" items = "${requestScope.cus_position}">
							<tr>
								<td><span class="None"> </span></td>
								<td><a href="#">${pos.fundName}</a></td>
								<td>${pos.share}</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>

				</div>
				<!--/span-->


			</div>
			<!--/row-->

		</div>
		<!--/.container-->

	</div>

<jsp:include page="footer.jsp" />
</body>
</html>
