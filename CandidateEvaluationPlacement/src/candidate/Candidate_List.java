package candidate;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.CachedRowSet;

import connect.Connect;

public class Candidate_List extends Connect {

	private static final long serialVersionUID = 1L;
	// Json
	public StringBuilder Str = new StringBuilder();
	public String pagetitle = "List Candidates";
	public String msg = "";
	public String candidate_img = "";
	public String StrSql = "";
	public String StrHTML = "";
	public String active = "";


	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
				msg = PadQuotes(request.getParameter("msg"));
				StrHTML = Listdata();
//				System.out.println("list candidates==" + StrHTML);
		} catch (Exception ex) {
			SOPError(this.getClass().getName());
			SOPError("Error in " + new Exception().getStackTrace()[0].getMethodName() + ": " + ex);
		}
	}

	public String Listdata() {
		String  address = "", email = "";
//		if (!msg.equals("")) {
			try {
				StrSql = "SELECT candidate_id, "
						+ " COALESCE(candidate_fname,'') AS candidate_fname, COALESCE(candidate_lname,'') AS candidate_lname, "
						+ " COALESCE(candidate_address,'') AS candidate_address,  COALESCE(candidate_img,'') AS candidate_img, "
						+ " COALESCE(candidate_area_id,'') AS candidate_area_id,  COALESCE(candidate_mobile1,'') as candidate_mobile1, "
						+ " COALESCE(candidate_email1,'') AS candidate_email1,  COALESCE(candidate_img,'') AS candidate_img, "
						+ " COALESCE(candidate_active,'') AS candidate_active, COALESCE(candidate_skillset,'') AS candidate_skillset,  "
						+ " COALESCE(candidate_experience,'') AS candidate_experience, COALESCE(area_name,'') AS area_name, "
						+ " COALESCE(area_pincode,'') AS area_pincode, COALESCE(city_name,'') AS city_name, COALESCE(city_state_id,'') AS city_state_id, "
						+ " COALESCE(state_name,'') AS state_name"
						+ " FROM candidatedb.rooman_candidate_data"
						+ " LEFT JOIN candidatedb.rooman_area ON area_id =  candidate_area_id"
						+ " LEFT JOIN candidatedb.rooman_city ON city_id =  area_city_id"
						+ " LEFT JOIN candidatedb.rooman_state ON state_id =  city_state_id"
						+ " GROUP BY candidate_id "
						+ " ORDER BY candidate_id";
				
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
					Str.append("<th data-toggle=\"true\">Candidate</th>\n");
					Str.append("<th>Contact</th>\n");
					Str.append("<th data-hide=\"phone\">Address</th>\n");
					Str.append("<th data-hide=\"phone,tablet\">Skill Set</th>\n");
					Str.append("<th data-hide=\"phone\">Experience</th>\n");
					Str.append("<th data-hide=\"phone,tablet\">Actions</th>\n");
					Str.append("</tr></thead><tbody>\n");
					
					while (rs.next()) {
						count = count + 1;
						
						if (!rs.getString("candidate_img").equals("")) {
							candidate_img = "<img style=height:75px;width:100px src=../Thumbnail.do?candidate_id=" + rs.getString("candidate_id")
									+ "&image_type=candidate" + "&width=100 alt=" + " " + ">";
						} else {
							candidate_img = "<img src=../admin-ifx/default.jpg width=100 height=75>";
						}
						
						if (rs.getString("candidate_active").equals("0")) {
							active = "<br><font color=red > [Inactive] </font>";
						} else {
							active = "";
						}
						Str.append("<tr>\n");
						Str.append("<td valign=top align=center>").append(count).append("</td>\n");
						Str.append("<td valign=top align=center>").append(rs.getString("candidate_id")).append("</td>");
						Str.append("<td valign=top align=center>").append(candidate_img + "<br>" + rs.getString("candidate_fname") + " " + rs.getString("candidate_lname") + "</a>" + active).append("</td>");
						
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
			} catch (SQLException ex) {
				SOPError("Naukriforu== " + this.getClass().getName());
				SOPError("Error in " + new Exception().getStackTrace()[0].getMethodName() + ": " + ex);
			}catch (Exception ex) {
				SOPError("Naukriforu== " + this.getClass().getName());
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
