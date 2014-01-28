<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<body>
	<jsp:include page="html_header.jsp" />
	<div id="wrap">
		<jsp:include page="header.jsp" />
		<div class="container">

			<div class="row row-offcanvas row-offcanvas-right">

				<jsp:include page="ee_nav.jsp" />

				<div class="col-xs-12 col-sm-9">
					<div class="page-header">
						<h2>Change Password</h2>
					</div>
					<jsp:include page="pop_result.jsp" />
					<form class="form-horizontal" action="emplchangepw.do"
						method="POST">
						<!--  role="form"> -->

						<div class="form-group">
							<label for="inputOldPassword3" class="col-sm-2 control-label">Old
								Password</label>
							<div class="col-sm-10" style="width: 300px">
								<input type="password" class="form-control"
									id="inputOldPassword3" name="OldPassword"
									placeholder="Old Password">
							</div>
						</div>

						<div class="form-group">
							<label for="inputNewPassword3" class="col-sm-2 control-label">New
								Password</label>
							<div class="col-sm-10" style="width: 300px">
								<input type="password" class="form-control"
									id="inputNewPassword3" name="NewPassword"
									placeholder="New Password">
							</div>
						</div>

						<div class="form-group">
							<label for="inputNewPassword3" class="col-sm-2 control-label">Confirm
								Password</label>
							<div class="col-sm-10" style="width: 300px">
								<input type="password" class="form-control"
									id="inputNewPassword3" name="CheckPassword"
									placeholder="Re-enter Password">
							</div>
						</div>

						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">

								<button type="submit" class="btn btn-primary"
									name="eechangepw_btn" value="submit">Submit</button>
								<button type="submit" class="btn btn-default" name="cancel_btn"
									value="cancel">Cancel</button>



							</div>
						</div>
					</form>
					<!-- error message -->
					<p>${requestScope.result }</p>
					<c:forEach var="error" items="${requestScope.errors }">
						<p>${error }</p>
					</c:forEach>
					<!-- error message -->

				</div>

			</div>
			<!--/span-->

		</div>
		<!--/row-->
	</div>
	<!--/.container-->

	<jsp:include page="footer.jsp" />
</body>
</html>
