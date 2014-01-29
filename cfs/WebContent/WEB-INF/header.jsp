<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


    <div class="navbar navbar-fixed-top navbar-inverse"><!--  role="navigation"> -->
    
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <c:choose>
         <c:when test="${requestScope.header_type eq'Employee'}">
          <a class="navbar-brand" href="emplviewcmlist.do">Carnegie Finance</a>
          </c:when>
         <c:when test="${requestScope.header_type eq'Customer'}">
          <a class="navbar-brand" href="cmviewacct.do">Carnegie Finance</a>
          </c:when>
         <c:when test="${requestScope.header_type eq'None'}">
          <a class="navbar-brand" href="#">Carnegie Finance</a>
          </c:when>
          </c:choose>
        </div>
        <div class="collapse navbar-collapse">

         <c:choose>
         <c:when test="${requestScope.header_type eq'Employee'}">
     		<p class="navbar-text navbar-right"><a href="logout.do" class="navbar-link">Logout</a></p>
          <p class="navbar-text navbar-right">Welcome! ${requestScope.header_type}: ${requestScope.header_name}</p>
          </c:when>
			<c:when test="${requestScope.header_type eq'Customer'}">
    		<p class="navbar-text navbar-right"><a href="logout.do" class="navbar-link">Logout</a></p>
          <p class="navbar-text navbar-right">Welcome! ${requestScope.header_type}: <a href="cmviewacct.do" class="navbar-link">${requestScope.header_name}</a></p>			
          </c:when>
          </c:choose>
        </div><!-- /.nav-collapse -->
      </div><!-- /.container -->
    </div><!-- /.navbar -->
