package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.log4j.Logger;

import connect.ConnectDate;

public class Connect extends HttpServlet {

	final Logger logger = Logger.getLogger(this.getClass());

	private static final long serialVersionUID = 1L;
	public Connection con = null;
	public Statement stmt = null;
	public PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public static String AppName = "StudentPlacement";
	public static String ClientName = "ClustTech";
	public static String ClientTeam = "Team Emax";

	public Connect() {
	}

	
	// connect class methods start

	/*public Connection connectDataBase() {
		Connection conn = null;
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource) envContext.lookup("jdbc/ConnectDB");
			conn = ds.getConnection();

		} catch (Exception ex) {
			SOPError("Clusttech-Candidateplacement===" + this.getClass().getName());
			SOPError("Error in " + new Exception().getStackTrace()[0].getMethodName() + ": " + ex);
		}
		// SOP("connection returned......");
		return conn;
	}*/

	public Connection connectDataBase() {
		Connection conn = null;

		String url = "jdbc:mysql://localhost:3306?autoReconnect=true&useSSL=false&user=gurushant&password=gurushant";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	
		return conn;
	}
	
	public void connectDB() {
		String url = "jdbc:mysql://localhost:3306?autoReconnect=true&useSSL=false&user=gurushant&password=gurushant";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url);
			stmt = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public String getIP(HttpServletRequest request) {
		String IP = "";
		if (request.getHeader("x-forwarded-for") != null) {
			IP = request.getHeader("x-forwarded-for").toString();
		}
		if (request.getRemoteHost() != null && IP.equals("")) {
			IP = request.getRemoteHost().toString();
		}
		return IP;
	}

	public String unescapehtml(String str) {
		return StringEscapeUtils.unescapeHtml4(str);
	}
	
	public void SOPError(String Str) {
		final Logger logger = Logger.getLogger(this.getClass());
		logger.error(Str);
	}

	public boolean isNumeric(String s) {
		String validChars = "0123456789.";
		boolean isNumeric = true;
		for (int i = 0; i < s.length() && isNumeric; i++) {
			char c = s.charAt(i);
			if (validChars.indexOf(c) == -1) {
				isNumeric = false;
			} else {
				isNumeric = true;
			}
		}
		return isNumeric;
	}

	public String CNumeric(String s) {
		if (s.equals("")) {
			return "0";
		} else if (isNumeric(s)) {
			return s;
		} else {
			return "0";
		}
	}

	public void SOP(String Str) {
			System.out.println(Str);
	}
	
	public String PadQuotes(String str) {
		if (str != null) {
			String str1 = str.trim();
			str1 = str1.replace("'", "&#39;").replace("\"", "&#34;").replace("[", "&#91;").replace("]", "&#93;");
			// str1=str1.replace("\n","\\\n");
			return str1;
		} else {
			return "";
		}
	}

	public String CheckBoxValue(String checkBox) {
		if (checkBox.equals("on")) {
			checkBox = "1";
		} else {
			checkBox = "0";
		}
		return checkBox;
	}

	public String StrSelectdrop(String value, String dropid) {
		try {
			if (!(dropid == null) && !(dropid.equals(""))) {
				if (value.equals(dropid)) {
					return " selected";
				}
			}
			return " ";
		} catch (Exception ex) {
			System.out.println(this.getClass().getName());
			System.out.println("Error in " + new Exception().getStackTrace()[0].getMethodName() + " : " + ex);
			return " ";
		}
	}

	public CachedRowSet processQuery(String StrSQL, int minutes) {
		// // StrSql = compdb(StrSql);
		ResultSet rspq = null;
		Statement stmt = null;
		try {
			connectDB();
			stmt = con.createStatement();
			rspq = stmt.executeQuery(StrSQL);
			CachedRowSet crs = RowSetProvider.newFactory().createCachedRowSet();
			crs.populate(rspq);
			return (crs);
		} catch (SQLException ex) {
			System.out.println(this.getClass().getName());
			System.out.println("Error in " + new Exception().getStackTrace()[0].getMethodName() + " : " + ex);
			return null;
		} finally {
			try {
				if (rspq != null) {
					rspq.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException ex) {
				System.out.println(this.getClass().getName());
				System.out.println("Error in " + new Exception().getStackTrace()[0].getMethodName() + " : " + ex);
			}
		}
	}

	
	
	public String UpdateQueryReturnID(String strSQL) {
		ResultSet rs = null;
		PreparedStatement stmtGetLastID = null, pstmt = null;
		String res = "0";
		Connection conn = connectDataBase();
		try {
			pstmt = conn.prepareStatement(strSQL);
			pstmt.executeUpdate();
			stmtGetLastID = conn.prepareStatement("SELECT LAST_INSERT_ID()");
			rs = stmtGetLastID.executeQuery();
			while (rs.next()) {
				res = rs.getString(1);
			}
			// SOPError(res+" res");
			return res;
		} catch (Exception ex) {
			SOPError(this.getClass().getName());
			SOPError("Error in "
					+ new Exception().getStackTrace()[0].getMethodName()
					+ " : " + ex);
			return "0";
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (stmtGetLastID != null) {
					stmtGetLastID.close();
				}
				if (rs != null) {
					rs.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				SOPError(this.getClass().getName());
				SOPError("Error in "
						+ new Exception().getStackTrace()[0].getMethodName()
						+ " : " + ex);
			}
		}
	}
	
	public int updateQuery(String strSQL) {
		int rsq;
		try {
			con = connectDataBase();
			stmt = con.createStatement();
			rsq = stmt.executeUpdate(strSQL);
			return (rsq);
		} catch (Exception ex) {
			SOPError(this.getClass().getName());
			SOPError(new Exception().getStackTrace()[0].getMethodName() + " : "
					+ ex);
			return 0;
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception ex) {
				SOPError(this.getClass().getName());
				SOPError(new Exception().getStackTrace()[0].getMethodName()
						+ " : " + ex);
			}
		}
	}
	
