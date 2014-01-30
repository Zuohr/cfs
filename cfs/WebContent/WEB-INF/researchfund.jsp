<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Flot Examples: Multiple Axes</title>
	<link href="css/examples.css" rel="stylesheet" type="text/css">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/offcanvas.css" rel="stylesheet">
	<!--[if lte IE 8]><script language="javascript" type="text/javascript" src="../../excanvas.min.js"></script><![endif]-->
	<script language="javascript" type="text/javascript" src="js/jquery.js"></script>
	<script language="javascript" type="text/javascript" src="js/jquery.flot.js"></script>
	<script language="javascript" type="text/javascript" src="js/jquery.flot.time.js"></script>
	<script type="text/javascript">

	$(function() {

		var oilprices = [
					<c:forEach var="record" items="${requestScope.records }">
		                 [${record.dateMilli },${record.price }],  
		             </c:forEach>
		                 
		                 ];



		function euroFormatter(v, axis) {
			return v.toFixed(axis.tickDecimals) + "€";
		}

		function doPlot(position) {
			$.plot("#placeholder", [
				{ data: oilprices,points: { show: true }, lines: { show: true }, label: "Fund price ($)" },
				
			], {
				xaxes: [ { mode: "time" } ],
				yaxes: [ { min: 0 }, {
					// align if we are to the right
					alignTicksWithAxis: position == "right" ? 1 : null,
					position: position,
					tickFormatter: euroFormatter
				} ],
				legend: { position: "sw" }
			});
		}

		doPlot("right");

		$("button").click(function () {
			doPlot($(this).text());
		});

		// Add the Flot version string to the footer

		$("#footer").prepend("Flot " + $.plot.version + " &ndash; ");
	});

	</script>
</head>

<body>

<div id="wrap">
		<jsp:include page="header.jsp" />
	<div class="container">
		<div class="col-xs-12 col-sm-9">
			<h2>${requestScope.fund.name } (${requestScope.fund.symbol })</h2>
	
			<div class="demo-container">
				<div id="placeholder" class="demo-placeholder"></div>
			</div>
			<table class="table table-striped">
				<thead>
					<tr>
						<th class="header-title">Date</th>
						<th class="header-ac" style="text-align:right; padding-right:240px;">Price</th>
					</tr>
				</thead>

				<tbody>
					<c:forEach var="record" items="${requestScope.records }">
						<tr>
							<td>${record.date }</td>
							<td style="text-align:right; padding-right:240px;">${record.price }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>

</body>
</html>
