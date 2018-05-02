<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<form name="form1" id="form1" method="get">
	<div class="container-fluid"  >
			<div class="container-fluid">
			<center>
			<font color="#ff0000"><b><%=mybean.msg%></b></font><br>
		</center>
		</div>
			
	</div>
	
	<div class="container-fluid">
				<div class="tab-pane" id="">
					<center>
						<strong><%=mybean.RecCountDisplay%></strong>
					</center>
					<!-- START PORTLET BODY -->

					<center>
						<%=mybean.PageNaviStr%>
					</center>

					<%=mybean.StrHTML%>

					<center>
						<%=mybean.PageNaviStr%>
					</center>
				</div>
	</div>
	
</form>