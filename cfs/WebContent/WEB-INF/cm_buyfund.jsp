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

            <table class="table table-striped">
              <thead>
                <tr>
                  <th class="header-status">
                  </th>
                  <th class="header-title">
                   	Fund Name
                  </th>
                  <th class="header-date">
                    Price
                  </th>
                  <th class="header-ac">
                    Action
                  </th>
                </tr>
              </thead>
              <tbody>
              <c:forEach var="list" items = "${requestScope.fundlist}">
                <tr>
                  <td>
                    <span class="None"> </span>
                  </td>
                  <td><a href="#"><c:out value="${list.fundname}"/></a></td>
                  <td><c:out value="${list.fundprice}"/></td>
                  <td>
               		<div class="btn-group">
                      <button type="button" class="btn btn-default" data-toggle="modal" data-target="#buyFund">Buy</button>
                    </div>
                  </td>
                </tr>
              </c:forEach>
              </tbody>
            </table>

        </div><!--/span-->
        
                <!-- Modal -->
                <div class="modal fade" id="buyFund" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                  <div class="modal-dialog">
                    <div class="modal-content">
                    <form class="form-horizontal" role="form">
                      <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="myModalLabel">Buy Fund</h4>
                      </div>
                      <div class="modal-body">
                        <ul class="list-group">
                          <li class="list-group-item">Fund Name:</li>
                          <li class="list-group-item">Avaliable Cash:</li>
                        </ul>
                      <div class="form-group">
                        <label for="inputEmail3" class="col-sm-2 control-label" >Amount:$</label>
                        <div class="col-sm-10">
                          <input type="number" class="form-control" style="width:80px;" id="inputAmount" >
                        </div>
                      </div>
                      </div>
                      <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary">Place Order</button>
                      </div>
                      </form>
                    </div><!-- /.modal-content -->
                  </div><!-- /.modal-dialog -->
                </div><!-- /.modal -->

      </div><!--/row-->
    </div><!--/.container-->
</div>       
	<jsp:include page="footer.jsp" />
  </body>
</html>
