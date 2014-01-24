<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="html_header.jsp" />
  <body>
<div id="wrap">
<jsp:include page="header.jsp" />
    <div class="container">

      <div class="row row-offcanvas row-offcanvas-right">
      
		<jsp:include page="cm_nav.jsp" />
		
		 <div class="col-xs-12 col-sm-9">
            <div class="page-header">
              <h2>Change Password</h2>
            </div>
			
		       <form class="form-horizontal" role="form" action="cmchangepw.do" method="POST">
			  <div class="form-group">
				<label for="inputOldPassword3" class="col-sm-2 control-label">Old Password</label>
				<div class="col-sm-10" style="width:300px">
				  <input type="Old Password" class="form-control" id="inputOldPassword3" name="OldPassword" placeholder="Old Password">
				   
				  
				</div>
			  </div>
			  
			  <div class="form-group">
				<label for="inputNewPassword3" class="col-sm-2 control-label">New Password</label>
				<div class="col-sm-10" style="width:300px">
				  <input type="NewPassword" class="form-control" id="inputNewPassword3" name="NewPassword" placeholder="New Password">
				</div>
				</div>
				
				<div class="form-group" >
				<label for="inputNewPassword3" class="col-sm-2 control-label">Check Password</label>
				<div class="col-sm-10" style="width:300px">
				  <input type="PasswordConfirm" class="form-control" id="inputNewPassword3" name="CheckPassword" placeholder="Re-enter Password">
				</div>
			  </div>
			  
			  <div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
				  				  
				  <button type="submit" class="btn btn-primary" name="cmchangepw_btn" value="submit" >Submit</button>
				  <button type="submit" class="btn btn-default" name="change_btn" value="cancel" >Cancel</button>
				  
				  
				  
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
	   
        </div><!--/span-->


      </div><!--/row-->

     


    </div><!--/.container-->
    
</div>    
    
	<jsp:include page="footer.jsp" />
  </body>
</html>
