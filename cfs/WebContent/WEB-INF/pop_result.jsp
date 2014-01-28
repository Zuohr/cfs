<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

			<c:choose>
			<c:when test="${requestScope.op_success!=null}">
			<div class="alert alert-success alert-dismissable">
			  <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
			  <strong>Congratulations!</strong> ${requestScope.op_success }
			</div>
			</c:when>
			<c:when test="${requestScope.op_fail!=null}">
			<div class="alert alert-danger alert-dismissable">
			  <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
			  <strong>Error!</strong> ${requestScope.op_fail }
			</div>
			</c:when>
			</c:choose>