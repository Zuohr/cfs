<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="html_header.jsp" />
<body>
	<div id="wrap">
<jsp:include page="header.jsp" />-->

		<div class="container">

			<div class="row row-offcanvas row-offcanvas-right">

				<jsp:include page="cm_nav.jsp" />

				<div class="col-xs-12 col-sm-9">
					<h2>Customer Information</h2>

					<table class="table">

						<tbody>
							<tr>
								<td>name</td>
								<td>customer name</td>
							</tr>
							<tr>
								<td>address</td>
								<td>customer address</td>
							</tr>
							<tr>
								<td>last trading day</td>
								<td>last trading day</td>
							</tr>
							<tr>
								<td>cash balance</td>
								<td>balance</td>
							</tr>
							<tr>
								<td></td>
								<td></td>
							</tr>
						</tbody>
					</table>



					<h5>fund owned by the customer</h5>

					<table class="table table-striped">
						<thead>
							<tr>
								<th class="header-status"></th>
								<th class="header-title">#</th>
								<th class="header-date">Fund Name</th>
								<th class="header-ac">Price</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><span class="None"> </span></td>
								<td>1</td>
								<td><a href="#">Fund A</a></td>
								<td>20</td>
							</tr>
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
