package candidate;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import connect.Connect;

@WebServlet("/Login")

public class Login extends Connect {
	public static final long serialVersionUID = 1L;
	
	public String emp_uname = "";
	public String emp_upass = "";
	public String msg = "";
	public String Strsql = "";
	public String count = "";
	public String action = "";
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Getting into dopost");
		
		emp_uname = PadQuotes(request.getParameter("txt_emp_uname"));
		System.out.println("emp_uname===" + emp_uname);
		
		emp_upass = PadQuotes(request.getParameter("txt_emp_upass"));
		System.out.println("emp_upass===" + emp_upass);
		
		action = PadQuotes(request.getParameter("action"));
		System.out.println("action===" + action);
		
		msg = PadQuotes(request.getParameter("msg"));
		System.out.println("msg===" + msg);
		
		if(action.equals("Login")) {
			connectDB();
			PopulateEmp(emp_uname, emp_upass, request, response);
			close();
		}
		
//		checkForm();
//		if (!msg.equals("")) {
//			msg = "Error!<br>" + msg;
//		} else {
//			count = PopulateEmp(emp_uname, emp_upass);
//			if(count.equals("1")) {
//				response.sendRedirect("candidate/candidate-home.jsp?msg=Successfully logged in!");
//			}
//			else {
//				msg = "Error!" + msg;
//			}
//		}

	}
	
	public String checkForm() {
		msg = "";
		if (emp_uname.equals("") && emp_upass.equals("")) {
			msg = "User Name & Password is blank!";
		} else if (emp_uname.equals("")) {
			msg = "User name is blank!";
		} else if (emp_upass.equals("")) {
			msg = "Password is blank!";
		}
		return msg;
	}
	
	public void PopulateEmp(String uname, String upass, HttpServletRequest request, HttpServletResponse response) {
		
		checkForm();
//		System.out.println("msg===checkform===" + msg);
		if(msg.equals("")) {
		try {
			Strsql = "SELECT COUNT(emp_id)"
					+ " FROM candidatedb.rooman_emp"
					+ " WHERE 1=1"
					+ " AND emp_uname = '" + uname+"'"
					+ " AND emp_upass = '" + upass+"'";
			
			System.out.println("PopulateEmp==" + Strsql);

			String res = ExecuteQuery(Strsql);
			System.out.println("return result populate emp==" + res);
			
			if(res.equals("1")) {
				response.sendRedirect("candidate/candidate-home.jsp?msg=Successfully loggeddd in!");
			}else {
				msg += "Error!<br>Invalid Account!";
				response.sendRedirect("login.jsp?msg=Invalid Account!");
			}
			
		} catch (Exception ex) {
			System.out.println(this.getClass().getName());
			System.out.println("Error in " + new Exception().getStackTrace()[0].getMethodName() + ": " + ex);
		}
	
		}
		else {
			msg += "Error<br>" + msg; 
		}
	}

	
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 doPost(request, response);
	 }

}
