<%-- <%@ page errorPage="../portal/error.jsp"%> --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:useBean id="mybean" class="candidate.Candidate_Evaluation_Update" scope="request" />
<%mybean.doPost(request,response); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta charset="utf-8" />
<title><%=mybean.AppName%>-<%=mybean.ClientName%></title>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta content="width=device-width, initial-scale=1" name="viewport" />
<%-- <%@include file="../Library/css.jsp"%> --%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="../assets/css/bootstrap.min.css" rel="stylesheet">
<link href="../assets/css/mdb.min.css" rel="stylesheet">
<link href="../assets/css/bootstrap-material-datetimepicker.css" rel="stylesheet">
<link href="../assets/css/style.css" rel="stylesheet">
</head>
<body class="page-container-bg-solid page-boxed page-header-menu-fixed">
<%-- 	<%@include file="../Library/include/header.jsp"%> --%>


	<div class="page-container">
		<!-- BEGIN CONTENT -->
		<div class="page-content-wrapper">
			<div class="page-head">
				<div class="container-fluid">
					<div class="page-title">
						<h1><%=mybean.status%>&nbsp;File
						</h1>
					</div>
				</div>
			</div>
			<!-- END PAGE HEAD-->
			<!-- BEGIN PAGE CONTENT BODY -->
			<div class="page-content">
				<div class="page-content-inner">

					<center>
						<font color="#ff0000"><b><%=mybean.msg%></b></font>
					</center>
					<!-- START ADD TICKET -->
					<div class="container-fluid">
						<div class="container-fluid portlet box">
							<div class="portlet-title" style="text-align: center">
								<div class="caption" style="float: none"><%=mybean.status%> Evaluation
								</div>
							</div>
							<div class="portlet-body portlet-empty container-fluid">
								<div class="tab-pane" id="">
									<!-- START PORTLET BODY -->

									<form id="form1" name="form1" method="post"
										class="form-horizontal">
										<div class="form-element6 ">
											<label>Candidate Name<font color="#ff0000">*</font>:
												&nbsp;
											</label>
											<%=mybean.candidate_name%>
										</div>
										<div class="form-element6 ">
											<label>Skill<font color="#ff0000">*</font>: &nbsp;
											</label>
											<%=mybean.PopulateSkill()%>
										</div>

										<%
											if (mybean.status.equals("Update")) {
										%>
										<div class="form-element6 ">
											<label>Evaluator<font color="#ff0000">*</font>:
												&nbsp;
											</label>
											<%=mybean.PopulateEvaluator()%>
										</div>
										<div class="form-element6 ">
											<label>Time<font color="#ff0000">*</font>: &nbsp;
											</label>
											<div class="input-group date">
												<input type="text" size="50" maxlength="16" class="form-control datetime-mask" value="<%=mybean.candidate_eval_time%>" name="txt_eval_time" id="txt_eval_time" onKeypress='return isNumber(event)' />
												<span class="input-group-addon btn" style="padding: 0 4px;">
												<input type="text" class="datetimepicker" value="<%=mybean.candidate_eval_time%>" onchange="PostDate(this);" />
												</span>
											</div>
										</div>
										<%
											}
										%>

										<div class="form-element6">
											<label>Notes:&nbsp;</label>
											<textarea name="txt_eval_notes" cols="70" rows="4"
												maxlength="2000" class="form-control" id="txt_eval_notes"> <%=mybean.candidate_eval_notes%> </textarea>
										</div>

										<br/>
											<div class="row"></div>
											<div>
												<input type="button" class="btn btn-primary" value="Add SubSkills" id="addsubskills" style="float: right"/>
											</div> 
											<br/><br/>
											<table id="myTable" class="table-responsive table-condensed" data-filter="filter">
												<thead>
													<tr>
														<th style="width: 68%" data-toggle="true">SubSkills</th>
														<th data-hide="phone"></th>
														<th style="width: 30%" data-hide="phone">Rating</th>
													</tr>
												</thead>
												<tbody id="subskill">
													<%=mybean.PopulateSubSkill(mybean.candidate_skill_id)%>
												</tbody>
											</table>
											<div class="row">
											<center>
												<%
													if (mybean.status.equals("Add")) {
												%>
												<input name="addbutton" type="submit" class="btn btn-success" id="addbutton" value="Add Evaluation" /> 
												<input type="hidden" id="add_button" name="add_button" value="yes" /> 
												<input type="hidden" name="add" id="add" value="yes" />
												<%
													} else if (mybean.status.equals("Update")) {
												%>
												<input type="hidden" id="update_button" name="update_button" value="yes" /> 
												<input name="updatebutton" type="submit" class="btn btn-success" id="updatebutton" value="Update Evaluation" /> 
												<input name="delete_button" type="button" class="btn btn-success" id="delete_button" value="Delete Evaluation" /> 
												<input type="hidden" name="update" id="update" value="yes"> <%
												 	}
												 %>
											</center>
										</div>
										<input name="candidate_id" type="hidden" id="candidate_id" value="<%=mybean.candidate_id%>"/>
										<input name="candidate_eval_id" type="hidden" id="candidate_eval_id" value="<%=mybean.candidate_eval_id%>"/>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- END ADD TICKET -->
		</div>
	</div>
	<!-- END CONTENT -->
	<!-- END CONTAINER -->
	<!-- BEGIN FOOTER -->

		<script type="text/javascript" src="../assets/js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="../Library/dynacheck.js"></script>
	<script type="text/javascript" src="../assets/js/popper.min.js"></script>
	<script type="text/javascript" src="../assets/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="../assets/js/mdb.min.js"></script>
	<script type="text/javascript" src="../assets/js/bootstrap-material-datetimepicker.js"></script>
	
	<script language="JavaScript" type="text/javascript">
	 $(document).ready(function() {
		 ajaxtable();
		 //Skill Ajax
		 $("#dr_candidate_skill_id").change(function(){
				var skill_id =$("#dr_candidate_skill_id").val();
				showHint('candidate-check.jsp?page=candidateevaluate&subskill=yes&candidate_skill_id='+skill_id,'subskill');
				setTimeout(function(){ajaxtable();}, 500)
			});
		 e.preventDefault();
	 });
	</script>
	
	
	 <script>
		function FormFocus() { //v1.0
			document.form1.txt_candidate_name.focus();
		}
		
		function ajaxtable(){
// 			alert("loaded first time");
			var currentrow = 3;
			$('#myTable tr').hide();
			$('#myTable tr:eq(0)').show();
			$('#myTable tr:eq(1)').show();
			$('#myTable tr:eq(2)').show();
			$('#myTable tr:eq(3)').show();
			
			for(var i=4;i<10;i++){
				if($('#dr_candidate_subskill_id'+i).val()!='0'){
					currentrow++;
					$('#myTable tr:eq('+ currentrow +')').show();
				}
			}
			$('#addsubskills').click(function(){
// 				alert("on subskill button click");
				currentrow++;
				$('#myTable tr:eq('+ currentrow +')').show();
			});
		}
	</script>


</body>

</html>
