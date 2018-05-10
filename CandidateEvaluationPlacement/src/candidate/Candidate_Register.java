package candidate;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.CachedRowSet;

import connect.Connect;

public class Candidate_Register extends Connect {
public static final long serialVersionUID = 1L;
	
	public String candidate_fname = "";
	public String candidate_lname = "";
	public String candidate_upass = "";
	public String candidate_mobileno1 = "";
	public String candidate_mobileno2 = "";
	public String candidate_email1 = "";
	public String candidate_email2 = "";
	public String candidate_gender = "";
	public String candidate_dob = "";
	public String candidate_married = "";
	public String candidate_physically_challenged = "";
	public String candidate_address = "";
	public String candidate_aadhaar_no = "";
	public String candidate_active = "";
	public String candidate_notes = "";
	
	public String candidate_qualification_id = "0";
	public String candidate_specialization_id = "0";
	public String candidate_yop = "0";
	public String candidate_institute_id = "0";
	
	public String candidate_experience= "0";
	public String candidate_current_sal= "";
	public String candidate_expected_sal= "";
	public String candidate_sector_id = "0";
	public String candidate_functionalarea_id1 = "0";
	public String candidate_role_id1 = "0";
	public String candidate_functionalarea_id2 = "0";
	public String candidate_role_id2 = "0";
	public String candidate_functionalarea_id3 = "0";
	public String candidate_role_id3 = "0";
	public String candidate_area_id = "0";
	public String candidate_curcity_id = "0";
	public String candidate_prefcity_id1 = "0";
	public String candidate_prefcity_id2 = "0";
	public String candidate_prefcity_id3 = "0";
	public String candidate_skillset = "";
	
	public String specialization;
	public String functionalarea1;
	public String functionalarea2;
	public String functionalarea3;
	
	public String msg = "", msg1="";
	public String Strsql = "";
	public String add = "";
	public String update = "";
	public String addB = "";
	public String updateB = "";
	public String deleteB = "";
	public String QueryString = "";
	public String status = "";
	public String candidate_id = "0";
	public Connection con = null;
	Statement stmttx = null;


	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
//				CheckSession(request, response);
				add = PadQuotes(request.getParameter("add"));
				update = PadQuotes(request.getParameter("update"));
				addB = PadQuotes(request.getParameter("add_button"));
				updateB = PadQuotes(request.getParameter("update_button"));
				deleteB = PadQuotes(request.getParameter("delete_button"));
				msg = PadQuotes(request.getParameter("msg"));
				QueryString = PadQuotes(request.getQueryString());
				candidate_id = CNumeric(PadQuotes(request.getParameter("candidate_id")));
				specialization = PadQuotes(request.getParameter("specialization"));
				functionalarea1 = PadQuotes(request.getParameter("fa1"));
				functionalarea2 = PadQuotes(request.getParameter("fa2"));
				functionalarea3 = PadQuotes(request.getParameter("fa3"));

				if (specialization.equals("yes")) {
					candidate_qualification_id = CNumeric(PadQuotes(request.getParameter("candidate_qualification_id")));
					msg = PopulateSpecialization(candidate_qualification_id);
//					System.out.println("msg==specialization.equals.yes =" + msg);
				}
				
				if (functionalarea1.equals("yes")) {
					candidate_functionalarea_id1  = CNumeric(PadQuotes(request.getParameter("candidate_functionalarea1_id")));
					msg = PopulateRoleOne(candidate_functionalarea_id1);
				}
				
				if (functionalarea2.equals("yes")) {
					candidate_functionalarea_id2 = CNumeric(PadQuotes(request.getParameter("candidate_functionalarea2_id")));
					msg = PopulateRoleTwo(candidate_functionalarea_id2);
				}
				
