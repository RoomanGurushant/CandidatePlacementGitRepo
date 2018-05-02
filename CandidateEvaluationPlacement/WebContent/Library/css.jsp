<%@page import="javax.servlet.http.Cookie"%>
<%! public String theme = "1";%>

 <%

Cookie cookie = null;
Cookie[] cookies = null;
cookies = request.getCookies();
if (cookies != null) {
	for (int i = 0; i < cookies.length; i++) {
		cookie = cookies[i];
		if ((cookie.getName()).compareTo("axelatheme") == 0) {
			theme = cookie.getValue();
		}
	}
}
%>

<link href="../admin-ifx/favicon.ico" rel="shortcut icon" type="image/x-icon">
<link href="../assets/css/font-awesome.css" rel="stylesheet" type="text/css" />
<link href="../assets/css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="../assets/css/components-rounded.css" rel="stylesheet" id="style_components" type="text/css" />

<!-- new timepicker -->
<link href="../assets/css/bootstrap-material-datetimepicker.css" rel="stylesheet"	type="text/css" />
<!-- new timepicker -->

<!-- SELECT 2 -->
<link href="../assets/css/select2.min.css" rel="stylesheet" type="text/css" />
<link href="../assets/css/select2-bootstrap.min.css" rel="stylesheet" type="text/css" />
<!-- ========== -->

<!-- DROPDOWN LIST MULTI SELECT -->
<link rel="stylesheet" href="../assets/css/bootstrap-multiselect.css" type="text/css">
<!-- ========================= -->

<LINK REL="STYLESHEET" TYPE="text/css" HREF="../assets/css/footable.core.css">
<link href="../assets/themes/theme<%=theme%>.css" rel="stylesheet" type="text/css" />
<link href="../assets/css/plugins.css" rel="stylesheet" type="text/css" />

<link href="../assets/css/simple-line-icons.min.css" rel="stylesheet" type="text/css" />
<link href="../assets/css/jquery_toast.min.css" rel="stylesheet" type="text/css" />
<link href="../assets/css/style.css" rel="stylesheet" type="text/css" />