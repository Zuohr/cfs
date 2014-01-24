<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    
    <title>CFS Login</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/signin.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy this line! -->
    <!--[if lt IE 9]><script src="../../docs-assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
  </head>

  <body background="img/shutterstock_51832540.jpg">


    <br />
    <div class="container">
      <h1 style="text-align:center; color:#fff">Carnegie Financial Service</h1>
      <br />
      <div class="tab-content">

      <ul class="nav nav-pills" id="myTab" style="width:300px; margin-left:auto; margin-right:auto;">
  <li class="active"><a href="#as_employee" data-toggle="tab">I am an employee</a></li>
  <li><a href="#as_customer" data-toggle="tab">I am a customer</a></li>
  
  		<p>${requestScope.result }</p>
		<p>${requestScope.errors["0"] }</p>
		<p>${requestScope.errors["1"] }</p>
</ul>

<!-- <div class="tab-content"> -->
  <div class="tab-pane active" id="as_employee">
		       <form class="form-horizontal" action="empllogin.do" id="eelogin_form" method="post"><!--  role="form"> -->
       
        <input type="text" class="form-control" placeholder="Email address" name="userName" required autofocus>
        <br>
        <input type="password" class="form-control" placeholder="Password" name = "password" required>
        
        <button class="btn btn-lg btn-primary btn-block" name = "submit" value="employee" type="submit">Sign in as employee</button>
      </form>
  </div>
  <div class="tab-pane" id="as_customer">
		       <form class="form-horizontal" action="cmlogin.do" id="cmlogin_form" method="post"><!--  role="form"> -->
       
        <input type="text" class="form-control" name="userName" placeholder="Email address" required autofocus>
        <br>
        <input type="password" class="form-control" name = "password" placeholder="Password" required>
        
        <button class="btn btn-lg btn-primary btn-block"  name="submit" value="customer" type="submit">Sign in as customer</button>
      </form>
      
  </div>

</div>

<script>
  $(function () {
    $('#myTab a:last').tab('show')
  })
</script>

      <!-- <form class="form-signin" role="form">
        <h2 class="form-signin-heading">Please sign in</h2>
        <input type="text" class="form-control" placeholder="Email address" required autofocus>
        <input type="password" class="form-control" placeholder="Password" required>
        
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
      </form> -->

    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>