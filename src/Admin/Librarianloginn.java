package Admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Librarianloginn
 */
@WebServlet("/Librarianloginn")
public class Librarianloginn extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String id=request.getParameter("userid");
		String pass=request.getParameter("password");
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle");
		    Statement s=conn.createStatement();
		    ResultSet r=s.executeQuery("select userid from addlibrarian where exists (select password from addlibrarian where userid='"+id+"' AND password='"+pass+"')");
		    if(r.next())
		    {
		    	HttpSession session=request.getSession();
				request.getRequestDispatcher("/Welcomelibrarian.html").include(request, response);
		    }
		    else
			{
				out.println("<h3>Sorry,Wrong User id or Password</h3>");
				request.getRequestDispatcher("/onlylibrarianform.html").include(request, response);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
