<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar"> <!-- role="navigation"> -->
          <div class="list-group">
            <a href="emplviewcmlist.do" class="list-group-item ${ requestScope.nav_eeviewcmlist }">View Customer</a>
            <a href="emplchangepw.do" class="list-group-item ${ requestScope.nav_eechangepw }">Change My Password</a>
            <a href="createempl.do" class="list-group-item ${ requestScope.nav_eecreateempl }">Create Employee</a>
            <a href="createcm.do" class="list-group-item ${ requestScope.nav_eecreatecm }">Create Customer</a>
            <a href="createfund.do" class="list-group-item ${ requestScope.nav_eecreatefund }">Create Fund</a>
            <a href="transition.do" class="list-group-item ${ requestScope.nav_eetransition }">Transition Day</a>
          </div>
          
        </div><!--/span-->