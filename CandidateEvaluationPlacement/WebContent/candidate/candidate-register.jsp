<%-- <%@ page errorPage="../portal/error.jsp"%> --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:useBean id="mybean" class="candidate.Candidate_Register" scope="request" />
<%mybean.doPost(request,response); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta charset="utf-8" />
<title><%=mybean.AppName%>-<%=mybean.ClientName%></title>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta content="width=device-width, initial-scale=1" name="viewport" />
<%-- <%@include file="../Library/css.jsp"%> --%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"/>
<link href="../assets/css/bootstrap.min.css" rel="stylesheet"/>
<link href="../assets/css/mdb.min.css" rel="stylesheet"/>
<link href="../assets/css/bootstrap-material-datetimepicker.css" rel="stylesheet"/>
<link href="../assets/css/style.css" rel="stylesheet"/>
</head>

<body class="page-container-bg-solid page-boxed page-header-menu-fixed">
<%-- 	<%@include file="../Library/include/header.jsp"%> --%>


	<div class="page-container">
		<!-- BEGIN CONTENT -->
		<div class="page-content-wrapper">
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
							<div class="portlet-body portlet-empty container-fluid">
								<div class="tab-pane" id="">
									<!-- START PORTLET BODY -->

								<form id="form1" name="form1" method="post" class="form-horizontal">
									<div class="card">
										<!-- Card body -->
										<div class="card-body">
											<p class="h4 text-center py-4 text-primary">REGISTRATION FORM</p>
											<div class="card">
												<h4 class="card-header primary-color white-text">Personal
													Details</h4>
												<div class="card-body">
													<div class="row">
														<div class="col-md-6">
															<div class="md-form">
																<i class="fa fa-user prefix grey-text"></i> 
																<input type="text" id="txt_candidate_fname" name="txt_candidate_fname" class="form-control" value="<%=mybean.candidate_fname%>"/> 
																<label for="txt_candidate_fname" class="font-weight-light">First Name<font color="#ff0000"><b>*</b></font> </label>
															</div>
														</div>
						
														<div class="col-md-6">
															<div class="md-form">
																<i class="fa fa-user prefix grey-text"></i> 
																<input type="text" id="txt_candidate_lname" name="txt_candidate_lname" class="form-control" value="<%=mybean.candidate_lname%>"/> 
																<label for="txt_candidate_lname" class="font-weight-light">Last Name<font color="#ff0000"><b>*</b></font> </label>
															</div>
														</div>
						
														<div class="col-md-6">
															<div class="md-form">
																<i class="fa fa-lock prefix grey-text"></i> 
																<input type="password" id="txt_candidate_password" name="txt_candidate_password" class="form-control" value="<%=mybean.candidate_upass%>"/> 
																<label for="txt_candidate_password" class="font-weight-light">Password<font color="#ff0000"><b>*</b></font></label>
															</div>
														</div>
														<div class="col-md-6">
															<div class="md-form">
																<i class="fa fa-wheelchair prefix grey-text"></i> 
																<input type="checkbox" id="chk_physically_challenged" name="chk_physically_challenged" class="form-control" <%=mybean.PopulateCheck(mybean.candidate_physically_challenged)%>/> 
																<label for="chk_physically_challenged" class="font-weight-light">Physically challenged</label>
															</div>
														</div>
													</div>
													<div class="row">
														<div class="col-md-6">
															<div class="md-form">
																<i class="fa fa-mobile prefix grey-text"></i> 
																<input type="text" id="txt_candidate_mobileno1" name="txt_candidate_mobileno1" class="form-control" value="<%=mybean.candidate_mobileno1%>"/>
																<label for="txt_candidate_mobileno1" class="font-weight-light">Mobile No<font color="#ff0000"><b>*</b></font></label>
																<span class="grey-text">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(eg.9999999999)</span>
															</div>
														</div>
														<div class="col-md-6">
															<div class="md-form">
																<i class="fa fa-mobile prefix grey-text"></i> 
																<input type="text" id="txt_candidate_mobileno2" name="txt_candidate_mobileno2" class="form-control" value="<%=mybean.candidate_mobileno2%>" />
																<label for="txt_candidate_mobileno2" class="font-weight-light">Alternative Mobile<font color="#ff0000"><b>*</b></font></label>
																<span class="grey-text">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(eg.9999999999)</span>
															</div>
														</div>
														<div class="col-md-6">
															<div class="md-form">
																<i class="fa fa-envelope prefix grey-text"></i> 
																<input type="email" id="txt_candidate_email1" name="txt_candidate_email1" class="form-control" value="<%=mybean.candidate_email1%>" /> 
																<label for="txt_candidate_email1" class="font-weight-light">Email 1<font color="#ff0000"><b>*</b></font></label>
															</div>
														</div>
														<div class="col-md-6">
															<div class="md-form">
																<i class="fa fa-envelope prefix grey-text"></i> 
																<input type="email" id="txt_candidate_email2" name="txt_candidate_email2" class="form-control" value="<%=mybean.candidate_email2%>" /> 
																<label for="txt_candidate_email2" class="font-weight-light">Alternative Email<font color="#ff0000"><b>*</b></font></label>
															</div>
														</div>
													</div>
						
													<div class="row">
														<div class="col-md-6">
															    <label>Gender<font color="#ff0000">*</font>:</label> 
															     <%=mybean.PopulateGender()%>
														</div>
						
														<div class="col-md-6">
															<div class="md-form">
																<i class="fa fa-calendar prefix grey-text"></i> 
																<input type="text" id="date_candidate_dob" name="date_candidate_dob"  value="<%=mybean.candidate_dob%>" class="form-control datepicker" /> 
																<label for="date-picker-example" class="font-weight-light">DOB<font color="#ff0000"><b>*</b></font></label>
															</div>
														</div>
													</div>
						
													<div class="row">
														<div class="col-md-6">
																<label>Marital Status: &nbsp; </label>
																<%=mybean.PopulateMarried()%>
														</div>
						
														<div class="col-md-6">
															<label for="Qualification" class="font-weight-light">Area Name<font color="#ff0000"><b>*</b></font>: </label>
															<%=mybean.PopulateArea()%>
														</div>
													</div>
													<br>
						
													<div class="row">
														<div class="col-md-6">
															<div class="md-form">
																<i class="fa fa-address-card prefix grey-text"></i>
																<textarea class="form-control rounded-0" id="textarea_candidate_address" name="textarea_candidate_address" rows="2"><%=mybean.candidate_address%></textarea>
																<label for="textarea_candidate_address" class="font-weight-light">Address<font
																	color="#ff0000"><b>*</b></font></label>
															</div>
														</div>
						
														<div class="col-md-6">
															<div class="md-form">
																<i class="fa fa-address-card-o prefix grey-text"></i> 
																<input type="text" maxlength="14" id="txt_candidate_aadhaar" name="txt_candidate_aadhaar" class="form-control" value="<%=mybean.candidate_aadhaar_no%>"/> 
																<label for="txt_candidate_aadhaar" class="font-weight-light">Aadhar number<font color="#ff0000"><b>*</b></font>
																</label>
															</div>
														</div>
													</div>
						
													<div class="row">
														<div class="col-md-6">
															<div class="md-form">
																<i class="fa fa-exclamation-triangle prefix grey-text"></i> 
																 <input type="checkbox" id="chk_active" name="chk_active" class="form-control" style="margin-left: -85px" <%=mybean.PopulateCheck(mybean.candidate_active)%> />	 
																 <label for="chk_active" class="font-weight-light">Active</label>
						
															</div>
														</div>
						
														<div class="col-md-6">
															<div class="md-form">
																<i class="fa fa-pencil-square-o prefix grey-text"></i>
																<textarea class="form-control rounded-0" id="txt_candidate_notes" name="txt_candidate_notes" rows="2"><%=mybean.candidate_notes%></textarea>
																<label for="txt_candidate_notes" class="font-weight-light">Notes</label>
															</div>
														</div>
													</div>
												</div>
											</div>
											<br>
											<div class="card">
												<h4 class="card-header primary-color white-text">Educational Details</h4>
												<div class="card-body">
													<div class="row">
														<div class="col-md-6">
															<label for="dr_candidate_qualification_id" class="font-weight-light qualn">Highest Qualification<font color="#ff0000"><b>*</b></font>: </label>
															<%=mybean.PopulateQualification()%>
														</div>
														<div class="col-md-6">
															<label for="dr_candidate_specialization_id" class="font-weight-light specn">Specialization<font color="#ff0000"><b>*</b></font>: </label>
															<div id="specialization">
																<%=mybean.PopulateSpecialization(mybean.candidate_qualification_id)%>
															</div>
														</div>
													</div>
													<br>
													<div class="row">
														<div class="col-md-6">
															<label for="Qualification" class="font-weight-light">Passing Year<font color="#ff0000"><b>*</b></font>: </label>
															<%=mybean.PopulatePassingYear()%>
														</div>
														<div class="col-md-6">
															<label for="Qualification" class="font-weight-light">Institute<font color="#ff0000"><b>*</b></font>: </label>
															<%=mybean.PopulateInstitutes()%>
														</div>
													</div>
						
												</div>
											</div>
											<br>		
											<div class="card">
												<h4 class="card-header primary-color white-text">Work history</h4>
												<div class="card-body">
													<div class="row">
														<div class="col-md-6">
															<label for="Qualification" class="font-weight-light">Experience<font color="#ff0000"><b>*</b></font>: </label>
															<%=mybean.PopulateExperience()%>
														</div>
														<div class="col-md-6">
															<div class="md-form">
																<i class="fa fa-money prefix grey-text"></i> 
																<input type="text" id="txt_candidate_cur_sal" name="txt_candidate_cur_sal" class="form-control" value="<%=mybean.candidate_current_sal%>"/>
																<span class="grey-text">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(per month)</span> 
																<label for="txt_candidate_cur_sal" class="font-weight-light">Current Salary<font color="#ff0000"><b>*</b></font></label>
															</div>
														</div>
													</div>
													<br>
													<div class="row">
														<div class="col-md-6">
															<div class="md-form">
																<i class="fa fa-money prefix grey-text"></i> 
																<input type="text" id="txt_candidate_exp_sal" name="txt_candidate_exp_sal" class="form-control" value="<%=mybean.candidate_expected_sal%>" />
																<span class="grey-text">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(per month)</span>
																<label for="txt_candidate_exp_sal" class="font-weight-light">Expected Salary<font color="#ff0000"><b>*</b></font>
																</label>
															</div>
														</div>
														<div class="col-md-6">
															<label for="candidate_sector_id" class="font-weight-light">Sector<font color="#ff0000"><b>*</b></font>: </label>
															<%=mybean.PopulateSector()%>
														</div>
													</div>
													<br>
													<div class="row">
														<div class="col-md-6">
															<label for="dr_candidate_functionalarea1_id" class="font-weight-light">Functional Area 1<font color="#ff0000"><b>*</b></font>: </label>
															<%=mybean.PopulateFAOne()%>
														</div>
														<div class="col-md-6">
															<label for="dr_candidate_role1_id" class="font-weight-light">Role 1<font color="#ff0000"><b>*</b></font>: </label>
															<div id="role1">
															<%=mybean.PopulateRoleOne(mybean.candidate_functionalarea_id1)%>
															</div>
														</div>
													</div>
													<br>
						
													<div class="row">
														<div class="col-md-6">
															<label for="dr_candidate_functionalarea2_id" class="font-weight-light">Functional Area 2:</label>
															<%=mybean.PopulateFATwo()%>
														</div>
														<div class="col-md-6">
															<label for="dr_candidate_role2_id" class="font-weight-light">Role 2:</label>
															<div id="role2">
															<%=mybean.PopulateRoleTwo(mybean.candidate_functionalarea_id2)%>
															</div>
														</div>
													</div>
													<br>
						
													<div class="row">
														<div class="col-md-6">
															<label for="dr_candidate_functionalarea3_id" class="font-weight-light">Functional Area 3:</label>
															<%=mybean.PopulateFAThree()%>
														</div>
														<div class="col-md-6">
															<label for="dr_candidate_role3_id" class="font-weight-light">Role 3:</label>
															<div id="role3">
															<%=mybean.PopulateRoleThree(mybean.candidate_functionalarea_id3)%>
															</div>
														</div>
													</div>
													<br>
						
													<div class="row">
														<div class="col-md-6">
															<label for="Qualification" class="font-weight-light">Current Location1<font color="#ff0000"><b>*</b></font>: </label>
															<%=mybean.PopulateCurrentLocation()%>
														</div>
														<div class="col-md-6">
															<label for="Qualification" class="font-weight-light">Preferred Location1<font color="#ff0000"><b>*</b></font>: </label>
															<%=mybean.PopulatePrefLocationOne()%>
														</div>
													</div>
													<br>
						
													<div class="row">
														<div class="col-md-6">
															<label for="Qualification" class="font-weight-light">Preferred Location2:</label>
															<%=mybean.PopulatePrefLocationTwo()%>
														</div>
														<div class="col-md-6">
															<label for="Qualification" class="font-weight-light">Preferred Location3:</label>
															<%=mybean.PopulatePrefLocationThree()%>
														</div>
													</div>
													<br>
						
													<div class="row">
														<div class="col-md-6">
															<div class="md-form">
																<i class="fa fa-cogs prefix grey-text"></i>
																<textarea class="form-control rounded-0" id="textarea_candidate_skillset" name="textarea_candidate_skillset"  rows="2"><%=mybean.candidate_skillset%></textarea>
																<label for="textarea_candidate_skillset" class="font-weight-light">Skillset<font color="#ff0000"><b>*</b></font></label>
															</div>
														</div>
													</div>
						
												</div>
												<!-- Material form register -->
											</div>
											<!-- Card body -->
										</div>
									</div>
											
											<div class="row">
											<center>
												<%
													if (mybean.status.equals("Add")) {
												%>
												<input name="addbutton" type="submit" class="btn btn-success" id="addbutton" value="Add Candidate" /> 
												<input type="hidden" id="add_button" name="add_button" value="yes" /> 
												<input type="hidden" name="add" id="add" value="yes" />
												<%
													} else if (mybean.status.equals("Update")) {
												%>
												<input type="hidden" id="update_button" name="update_button" value="yes" /> 
												<input name="updatebutton" type="submit" class="btn btn-success" id="updatebutton" value="Update Candidate" /> 
												<input name="delete_button" type="submit" class="btn btn-success" id="delete_button" value="Delete Candidate" /> 
												<input type="hidden" name="update" id="update" value="yes"/>
												<%
												 	}
												 %>
											</center>
										</div>
										<input name="candidate_id" type="hidden" id="candidate_id" value="<%=mybean.candidate_id%>"/>
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
	
	<script>
		// Data Picker Initialization
		$('.datetimepicker').bootstrapMaterialDatePicker({
			format : 'DD/MM/YYYY HH:mm',
			switchOnClick : true,
			clearButton : true
		});
	</script>

	<script>
		$("#txt_candidate_aadhaar").on('keypress', function() {
			$(this).val(function(index, value) {
				return value.replace(/\W/gi, '').replace(/(.{4})/g, '$1 ');
			});
		});
	</script>
	
	<script language="JavaScript" type="text/javascript">
	 $(document).ready(function() {
		// QUAL ajax
		 $("#dr_candidate_qualification_id").change( function() {
			var qualid = $("#dr_candidate_qualification_id") .val();
			alert("spec change===" + qualid);
			showHint( 'candidate-check.jsp?page=candidateupdate&specialization=yes&candidate_qualification_id=' + qualid, 'specialization');	
		});
		
		//FA1 ajax
		$("#dr_candidate_functionalarea1_id").change( function() {
			var fa1_id = $("#dr_candidate_functionalarea1_id") .val();
// 			alert("FA1 change===" + fa1_id);
			showHint( 'candidate-check.jsp?page=candidateupdate&fa1=yes&candidate_functionalarea1_id=' + fa1_id, 'role1');	
		});
		
		//FA2 ajax
		$("#dr_candidate_functionalarea2_id").change( function() {
			var fa2_id = $("#dr_candidate_functionalarea2_id") .val();
// 			alert("FA2 change===" + fa2_id);
			showHint( 'candidate-check.jsp?page=candidateupdate&fa2=yes&candidate_functionalarea2_id=' + fa2_id, 'role2');	
		});
		
		//FA3 ajax
		$("#dr_candidate_functionalarea3_id").change( function() {
			var fa3_id = $("#dr_candidate_functionalarea3_id") .val();
// 			alert("FA3 change===" + fa3_id);
			showHint( 'candidate-check.jsp?page=candidateupdate&fa3=yes&candidate_functionalarea3_id=' + fa3_id, 'role3');	
		});
		
		 e.preventDefault();
	 });
	</script>

</body>

</html>
