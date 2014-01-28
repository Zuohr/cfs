<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="html_header.jsp" />
<body>
	<div id="wrap">
		<jsp:include page="header.jsp" />

<!-- start price list -->

		<div class="container">
			<div class="col-xs-12 col-sm-9">
				<h2>${requestScope.fund.name } (${requestScope.fund.symbol })</h2>

				<table class="table table-striped">
					<thead>
						<tr>
							<th class="header-title">Date</th>
							<th class="header-ac">Price</th>
						</tr>
					</thead>

					<tbody>
						<c:forEach var="record" items="${requestScope.records }">
							<tr>
								<td>${record.date }</td>
								<td>${record.price }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

			</div>
			<!--/span-->


		</div>
		<!--/row-->

<!-- end price list -->

		</div>
		<!--/.container-->

	<jsp:include page="footer.jsp" />
</body>
</html>
