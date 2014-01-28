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
						<h2>Request Check</h2>
					</div>

					<form class="form-horizontal" role="form" action="requestcheck.do" method="POST">
						<ul class="list-group">
							<li class="list-group-item">Available balance:${ requestScope.balance }</li>
						</ul>
						<div class="form-group">

							<label for="inputEmail3" class="col-sm-2 control-label">Request
								Amount</label>
							<div class="col-sm-10" style="width: 120px;">
								<input type="number" class="form-control" id="inputEmail3"
									placeholder="$" name="check" >
							</div>
						</div>

						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" class="btn btn-primary" name="requestcheck_btn" value="submit">Submit</button>
							</div>
						</div>
					</form>
					 <!-- error message -->
      <p>${requestScope.result }</p>
      <c:forEach var="error" items="${requestScope.errors }">
      <p>${errors }</p>
      </c:forEach>
      <!-- error message -->
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