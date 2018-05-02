<%@ page errorPage="../portal/error.jsp"%>
<%! String requestpage =""; %>
<% if(request.getParameter("page") != null){
	requestpage = request.getParameter("page");
}%>

<%switch(requestpage){		
	case "candidateupdate" : %>
	<jsp:useBean id="candidateupdate" class="candidate.Candidate_Register" scope="request" />
						   <% candidateupdate.doGet(request, response); %>
							<%=candidateupdate.msg%>
							<% break;
							
							
	case "candidateevaluate" : %> <jsp:useBean id="candidateevaluate" class="candidate.Candidate_Evaluation_Update" scope="request" />
	<% candidateevaluate.doGet(request, response); %>
	<%=candidateevaluate.msg%>
	<% break;
	
	default :  response.sendRedirect("../candidate/error-page.jsp?msg=Invalid Request!"); 
} %>