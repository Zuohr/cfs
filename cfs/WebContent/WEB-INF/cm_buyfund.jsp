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
      
		<jsp:include page="cm_nav.jsp" />
        
        <div class="col-xs-12 col-sm-9">
            <div class="page-header">
              <h2>Buy Fund</h2>
            </div>
           
		<jsp:include page="pop_result.jsp" />
		<p style="color:red">${requestScope.errors["0"] }</p>
		<p style="color:red">${requestScope.errors["1"] }</p>
		 <h3>Current Balance:  $${requestScope.ava_bal} </h3>
            <table class="table table-striped">
              <thead>
                <tr>
                  <th class="header-status">
                  </th>
                  <th class="header-title">
                   	Fund Name
                  </th>
                  <th class="header-title">
                   	Ticker
                  </th>
                  <th class="header-title">
                   	Last Trading Day Price
                  </th>
                  <th class="header-ac">
                    Action
                  </th>
                </tr>
              </thead>
              <tbody>
              <c:forEach var="fund" items = "${requestScope.bf_list}">
                <tr>
                  <td>
                    <span class="None"> </span>
                  </td>
                  <td>
                  	<a href="#">${fund.fundName}</a>
                  </td>
                  <td>
                  	<a href="#">${fund.ticker}</a>
                  </td>
                  <td>
                  	$ ${fund.price}
                  </td>
                  <td>

               		<div class="btn-group">
                      <button type="button" class="btn btn-default" data-backdrop="static" data-keyboard="false"  data-toggle="modal" data-target="#${fund.fundId}">Buy</button>
                    </div>
                  </td>
                  
                                  <!-- Modal -->
                </tr>
                <div class="modal fade" id="${fund.fundId}" tabindex="-1"  role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                  <div class="modal-dialog">
                    <div class="modal-content">
                    <form class="form-horizontal" action = "buyfund.do" id="buyfund_form" method="post" role="form">
                      <div class="modal-header">     
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="myModalLabel">Buy Fund</h4>
                      </div>
                      <div class="modal-body">
                        <ul class="list-group">
                          <li class="list-group-item">Fund Name: ${fund.fundName}</li>
                          <li class="list-group-item">Avaliable Cash:${requestScope.ava_bal}</li>
                        </ul>
                      <div class="form-group">
                        <label for="inputEmail3" class="col-sm-2 control-label" >Amount:$</label>
                        <div class="col-sm-10">
                          <input type="number" class="form-control" name="deposit" style="width:80px;" id="inputAmount" >
							<p style="color:red">${requestScope.result }</p>
							<p style="color:red">${requestScope.errors["0"] }</p>
                          <input type="hidden" name="fundId" value="${fund.fundId}"> 
                        </div>
                      </div>
                      </div>
                      <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal" value="cancel">Close</button>
                        <button type="submit" class="btn btn-primary" data-backdrop="static" data-keyboard="false" name="buyfund_btn" value="submit">Place Order</button>
                      </div>

                      </form>
                      
                    </div><!-- /.modal-content -->
                  </div><!-- /.modal-dialog -->
                </div><!-- /.modal -->
                
                
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
