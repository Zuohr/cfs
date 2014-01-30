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
              <h2>Create New Customer</h2>
            </div>
			<jsp:include page="pop_result.jsp" />
		       <form class="form-horizontal" action="createcm.do" id="createcm_form" method="post"><!--  role="form"> -->
			  <div class="form-group">
				<label for="inputFirstName3" class="col-sm-2 control-label">First Name</label>
				<div class="col-sm-10" style="width:300px">
				  <input type="text" class="form-control" id="inputFirstName3" name="firstName" placeholder="First Name">
				</div>
				<span class="label label-danger" style="position:relative; top:10px;">${requestScope.errors["0"] }</span>
			  </div>
			  
			  <div class="form-group">
				<label for="inputLastName3" class="col-sm-2 control-label">Last Name</label>
				<div class="col-sm-10" style="width:300px">
				  <input type="text" class="form-control" id="inputLastName3" name="lastName" placeholder="Last Name">
				</div>
				<span class="label label-danger" style="position:relative; top:10px;">${requestScope.errors["1"] }</span>
			  </div>
			  <div class="form-group">
				<label for="inputUserName3" class="col-sm-2 control-label">User Name</label>
				<div class="col-sm-10" style="width:300px">
				  <input type="text" class="form-control" id="inputUserName3" name="userName" placeholder="User Name">
				</div>
				<span class="label label-danger" style="position:relative; top:10px;">${requestScope.errors["2"] }</span>
			  </div>
			  
			  <div class="form-group">
				<label for="inputUserName3" class="col-sm-2 control-label">Address1</label>
				<div class="col-sm-10" style="width:300px">
				  <input type="text" class="form-control" id="inputUserName3" name="addr1" placeholder="Address1" >
				</div>
				<span class="label label-danger" style="position:relative; top:10px;">${requestScope.errors["3"] }</span>
			  </div>
			  
			   <div class="form-group">
				<label for="inputUserName3" class="col-sm-2 control-label">Address2</label>
				<div class="col-sm-10" style="width:300px">
				  <input type="text" class="form-control" id="inputUserName3" name="addr2" placeholder="Address2">
				</div>
			  </div>
			  
			  <div class="form-group">
				<label for="inputUserName3" class="col-sm-2 control-label">City</label>
				<div class="col-sm-10" style="width:300px">
				  <input type="text" class="form-control" id="inputUserName3" name="city" placeholder="City">
				</div>
				<span class="label label-danger" style="position:relative; top:10px;">${requestScope.errors["4"] }</span>
			  </div>
			  
			  <div class="form-group">
				<label for="inputUserName3" class="col-sm-2 control-label">State</label>
				<div class="col-sm-10" style="width:300px">
				  <input type="text" class="form-control" id="inputUserName3" name="state" placeholder="State">
				</div>
			  </div>
			  
			  <div class="form-group">
				<label for="inputUserName3" class="col-sm-2 control-label">Zip Code</label>
				<div class="col-sm-10" style="width:300px">
				  <input type="text" class="form-control" id="inputUserName3" name="zip" placeholder="5 digit zip code">
				</div>
				<span class="label label-danger" style="position:relative; top:10px;">${requestScope.errors["5"] }</span>
			  </div>

			  <div class="form-group">
				<label for="inputPassword3" class="col-sm-2 control-label">Password</label>
				<div class="col-sm-10" style="width:300px">
				  <input type="Password" class="form-control" id="inputPassword3" name="password" placeholder="Password">
				</div>
				<span class="label label-danger" style="position:relative; top:10px;">${requestScope.errors["6"] }</span>
				</div>
				
				<div class="form-group">
				<label for="inputPassword3" class="col-sm-2 control-label">Confirm Password</label>
				<div class="col-sm-10" style="width:300px">
				  <input type="Password" class="form-control" id="inputPassword3" name="password2" placeholder="Re-enter Password">
				</div>
			  </div>
			  
			  <div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
				  		<button type="submit" class="btn btn-primary" name="createcm_btn" value="submit"  form="createcm_form">Submit</button>
				 	    <button type="submit" class="btn btn-default" name="createcm_btn" value="cancel"  form="createcm_form">Cancel</button>
				</div>
			  </div>
			</form>
	     </div>
        </div><!--/span-->


      </div><!--/row-->

     


    </div><!--/.container-->
    
<jsp:include page="footer.jsp" />
  </body>
</html>
