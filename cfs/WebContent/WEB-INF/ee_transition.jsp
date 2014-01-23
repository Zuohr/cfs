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
              <h2>Transition day</h2>
            </div>
            
       <form class="form-horizontal" id="transition_form"> <!-- role="form"> -->
		<input type="hidden" name="fund_num" value="${requestScope:fund_num }">
        <div class="form-group">
        <label for="inputFirstName3" class="col-sm-2 control-label">Date</label>
        <div class="col-sm-10" style="width:300px">
          <input type="text" class="form-control" id="inputFirstName3" name="date" placeholder="mm/dd/yyyy">
        </div>    
        </div>

		<c:forEach var="fund" items="${requestScope:fund_list }">
        <div class="form-group">
        <label for="inputFirstName3" class="col-sm-2 control-label">${fund.name }(${fund.symbol })</label>
        <div class="col-sm-10" style="width:300px">
          <input type="text" class="form-control" id="inputFirstName3" name="price_${fund.id }" placeholder="Price">
        </div>
        </div>
        </c:forEach>
        
        <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
          <button type="submit" form="transition_form" class="btn btn-primary" name="btn_transition" value="submit">Submit</button>
        
          <button type="submit" form="transition_form" class="btn btn-default" name="btn_transition" value="cancel">Cancel</button>
        </div>
        </div>
      </form>
      <!-- error message -->
      <p>${requestScope:result }</p>
      <c:forEach var="error" items="${requestScope:errors }">
      <p>${error }</p>
      </c:forEach>
      <!-- error message -->
       </div>
     
        </div><!--/span-->


      </div><!--/row-->

     


    </div><!--/.container-->
    
<!-- </div>  -->   
    
<jsp:include page="footer.jsp" />
  </body>
</html>
