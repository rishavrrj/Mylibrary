package Admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Adminloginn
 */
@WebServlet("/Adminloginn")
public class Adminloginn extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
  
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String id=request.getParameter("userid");
		String pass=request.getParameter("password");
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		if(id.equals("rishav") && pass.equals("rishav"))
		{
			HttpSession session=request.getSession();
			request.getRequestDispatcher("/Welcomeadmin.html").include(request, response);
		}
		else
		{
			out.println("<h3>Sorry,Wrong User id or Password</h3>");
			request.getRequestDispatcher("/onlyadminform.html").include(request, response);
		}
		
	}

}
