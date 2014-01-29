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

				<jsp:include page="ee_nav.jsp" />

				<div class="col-xs-12 col-sm-9">
					<h2>Customer Information</h2>

					<table class="table">

						<tbody>
							<tr>
								<td><strong>Name</strong></td>
								<td>${requestScope.view_customer.firstname}&nbsp;
									${requestScope.view_customer.lastname}</td>
							</tr>
							<tr>
								<td><strong>Address</strong></td>
								<td>${requestScope.view_customer.addr1}<br />
									${requestScope.view_customer.addr2} <br />
									${requestScope.view_customer.city },
									${requestScope.view_customer.state },
									${requestScope.view_customer.zip }
								</td>
							</tr>
							<tr>
								<td><strong>Last Trading Day</strong></td>
								<td>${requestScope.lastTradingDay}</td>
							</tr>
							<tr>
								<td><strong>Cash Balance</strong></td>
								<td>${requestScope.cash}</td>
							</tr>
							<tr>
							<tr>
								<td><strong>Available Balance</strong></td>
								<td>${requestScope.balance }</td>
							</tr>
							</tr>


						</tbody>
					</table>



					<h3>Fund list</h3>

					<table class="table table-striped">
						<thead>
							<tr>
								<th class="header-date">Fund Name</th>
								<th class="header-ac">Share</th>
								<th class="header-ac">Available Share</th>
								<th class="header-ac">Last trading price</th>
								<th class="header-ac">Value</th>
							</tr>

						</thead>
						<tbody>
							<c:forEach var="pos" items="${requestScope.cus_position}">
								<tr>
									<td>${pos.fundName}</td>
									<td>${pos.share}</td>
									<td>${pos.shareBalance}</td>
									<td>${pos.lastPrice}</td>
									<td>${pos.value}</td>
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
