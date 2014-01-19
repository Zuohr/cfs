<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
			
		       <form class="form-horizontal" role="form">
			  <div class="form-group">
				<label for="inputEmail3" class="col-sm-2 control-label">Old Password</label>
				<div class="col-sm-10" style="width:300px">
				  <input type="Old Password" class="form-control" id="inputEmail3" placeholder="Old Password">
				</div>
			  </div>
			  
			  <div class="form-group">
				<label for="inputPassword3" class="col-sm-2 control-label">New Password</label>
				<div class="col-sm-10" style="width:300px">
				  <input type="New Password" class="form-control" id="inputPassword3" placeholder="New Password">
				</div>
				</div>
				
				<div class="form-group">
				<label for="inputPassword3" class="col-sm-2 control-label">Check Password</label>
				<div class="col-sm-10" style="width:300px">
				  <input type="Password Confirm" class="form-control" id="inputPassword3" placeholder="Re-enter Password">
				</div>
			  </div>
			  
			  <div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
				  <button type="submit" class="btn btn-primary">Submit</button>
				
				
				
				  <button type="cancel" class="btn btn-default">cancel</button>
				</div>
			  </div>
			</form>
		
	   
	     </div>
	   
        </div><!--/span-->


      </div><!--/row-->

     


    </div><!--/.container-->
    
</div>    
<jsp:include page="footer.jsp" />
  </body>
</html>