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
                            <th>#</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Username</th>
                            <th>rest Password</th>

                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>1</td>
                            <td>Mark</td>
                            <td>Otto</td>
                            <td><a href="#">@mdo</a></td>
                            <td>
                                <div class="btn-group">
                                    <button type="button" class="btn btn-default" data-toggle="modal" data-target="#buyFund">Reset Password</button>
                                </div>
                            </td>

                        </tr>
                                                <tr>
                            <td>2</td>
                            <td>Jacob</td>
                            <td>Thornton</td>
                            <td><a href="#">@mdo</a></td>
                            <td>
                                <div class="btn-group">
                                    <button type="button" class="btn btn-default" data-toggle="modal" data-target="#buyFund">Reset Password</button>
                                </div>
                            </td>

                        </tr>
                        <tr>
                            <td>3</td>
                            <td>Larry the Bird</td>
                            <td>Jacob</td>
                            <td><a href="#">@mdo</a></td>
                            <td>
                                <div class="btn-group">
                                    <button type="button" class="btn btn-default" data-toggle="modal" data-target="#buyFund">Reset Password</button>
                                </div>
                            </td>

                        </tr>
                    </tbody>
                </table>
        </div><!--/span-->


      </div><!--/row-->

     


    </div><!--/.container-->
    
</div>    
    
<jsp:include page="footer.jsp" />
  </body>
</html>
