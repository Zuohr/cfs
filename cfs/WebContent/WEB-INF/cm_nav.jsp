<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" style="margin-top:40px;"> <!-- role="navigation"> -->
          <div class="list-group">
            <a href="cmviewacct.do" class="list-group-item ${ requestScope.nav_cmviewacct }">View Account</a>
            <a href="cmchangepw.do" class="list-group-item ${ requestScope.nav_cmchgpw }">Change Password</a>
            <a href="buyfund.do" class="list-group-item ${ requestScope.nav_cmbuyfund }">Buy Fund</a>
            <a href="sellfund.do" class="list-group-item ${ requestScope.nav_cmsellfund }">Sell Fund</a>
            <a href="requestcheck.do" class="list-group-item ${ requestScope.nav_cmreqcheck }">Request Check</a>
            <a href="cmviewtranhistory.do" class="list-group-item ${ requestScope.nav_cmtranhistory }">Transactions History</a>
          </div>
          
        </div><!--/span-->