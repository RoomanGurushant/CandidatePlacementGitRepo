package candidate;

import java.io.IOException;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import connect.Connect;


public class Candidate_Eval_List extends Connect {
	private static final long serialVersionUID = 1L;
	// Json
	public StringBuilder Str = new StringBuilder();
	public String all = "";
	public String msg = "";
	public String StrSql = "";
	public String StrHTML = "";
	public String QueryString = "";
	public String eval_id = "0";
	public String candidate_id = "0";
	public String active = "";
	public String group = "";
//	public Connection con = null;

	public void doPost(HttpServletRequest request, HttpServletResponse response) {

		try {
			msg = PadQuotes(request.getParameter("msg"));
			QueryString = PadQuotes(request.getQueryString());
			eval_id = CNumeric(PadQuotes(request.getParameter("eval_id")));
			candidate_id = CNumeric(PadQuotes(request.getParameter("candidate_id")));
			all = PadQuotes(request.getParameter("all"));
			StrHTML = Listdata();
		} catch (Exception ex) {
			SOPError(this.getClass().getName());
			SOPError("Error in " + new Exception().getStackTrace()[0].getMethodName() + ": " + ex);
		}
	}

	public String Listdata() {
		String  address = "", email = "";
//		if (!msg.equals("")) {
			try {
				StrSql = " SELECT eval_id, "
						+ " COALESCE(eval_candidate_id,'0') AS eval_candidate_id, "
						+ " COALESCE(eval_skill_id,'0') AS eval_skill_id, "
						+ " COALESCE(eval_emp_id,'0') AS eval_emp_id, "
						+ " COALESCE(eval_time,'') AS eval_time,"
						+ " COALESCE(candidate_fname,'') AS candidate_fname,"
						+ " COALESCE(candidate_lname,'') AS candidate_lname,"
						+ " COALESCE(emp_name,'') AS emp_name,"
						+ " COALESCE(eval_notes,'') AS eval_notes,"
						+ " COALESCE(skill_name,'') AS skill_name,"
						+ " COALESCE(eval_type,'') AS eval_type"
						+ " FROM candidatedb.rooman_candidate_eval"
						+ " INNER JOIN candidatedb.rooman_candidate_data ON candidate_id = eval_candidate_id "
						+ " INNER JOIN candidatedb.rooman_eval_skill ON skill_id = eval_skill_id "
						+ " INNER JOIN candidatedb.rooman_emp ON emp_id = eval_emp_id "
						+ " WHERE 1 = 1 ";
				
					System.out.println("strsql==list==" + StrSql);
					
					
					connectDB(); // implementaion is giving connection object
					
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
					Str.append("<th data-hide=\"phone,tablet\">Evaluator</th>\n");
					Str.append("<th data-hide=\"phone,tablet\">Actions</th>\n");
					Str.append("</tr></thead><tbody>\n");
					
					while (rs.next()) {
					
					count = count + 1;
					Str.append("<tr>\n");
					Str.append("<td valign=top align=center>").append(count).append("</td>\n");
					Str.append("<td valign=top align=center>").append(rs.getString("eval_id")).append("</td>");
					Str.append("<td valign=top align=center>").append(rs.getString("candidate_fname") + " " + rs.getString("candidate_lname") + "</a>").append("</td>");
					Str.append("<td valign=top align=center>").append(rs.getString("skill_name")).append("</td>");
					Str.append("<td valign=top align=center>").append(rs.getString("eval_time")).append("</td>");
					Str.append("<td valign=top align=center>").append(rs.getString("emp_name")).append("</td>");
					Str.append("<td valign=top align=left>");
					Str.append("<a href=\"candidate-eval-update.jsp?update=yes&eval_id=").append(rs.getString("eval_id")).append("\">").append("Update Evaluation").append("</a><br>");
					Str.append("</td>\n");
					Str.append("</tr>\n");
					}
					Str.append("</tbody></table></div>\n");
					rs.close();
					close();
			} catch (Exception ex) {
				SOPError("Candidate== " + this.getClass().getName());
				SOPError("Error in " + new Exception().getStackTrace()[0].getMethodName() + ": " + ex);
			}
//		}
		return Str.toString();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
