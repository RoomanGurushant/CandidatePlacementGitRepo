<jsp:useBean id="mybean" class="portal.Error" scope="request"/>
<%mybean.doPost(request,response); %>
<!DOCTYPE html>
<html lang="en">
<head>

<title><%=mybean.AppName%> - <%=mybean.ClientName%></title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1" name="viewport" />

<%@include file="../Library/css.jsp"%>
</head>

<!-- END HEAD -->

<body class="page-container-bg-solid page-header-menu-fixed">
<%-- 	<%@include file="../Library/include/header.jsp"%> --%>
	<!-- BEGIN CONTAINER -->
	<div class="page-container">
		<!-- BEGIN CONTENT -->
		<div class="page-content-wrapper">
			<!-- BEGIN CONTENT BODY -->
			<!-- BEGIN PAGE HEAD-->
			<div class="page-head">
				<div class="container-fluid">
					<!-- BEGIN PAGE TITLE -->
					<div class="page-title">
						<h1>Error</h1>
					</div>
					<!-- END PAGE TITLE -->
				</div>
			</div>
			
			<div class="page-content">
			<div class="page-content-inner">
<!-- 				<div class="container-fluid"> -->
<!-- 					<ul class="page-breadcrumb breadcrumb"> -->
<!-- 						<li><a href="home.jsp">Home</a> &gt;</li> -->
<!-- 						<li><a href="error.jsp">Error</a> :</li> -->
<!-- 					</ul> -->
<!-- 				</div> -->
				<div class="page-content-inner">
					<center>
						<b><font color="#FF0000"><b><%=mybean.msg%><br>
							</b></font></b>
					</center>

				</div>
			</div>
			</div>
		</div>
	</div>
<%-- 	<%@include file="../Library/include/footer.jsp"%> --%>

<%-- 	<%@include file="../Library/js.jsp" %>  --%>

</body>

</html>












