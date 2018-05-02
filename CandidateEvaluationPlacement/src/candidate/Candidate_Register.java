package candidate;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.CachedRowSet;
import connect.Connect;

//@WebServlet("/Candidate_Register")

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

	
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Getting into dopost candidate register");
		
		candidate_fname = PadQuotes(request.getParameter("txt_candidate_fname"));
		//System.out.println("candidate_fname===" + candidate_fname);
		
		candidate_lname = PadQuotes(request.getParameter("txt_candidate_lname"));
		//System.out.println("candidate_lname===" + candidate_lname);
		
		candidate_upass = PadQuotes(request.getParameter("txt_candidate_password"));
		//System.out.println("candidate_password===" + candidate_upass);
		
		candidate_mobileno1 = PadQuotes(request.getParameter("txt_candidate_mobileno1"));
		//System.out.println("candidate_mobileno1===" + candidate_mobileno1);
		
		candidate_mobileno2 = PadQuotes(request.getParameter("txt_candidate_mobileno2"));
		//System.out.println("candidate_mobileno2===" + candidate_mobileno2);
		
		candidate_email1 = PadQuotes(request.getParameter("txt_candidate_email1"));
		//System.out.println("candidate_email1===" + candidate_email1);
		
		candidate_email2 = PadQuotes(request.getParameter("txt_candidate_email2"));
		//System.out.println("candidate_email2===" + candidate_email2);
		
		candidate_gender = PadQuotes(request.getParameter("radio_candidate_gender"));
		if (candidate_gender.equals("on") || candidate_gender.equals("1")) {
			candidate_gender = "1";
		} else {
			candidate_gender = "0";
		}
		//System.out.println("candidate_gender===" + candidate_gender);
		
		candidate_dob = PadQuotes(request.getParameter("date_candidate_dob"));
		//System.out.println("candidate_dob===" + candidate_dob);
		
		candidate_married = PadQuotes(request.getParameter("radio_candidate_marital"));
		if (candidate_married.equals("on") || candidate_married.equals("1")) {
			candidate_married = "1";
		} else {
			candidate_married = "0";
		}
		//System.out.println("candidate_married===" + candidate_married);
		
		candidate_physically_challenged = CheckBoxValue(PadQuotes(request.getParameter("chk_physically_challenged")));
		//System.out.println("candidate_physically_challenged===" + candidate_physically_challenged);
		
		candidate_address = PadQuotes(request.getParameter("textarea_candidate_address")); 
		//System.out.println("candidate_address===" + candidate_address);
		
		candidate_aadhaar_no = PadQuotes(request.getParameter("txt_candidate_aadhaar"));
		//System.out.println("candidate_aadhaar_no===" + candidate_aadhaar_no);
		
		candidate_active = CheckBoxValue(PadQuotes(request.getParameter("chk_active")));
		//System.out.println("candidate_active===" + candidate_active);
		
		candidate_notes = PadQuotes(request.getParameter("txt_candidate_notes"));
		//System.out.println("candidate_notes===" + candidate_notes);
		
		candidate_qualification_id = CNumeric(PadQuotes(request.getParameter("dr_candidate_qualification_id")));
		//System.out.println("candidate_qualification_id===" + candidate_qualification_id);
		
		candidate_specialization_id = CNumeric(PadQuotes(request.getParameter("dr_candidate_specialization_id")));
		//System.out.println("candidate_specialization_id===" + candidate_specialization_id);
		
		candidate_yop = CNumeric(PadQuotes(request.getParameter("dr_candidate_passingyear_id")));
		//System.out.println("candidate_passingyear_id===" + candidate_yop);
		
		candidate_institute_id = CNumeric(PadQuotes(request.getParameter("dr_candidate_institute_id")));
		//System.out.println("candidate_institute_id===" + candidate_institute_id);
		
		candidate_experience= CNumeric(PadQuotes(request.getParameter("dr_candidate_expr_id")));
		//System.out.println("candidate_expr_id===" + candidate_experience);
		 
		candidate_current_sal = PadQuotes(request.getParameter("txt_candidate_cur_sal"));
		//System.out.println("candidate_cur_sal===" + candidate_current_sal);
		
		candidate_expected_sal = PadQuotes(request.getParameter("txt_candidate_exp_sal"));
		//System.out.println("candidate_exp_sal===" + candidate_expected_sal);
		
		candidate_sector_id = CNumeric(PadQuotes(request.getParameter("dr_candidate_sector_id")));
		//System.out.println("candidate_sector_id===" + candidate_sector_id);
		
		candidate_functionalarea_id1 = CNumeric(PadQuotes(request.getParameter("dr_candidate_functionalarea1_id")));
		//System.out.println("candidate_functionalarea1_id===" + candidate_functionalarea_id1);
		
		candidate_role_id1 = CNumeric(PadQuotes(request.getParameter("dr_candidate_role1_id")));
		//System.out.println("candidate_role1_id===" + candidate_role_id1);
		
		candidate_functionalarea_id2 = CNumeric(PadQuotes(request.getParameter("dr_candidate_functionalarea2_id")));
		//System.out.println("candidate_functionalarea2_id===" + candidate_functionalarea_id2);
		
		candidate_role_id2 = CNumeric(PadQuotes(request.getParameter("dr_candidate_role2_id")));
		//System.out.println("candidate_role2_id===" + candidate_role_id2);
		
		candidate_functionalarea_id3 = CNumeric(PadQuotes(request.getParameter("dr_candidate_functionalarea3_id")));
		//System.out.println("candidate_functionalarea3_id===" + candidate_functionalarea_id3);
		
		candidate_role_id3 = CNumeric(PadQuotes(request.getParameter("dr_candidate_role3_id")));
		//System.out.println("candidate_role3_id===" + candidate_role_id3);
		
		candidate_curcity_id = CNumeric(PadQuotes(request.getParameter("dr_candidate_currentcity_id")));
		//System.out.println("candidate_currentcity_id===" + candidate_curcity_id);
		
		candidate_prefcity_id1 = CNumeric(PadQuotes(request.getParameter("dr_candidate_prefcity1_id")));
		//System.out.println("candidate_prefcity1_id===" + candidate_prefcity_id1);
		
		candidate_prefcity_id2 = CNumeric(PadQuotes(request.getParameter("dr_candidate_prefcity2_id")));
		//System.out.println("candidate_prefcity2_id===" + candidate_prefcity_id2);
		
		candidate_prefcity_id3 = CNumeric(PadQuotes(request.getParameter("dr_candidate_prefcity3_id")));
		//System.out.println("candidate_prefcity3_id===" + candidate_prefcity_id3);
		
		candidate_skillset = PadQuotes(request.getParameter("textarea_candidate_skillset"));
		//System.out.println("candidate_skillset===" + candidate_skillset);
		
		specialization = PadQuotes(request.getParameter("specialization"));
