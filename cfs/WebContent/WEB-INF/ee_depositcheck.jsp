<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
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
					<div class="page-header">
						<h2>Deposit Check</h2>

					</div>
					<jsp:include page="pop_result.jsp" />
					<form class="form-horizontal" action="deposit.do" method="POST">
						<!--  role="form"> -->
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">User
								Name</label>
							<div class="col-sm-10" style="width: 150px;">
								<input type="text" class="form-control" placeholder="User name"
									name="username" value="${requestScope.username }">
							</div>
							<span class="label label-danger" style="position:relative; top:10px;">${requestScope.errors["0"] }</span>
						</div>
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">Amount</label>
							<div class="col-sm-10" style="width: 200px;">
								<input type="text" class="form-control"
									placeholder="$" name="deposit">
							</div>
							<span class="label label-danger" style="position:relative; top:10px;">${requestScope.errors["1"] }</span>
						</div>

						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" class="btn btn-primary" name="deposit_btn"
									value="submit">Submit</button>
								<button type="submit" class="btn btn-default" name="deposit_btn"
									value="cancel">Cancel</button>
							</div>
						</div>
					</form>
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