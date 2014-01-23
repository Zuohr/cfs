<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<jsp:include page="html_header.jsp" />
<body>
	<div id="wrap">
		<jsp:include page="header.jsp" />

		<div class="container">
			<jsp:include page="cm_nav.jsp" />
			<div class="col-xs-12 col-sm-9">
				<h2>Transaction History</h2>

				<table class="table table-striped">
					<thead>
						<tr>
							<th class="header-title">Date</th>
							<th class="header-date">Operation</th>
							<th class="header-ac">Fund Name</th>
							<th class="header-ac">Number</th>
							<th class="header-ac">Price</th>
							<th class="header-ac">Amount</th>
							<th class="header-ac">Status</th>
						</tr>
					</thead>

					<tbody>
						<c:forEach var="record" items="${requestScope.history }">
							<tr>
								<td>${record.date }</td>
								<td>${record.type }</td>
								<td>${record.fundname }</td>
								<%-- <td><a href="#">${record.fundname }</a></td> --%>
								<td>${record.share }</td>
								<td>${record.price }</td>
								<td>${record.dollar }</td>
								<td>${record.state }</td>
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

	<jsp:include page="footer.jsp" />
</body>
</html>
