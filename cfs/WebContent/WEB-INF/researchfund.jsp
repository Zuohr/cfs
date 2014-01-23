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
					<div class="page-header">
						<h2>Fund A</h2>
					</div>

					<div style="width: 600px; height: 400px; border: 1px solid;"></div>
					<br>
					<form class="form-horizontal" role="form">
						<ul class="list-group">
							<li class="list-group-item">Current Balance</li>
						</ul>
						<div class="form-group">

							<label for="inputEmail3" class="col-sm-2 control-label">Order
								Amount</label>
							<div class="col-sm-10" style="width: 120px;">
								<input type="number" class="form-control" id="inputEmail3"
									placeholder="$">
							</div>
						</div>

						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" class="btn btn-primary">Place
									Order</button>
							</div>
						</div>
					</form>

				</div>
				<!--/span-->



			</div>
			<!--/row-->

<!-- start price list -->

		<div class="container">
			<div class="col-xs-12 col-sm-9">
				<h2>Fund price</h2>

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





		


	</div>

	<jsp:include page="footer.jsp" />
</body>
</html>
