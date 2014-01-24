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

<div class="row">
    <div class="col-lg-6">
        <h2>Customer List</h2>
    </div><!-- /.col-lg-6 -->
<div class="col-lg-6">

    <div class="input-group">
        
        <input type="text" class="form-control">
            <span class="input-group-btn">
                <button class="btn btn-default" type="button">Go!</button>
            </span>
            </div><!-- /input-group -->
    <br>
</div><!-- /.col-lg-6 -->


                
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Username</th>
                            <th>Option</th>

                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="customer" items="${requestScope.customer_list}">
                        <tr>
                            <td>${customer.id}</td>
                            <td>${customer.firstname}</td>
                            <td>${customer.lastname}</td>
                            <td><a href="#">${customer.username}</a></td>
                            <td>
                            	&nbsp;&nbsp;&nbsp;
                            	<a href="#" class="btn btn-primary btn-xs" role="button">Reset Password</a>
								&nbsp;&nbsp;
								<a href="emplviewacct.do?usr=${customer.username}" class="btn btn-primary btn-xs" role="button">View Account</a>
								&nbsp;&nbsp;
								<a href="#" class="btn btn-primary btn-xs" role="button">Deposit</a>
								&nbsp;&nbsp;
								<a href="#" class="btn btn-primary btn-xs" role="button">transaction History</a>
                            </td>

                        </tr>
                     </c:forEach>
                    </tbody>
                </table>
        </div><!--/span-->


      </div><!--/row-->

     


    </div><!--/.container-->
    
</div>    
    
<jsp:include page="footer.jsp" />
  </body>
</html>
