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
						<h2>Sell Fund</h2>
					</div>
					<jsp:include page="pop_result.jsp" />
					<p style="color: red">${requestScope.errors["0"] }</p>
					<p style="color: red">${requestScope.errors["1"] }</p>
					<table class="table table-striped">
						<thead>
							<tr>
								<th class="header-status"></th>
								<th class="header-title">Fund Name</th>
								<th class="header-date">Shares</th>
								<th class="header-date">Avaliable Shares</th>
								<th class="header-ac">Action</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="plist" items="${requestScope.cus_position}">
								<tr>
									<td><span class="None"> </span></td>
									<td><a href="#">${plist.fundName}</a></td>
									<td>${plist.share}</td>
									<td>${plist.shareBalance}</td>
									<td>
										<div class="btn-group">
											<button type="button" class="btn btn-default"
												data-toggle="modal" data-target="#${plist.fundName}">Sell</button>
										</div>
									</td>
								</tr>

								<!-- Modal -->
								<div class="modal fade" id="${plist.fundName}" tabindex="-1"
									role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
									<div class="modal-dialog">
										<div class="modal-content">
											<form class="form-horizontal" action="sellfund.do"
												id="sellfund_form" method="post" role="form">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal"
														aria-hidden="true">&times;</button>
													<h4 class="modal-title" id="myModalLabel">Sell Fund:</h4>
												</div>


												<div class="modal-body">
													<ul class="list-group">
														<li class="list-group-item">Fund name :
															${plist.fundName}</li>
														<li class="list-group-item">Available share :
															${plist.shareBalance}</li>
													</ul>
													<div class="form-group">
														<label for="inputEmail3" class="col-sm-2 control-label">Amount:</label>
														<div class="col-sm-10">
															<input type="text" name="sellAmount" class="form-control"
																style="width: 80px;" id="inputAmount"> <input
																type="hidden" name="fundId" value="${plist.fundId}">
														</div>
													</div>
												</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-default"
														data-dismiss="modal">Close</button>
													<button type="submit" class="btn btn-primary"
														name="sellfund_btn" value="submit">Place Order</button>
												</div>
											</form>
										</div>
										<!-- /.modal-content -->
									</div>
									<!-- /.modal-dialog -->
								</div>
								<!-- /.modal -->


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
