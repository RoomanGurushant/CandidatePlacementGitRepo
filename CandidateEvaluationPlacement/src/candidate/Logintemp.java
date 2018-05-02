package candidate;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.codec.binary.Base64;
//import com.sun.jersey.core.util.Base64;

import connect.Connect;

public class Logintemp extends Connect {
	private static final long serialVersionUID = 1L;
	public String action = "";
	public String msg = "";
	public String signinid = "0";
	public String password = "";
	public String StrSql = "";
	public String emp_id = "0";
	public String user_jsessionid = "0";
	public int signincount = 0;
	public String code = "";
	public String uuid = "", check = "";
	public String credentials1 = "", credentials2 = "";
	Map<Integer, Object> map = new HashMap<Integer, Object>();
	public int mapkey = 0;
	public static final String CHARTSET = "ISO-8859-1";
	public static String encodedEmail;
	public static String decodedEmail;
	public static String encodedPass;
	public static String decodedPass;

	public Logintemp() {
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession(true);
			user_jsessionid = session.getId();
			action = PadQuotes(request.getParameter("action"));
			msg = PadQuotes(request.getParameter("msg"));
			if (session.getAttribute("signincount") != null) {
				signincount = Integer.parseInt(session.getAttribute("signincount").toString());
			}
			code = (String) request.getParameter("code");
			GetValues(request, response);
			Cookie[] cookies = request.getCookies();
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("uuid")) {
					if (cookies[i].getValue() != "" && cookies[i].getValue().length() > 0) {
						decodedEmail = new String(Base64.decodeBase64(cookies[i].getValue().getBytes(CHARTSET)));
						credentials1 = decodedEmail;
					}

				}
				if (cookies[i].getName().equals("check")) {
					if (cookies[i].getValue() != "" && cookies[i].getValue().length() > 0) {
						decodedPass = new String(Base64.decodeBase64(cookies[i].getValue().getBytes(CHARTSET)));
						credentials2 = decodedPass;
					}
				}
			}
			mapkey = 0;
			if (credentials1 != "" && credentials2 != "") {
				Signin(credentials1, credentials2, request, response);
				if (msg.equals("ERROR")) {
					response.sendRedirect(response
							.encodeRedirectURL("error.jsp?msg=Access denied. Please contact system administrator!"));
				} else if (!msg.equals("")) {
					msg = " " + msg;
					signincount++;
					request.getSession().setAttribute("signincount",
							signincount);
				} else if (msg.equals("")) {
					request.getSession().setAttribute("signincount", null);
				}
			}

			if ("Sign In".equals(action)) {
				CheckFields();
				if (msg.equals("")) {
					mapkey = 0;
					Signin(signinid, password, request, response);
				}
				if (msg.equals("ERROR")) {
					response.sendRedirect(response.encodeRedirectURL("error.jsp?msg=Access denied. Please contact system administrator!"));
				} else if (!msg.equals("")) {
					msg = " " + msg;
					signincount++;
					request.getSession().setAttribute("signincount", signincount);
				} else if (msg.equals("")) {
					request.getSession().setAttribute("signincount", null);
				}
			}
		} catch (Exception ex) {
			SOPError("FMS-Rooman===" + this.getClass().getName());
			SOPError("Error in " + new Exception().getStackTrace()[0].getMethodName() + ": " + ex);
		}
	}

	protected void GetValues(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		action = PadQuotes(request.getParameter("action"));
		signinid = PadQuotes(request.getParameter("userid"));
		password = PadQuotes(request.getParameter("password"));
	}

	protected void CheckFields() {
		msg = "";
		if (signinid.equals("") && password.equals("")) {
			msg = "Sign In Id & Password is blank!";
		} else if (signinid.equals("")) {
			msg = "Sign In ID is blank!";
		} else if (password.equals("")) {
			msg = "Password is blank!";
		}
	}

	protected String Signin(String signinid, String password, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CheckFields();
		String loginmsg = "";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			StrSql = "SELECT emp_id, emp_name, emp_uname, emp_upass"
					+ " FROM axela_emp"
					+ " WHERE 1=1";
//					+ " AND emp_active = 1";
			StrSql += " AND emp_email1 = ? "
					+ " AND emp_upass = ? ";
			conn = connectDb();
			pstmt = conn.prepareStatement(StrSql);
			pstmt.setString(1, signinid);
			pstmt.setString(2, password);
			// SOP("index SignIn==" + StrSql);
			rs = pstmt.executeQuery();
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					emp_id = rs.getString("emp_id");
					uuid = rs.getString("emp_uname");
					check = rs.getString("emp_upass");
					if (!password.equals(rs.getString("emp_upass"))) {
						msg = "Please check Sign In Id and password!";
					}
//					if (!rs.getString("emp_ip_access").equals("")) {
//						String IP = getIP(request);
//						String[] str = new String[10];
//						if (rs.getString("emp_ip_access").contains(", ")) {
//							str = rs.getString("emp_ip_access").split(", ");
//							for (int j = 0; j < str.length; j++) {
//								if (!IP.equals(str[j])) {
//									msg = "Access denied!";
//								} else {
//									msg = "";
//									break;
//								}
//							}
//						} else if (!IP.equals(rs.getString("emp_ip_access"))) {
//							msg = "Access denied!";
//						}
//					}
					if (msg.equals("")) {
						AssignSession(rs.getString("emp_id"), request);
						Cookie cookie = new Cookie("axelatheme", "1");
						cookie.setMaxAge(60 * 60 * 24 * 30 * 60);
						cookie.setPath(request.getContextPath());
						response.addCookie(cookie);
						// if (AppRun().equals("1")) {

						updateQuery("UPDATE axela_emp_log"
								+ " SET log_signout_time = " + ToLongDate(kknow()) + ""
								+ " WHERE log_emp_id = " + emp_id + ""
								+ " AND log_signout_time = ''");

						updateQuery("INSERT INTO axela_emp_log"
								+ " (log_emp_id,"
								+ " log_session_id,"
								+ " log_remote_host,"
								+ " log_remote_agent,"
								+ " log_attemptcount,"
								+ " log_signin_time, "
								+ " log_signout_time)"
								+ " VALUES"
								+ " (" + emp_id + ","
								+ " '" + user_jsessionid + "',"
								+ " '" + request.getRemoteHost() + "',"
								+ " '" + request.getHeader("User-Agent") + "',"
								+ " " + signincount + ","
								+ " " + ToLongDate(kknow()) + ","
								+ " '')");

						updateQuery("DELETE FROM axela_emp_user"
								+ " WHERE user_emp_id = " + emp_id);

						updateQuery("INSERT INTO axela_emp_user"
								+ " (user_jsessionid,"
								+ " user_emp_id,"
								+ " user_logintime,"
								+ " user_ip,"
								+ " user_agent)"
								+ " VALUES"
								+ " ('" + user_jsessionid + "',"
								+ " " + rs.getString("emp_id") + ","
								+ " '" + ToLongDate(kknow()) + "',"
								+ " '" + request.getRemoteHost() + "',"
								+ " '" + request.getHeader("User-Agent") + "')");
						// }
						loginmsg = "Valid";
					} else {
						loginmsg = msg;
					}
				}
			} else {
				msg = "The Sign In Id or password you entered is incorrect!";
			}
		} catch (Exception ex) {
			SOPError("Naukri For U== " + this.getClass().getName());
			SOPError("Error in " + new Exception().getStackTrace()[0].getMethodName() + ": " + ex);
			msg = "ERROR";
		} finally {
			rs.close();
			if (pstmt != null && !pstmt.isClosed()) {
				pstmt.close();
			}
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		}
		return loginmsg;
	}
}