//		//System.out.println("specialization===" + specialization);
		
		functionalarea1 = PadQuotes(request.getParameter("fa1"));
		//System.out.println("functionalarea1===" + functionalarea1);
		
		functionalarea2 = PadQuotes(request.getParameter("fa2"));
		//System.out.println("functionalarea2===" + functionalarea2);
		
		functionalarea3 = PadQuotes(request.getParameter("fa3"));
		//System.out.println("functionalarea3===" + functionalarea3);
		
		msg = PadQuotes(request.getParameter("msg"));
		
		connectDB();
		
		checkForm();
		
		if (specialization.equals("yes")) {
			candidate_qualification_id = CNumeric(PadQuotes(request.getParameter("candidate_qualification_id")));
			msg = PopulateSpecialization(candidate_qualification_id);
			//System.out.println("msg===" + msg);
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
		
//		if (!msg.equals("")) {
//			response.sendRedirect("candidate-register.jsp?msg=" + msg);
//		} else {
			addFields(request, response);
//		}

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
	
	public String PopulateSpecialization() {
		StringBuilder Str = new StringBuilder();
		try {
			Strsql = "SELECT specialization_id, specialization_name"
					+ " FROM candidatedb.rooman_specialization"
					+ " WHERE 1=1"
					+ " GROUP BY specialization_id"
					+ " ORDER BY specialization_name";
			//System.out.println("PopulateSpecialization==" + Strsql);
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
					Str.append("<option value=").append(i).append(">"); 
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
					Str.append("<option value=").append(i).append(">"); 
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
	
	
	
	
	public String checkForm() {
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
			msg += "Please choose Gender!<br>";
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
		if(candidate_curcity_id.equals("0")) {
			msg += "Please select Current City!<br>";
		}
		if(candidate_prefcity_id1.equals("0")) {
			msg += "Please select Preferred City!<br>";
		}
		
		if (!msg.equals("")) {
			msg = "Error!<br>" + msg;
		}
		return msg;
	}

	public void addFields(HttpServletRequest request, HttpServletResponse response) {
		try {
			if (msg.equals("")) {
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
						+ "'" + candidate_dob + "',"
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
						+ "'" + candidate_curcity_id + "',"
						+ "'" + candidate_prefcity_id1 + "',"
						+ "'" + candidate_prefcity_id2 + "',"
						+ "'" + candidate_prefcity_id3 + "',"
						+ "'" + candidate_skillset + "'"
						+ ")";
					 //System.out.println("insert qry=="+Strsql);
					 int res = stmt.executeUpdate(Strsql);
//					 //System.out.println("afftected rws are==" + res);
					if (res != 0) {
						response.sendRedirect("candidate/candidate-list.jsp?msg=Candidate Registered Successfully!");
					}else {
						response.sendRedirect("candidate/candidate-register.jsp?msg=Something is not right!");
					}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}

	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
