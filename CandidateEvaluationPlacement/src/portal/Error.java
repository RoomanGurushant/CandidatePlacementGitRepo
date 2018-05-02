package portal;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import connect.Connect;

public class Error extends Connect {
	private static final long serialVersionUID = 1L;
	public String msg = "";
    public String emp_id = "";

    public void doPost(HttpServletRequest request, HttpServletResponse response) {

        msg = PadQuotes(request.getParameter("msg"));
        msg = unescapehtml(msg);

    }
 
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
