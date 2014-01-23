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
              <h2>Create New Fund</h2>
            </div>
			
		       <form class="form-horizontal" action="createfund.do" id="createfund_form" method="post"><!--  role="form"> -->
			  <div class="form-group">
				<label for="inputFundName3" class="col-sm-2 control-label">Fund Name</label>
				<div class="col-sm-10" style="width:300px">
				  <input type="text" class="form-control" id="inputFundName3" name="fundname" placeholder="Fund Name">
				</div>
			  </div>
			  
			  <div class="form-group">
				<label for="inputTicker3" class="col-sm-2 control-label">Ticker</label>
				<div class="col-sm-10" style="width:300px">
				  <input type="text" class="form-control" id="inputTicker3" name="ticker" placeholder="Ticker">
				</div>
			  </div>
			  
			  
			  
			  <div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
				  <button type="submit" class="btn btn-primary" name="createfund_btn" value="submit" form="createfund_form">Submit</button>
				  <button type="submit" class="btn btn-default" name="createfund_btn" value="cancel" form="createfund_form">Cancel</button>
				</div>
			  </div>
			</form>
		<!--  -->
		<p>${requestScope.result }</p>
		<p>${requestScope.errors["0"] }</p>
		<p>${requestScope.errors["1"] }</p>
		<!--  -->
	   
	     </div>
	   
        </div><!--/span-->


      </div><!--/row-->

     


    </div><!--/.container-->
    
<!-- </div> -->    
    
<jsp:include page="footer.jsp" />
  </body>
</html>
