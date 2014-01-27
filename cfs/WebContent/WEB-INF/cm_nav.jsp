<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar"> <!-- role="navigation"> -->
          <div class="list-group">
            <a href="cmviewacct.do" class="list-group-item ${ requestScope.nav_viewacct }">View Account</a>
            <a href="cmchangepw.do" class="list-group-item ${ requestScope.nav_chgpw }">Change Password</a>
            <a href="buyfund.do" class="list-group-item ${ requestScope.nav_buyfund }">Buy Fund</a>
            <a href="sellfund.do" class="list-group-item ${ requestScope.nav_sellfund }">Sell Fund</a>
            <a href="requestcheck.do" class="list-group-item ${ requestScope.nav_reqcheck }">Request Check</a>
            <a href="cmviewtranhistory.do" class="list-group-item ${ requestScope.nav_tranhistory }">Transactions History</a>
          </div>
          
        </div><!--/span-->