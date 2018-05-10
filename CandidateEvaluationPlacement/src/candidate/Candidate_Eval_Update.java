package candidate;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.CachedRowSet;

import connect.Connect;

public class Candidate_Eval_Update extends Connect {
	private static final long serialVersionUID = 1L;
	public String add = "";
	public String update = "";
	public String addB = "";
	public String updateB = "";
	public String deleteB = "";
	public String status = "";
	public String msg = "";
	public String StrSql = "";
	public String candidate_id = "0";
	public String candidate_name = "";
	public String candidate_eval_notes = "";
	public String candidate_active = "1";
	public String candidate_skill_id = "0";
	public String[] candidate_subskill_id = new String[10];
	public String[] candidate_rating_id = new String[10];
	public String candidate_emp_id = "0";
	public String candidate_eval_time = "";
	public String candidate_eval_id = "0";
	public String subskill = "";
	public Connection con = null;
	Statement stmttx = null;
	public String QueryString = "";
	public DecimalFormat deci = new DecimalFormat("0.00");

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
				candidate_eval_id = CNumeric(PadQuotes(request.getParameter("candidate_eval_id")));
				candidate_name = ExecuteQuery("SELECT CONCAT(candidate_fname,' ',candidate_lname) FROM candidatedb.rooman_candidate_data WHERE candidate_id = " + candidate_id);
				subskill = PadQuotes(request.getParameter("subskill"));
//				SOP("subskill===" + subskill);

				if (subskill.equals("yes")) {
					candidate_skill_id = CNumeric(PadQuotes(request.getParameter("candidate_skill_id")));
					msg = PopulateSubSkill(candidate_skill_id);
				}
				if ("yes".equals(add)) {
					status = "Add";
					if (!"yes".equals(addB)) {
						candidate_active = "1";
					} else {
						GetValues(request, response);
						AddFields();
							if (!msg.equals("")) {
								msg = "Error!" + msg;
							} else {
								msg = "Evaluation Added Successfully!";
							}
					}
				} 
				/*else if ("yes".equals(update)) {
					status = "Update";
					if (!"yes".equals(updateB) && !"Delete Evaluation".equals(deleteB)) {
						PopulateFields();
					} else if ("yes".equals(updateB) && !"Delete Evaluation".equals(deleteB)) {
						GetValues(request, response);
							UpdateFields();
							if (!msg.equals("")) {
								msg = "Error!" + msg;
						} else {
							msg = "Error!" + "<br>" + "Access Denied! please contact Administrator";
						}
					} else if ("Delete Evaluation".equals(deleteB)) {
						GetValues(request, response);
							DeleteFields();
							if (!msg.equals("")) {
								msg = "Error!" + msg;
							} else {
								msg = "Evaluation Deleted Successfully!";
							}
					}
				}*/
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

		candidate_skill_id = CNumeric(PadQuotes(request.getParameter("dr_candidate_skill_id")));
		candidate_eval_notes = PadQuotes(request.getParameter("txt_eval_notes"));
//		SOP("candidate_skill_id=="+ candidate_skill_id);
//		SOP("candidate_eval_notes=="+ candidate_eval_notes);
		
