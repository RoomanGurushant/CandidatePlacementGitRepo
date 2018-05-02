<%@ page errorPage="../portal/error.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:useBean id="mybean" class="candidate.Candidate_Register" scope="request" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>Register Form</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="../assets/css/bootstrap.min.css" rel="stylesheet">
<link href="../assets/css/mdb.min.css" rel="stylesheet">
<link href="../assets/css/bootstrap-material-datetimepicker.css" rel="stylesheet">
<link href="../assets/css/style.css" rel="stylesheet">
</head>

<body>
	<div class="col-md-8" style="margin: 0px auto; top: 100px">
		<form id='register-form' action="../CandidateRegister" method="POST">
			<div class="card">
				<!-- Card body -->
				<div class="card-body">

					<!-- Material form register -->

					<p class="h4 text-center py-4 text-primary">REGISTRATION FORM</p>

					<!-- Material input username -->
					<div class="card">
						<h4 class="card-header primary-color white-text">Personal
							Details</h4>
						<div class="card-body">
							<div class="row">
								<div class="col-md-6">
									<div class="md-form">
										<i class="fa fa-user prefix grey-text"></i> 
										<input type="text" id="txt_candidate_fname" name="txt_candidate_fname" class="form-control"> 
										<label for="txt_candidate_fname" class="font-weight-light">First Name<font color="#ff0000"><b>*</b></font> </label>
									</div>
								</div>

								<div class="col-md-6">
									<div class="md-form">
										<i class="fa fa-user prefix grey-text"></i> 
										<input type="text" id="txt_candidate_lname" name="txt_candidate_lname" class="form-control"> 
										<label for="txt_candidate_lname" class="font-weight-light">Last Name<font color="#ff0000"><b>*</b></font> </label>
									</div>
								</div>

								<div class="col-md-6">
									<div class="md-form">
										<i class="fa fa-lock prefix grey-text"></i> 
										<input type="password" id="txt_candidate_password" name="txt_candidate_password" class="form-control"> 
										<label for="txt_candidate_password" class="font-weight-light">Password<font color="#ff0000"><b>*</b></font></label>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6">
									<div class="md-form">
										<i class="fa fa-mobile prefix grey-text"></i> 
										<input type="text" id="txt_candidate_mobileno1" name="txt_candidate_mobileno1" class="form-control">
										<label for="txt_candidate_mobileno1" class="font-weight-light">Mobile No<font color="#ff0000"><b>*</b></font></label>
										<span class="grey-text">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(eg.9999999999)</span>
									</div>
								</div>
								<div class="col-md-6">
									<div class="md-form">
										<i class="fa fa-mobile prefix grey-text"></i> 
										<input type="text" id="txt_candidate_mobileno2" name="txt_candidate_mobileno2" class="form-control">
										<label for="txt_candidate_mobileno2" class="font-weight-light">Alternative Mobile<font color="#ff0000"><b>*</b></font></label>
										<span class="grey-text">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(eg.9999999999)</span>
									</div>
								</div>
								<div class="col-md-6">
									<div class="md-form">
										<i class="fa fa-envelope prefix grey-text"></i> 
										<input type="email" id="txt_candidate_email1" name="txt_candidate_email1" class="form-control"> 
										<label for="txt_candidate_email1" class="font-weight-light">Email 1<font color="#ff0000"><b>*</b></font></label>
									</div>
								</div>
								<div class="col-md-6">
									<div class="md-form">
										<i class="fa fa-envelope prefix grey-text"></i> 
										<input type="email" id="txt_candidate_email2" name="txt_candidate_email2" class="form-control"> 
										<label for="txt_candidate_email2" class="font-weight-light">Alternative Email<font color="#ff0000"><b>*</b></font></label>
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-md-6">
									<div class="md-form">
										<i class="fa fa-transgender prefix grey-text"></i> 
										<label for="materialFormCardgender1" class="font-weight-light">Gender<font color="#ff0000"><b>*</b></font></label>

										<div class="col-md-3" style="margin-left: 105px">
											<input class="form-check-input" name="radio_candidate_gender" type="radio" id="radio_candidate_gender_male" style="margin-top: 16px;" checked="checked">
											<label class="form-check-label" for="radio_candidate_gender_male" style="margin-left: 10px;">Male</label>
										</div>

										<div class="col-md-3" style="margin-left: 185px">
											<input class="form-check-input" name="radio_candidate_gender" type="radio" style="margin-top: 16px;" id="radio_candidate_gender_female"> 
											<label class="form-check-label" for="radio_candidate_gender_female" style="margin-left: 10px;">Female</label>
										</div>
									</div>
								</div>

								<div class="col-md-6">
									<div class="md-form">
										<i class="fa fa-calendar prefix grey-text"></i> 
										<input type="text" id="date_candidate_dob" name="date_candidate_dob" class="form-control datepicker"> 
										<label for="date-picker-example" class="font-weight-light">DOB<font color="#ff0000"><b>*</b></font></label>
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-md-6">
									<div class="md-form">
										<i class="fa fa-universal-access prefix grey-text"></i> 
										<label for="materialFormCardMstatus1" class="font-weight-light">Marital Status<font color="#ff0000"><b>*</b></font> </label>
										<div class="col-md-3" style="margin-left: 155px">
											<input class="form-check-input" name="radio_candidate_marital" type="radio" id="radio_candidate_marital_married" style="margin-top: 16px;" checked="checked">
											<label class="form-check-label" for="radio_candidate_marital_married" style="margin-left: 10px;">Single</label>
										</div>

										<div class="col-md-3" style="margin-left: 235px">
											<input class="form-check-input" name="radio_candidate_marital" type="radio" style="margin-top: 16px;" id="radio_candidate_marital_unmarried"> 
											<label class="form-check-label" for="radio_candidate_marital_unmarried" style="margin-left: 10px;">Married</label>
										</div>
									</div>
								</div>

								<div class="col-md-6">
									<div class="md-form">
										<i class="fa fa-wheelchair prefix grey-text"></i> 
										<input type="checkbox" id="chk_physically_challenged" name="chk_physically_challenged" class="form-control"> 
										<label for="chk_physically_challenged" class="font-weight-light">Physically challenged</label>
									</div>
								</div>
							</div>
							<br>

							<div class="row">
								<div class="col-md-6">
									<div class="md-form">
										<i class="fa fa-address-card prefix grey-text"></i>
										<textarea class="form-control rounded-0" id="textarea_candidate_address" name="textarea_candidate_address" rows="2"></textarea>
										<label for="textarea_candidate_address" class="font-weight-light">Address<font
											color="#ff0000"><b>*</b></font></label>
									</div>
								</div>

								<div class="col-md-6">
									<div class="md-form">
										<i class="fa fa-address-card-o prefix grey-text"></i> 
										<input type="text" maxlength="14" id="txt_candidate_aadhaar" name="txt_candidate_aadhaar" class="form-control"> 
										<label for="txt_candidate_aadhaar" class="font-weight-light">Aadhar number<font color="#ff0000"><b>*</b></font>
										</label>
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-md-6">
									<div class="md-form">
										<i class="fa fa-exclamation-triangle prefix grey-text"></i> 
										 <input type="checkbox" id="chk_active" name="chk_active" class="form-control" style="margin-left: -85px" checked> 
										 <label for="chk_active" class="font-weight-light">Active</label>

									</div>
								</div>

								<div class="col-md-6">
									<div class="md-form">
										<i class="fa fa-pencil-square-o prefix grey-text"></i>
										<textarea class="form-control rounded-0" id="txt_candidate_notes" name="txt_candidate_notes" rows="2"></textarea>
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
										<input type="text" id="txt_candidate_cur_sal" name="txt_candidate_cur_sal" class="form-control">
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
										<input type="text" id="txt_candidate_exp_sal" name="txt_candidate_exp_sal" class="form-control">
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
										<textarea class="form-control rounded-0" id="textarea_candidate_skillset" name="textarea_candidate_skillset"  rows="2"></textarea>
										<label for="textarea_candidate_skillset" class="font-weight-light">Skillset<font color="#ff0000"><b>*</b></font></label>
									</div>
								</div>
							</div>

						</div>
						<!-- Material form register -->
					</div>
					<!-- Card body -->
				</div>
				<center>
					<input id='submit-data' class="btn btn-primary" type="submit" value="Register"></b> 
					<input type="reset" class="btn btn-primary" value="Clear"></b>
				</center>
			</div>
		</form>

	</div>
	<br>
	<br>
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
// 			alert("spec change===" + qualid);
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
