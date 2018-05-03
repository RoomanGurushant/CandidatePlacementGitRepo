<%@ page errorPage="../portal/error.jsp"%>
<jsp:useBean id="mybean" class="candidate.Candidate_Eval_List" scope="request" />
<%mybean.doPost(request,response); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<meta charset="utf-8" />
<title><%=mybean.AppName%> - <%=mybean.ClientName%></title>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<meta content="width=device-width, initial-scale=1" name="viewport" />
<%@include file="../Library/css.jsp"%>
</HEAD>

<body class="page-container-bg-solid page-header-menu-fixed">

	<div class="page-container">
		<!-- BEGIN CONTENT -->
		<div class="page-content-wrapper">
			<!-- BEGIN CONTENT BODY -->
			<!-- BEGIN PAGE HEAD-->
			<div class="page-head">
				<div class="container-fluid">
					<!-- BEGIN PAGE TITLE -->
					<div class="page-title">
						<h1>List Evaluation</h1>
					</div>
					<!-- END PAGE TITLE -->
				</div>
			</div>
			<!-- END PAGE HEAD-->
			<!-- BEGIN PAGE CONTENT BODY -->
			<div class="page-content">
				<div class="page-content-inner">
					<div class="tab-pane" id="">
						<form>
						<%=mybean.StrHTML%>
						</form>
					</div>
				</div>
			</div>
			<!-- END PAGE CONTENT INNER -->
		</div>
		<!-- END PAGE CONTENT BODY -->
		<!-- END CONTENT BODY -->
	</div>
	<!-- END CONTAINER -->
	<!-- BEGIN FOOTER -->


	<%@include file="../Library/js.jsp"%>

	<script>

function showContent(id){
	var num = id.match(/\d/g);
	conid = num.join("");
	var con1=("moreContent")+conid;
	document.getElementById(id).style.display="none";
	document.getElementById(con1).style.display="block";
	
}

function hideContent(id){
		var num = id.match(/\d/g);
		conid = num.join("");
		var con2=("lessContent")+conid;
		var con3=("moreContent")+conid;
	
		document.getElementById(con3).style.display="none";
		document.getElementById(con2).style.display="block";
	}

</script>



</body>
</HTML>
