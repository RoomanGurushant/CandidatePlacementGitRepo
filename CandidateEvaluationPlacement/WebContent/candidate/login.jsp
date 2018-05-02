<%@ page errorPage="../portal/error.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:useBean id="mybean" class="candidate.Login" scope="session" />
<%mybean.doPost(request,response); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>Login Form</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="assets/css/bootstrap.min.css" rel="stylesheet">
<link href="assets/css/mdb.min.css" rel="stylesheet">
<link href="assets/css/style.css" rel="stylesheet">
</head>

<body>
	<div class="col-md-4" style="margin: 0px auto; top: 100px">
		<div class="card">
			<div class="card-body">
				<form id='login-form' action="Login" method="POST">
					<p class="h4 text-center py-4">Sign In</p>
					
					<font color="#FF0000"><center><b><%=mybean.msg%></b></center></font>
					
					
					<div class="md-form">
						<i class="fa fa-user prefix grey-text"></i> 
						<input type="text" id="txt_emp_uname" name="txt_emp_uname" class="form-control"> 
						<label for="txt_emp_uname" class="font-weight-light">User name</label>
					</div>
					<div class="md-form">
						<i class="fa fa-lock prefix grey-text"></i> 
						<input type="password" id="txt_emp_upass" name="txt_emp_upass" class="form-control"> 
						<label for="txt_emp_upass" class="font-weight-light">Your password</label>
					</div>

					<div class="text-center py-4 mt-3">
						<button class="btn btn-cyan" name="action" id="action" type="submit" value="Login">Login</button><br>
					</div>
					
<!-- 					<div class="text-center py-4 mt-3"> -->
<!-- 						Not Registered? <a href="candidate/candidate-register.jsp">Register now</a> -->
<!-- 					</div> -->
				</form>

			</div>
		</div>
	</div>
	<script type="text/javascript" src="assets/js/fontawesome.js"></script>
	<script type="text/javascript" src="assets/js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="assets/js/popper.min.js"></script>
	<script type="text/javascript" src="assets/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="assets/js/mdb.min.js"></script>
</body>

</html>
