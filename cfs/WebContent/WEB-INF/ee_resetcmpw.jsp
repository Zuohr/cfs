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
						<h2>Reset Customer Password</h2>
					</div>
					<jsp:include page="pop_result.jsp" />
					<form class="form-horizontal" action="resetcmpw.do" method="POST">
						<!--  role="form"> -->
						<%-- <ul class="list-group">
							<li class="list-group-item">${ requestScope.FirstName },${ requestScope.LastName }</li>
						</ul> --%>

						<div class="form-group">
							<label for="inputPassword3" class="col-sm-2 control-label">User Name</label>
							<div class="col-sm-10" style="width: 300px">
								<input type="text" class="form-control" id="inputPassword3"
									placeholder="User Name" name="UserName" value="${requestScope.username}">
							</div>
						</div>
						
						<div class="form-group">
							<label for="inputPassword3" class="col-sm-2 control-label">New
								Password</label>
							<div class="col-sm-10" style="width: 300px">
								<input type="password" class="form-control" id="inputPassword3"
									placeholder="New Password" name="NewPassword">
							</div>
						</div>

						<div class="form-group">
							<label for="inputPassword3" class="col-sm-2 control-label">Confirm
								Password</label>
							<div class="col-sm-10" style="width: 300px">
								<input type="password" class="form-control" id="inputPassword3"
									placeholder="Re-enter Password" name="CheckPassword">
							</div>
						</div>

						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">

								<button type="submit" class="btn btn-primary"
									name="resetcmpw_btn" value="submit">Submit</button>
								<button type="submit" class="btn btn-default" name="resetcmpw_btn"
									value="cancel">Cancel</button>
							</div>
						</div>
						<input type="hidden" name="username" value=${requestScope.username }>
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