<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
              <h2>Create New Employee</h2>
            </div>
			
		       <form class="form-horizontal" action="createempl.do" id="createee_form" method="post"><!--  role="form"> -->
			  <div class="form-group">
				<label for="inputFirstName3" class="col-sm-2 control-label">First Name</label>
				<div class="col-sm-10" style="width:300px">
				  <input type="text" class="form-control" id="inputFirstName3" name="firstName" placeholder="First Name">
				</div>
			  </div>
			  
			  <div class="form-group">
				<label for="inputLastName3" class="col-sm-2 control-label">Last Name</label>
				<div class="col-sm-10" style="width:300px">
				  <input type="text" class="form-control" id="inputLastName3" name="lastName" placeholder="Last Name">
				</div>
			  </div>
			  <div class="form-group">
				<label for="inputUserName3" class="col-sm-2 control-label">User Name</label>
				<div class="col-sm-10" style="width:300px">
				  <input type="text" class="form-control" id="inputUserName3" name="userName" placeholder="User Name">
				</div>
			  </div>

			  
			  
			  <div class="form-group">
				<label for="inputPassword3" class="col-sm-2 control-label">Password</label>
				<div class="col-sm-10" style="width:300px">
				  <input type="Password" class="form-control" id="inputPassword3" name="password" placeholder="Password">
				</div>
				</div>
				
				<div class="form-group">
				<label for="inputPassword3" class="col-sm-2 control-label">Check Password</label>
				<div class="col-sm-10" style="width:300px">
				  <input type="Password" class="form-control" id="inputPassword3" name="password2" placeholder="Re-enter Password">
				</div>
			  </div>
			  
			  <div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
				  		<button type="submit" class="btn btn-primary" name="createee_btn" value="submit"  form="createee_form">Submit</button>
				 	    <button type="submit" class="btn btn-default" name="createee_btn" value="cancel"  form="createee_form">Cancel</button>
				</div>
			  </div>
			</form>
		<p>${requestScope.result }</p>
		<p>${requestScope.errors["0"] }</p>
		<p>${requestScope.errors["1"] }</p>
		<p>${requestScope.errors["2"] }</p>
		<p>${requestScope.errors["3"] }</p>

	     </div>
        </div><!--/span-->


      </div><!--/row-->

     


    </div><!--/.container-->
    
</div>    
    
<jsp:include page="footer.jsp" />
  </body>
</html>
