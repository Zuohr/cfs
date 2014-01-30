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
              <h4>Last trading date: ${requestScope.last_day}</h4>
            </div>
       <jsp:include page="pop_result.jsp" />     
       <form class="form-horizontal" id="transition_form"> <!-- role="form"> -->
		<input type="hidden" name="fund_num" value="${requestScope.fund_num }">
        <div class="form-group">
        <label for="inputFirstName3" class="col-sm-2 control-label" style="width:300px">Date</label>
        <div class="col-sm-10" style="width:300px">
          <input type="text" class="form-control" id="inputFirstName3" name="date" placeholder="mm/dd/yyyy">
        </div> 
        <span class="label label-danger" style="position:relative; top:10px;">${requestScope.errors["0"] }</span>   
        </div>

		<c:forEach var="fund" items="${requestScope.fund_list }">
        <div class="form-group">
        <label for="inputFirstName3" class="col-sm-2 control-label" style="width:300px">${fund.name} </label>
        <div class="col-sm-10" style="width:300px">
          <input type="text" class="form-control" id="inputFirstName3" name="price_${fund.id }" placeholder="Price">
        </div>
        <span class="label label-danger" style="position:relative; top:10px;">${requestScope.errors["1"] }</span>
        </div>
        </c:forEach>
        
        <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
          <button type="submit" form="transition_form" class="btn btn-primary" name="btn_transition" value="submit">Submit</button>
        
          <button type="submit" form="transition_form" class="btn btn-default" name="btn_transition" value="cancel">Cancel</button>
        </div>
        </div>
      </form>

       </div>
     
        </div><!--/span-->


      </div><!--/row-->

     


    </div><!--/.container-->
    
<!-- </div>  -->   
    
<jsp:include page="footer.jsp" />
  </body>
</html>