				if (functionalarea3.equals("yes")) {
					candidate_functionalarea_id3 = CNumeric(PadQuotes(request.getParameter("candidate_functionalarea3_id")));
					msg = PopulateRoleThree(candidate_functionalarea_id3);
				}
				
				
				if ("yes".equals(add)) {
					status = "Add";
					if (!"yes".equals(addB)) {
						candidate_active = "1";
					} else {
						GetValues(request, response);
						AddFields();
							if (!msg.equals("")) {
								msg = "Error!<br>" + msg;
							} else {
								msg = "Candidate Added Successfully!";
								response.sendRedirect("../candidate/candidate-list.jsp?msg=Candidate Registered Successfully!");
							}
					}
				} 
				else if ("yes".equals(update)) {
					status = "Update";
					if (!"yes".equals(updateB) && !"Delete Candidate".equals(deleteB)) {
						PopulateFields();
					} else if ("yes".equals(updateB) && !"Delete Candidate".equals(deleteB)) {
						GetValues(request, response);
							UpdateFields();
							if (!msg.equals("")) {
								msg = "Error!" + msg;
							} else {
								msg = "Candidate Updated Successfully!";
								response.sendRedirect(response.encodeRedirectURL("candidate-list.jsp?msg=" + msg));
							}
					} else if ("Delete Candidate".equals(deleteB)) {
						GetValues(request, response);
							DeleteFields();
							if (!msg.equals("")) {
								msg = "Error!" + msg;
							} else {
								msg = "Candidate Deleted Successfully!";
								response.sendRedirect(response.encodeRedirectURL("candidate-list.jsp?msg=" + msg));
							}
					}
				}
		} catch (Exception ex) {
			SOPError(this.getClass().getName());
			SOPError("Error in " + new Exception().getStackTrace()[0].getMethodName() + ": " + ex);
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void GetValues(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		candidate_fname = PadQuotes(request.getParameter("txt_candidate_fname"));
		candidate_lname = PadQuotes(request.getParameter("txt_candidate_lname"));
		candidate_upass = PadQuotes(request.getParameter("txt_candidate_password"));
		candidate_mobileno1 = PadQuotes(request.getParameter("txt_candidate_mobileno1"));
		candidate_mobileno2 = PadQuotes(request.getParameter("txt_candidate_mobileno2"));
		candidate_email1 = PadQuotes(request.getParameter("txt_candidate_email1"));
		candidate_email2 = PadQuotes(request.getParameter("txt_candidate_email2"));
		candidate_gender = PadQuotes(request.getParameter("dr_candidate_gender"));
		candidate_dob = PadQuotes(request.getParameter("date_candidate_dob"));
		SOP("candidate dob=="+ candidate_dob);
		candidate_married = PadQuotes(request.getParameter("dr_candidate_married"));
		candidate_physically_challenged = CheckBoxValue(PadQuotes(request.getParameter("chk_physically_challenged")));
		candidate_address = PadQuotes(request.getParameter("textarea_candidate_address")); 
		candidate_aadhaar_no = PadQuotes(request.getParameter("txt_candidate_aadhaar"));
		candidate_active = CheckBoxValue(PadQuotes(request.getParameter("chk_active")));
		candidate_notes = PadQuotes(request.getParameter("txt_candidate_notes"));
		candidate_qualification_id = CNumeric(PadQuotes(request.getParameter("dr_candidate_qualification_id")));
		candidate_specialization_id = CNumeric(PadQuotes(request.getParameter("dr_candidate_specialization_id")));
		candidate_yop = CNumeric(PadQuotes(request.getParameter("dr_candidate_passingyear_id")));
		candidate_institute_id = CNumeric(PadQuotes(request.getParameter("dr_candidate_institute_id")));
		candidate_experience= CNumeric(PadQuotes(request.getParameter("dr_candidate_expr_id")));
		candidate_current_sal = PadQuotes(request.getParameter("txt_candidate_cur_sal"));
		candidate_expected_sal = PadQuotes(request.getParameter("txt_candidate_exp_sal"));
		candidate_sector_id = CNumeric(PadQuotes(request.getParameter("dr_candidate_sector_id")));
		candidate_functionalarea_id1 = CNumeric(PadQuotes(request.getParameter("dr_candidate_functionalarea1_id")));
		candidate_role_id1 = CNumeric(PadQuotes(request.getParameter("dr_candidate_role1_id")));
		candidate_functionalarea_id2 = CNumeric(PadQuotes(request.getParameter("dr_candidate_functionalarea2_id")));
		candidate_role_id2 = CNumeric(PadQuotes(request.getParameter("dr_candidate_role2_id")));
		candidate_functionalarea_id3 = CNumeric(PadQuotes(request.getParameter("dr_candidate_functionalarea3_id")));
		candidate_role_id3 = CNumeric(PadQuotes(request.getParameter("dr_candidate_role3_id")));
		candidate_area_id = CNumeric(PadQuotes(request.getParameter("dr_candidate_area_id")));
		candidate_curcity_id = CNumeric(PadQuotes(request.getParameter("dr_candidate_currentcity_id")));
		candidate_prefcity_id1 = CNumeric(PadQuotes(request.getParameter("dr_candidate_prefcity1_id")));
		candidate_prefcity_id2 = CNumeric(PadQuotes(request.getParameter("dr_candidate_prefcity2_id")));
		candidate_prefcity_id3 = CNumeric(PadQuotes(request.getParameter("dr_candidate_prefcity3_id")));
		candidate_skillset = PadQuotes(request.getParameter("textarea_candidate_skillset"));
		
	}

	protected void CheckForm() {
		
		if (candidate_fname.equals("")) {
			msg += "Please enter First Fname!<br>";
		}
		if (candidate_lname.equals("")) {
			msg += "Please enter Last Name!<br>";
		}
		if (candidate_upass.equals("")) {
			msg += "Please enter Password!<br>";
		}
		if (candidate_mobileno1.equals("")) {
			msg += "Please enter Mobile No.!<br>";
		}
		if (candidate_email1.equals("")) {
			msg += "Please enter Email!<br>";
		}
		if (candidate_gender.equals("0")) {
			msg += "Please select Gender!<br>";
		}
		if (candidate_dob.equals("")) {
			msg += "Please enter DOB!<br>";
		}
		if (candidate_married.equals("0")) {
			msg += "Please choose Marital Status!<br>";
		}
		if (candidate_address.equals("")) {
			msg += "Please enter address<br>!";
		}
		if (candidate_aadhaar_no.equals("")) {
			msg += "Please enter Aadhar No.!<br>";
		}
		if(candidate_qualification_id.equals("0")) {
			msg += "Please select Qualification!<br>";
		}
		if(candidate_specialization_id.equals("0")) {
			msg += "Please select Specialization!<br>";
		}
		if(candidate_yop.equals("0")) {
			msg += "Please select Passing Year!<br>";
		}
		if(candidate_institute_id.equals("0")) {
			msg += "Please select Institute!<br>";
		}
		if(candidate_experience.equals("0")) {
			msg += "Please select Exprereince!<br>";
		}
		if(candidate_current_sal.equals("0")) {
			msg += "Please enter Current Salary!<br>";
		}
		if(candidate_expected_sal.equals("0")) {
			msg += "Please enter Exprected Salary!<br>";
		}
		if(candidate_sector_id.equals("0")) {
			msg += "Please select Sector!<br>";
		}
		if(candidate_functionalarea_id1.equals("0")) {
			msg += "Please select Funcational Area 1!<br>";
		}
		if(candidate_role_id1.equals("0")) {
			msg += "Please select Role 1!<br>";
		}
		if(candidate_area_id.equals("0")) {
			msg += "Please select Area!<br>";
		}
		
		if(candidate_curcity_id.equals("0")) {
			msg += "Please select Current City!<br>";
		}
		if(candidate_prefcity_id1.equals("0")) {
			msg += "Please select Preferred City!<br>";
		}
		
		if (!msg.equals("")) {
			msg = "Error!<br>" + msg;
		}
	}

	protected void AddFields() throws SQLException {
		CheckForm();
		if (msg.equals("")) {
			try {
				con = connectDataBase();
				stmttx = con.createStatement();
				Strsql = "INSERT INTO candidatedb.rooman_candidate_data " 
						+ " (" 
						+ " candidate_fname, " 
						+ " candidate_lname, "
						+ " candidate_uuid, "
						+ " candidate_upass, "
						+ " candidate_mobile1, "
						+ " candidate_mobile2, "
						+ " candidate_email1, "
						+ " candidate_email2, "
						+ " candidate_gender, "
						+ " candidate_dob, "
						+ " candidate_married, "
						+ " candidate_physically_challenged, "
						+ " candidate_address, "
						+ " candidate_aadhaar_no, "
						+ " candidate_active, "
						+ " candidate_notes, "
						+ " candidate_qualification_id, "
						+ " candidate_specialization_id, "
						+ " candidate_yop, "
						+ " candidate_institute_id, "
						+ " candidate_experience, "
						+ " candidate_current_sal,"
						+ " candidate_expected_sal, "
						+ " candidate_sector_id, "
						+ " candidate_functionalarea_id1, "
						+ " candidate_role_id1, "
						+ " candidate_functionalarea_id2, "
						+ " candidate_role_id2, "
						+ " candidate_functionalarea_id3, "
						+ " candidate_role_id3, "
						+ " candidate_area_id, "
						+ " candidate_curcity_id, "
						+ " candidate_prefcity_id1, "
						+ " candidate_prefcity_id2, "
						+ " candidate_prefcity_id3, "
						+ " candidate_skillset"
						+ " )" 
						+ " VALUES ("
						+ "'" + candidate_fname + "'," 
						+ "'" + candidate_lname + "'," 
						+ "UUID(),"
						+ "'" + candidate_upass + "'," 
						+ "'" + candidate_mobileno1 + "'," 
						+ "'" + candidate_mobileno2 + "'," 
						+ "'" + candidate_email1 + "'," 
						+ "'" + candidate_email2 + "'," 
						+ "'" + candidate_gender + "',"
						+ "'" + ConvertShortDateToStr(candidate_dob) + "',"
						+ "'" + candidate_married + "',"
						+ "'" + candidate_physically_challenged + "',"
						+ "'" + candidate_address + "',"
						+ "'" + candidate_aadhaar_no + "',"
						+ "'" + candidate_active + "',"
						+ "'" + candidate_notes + "',"
						+ "'" + candidate_qualification_id + "',"
						+ "'" + candidate_specialization_id + "',"
						+ "'" + candidate_yop + "',"
						+ "'" + candidate_institute_id + "',"
						+ "'" + candidate_experience + "',"
						+ "'" + candidate_current_sal + "',"
						+ "'" + candidate_expected_sal + "',"
						+ "'" + candidate_sector_id + "',"
						+ "'" + candidate_functionalarea_id1 + "',"
						+ "'" + candidate_role_id1 + "',"
						+ "'" + candidate_functionalarea_id2 + "',"
						+ "'" + candidate_role_id2 + "',"
						+ "'" + candidate_functionalarea_id3 + "',"
						+ "'" + candidate_role_id3 + "',"
						+ "'" + candidate_area_id + "',"
						+ "'" + candidate_curcity_id + "',"
						+ "'" + candidate_prefcity_id1 + "',"
						+ "'" + candidate_prefcity_id2 + "',"
						+ "'" + candidate_prefcity_id3 + "',"
						+ "'" + candidate_skillset + "'"
						+ ")";

//				 SOP("Insert eval query===" + StrSql);
				candidate_id = UpdateQueryReturnID(Strsql);

			} catch (Exception e) {
				SOPError("Candidate Placement===" + this.getClass().getName());
				SOPError("Error in " + new Exception().getStackTrace()[0].getMethodName() + ": " + e);
			} finally {
				stmttx.close();
				if (con != null && !con.isClosed()) {
					con.close();
				}
			}
		}
	}

	protected void UpdateFields() throws SQLException {
		CheckForm();
		if (msg.equals("")) {
			try {
				con = connectDataBase();
				con.setAutoCommit(false);
				stmttx = con.createStatement();

				Strsql =  " UPDATE candidatedb.rooman_candidate_data" 
				        + " SET"
						+ " candidate_fname = '" + candidate_fname + "',"
						+ " candidate_lname = '" + candidate_lname + "',"
						+ " candidate_upass = '" + candidate_upass + "',"
					 	+ " candidate_physically_challenged = '" + candidate_physically_challenged + "',"
					 	+ " candidate_mobile1 = '" + candidate_mobileno1 + "',"
						+ " candidate_email1 = '" + candidate_email1 + "',"
						+ " candidate_mobile2 = '" + candidate_mobileno2 + "',"
						+ " candidate_email2 = '" + candidate_email2 + "',"
					 	+ " candidate_gender  = '" + candidate_gender + "',"
					 	+ " candidate_dob = '" + ConvertShortDateToStr(candidate_dob) + "',"
					 	+ " candidate_married = '" + candidate_married + "',"
					 	+ " candidate_area_id = " + candidate_area_id + ","
						+ " candidate_address = '" + candidate_address + "',"
						+ " candidate_aadhaar_no = '" + candidate_aadhaar_no + "',"
						+ " candidate_active = '" + candidate_active + "',"
						+ " candidate_notes = '" + candidate_notes + "',"
						+ " candidate_qualification_id = " + candidate_qualification_id + ","
						+ " candidate_specialization_id = " + candidate_specialization_id + ","
						+ " candidate_yop = '" + candidate_yop + "',"
						+ " candidate_institute_id = " + candidate_institute_id + ","
						+ " candidate_experience = " + candidate_experience + ","
						+ " candidate_current_sal = '" + candidate_current_sal + "',"
						+ " candidate_expected_sal = '" + candidate_expected_sal + "',"
						+ " candidate_sector_id= '" + candidate_sector_id + "',"
						+ " candidate_functionalarea_id1 = " + candidate_functionalarea_id1 + ","
						+ " candidate_functionalarea_id2 = " + candidate_functionalarea_id2 + ","
						+ " candidate_functionalarea_id3 = " + candidate_functionalarea_id3 + ","
						+ " candidate_role_id1 = " + candidate_role_id1 + ","
						+ " candidate_role_id2 = " + candidate_role_id2 + ","
						+ " candidate_role_id3 = " + candidate_role_id3 + ","
						+ " candidate_curcity_id = " + candidate_curcity_id + ","
						+ " candidate_prefcity_id1 = " + candidate_prefcity_id1 + ","
						+ " candidate_prefcity_id2 = " + candidate_prefcity_id2 + ","
						+ " candidate_prefcity_id3 = " + candidate_prefcity_id3 + ","
						+ " candidate_skillset = '" + candidate_skillset + "'"
						+ " WHERE candidate_id = " + candidate_id + "";

				 SOP("update candidate===" + Strsql);
				stmttx.addBatch(Strsql);
//				SOP("UPDATEEE=" + Strsql);
				stmttx.executeBatch();
				con.commit();
			} catch (Exception e) {
				if (con.isClosed()) {
					SOPError("conn is closed.....");
				}
				if (!con.isClosed() && con != null) {
					con.rollback();
					SOPError("connection rollback...\n sql--" + Strsql);
				}
				msg = "<br>Transaction Error!";
				SOPError(this.getClass().getName());
				SOPError("Error in " + new Exception().getStackTrace()[0].getMethodName() + ": " + e);
			} finally {
				stmttx.close();
				con.commit();
				if (con != null && !con.isClosed()) {
					con.close();
				}
			}
		}
	}

	protected void DeleteFields() {
		SOP("inside delete feilds");
		Strsql = "SELECT COUNT(eval_id)"
				+ " FROM candidatedb.rooman_candidate_eval "
				+ " WHERE eval_candidate_id = " + candidate_id + "";
		if (!ExecuteQuery(Strsql).equals("0")) {
			msg += "<br>Candidate is associated with Evaluation(s)!";
		}

		if (msg.equals("")) {
			try {
				Strsql = "DELETE FROM candidatedb.rooman_candidate_data"
						+ " WHERE candidate_id = " + candidate_id + "";
				updateQuery(Strsql);

			} catch (Exception ex) {
				SOPError(this.getClass().getName());
				SOPError("Error in " + new Exception().getStackTrace()[0].getMethodName() + ": " + ex);
			}
		}
	}

	protected void PopulateFields() {
		try {
			
				Strsql = " SELECT candidate_id, candidate_title_id, "
						+ " COALESCE(candidate_fname,'') AS candidate_fname, COALESCE(candidate_lname,'') AS candidate_lname, candidate_upass, "
						+ " candidate_qualification_id, candidate_specialization_id,  candidate_institute_id, candidate_mobile1, candidate_email1, "
						+ " COALESCE(candidate_mobile2,'') AS candidate_mobile2, COALESCE(candidate_email2,'') AS candidate_email2, "
						+ " candidate_married, candidate_gender, candidate_address, candidate_area_id, candidate_experience, "
						+ " candidate_curcity_id, candidate_prefcity_id1, candidate_prefcity_id2, candidate_prefcity_id3, candidate_expected_sal, COALESCE(candidate_notes,'') AS candidate_notes, "
						+ " candidate_fresher, candidate_current_sal, candidate_lang, candidate_skillset, candidate_active, candidate_physically_challenged, "
						+ " candidate_aadhaar_no, candidate_dob, candidate_yop, candidate_sector_id, candidate_functionalarea_id1, candidate_functionalarea_id2, "
						+ " candidate_functionalarea_id3, candidate_role_id1, candidate_role_id2, candidate_role_id3, candidate_register_date "
						+ " FROM candidatedb.rooman_candidate_data "
						+ " WHERE candidate_id = " + candidate_id + "";
				SOP("StrSql PopulateFields--= " + Strsql);
			
			 CachedRowSet crs = processQuery(Strsql, 0);

			while (crs.next()) {
				candidate_id = crs.getString("candidate_id");
				candidate_fname = crs.getString("candidate_fname");
				candidate_lname = crs.getString("candidate_lname");
				candidate_upass = crs.getString("candidate_upass");
				candidate_physically_challenged = crs.getString("candidate_physically_challenged");
				candidate_mobileno1 = crs.getString("candidate_mobile1");
				candidate_mobileno2 = crs.getString("candidate_mobile2");
				candidate_email1 = crs.getString("candidate_email1");
				candidate_email2 = crs.getString("candidate_email2");
				candidate_gender = crs.getString("candidate_gender");
				candidate_dob = strToShortDate(crs.getString("candidate_dob"));
				candidate_married = crs.getString("candidate_married");
				candidate_area_id = crs.getString("candidate_area_id");
				candidate_address = crs.getString("candidate_address");
				candidate_aadhaar_no = crs.getString("candidate_aadhaar_no");
				candidate_active = crs.getString("candidate_active");
				candidate_notes = crs.getString("candidate_notes");
				candidate_qualification_id = crs.getString("candidate_qualification_id");
				candidate_specialization_id = crs.getString("candidate_specialization_id");
				candidate_yop = crs.getString("candidate_yop");
				candidate_institute_id = crs.getString("candidate_institute_id");
				candidate_experience = crs.getString("candidate_experience");
				candidate_current_sal = crs.getString("candidate_current_sal");
				candidate_expected_sal = crs.getString("candidate_expected_sal");
				candidate_sector_id = crs.getString("candidate_sector_id");
				candidate_functionalarea_id1 = crs.getString("candidate_functionalarea_id1");
				candidate_functionalarea_id2 = crs.getString("candidate_functionalarea_id2");
				candidate_functionalarea_id3 = crs.getString("candidate_functionalarea_id3");
				candidate_role_id1 = crs.getString("candidate_role_id1");
				candidate_role_id2 = crs.getString("candidate_role_id2");
				candidate_role_id3 = crs.getString("candidate_role_id3");
				candidate_curcity_id = crs.getString("candidate_curcity_id");
				candidate_prefcity_id1 = crs.getString("candidate_prefcity_id1");
				candidate_prefcity_id2 = crs.getString("candidate_prefcity_id2");
				candidate_prefcity_id3 = crs.getString("candidate_prefcity_id3");
				candidate_skillset = crs.getString("candidate_skillset");
			}
			crs.close();
		} catch (Exception ex) {
			SOPError(this.getClass().getName());
			SOPError("Error in " + new Exception().getStackTrace()[0].getMethodName() + ": " + ex);
		}
	}
	
	
	public String PopulateGender() {

		String sex = "<select name=\"dr_candidate_gender\" class=\"form-control\" id=\"dr_candidate_gender\">";
		sex += "<option value = 0>Select</option>";
		sex = sex + "<option value = 1" + Selectdrop(1, candidate_gender) + ">Male</option>\n";
		sex = sex + "<option value = 2" + Selectdrop(2, candidate_gender) + ">Female</option>\n";
		sex += "</select>";
		return sex;
	}
	
	public String PopulateMarried() {

		String married = "<select name=\"dr_candidate_married\" class=\"form-control\" id=\"dr_candidate_married\">";
		married += "<option value = 0>Select</option>";
		married = married + "<option value = 1" + Selectdrop(1, candidate_married) + ">Married</option>\n";
		married = married + "<option value = 2" + Selectdrop(2, candidate_married) + ">Single</option>\n";
		married += "</select>";
		return married;
	}
	
	
	public String PopulateQualification() {
		StringBuilder Str = new StringBuilder();
		try {
			Strsql = "SELECT qualification_id, qualification_name"
					+ " FROM candidatedb.rooman_qualification"
					+ " WHERE 1=1"
//					+ " AND qualification_candidate = 1"
					+ " GROUP BY qualification_id"
					+ " ORDER BY qualification_name";
			//System.out.println("PopulateQualification==" + Strsql);
			CachedRowSet crs = processQuery(Strsql, 0);
			//System.out.println("crs=="+ crs); 
			Str.append("<select name=\"dr_candidate_qualification_id\" class=\"form-control\" id=\"dr_candidate_qualification_id\">");
			Str.append("<option value=0> Select </option>\n");
			while (crs.next()) {
				Str.append("<option value=").append(crs.getString("qualification_id"));
				Str.append(StrSelectdrop(crs.getString("qualification_id"), candidate_qualification_id));
				Str.append(">").append(crs.getString("qualification_name")).append("</option>\n");
			}
			Str.append("</select>");
			crs.close();
			return Str.toString();
		} catch (Exception ex) {
			//System.out.println(this.getClass().getName());
			//System.out.println("Error in " + new Exception().getStackTrace()[0].getMethodName() + ": " + ex);
			return "";
		}
	}
	
	public String PopulateSpecialization(String qualification_id) {
		StringBuilder Str = new StringBuilder();
		try {
			Strsql = "SELECT specialization_id, specialization_name"
					+ " FROM candidatedb.rooman_specialization"
					+ " WHERE specialization_qualification_id='" + qualification_id + "'"
					+ " GROUP BY specialization_id"
					+ " ORDER BY specialization_name";
			
			//System.out.println("PopulateSpecialization=ajax =" + Strsql);
			CachedRowSet crs = processQuery(Strsql, 0);
			Str.append("<select name=\"dr_candidate_specialization_id\" class=\"form-control\" id=\"dr_candidate_specialization_id\">");
			Str.append("<option value=0> Select </option>\n");
			while (crs.next()) {
				Str.append("<option value=").append(crs.getString("specialization_id"));
				Str.append(StrSelectdrop(crs.getString("specialization_id"), candidate_specialization_id));
				Str.append(">").append(crs.getString("specialization_name")).append("</option>\n");
			}
			Str.append("</select>");
			crs.close();
		} catch (Exception ex) {
			//System.out.println(this.getClass().getName());
			//System.out.println("Error in " + new Exception().getStackTrace()[0].getMethodName() + ": " + ex);
			return "";
		}
		return Str.toString();
	}

	public String PopulatePassingYear() {
		StringBuilder Str = new StringBuilder();
		try {
			Str.append("<select name=\"dr_candidate_passingyear_id\" class=\"form-control\" id=\"dr_candidate_passingyear_id\">");
			Str.append("<option value=0> Select YOP</option>\n");
				for(int i = 2010; i<2018; i++) {
					Str.append("<option value=").append(i).append(Selectdrop(i, candidate_yop)).append(">"); 
					Str.append(i).append("</option>\n");
				}
			Str.append("</select>");
			return Str.toString();
		} catch (Exception ex) {
			//System.out.println(this.getClass().getName());
			//System.out.println("Error in " + new Exception().getStackTrace()[0].getMethodName() + ": " + ex);
			return "";
		}
	}
	
	public String PopulateInstitutes() {
		StringBuilder Str = new StringBuilder();
		try {
			Strsql = "SELECT institute_id, institute_name"
					+ " FROM candidatedb.rooman_institute"
					+ " WHERE 1=1"
					+ " GROUP BY institute_id"
					+ " ORDER BY institute_name";
//			//System.out.println("PopulateInstitute==" + Strsql);
			CachedRowSet crs = processQuery(Strsql, 0);

			Str.append("<select name=\"dr_candidate_institute_id\" class=\"form-control\" id=\"dr_candidate_institute_id\">");
			Str.append("<option value=0> Select </option>\n");
			while (crs.next()) {
				Str.append("<option value=").append(crs.getString("institute_id"));
				Str.append(StrSelectdrop(crs.getString("institute_id"), candidate_institute_id));
				Str.append(">").append(crs.getString("institute_name")).append("</option>\n");
			}
			Str.append("</select>");
			crs.close();
			return Str.toString();
		} catch (Exception ex) {
			//System.out.println(this.getClass().getName());
			//System.out.println("Error in " + new Exception().getStackTrace()[0].getMethodName() + ": " + ex);
			return "";
		}
	}
	
	public String PopulateExperience() {
		StringBuilder Str = new StringBuilder();
		try {
			Str.append("<select name=\"dr_candidate_expr_id\" class=\"form-control\" id=\"dr_candidate_expr_id\">");
			Str.append("<option value=0> Select Experience</option>\n");
			Str.append("<option value=1> Fresher</option>\n");
				for(int i = 2; i<11; i++) {
					Str.append("<option value=").append(i).append(Selectdrop(i, candidate_experience)).append(">"); 
					Str.append(i + "Year(s)").append("</option>\n");
				}
			Str.append("</select>");
			return Str.toString();
		} catch (Exception ex) {
			//System.out.println(this.getClass().getName());
			//System.out.println("Error in " + new Exception().getStackTrace()[0].getMethodName() + ": " + ex);
			return "";
		}
	}
	
	public String PopulateSector() {
		StringBuilder Str = new StringBuilder();
		try {
			Strsql = "SELECT sector_id, sector_name"
					+ " FROM candidatedb.rooman_sector"
					+ " WHERE 1=1"
					+ " GROUP BY sector_id"
					+ " ORDER BY sector_name";
//			//System.out.println("PopulateSector==" + Strsql);
			CachedRowSet crs = processQuery(Strsql, 0);

			Str.append("<select name=\"dr_candidate_sector_id\" class=\"form-control\" id=\"dr_candidate_sector_id\">");
			Str.append("<option value=0> Select </option>\n");
			while (crs.next()) {
				Str.append("<option value=").append(crs.getString("sector_id"));
				Str.append(StrSelectdrop(crs.getString("sector_id"), candidate_sector_id));
				Str.append(">").append(crs.getString("sector_name")).append("</option>\n");
			}
			Str.append("</select>");
			crs.close();
			return Str.toString();
		} catch (Exception ex) {
			//System.out.println(this.getClass().getName());
			//System.out.println("Error in " + new Exception().getStackTrace()[0].getMethodName() + ": " + ex);
			return "";
		}
	}

	public String PopulateFAOne() {
		StringBuilder Str = new StringBuilder();
		try {
			Strsql = "SELECT functionalarea_id, functionalarea_name"
					+ " FROM candidatedb.rooman_functionalarea"
					+ " WHERE 1=1"
					+ " GROUP BY functionalarea_id"
					+ " ORDER BY functionalarea_name";
//			//System.out.println("PopulateFAOne==" + Strsql);
			CachedRowSet crs = processQuery(Strsql, 0);

			Str.append("<select name=\"dr_candidate_functionalarea1_id\" class=\"form-control\" id=\"dr_candidate_functionalarea1_id\">");
			Str.append("<option value=0> Select </option>\n");
			while (crs.next()) {
				Str.append("<option value=").append(crs.getString("functionalarea_id"));
				Str.append(StrSelectdrop(crs.getString("functionalarea_id"), candidate_functionalarea_id1));
				Str.append(">").append(crs.getString("functionalarea_name")).append("</option>\n");
			}
			Str.append("</select>");
			crs.close();
			return Str.toString();
		} catch (Exception ex) {
			//System.out.println(this.getClass().getName());
			//System.out.println("Error in " + new Exception().getStackTrace()[0].getMethodName() + ": " + ex);
			return "";
		}
	}
	
	
	public String PopulateRoleOne(String fa_id1) {
		StringBuilder Str = new StringBuilder();
		String Strsql1 = "";
		try {
			
			Strsql1 = "SELECT rolegroup_id, rolegroup_name"
					+ " FROM candidatedb.rooman_rolegroup"
					+ " WHERE 1=1 ";
			
			if(!fa_id1.equals("0")) {
				Strsql1 +=  " AND rolegroup_functionalarea_id='" + fa_id1 + "'";
			}
			
			Strsql1 +=   " ORDER BY rolegroup_name";
			CachedRowSet crs = processQuery(Strsql1, 0);
			Str.append("<select name=\"dr_candidate_role1_id\" class=\"form-control\" id=\"dr_candidate_role1_id\">");
			Str.append("<option value=0> Select </option>\n");
			while (crs.next()) {
				Str.append("<optgroup label='" + crs.getString("rolegroup_name") + "'>");
				
				Strsql = "SELECT role_id, role_name"
						+ " FROM candidatedb.rooman_role"
						+ " WHERE 1=1"
						+ " AND role_rolegroup_id='" + crs.getString("rolegroup_id") + "'"
						+ " GROUP BY role_id"
						+ " ORDER BY role_name";
				
//				//System.out.println("PopulateRoleOne==" + Strsql);
				CachedRowSet crs1 = processQuery(Strsql, 0);
				while (crs1.next()) {
				Str.append("<option value=").append(crs1.getString("role_id"));
				Str.append(StrSelectdrop(crs1.getString("role_id"), candidate_role_id1));
				Str.append(">").append(crs1.getString("role_name")).append("</option>\n");
				}
				Str.append("</optgroup>");
				crs1.close();
			}
			Str.append("</select>");
			crs.close();
			return Str.toString();
		} catch (Exception ex) {
			//System.out.println(this.getClass().getName());
			//System.out.println("Error in " + new Exception().getStackTrace()[0].getMethodName() + ": " + ex);
			return "";
		}
	}
	
	
	public String PopulateFATwo() {
		StringBuilder Str = new StringBuilder();
		try {
			Strsql = "SELECT functionalarea_id, functionalarea_name"
					+ " FROM candidatedb.rooman_functionalarea"
					+ " WHERE 1=1"
					+ " GROUP BY functionalarea_id"
					+ " ORDER BY functionalarea_name";
//			//System.out.println("PopulateFAtwo==" + Strsql);
			CachedRowSet crs = processQuery(Strsql, 0);

			Str.append("<select name=\"dr_candidate_functionalarea2_id\" class=\"form-control\" id=\"dr_candidate_functionalarea2_id\">");
			Str.append("<option value=0> Select </option>\n");
			while (crs.next()) {
				Str.append("<option value=").append(crs.getString("functionalarea_id"));
				Str.append(StrSelectdrop(crs.getString("functionalarea_id"), candidate_functionalarea_id2));
				Str.append(">").append(crs.getString("functionalarea_name")).append("</option>\n");
			}
			Str.append("</select>");
			crs.close();
			return Str.toString();
		} catch (Exception ex) {
			//System.out.println(this.getClass().getName());
			//System.out.println("Error in " + new Exception().getStackTrace()[0].getMethodName() + ": " + ex);
			return "";
		}
	}
	
	
	public String PopulateRoleTwo(String fa_id2) {
		StringBuilder Str = new StringBuilder();
		String Strsql1 = "";
		try {
			
			Strsql1 = "SELECT rolegroup_id, rolegroup_name"
					+ " FROM candidatedb.rooman_rolegroup"
					+ " WHERE 1 = 1 ";
			if(!fa_id2.equals("0")) {
				Strsql1 +=  " AND rolegroup_functionalarea_id='" + fa_id2 + "'";
			}
			Strsql1 +=   " ORDER BY rolegroup_name";
			
			CachedRowSet crs = processQuery(Strsql1, 0);
			Str.append("<select name=\"dr_candidate_role2_id\" class=\"form-control\" id=\"dr_candidate_role2_id\">");
			Str.append("<option value=0> Select </option>\n");
			while (crs.next()) {
				Str.append("<optgroup label='" + crs.getString("rolegroup_name") + "'>");
				
				Strsql = "SELECT role_id, role_name"
						+ " FROM candidatedb.rooman_role"
						+ " WHERE 1=1"
						+ " AND role_rolegroup_id='" + crs.getString("rolegroup_id") + "'"
						+ " GROUP BY role_id"
						+ " ORDER BY role_name";
				
//				//System.out.println("PopulateRoleOne==" + Strsql);
				CachedRowSet crs1 = processQuery(Strsql, 0);
				while (crs1.next()) {
				Str.append("<option value=").append(crs1.getString("role_id"));
				Str.append(StrSelectdrop(crs1.getString("role_id"), candidate_role_id2));
				Str.append(">").append(crs1.getString("role_name")).append("</option>\n");
				}
				Str.append("</optgroup>");
				crs1.close();
			}
			Str.append("</select>");
			crs.close();
			return Str.toString();
		} catch (Exception ex) {
			//System.out.println(this.getClass().getName());
			//System.out.println("Error in " + new Exception().getStackTrace()[0].getMethodName() + ": " + ex);
			return "";
		}
	}
	
	public String PopulateFAThree() {
		StringBuilder Str = new StringBuilder();
		try {
			Strsql = "SELECT functionalarea_id, functionalarea_name"
					+ " FROM candidatedb.rooman_functionalarea"
					+ " WHERE 1=1"
					+ " GROUP BY functionalarea_id"
					+ " ORDER BY functionalarea_name";
//			//System.out.println("PopulateFAThree==" + Strsql);
			CachedRowSet crs = processQuery(Strsql, 0);

			Str.append("<select name=\"dr_candidate_functionalarea3_id\" class=\"form-control\" id=\"dr_candidate_functionalarea3_id\">");
			Str.append("<option value=0> Select </option>\n");
			while (crs.next()) {
				Str.append("<option value=").append(crs.getString("functionalarea_id"));
				Str.append(StrSelectdrop(crs.getString("functionalarea_id"), candidate_functionalarea_id3));
				Str.append(">").append(crs.getString("functionalarea_name")).append("</option>\n");
			}
			Str.append("</select>");
			crs.close();
			return Str.toString();
		} catch (Exception ex) {
			//System.out.println(this.getClass().getName());
			//System.out.println("Error in " + new Exception().getStackTrace()[0].getMethodName() + ": " + ex);
			return "";
		}
	}
	
	
	public String PopulateRoleThree(String fa_id3) {
		StringBuilder Str = new StringBuilder();
		String Strsql1 = "";
		try {
			
			Strsql1 = "SELECT rolegroup_id, rolegroup_name"
					+ " FROM candidatedb.rooman_rolegroup"
					+ " WHERE 1=1 ";
			
			if(!fa_id3.equals("0")) {
				Strsql1 +=  " AND rolegroup_functionalarea_id='" + fa_id3 + "'";
			}
			
			Strsql1 +=   " ORDER BY rolegroup_name";
			CachedRowSet crs = processQuery(Strsql1, 0);
			Str.append("<select name=\"dr_candidate_role3_id\" class=\"form-control\" id=\"dr_candidate_role3_id\">");
			Str.append("<option value=0> Select </option>\n");
			while (crs.next()) {
				Str.append("<optgroup label='" + crs.getString("rolegroup_name") + "'>");
				
				Strsql = "SELECT role_id, role_name"
						+ " FROM candidatedb.rooman_role"
						+ " WHERE 1=1"
						+ " AND role_rolegroup_id='" + crs.getString("rolegroup_id") + "'"
						+ " GROUP BY role_id"
						+ " ORDER BY role_name";
				
//				//System.out.println("PopulateRoleOne==" + Strsql);
				CachedRowSet crs1 = processQuery(Strsql, 0);
				while (crs1.next()) {
				Str.append("<option value=").append(crs1.getString("role_id"));
				Str.append(StrSelectdrop(crs1.getString("role_id"), candidate_role_id3));
				Str.append(">").append(crs1.getString("role_name")).append("</option>\n");
				}
				Str.append("</optgroup>");
				crs1.close();
			}
			Str.append("</select>");
			crs.close();
			return Str.toString();
		} catch (Exception ex) {
			//System.out.println(this.getClass().getName());
			//System.out.println("Error in " + new Exception().getStackTrace()[0].getMethodName() + ": " + ex);
			return "";
		}
	}
	
	
	public String PopulateArea() {
		StringBuilder Str = new StringBuilder();
		try {
			Strsql = "SELECT area_id, area_name"
					+ " FROM candidatedb.rooman_area"
					+ " WHERE 1=1"
					+ " GROUP BY area_id"
					+ " ORDER BY area_name";
			System.out.println("PopulateCurrentLocation==" + Strsql);
			CachedRowSet crs = processQuery(Strsql, 0);

			Str.append("<select name=\"dr_candidate_area_id\" class=\"form-control\" id=\"dr_candidate_area_id\">");
			Str.append("<option value=0> Select </option>\n");
			while (crs.next()) {
				Str.append("<option value=").append(crs.getString("area_id"));
				Str.append(StrSelectdrop(crs.getString("area_id"), candidate_area_id));
				Str.append(">").append(crs.getString("area_name")).append("</option>\n");
			}
			Str.append("</select>");
			crs.close();
			
		} catch (Exception ex) {
			System.out.println(this.getClass().getName());
			System.out.println("Error in " + new Exception().getStackTrace()[0].getMethodName() + ": " + ex);
		}
		return Str.toString();
	}
	
	public String PopulateCurrentLocation() {
		StringBuilder Str = new StringBuilder();
		try {
			Strsql = "SELECT city_id, city_name"
					+ " FROM candidatedb.rooman_city"
					+ " WHERE 1=1"
					+ " GROUP BY city_id"
					+ " ORDER BY city_name";
//			//System.out.println("PopulateCurrentLocation==" + Strsql);
			CachedRowSet crs = processQuery(Strsql, 0);

			Str.append("<select name=\"dr_candidate_currentcity_id\" class=\"form-control\" id=\"dr_candidate_currentcity_id\">");
			Str.append("<option value=0> Select </option>\n");
			while (crs.next()) {
				Str.append("<option value=").append(crs.getString("city_id"));
				Str.append(StrSelectdrop(crs.getString("city_id"), candidate_curcity_id));
				Str.append(">").append(crs.getString("city_name")).append("</option>\n");
			}
			Str.append("</select>");
			crs.close();
			return Str.toString();
		} catch (Exception ex) {
			//System.out.println(this.getClass().getName());
			//System.out.println("Error in " + new Exception().getStackTrace()[0].getMethodName() + ": " + ex);
			return "";
		}
	}
	
	public String PopulatePrefLocationOne() {
		StringBuilder Str = new StringBuilder();
		try {
			Strsql = "SELECT city_id, city_name"
					+ " FROM candidatedb.rooman_city"
					+ " WHERE 1=1"
					+ " GROUP BY city_id"
					+ " ORDER BY city_name";
//			//System.out.println("PopulatePrefLocationOne==" + Strsql);
			CachedRowSet crs = processQuery(Strsql, 0);

			Str.append("<select name=\"dr_candidate_prefcity1_id\" class=\"form-control\" id=\"dr_candidate_prefcity1_id\">");
			Str.append("<option value=0> Select </option>\n");
			while (crs.next()) {
				Str.append("<option value=").append(crs.getString("city_id"));
				Str.append(StrSelectdrop(crs.getString("city_id"), candidate_prefcity_id1));
				Str.append(">").append(crs.getString("city_name")).append("</option>\n");
			}
			Str.append("</select>");
			crs.close();
			return Str.toString();
		} catch (Exception ex) {
			//System.out.println(this.getClass().getName());
			//System.out.println("Error in " + new Exception().getStackTrace()[0].getMethodName() + ": " + ex);
			return "";
		}
	}
	
	
	public String PopulatePrefLocationTwo() {
		StringBuilder Str = new StringBuilder();
		try {
			Strsql = "SELECT city_id, city_name"
					+ " FROM candidatedb.rooman_city"
					+ " WHERE 1=1"
					+ " GROUP BY city_id"
					+ " ORDER BY city_name";
//			//System.out.println("PopulatePrefLocationTwo==" + Strsql);
			CachedRowSet crs = processQuery(Strsql, 0);

			Str.append("<select name=\"dr_candidate_prefcity2_id\" class=\"form-control\" id=\"dr_candidate_prefcity2_id\">");
			Str.append("<option value=0> Select </option>\n");
			while (crs.next()) {
				Str.append("<option value=").append(crs.getString("city_id"));
				Str.append(StrSelectdrop(crs.getString("city_id"), candidate_prefcity_id2));
				Str.append(">").append(crs.getString("city_name")).append("</option>\n");
			}
			Str.append("</select>");
			crs.close();
			return Str.toString();
		} catch (Exception ex) {
			//System.out.println(this.getClass().getName());
			//System.out.println("Error in " + new Exception().getStackTrace()[0].getMethodName() + ": " + ex);
			return "";
		}
	}
	
	
	public String PopulatePrefLocationThree() {
		StringBuilder Str = new StringBuilder();
		try {
			Strsql = "SELECT city_id, city_name"
					+ " FROM candidatedb.rooman_city"
					+ " WHERE 1=1"
					+ " GROUP BY city_id"
					+ " ORDER BY city_name";
//			//System.out.println("PopulatePrefLocationThree==" + Strsql);
			CachedRowSet crs = processQuery(Strsql, 0);

			Str.append("<select name=\"dr_candidate_prefcity3_id\" class=\"form-control\" id=\"dr_candidate_prefcity3_id\">");
			Str.append("<option value=0> Select </option>\n");
			while (crs.next()) {
				Str.append("<option value=").append(crs.getString("city_id"));
				Str.append(StrSelectdrop(crs.getString("city_id"), candidate_prefcity_id3));
				Str.append(">").append(crs.getString("city_name")).append("</option>\n");
			}
			Str.append("</select>");
			crs.close();
			return Str.toString();
		} catch (Exception ex) {
			//System.out.println(this.getClass().getName());
			//System.out.println("Error in " + new Exception().getStackTrace()[0].getMethodName() + ": " + ex);
			return "";
		}
	}
	
	
	

}
