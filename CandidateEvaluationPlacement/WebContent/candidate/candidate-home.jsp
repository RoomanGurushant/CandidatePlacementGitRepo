<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>Home Page</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="../assets/css/bootstrap.min.css" rel="stylesheet">
<link href="../assets/css/mdb.min.css" rel="stylesheet">
<link href="../assets/css/bootstrap-material-datetimepicker.css" rel="stylesheet">
<link href="../assets/css/style.css" rel="stylesheet">

<style>
.profile {
	height: 50px;
	width: 50px;
	border-radius: 50px;
}

.profile_img {
	height: 50px;
	width: 50px;
	border-radius: 50px;
}

.list {
	display: none;
	list-style-type: none;
	background: white;
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0
		rgba(0, 0, 0, 0.19);
	border-radius: 5px;
	float: none;
	position: absolute;
	margin-left: -50px;
	margin-top: 20px;
	padding: 5px;
}

.list ul li {
	list-style-type: none;
	color: #007bff;
	background: #ffffff;
	padding: 5px;
	margin-left: -35px;
}

.list ul li:hover {
	list-style-type: none;
	color: #ffffff;
	background: #007bff;
	padding: 5px;
	margin-left: -30px;
}
</style>
</head>
<body>
	<header> <!--Navbar--> <nav
		class="navbar navbar-expand-lg navbar-dark light-blue accent-4"
		style="padding:0px">
	<div class="container-fluid">
		<a class="navbar-brand" href="#">Logo</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link" href="#">Home
						<span class="sr-only">(current)</span>
				</a></li>

				<li class="nav-item btn-group"><a
					class="nav-link dropdown-toggle" id="navbarDropdownMenuLink"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Candidate
				</a>
					<div class="dropdown-menu dropdown-primary"
						aria-labelledby="navbarDropdownMenuLink">
						<a class="dropdown-item" href="candidate-register.jsp?add=yes">Add Candidate</a> 
						<a class="dropdown-item" href="candidate-list.jsp?all=yes">List Candidate</a>
					</div></li>
			</ul>

			<ul class="navbar-nav">
				<li class="nav-item btn-group"><a
					class="nav-link dropdown-toggle" id="navbarDropdownMenuLink1"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						<img src="../assets/img/img1.jpeg"
						class="img img-responsive profile_img">
				</a>
					<div class="dropdown-menu dropdown-primary dropdown-menu-right"
						aria-labelledby="navbarDropdownMenuLink1">
						<a class="dropdown-item" href="#">Change Profile pic</a> <a
							class="dropdown-item" href="../candidate/login.jsp">Logout</a>
					</div></li>
			</ul>
		</div>
	</div>
	</nav> <!--/.Navbar--> </header>

	<!-- SCRIPTS -->
	<script type="text/javascript" src="../assets/js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="../assets/js/popper.min.js"></script>
	<script type="text/javascript" src="../assets/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="../assets/js/mdb.min.js"></script>
	<script type="text/javascript" src="../assets/js/bootstrap-material-datetimepicker.js"></script>
	<script>
		$(document).ready(function() {
			$(".profile_img").hover(function() {
				$(".list").show();
			}, function() {
				$('.list').hide();
			});
		});
	</script>
</body>
</html>