		if (status.equals("Update")) {
			candidate_emp_id = PadQuotes(request.getParameter("dr_candidate_emp_id"));
			candidate_eval_time = PadQuotes(request.getParameter("txt_eval_time"));
		}
		for (int i = 0; i < candidate_subskill_id.length; i++) {
			candidate_subskill_id[i] = CNumeric(PadQuotes(request.getParameter("dr_candidate_subskill_id" + i)));
			candidate_rating_id[i] = PadQuotes(request.getParameter("dr_candidate_rating_id" + i));
			
//			SOP("candidate_subskill_id["+i+"]=="+ candidate_subskill_id[i]);
//			SOP("candidate_rating_id["+i+"]=="+ candidate_rating_id[i]);
		}
	}

	protected void CheckForm() {

		msg = "";
		if (candidate_skill_id.equals("0")) {
			msg += "<br>Select Skill!";
		} 
		
//		else {
//			StrSql = "SELECT eval_skill_id FROM rooman_eval WHERE eval_skill_id=" + candidate_skill_id
//					+ " AND eval_candidate_id = " + candidate_id;
//			if (!candidate_eval_id.equals("0")) {
//				StrSql += " AND eval_id != " + candidate_eval_id;
//			}
//			if (!ExecuteQuery(StrSql).equals("")) {
//				msg += "<br>Similar Skill found!";
//			}
//		}
		
		if (candidate_subskill_id[0].equals("0")) {
			if (candidate_rating_id[0].equals("")) {
				msg += "<br>Select atleast one SubSkill & Rating! ";
			} else {
				msg += "<br>Select atleast one SubSkill! ";
			}
		}else if(!candidate_subskill_id[0].equals("0")) {
			if (candidate_rating_id[0].equals("")) {
				msg += "<br>Select Rating for subskill! ";
			}
		}
		
		List<String> list = new LinkedList<String>();
		for (int i = 0; i < candidate_subskill_id.length; i++) {
			if (!candidate_subskill_id[i].equals("0")) {
				if (!candidate_subskill_id[i].equals("0")) {
					list.add(String.valueOf(candidate_subskill_id[i]));
				}
			}
		}
		Set<String> res = findDuplicates(list);
		if (res.isEmpty() == false) {
			msg += "<br>Select different SubSkills!";
		}
	}

	protected void AddFields() throws SQLException {
		CheckForm();
		String evalType= "0" ;
		if (msg.equals("")) {
			try {
				con = connectDataBase();
				con.setAutoCommit(false);
				stmttx = con.createStatement();
				if(candidate_skill_id.equals("1")){
					evalType = "1";
				}else {
					evalType = "0";
				}
				StrSql = "INSERT INTO candidatedb.rooman_candidate_eval"
						+ " ("
						+ " eval_candidate_id,"
						+ " eval_skill_id,"
						+ " eval_time,"
						+ " eval_emp_id,"						
						+ " eval_notes,"
						+ " eval_type)"
						+ " VALUES" + " ( "
						+ " '" + candidate_id + "',"
						+ " '" + candidate_skill_id + "',"
//						+ " " + ToLongDate(kknow()) + ","
						+ " '" + "20180501000000" + "',"
						+ " '1',"
						+ " '" + candidate_eval_notes + "',"
						+ " " + evalType + ")";

//				 SOP("Insert eval query===" + StrSql);
				candidate_eval_id = UpdateQueryReturnID(StrSql);
//				SOP("candidate_eval_id==" + candidate_eval_id);

				if (!candidate_eval_id.equals("0")) {
					for (int i = 0; i < candidate_subskill_id.length; i++) {
						if (!candidate_subskill_id[i].equals("0") && !candidate_rating_id[i].equals("")) {
							StrSql = "INSERT INTO candidatedb.rooman_candidate_eval_trans"
									+ " ("
									+ " evaltrans_eval_id,"
									+ " evaltrans_subskill_id,"
									+ " evaltrans_rating)"
									+ " VALUES" + " ( "
									+ " '" + candidate_eval_id + "',"
									+ " '" + candidate_subskill_id[i] + "',"
									+ " '" + candidate_rating_id[i] + "')";
//							 SOP("Insert eval trans query==11=" + StrSql);
							stmttx.addBatch(StrSql);
						}
					}
				}
				stmttx.executeBatch();
				con.commit();
			} catch (Exception e) {
				if (con.isClosed()) {
					SOPError("conn is closed.....");
				}
				if (!con.isClosed() && con != null) {
					con.rollback();
					SOPError("connection rollback...\n sql--" + StrSql);
				}
				msg = "<br>Transaction Error!";
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

				StrSql = "UPDATE rooman_eval" + " SET"
						+ " eval_skill_id = '" + candidate_skill_id + "',"
						+ " eval_emp_id = '" + candidate_emp_id + "',"
						+ " eval_time = '" + ConvertLongDateToStr(candidate_eval_time) + "',"
						+ " eval_notes = '" + candidate_eval_notes + "',"
						+ " eval_modified_date = '" + ToLongDate(kknow()) + "'"
						+ " WHERE eval_candidate_id = " + candidate_id + ""
						+ " AND eval_id = " + candidate_eval_id;
				// SOP("update candidate===" + StrSql);
				stmttx.addBatch(StrSql);

				if (!candidate_eval_id.equals("0")) {
					StrSql = "DELETE FROM rooman_eval_trans WHERE evaltrans_eval_id=" + candidate_eval_id;
					stmttx.addBatch(StrSql);
					for (int i = 0; i < candidate_subskill_id.length; i++) {
						if (!candidate_subskill_id[i].equals("0") && !candidate_rating_id[i].equals("")) {
							StrSql = "INSERT INTO rooman_eval_trans"
									+ " ("
									+ " evaltrans_eval_id,"
									+ " evaltrans_subskill_id,"
									+ " evaltrans_rating)"
									+ " VALUES" + " ( "
									+ " '" + candidate_eval_id + "',"
									+ " '" + candidate_subskill_id[i] + "',"
									+ " '" + candidate_rating_id[i] + "')";
							// SOP("Insert candidate query==11=" + StrSql);
							stmttx.addBatch(StrSql);
						}
					}
				}
				stmttx.executeBatch();
				con.commit();
			} catch (Exception e) {
				if (con.isClosed()) {
					SOPError("conn is closed.....");
				}
				if (!con.isClosed() && con != null) {
					con.rollback();
					SOPError("connection rollback...\n sql--" + StrSql);
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

		StrSql = " SELECT COUNT(interview_candidate_id)"
				+ " FROM rooman_interview "
				+ " WHERE interview_candidate_id = " + candidate_id + "";

		if (!ExecuteQuery(StrSql).equals("0")) {
			msg += "<br>Candidate is associated with  Interview(s)!";
		}
		if (msg.equals("")) {
			try {
				StrSql = "DELETE FROM rooman_eval"
						+ " WHERE eval_candidate_id = " + candidate_id + ""
						+ " AND eval_id = " + candidate_eval_id;
				updateQuery(StrSql);

				StrSql = " DELETE FROM rooman_eval_trans "
						+ " WHERE evaltrans_eval_id = " + candidate_eval_id;
				updateQuery(StrSql);
			} catch (Exception ex) {
				SOPError(this.getClass().getName());
				SOPError("Error in " + new Exception().getStackTrace()[0].getMethodName() + ": " + ex);
			}
		}
	}

	protected void PopulateFields() {
		try {
			StrSql = "SELECT eval_id, eval_candidate_id, eval_skill_id, eval_emp_id, eval_time, eval_notes, eval_entry_id, "
					+ " eval_entry_date, eval_modified_id, eval_modified_date "
					+ " FROM rooman_eval"
					+ " WHERE eval_candidate_id = " + candidate_id + ""
					+ " AND eval_id = " + candidate_eval_id;
			// SOP("StrSql PopulateFields--- " + StrSqlBreaker(StrSql));
			CachedRowSet crs = processQuery(StrSql, 0);

			while (crs.next()) {
				candidate_id = crs.getString("eval_candidate_id");
				candidate_eval_id = crs.getString("eval_id");
				candidate_skill_id = crs.getString("eval_skill_id");
				candidate_emp_id = crs.getString("eval_emp_id");
				candidate_eval_time = strToLongDate(crs.getString("eval_time"));
				candidate_eval_notes = crs.getString("eval_notes");
			}
			crs.close();
			if (!candidate_eval_id.equals("0")) {
				StrSql = "SELECT evaltrans_id, evaltrans_eval_id, evaltrans_subskill_id, evaltrans_rating "
						+ " FROM rooman_eval_trans"
						+ " WHERE evaltrans_eval_id = " + candidate_eval_id + "";
				// SOP("StrSql PopulateFields ---Eval TRans--- " + StrSqlBreaker(StrSql));
				CachedRowSet crs1 = processQuery(StrSql, 0);
				int i = 0;
				while (crs1.next()) {
					candidate_subskill_id[i] = crs1.getString("evaltrans_subskill_id");
					candidate_rating_id[i] = crs1.getString("evaltrans_rating");
					i++;
				}
				crs1.close();
			}
		} catch (Exception ex) {
			SOPError(this.getClass().getName());
			SOPError("Error in " + new Exception().getStackTrace()[0].getMethodName() + ": " + ex);
		}
	}

	public String PopulateSkill() {
		StringBuilder Str = new StringBuilder();
		try {
			StrSql = "SELECT skill_id, skill_name"
					+ " FROM candidatedb.rooman_eval_skill"
					+ " GROUP BY skill_id"
					+ " ORDER BY skill_name";
			CachedRowSet crs = processQuery(StrSql, 0);

			Str.append("<select name=\"dr_candidate_skill_id\" class=\"form-control\" id=\"dr_candidate_skill_id\">");
			Str.append("<option value=0> Select </option>\n");
			while (crs.next()) {
				Str.append("<option value=").append(crs.getString("skill_id"));
				Str.append(StrSelectdrop(crs.getString("skill_id"), candidate_skill_id));
				Str.append(">").append(crs.getString("skill_name")).append("</option>\n");
			}
			Str.append("</select>");
			crs.close();
			return Str.toString();
		} catch (Exception ex) {
			SOPError(this.getClass().getName());
			SOPError("Error in " + new Exception().getStackTrace()[0].getMethodName() + ": " + ex);
			return "";
		}
	}

	public String PopulateSubSkill(String candidate_skill_id) {
		StringBuilder Str = new StringBuilder();
		try {
			for (int i = 0; i < candidate_subskill_id.length; i++) {
				StrSql = "SELECT subskill_id, subskill_name"
						+ " FROM candidatedb.rooman_eval_skill_subskill"
						+ " WHERE subskill_skill_id = " + candidate_skill_id
						+ " GROUP BY subskill_id"
						+ " ORDER BY subskill_name";
//				System.out.println("subskill=="+ StrSql);
				CachedRowSet crs = processQuery(StrSql, 0);

				Str.append("<tr>");
				Str.append("<td><select name=dr_candidate_subskill_id" + i + "  class=form-control id=dr_candidate_subskill_id" + i + "  >");
				Str.append("<option value=0> Select </option>\n");
				while (crs.next()) {
					Str.append("<option value=").append(crs.getString("subskill_id"));
					Str.append(StrSelectdrop(crs.getString("subskill_id"), candidate_subskill_id[i]));
					Str.append(">").append(crs.getString("subskill_name")).append("</option>\n");
				}
				Str.append("</select></td>");
				crs.close();
				Str.append("<td></td>");
				Str.append("<td>");
				Str.append("<select name=dr_candidate_rating_id" + i + "  class=form-control  id=dr_candidate_rating_id" + i + "   >");
				Str.append("<option value=> Select </option>\n");
				for (int j = 0; j <= 10; j++) {
					Str.append("<option value=" + j + " ");
					Str.append(StrSelectdrop(Integer.toString(j), candidate_rating_id[i]));
					Str.append(">" + j + "</option>\n");
				}
				Str.append("</select></td></tr>");
			}
			return Str.toString();
		} catch (Exception ex) {
			SOPError(this.getClass().getName());
			SOPError("Error in " + new Exception().getStackTrace()[0].getMethodName() + ": " + ex);
			return "";
		}
	}

	public String PopulateEvaluator() {
		StringBuilder Str = new StringBuilder();
		try {
			StrSql = "SELECT emp_id, emp_name"
					+ " FROM rooman_emp"
					+ " GROUP BY emp_id"
					+ " ORDER BY emp_name";
			CachedRowSet crs = processQuery(StrSql, 0);

			Str.append("<select name=\"dr_candidate_emp_id\" class=\"form-control\" id=\"dr_candidate_emp_id\">");
			Str.append("<option value=0> Select </option>\n");
			while (crs.next()) {
				Str.append("<option value=").append(crs.getString("emp_id"));
				Str.append(StrSelectdrop(crs.getString("emp_id"), candidate_emp_id));
				Str.append(">").append(crs.getString("emp_name")).append("</option>\n");
			}
			Str.append("</select>");
			crs.close();
			return Str.toString();
		} catch (Exception ex) {
			SOPError(this.getClass().getName());
			SOPError("Error in " + new Exception().getStackTrace()[0].getMethodName() + ": " + ex);
			return "";
		}
	}
}