	public String ExecuteQuery(String strSQL) {
		ResultSet rsexeq = null;
		String res = "";
		try {
			connectDB();
			rsexeq = stmt.executeQuery(strSQL);
			while (rsexeq.next()) {
				res = rsexeq.getString(1);
			}
			return res;
		} catch (Exception ex) {
			SOPError(this.getClass().getName());
			SOPError(new Exception().getStackTrace()[0].getMethodName() + " : " + ex);
			return "0";
		} finally {
			try {
				if (rsexeq != null) {
					rsexeq.close();
				}
				if (stmt != null) {
					stmt.close();
				}

				if (con != null) {
					con.close();
				}
			} catch (Exception ex) {
				SOPError(this.getClass().getName());
				SOPError(new Exception().getStackTrace()[0].getMethodName() + " : " + ex);
			}
		}
	}

	public String PageNavi(String PageURL, int PageCurrent, int PageCount, int PageListSize) {
		String PageNavi = "<div><ul class=\"pagination\">";
		PageNavi += "&nbsp;";
		for (int i = (PageListSize * (Math.abs(((PageCurrent - 1) / PageListSize))))
				+ 1; i <= (PageListSize * (Math.abs((PageCurrent - 1) / PageListSize))) + PageListSize; i++) {
			if (i > PageCount) {
				break;
			}
			if (i == PageCurrent) {
				PageNavi += "<li><a><font color='red'>" + i + "</font></a></li>";
			} else {
				PageNavi += "<li onclick='TableAjaxCall(\"" + PageURL + i + "&pagenavi=yes\")'><a>" + i + "</a></li>";
				// <a // href='" // + // PageURL // +
			}
		}
		PageNavi += "</ul></div>";
		return PageNavi;
	}

	public void close() {
		try {
			if (stmt != null) {
				stmt.close();
			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			
			if (con != null) {
				con.close();
			}

		} catch (Exception e) {

		}
	}

	public void CheckSession(HttpServletRequest request, HttpServletResponse response) {
		try {
			// SOPInfo("CheckSession========" + request.getMethod());
			// SOPInfo("CheckSession========" + request.getClass());
			HttpSession session = request.getSession(true);
			// SOPInfo("================CheckSession==================");
			if (GetSession("emp_id", request).toString().equals("")) {
				response.sendRedirect( response.encodeRedirectURL("../candidate/login.jsp?msg=Your session has expired."));
			}

		} catch (Exception e) {
			SOPError(this.getClass().getName());
			SOPError("Error in " + new Exception().getStackTrace()[0].getMethodName() + " : " + e);
		}
	}

	public String GetSession(String key, HttpServletRequest request) {
		String sessionvalue = "";
		HttpSession session = request.getSession(true);

		Map getMap = (Map) session.getAttribute("sessionMap");
		if (getMap != null) {
			sessionvalue = getMap.get(key) + "";
		}
		if (sessionvalue.equals("null")) {
			sessionvalue = "";
		}
		return sessionvalue;
	}
	
	public void AssignSession(String emp_id, HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		session.setAttribute("emp_id", emp_id);
//		session.setAttribute("emp_role_id", role_id);
//		session.setAttribute("emp_recperpage", recperpage);
//		session.setMaxInactiveInterval(emp_timeout * 60);
	}

	public static Set<String> findDuplicates(List<String> listduplicates) {
		final Set<String> setToReturn = new HashSet<String>();
		final Set<String> set1 = new HashSet<String>();
		for (String listItem : listduplicates) {
			if (!set1.add(listItem)) {
				setToReturn.add(listItem);
			}
		}
		return setToReturn;
	}
	
	
	// connect class methods end

	// connectDate class methods start

	public Date kknow() {
		ConnectDate cdate = new ConnectDate();
		Date date;
		date = cdate.AddHoursDate(new Date(), 0, 0, 0);
		return date;
	}

	public String ToLongDate(Date dtDateTime) {
		try {
			String strOutDt = new SimpleDateFormat("yyyyMMddHHmmss").format(dtDateTime);
			return strOutDt;
		} catch (Exception ex) {
			logger.error("CandidatePlacement===" + this.getClass().getName());
			logger.error("Error in " + new Exception().getStackTrace()[0].getMethodName() + ": " + ex);
			return " " + ex;
		}
	}

	public String ConvertLongDateToStr(String strDateTime) {
		Date dttemp;
		String strOutDt = "";
		try {
			if (strDateTime != null && !strDateTime.equals("")) {
				dttemp = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(strDateTime);
				strOutDt = new SimpleDateFormat("yyyyMMddHHmmss").format(dttemp);
			}
			return strOutDt;
		} catch (Exception ex) {
			logger.error(this.getClass().getName());
			logger.error("Error in " + new Exception().getStackTrace()[0].getMethodName() + ": " + ex);
			return "";
		}
	}
	
	public String strToLongDate(String strDateTime) {
		String strOutDt = "";
		try {
			if (strDateTime != null && !strDateTime.equals("")) {
				Date dttemp = new SimpleDateFormat("yyyyMMddHHmmss").parse(strDateTime);
				strOutDt = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(dttemp);
			}
			return strOutDt;
		} catch (Exception ex) {
			logger.error(this.getClass().getName());
			logger.error("Error in " + new Exception().getStackTrace()[0].getMethodName() + ": " + ex);
			return " " + ex;
		}
	}
	
	// connectDate class methods end

}
