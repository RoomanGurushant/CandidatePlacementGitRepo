package candidate;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.CachedRowSet;

import org.apache.commons.lang3.StringEscapeUtils;

import connect.Connect;


public class Candidate_Eval_List extends Connect {

	// Json
	public StringBuilder Str = new StringBuilder();
	public String all = "";
	public String more = "";
	public String msg = "";
	public String StrSql = "";
	public String StrHTML = "";
	public String QueryString = "";
	public String eval_id = "0";
	public String candidate_id = "0";
	public String emp_idsession = "0";
	public String active = "";
	public String group = "";
	public Connection con = null;

	public void doPost(HttpServletRequest request, HttpServletResponse response) {

		try {
//			CheckSession(request, response);
			HttpSession session = request.getSession(true);
				more = PadQuotes(request.getParameter("more"));
				msg = PadQuotes(request.getParameter("msg"));
				QueryString = PadQuotes(request.getQueryString());
				eval_id = CNumeric(PadQuotes(request.getParameter("eval_id")));
				candidate_id = CNumeric(PadQuotes(request.getParameter("candidate_id")));
				all = PadQuotes(request.getParameter("all"));
				group = PadQuotes(request.getParameter("group"));

				StrHTML = Listdata();
		} catch (Exception ex) {
			SOPError(this.getClass().getName());
			SOPError("Error in " + new Exception().getStackTrace()[0].getMethodName() + ": " + ex);
		}
	}

	public String Listdata() {
		String address = "", email = "";
		if (!msg.equals("")) {
			// Check PageCurrent is valid for parse int
			try {
				StrSql = " SELECT eval_id, eval_candidate_id, eval_skill_id, eval_emp_id, eval_time, candidate_fname,"
						+ " candidate_lname, emp_name, eval_notes , skill_name, eval_type"
						+ " FROM rooman_candidate_eval"
						+ " INNER JOIN rooman_candidate_data ON candidate_id = eval_candidate_id "
						+ " INNER JOIN rooman_eval_skill ON skill_id = eval_skill_id "
						+ " INNER JOIN rooman_emp ON emp_id = eval_emp_id "
						+ " WHERE 1 = 1";

				
					System.out.println("strsql==list==" + StrSql);
					
					
					con = connectDataBase(); // implementaion is giving connection object
					
					preparedStatement = con.prepareStatement(StrSql);
//					System.out.println("\nPrepared stmt is created!!");
					
					ResultSet rs = preparedStatement.executeQuery();
//					System.out.println("\n executing query");
					
					int count = 0;
					Str.append("<div class=\"table\">\n");
					Str.append("<table style=\"background-color:#ffffff\" class=\"table table-responsive table-hover table-bordered\" data-filter=\"#filter\">\n");
					Str.append("<thead><tr align=center>\n");
					Str.append("<th data-toggle=\"true\">#</th>\n");
					Str.append("<th data-toggle=\"true\">ID</th>\n");
					Str.append("<th data-toggle=\"true\">Candidate Name</th>\n");
					Str.append("<th>Skill</th>\n");
					Str.append("<th data-hide=\"phone\">Time</th>\n");
					Str.append("<th data-hide=\"phone,tablet\">Skill Set</th>\n");
					Str.append("<th data-hide=\"phone\">Experience</th>\n");
					Str.append("<th data-hide=\"phone,tablet\">Actions</th>\n");
					Str.append("</tr></thead><tbody>\n");
					
					while (rs.next()) {
						count = count + 1;
						
						if (rs.getString("candidate_active").equals("0")) {
							active = "<br><font color=red > [Inactive] </font>";
						} else {
							active = "";
						}
						Str.append("<tr>\n");
						Str.append("<td valign=top align=center>").append(count).append("</td>\n");
						Str.append("<td valign=top align=center>").append(rs.getString("candidate_id")).append("</td>");
						Str.append("<td valign=top align=center>").append(rs.getString("candidate_fname") + " " + rs.getString("candidate_lname") + "</a>" + active).append("</td>");
						
						if (!rs.getString("candidate_email1").equals("")) {
							email = "<a href=mailto:" + rs.getString("candidate_email1") + ">" + rs.getString("candidate_email1") + "</a><br>";
							Str.append("<td valign=top align=left>").append(rs.getString("candidate_mobile1")  + "<br>" + email).append("</td>");
						}
						else {
							Str.append("<td valign=top align=left>").append(rs.getString("candidate_mobile1")).append("</td>");
						}
						
						if (rs.getString("candidate_address").equals(""))
						{
							address = "";
						}
						else {
							address = rs.getString("candidate_address") + ",<br>";
						}
						if (!rs.getString("area_name").equals("")) {
							address += rs.getString("area_name") + ",<br>";
						}
						if (!rs.getString("city_name").equals("")) {
							address += rs.getString("city_name");
						}
						if (!rs.getString("area_pincode").equals("")) {
							address += " - " + rs.getString("area_pincode");
						}
						if (!rs.getString("state_name").equals("")) {
							address += ",<br>" + rs.getString("state_name") + ".";
						}
						Str.append("<td valign=top align=left>").append(address).append("</td>");
						if (!rs.getString("candidate_skillset").equals("")) {
							Str.append("<td valign=top align=left>").append(rs.getString("candidate_skillset")).append("</td>");
						} else {
							Str.append("<td valign=top align=left>").append("Not Mentioned").append("</td>");
						}
						Str.append("<td valign=top align=left>").append("candidate_experience").append("</td>");
						Str.append("<td valign=top align=left>");
						Str.append("<a href=\"candidate-register.jsp?update=yes&candidate_id=").append(rs.getString("candidate_id")).append("\">").append("Update candidate").append("</a><br>");
						Str.append("<a href=\"candidate-evaluation-update.jsp?add=yes&candidate_id=").append(rs.getString("candidate_id")).append("\">").append("Add Evaluation").append("</a><br>");
						Str.append("<a href=\"candidate-eval-list.jsp?all=yes\">").append("List Evaluation").append("</a><br>");
						Str.append("</td>\n");
						Str.append("</tr>\n");
					}
					Str.append("</tbody></table></div>\n");
					rs.close();
					close();
			} catch (Exception ex) {
				SOPError("Naukriforu== " + this.getClass().getName());
				SOPError("Error in " + new Exception().getStackTrace()[0].getMethodName() + ": " + ex);
				return "";
			}
		}
		return Str.toString();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
