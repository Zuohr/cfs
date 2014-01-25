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
              <h2>Deposit Check</h2>
        
            </div>

            <form class="form-horizontal" role="form" action="deposit.do" method="POST">
                <ul class="list-group">
                  <li class="list-group-item" >${ requestScope.FirstName },${ requestScope.LastName }</li>
                </ul>
              <div class="form-group" >

                <label for="inputEmail3" class="col-sm-2 control-label">Transact Cash</label>
                <div class="col-sm-10" style="width:120px;">
                  <input type="number" class="form-control"  placeholder="$" name="deposit" >
                </div>
              </div>

              <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                  <button type="submit" class="btn btn-primary" name="deposit_btn" value="submit">Submit</button>
                </div>
              </div>
            </form>
            <!-- error message -->
      <p>${requestScope.result }</p>
      <c:forEach var="error" items="${requestScope.errors }">
      <p>${error }</p>
      </c:forEach>
      <!-- error message -->
        </div><!--/span-->


      </div><!--/row-->

     


    </div><!--/.container-->
    
</div>    
    
    
<jsp:include page="footer.jsp" />
  </body>
</html>