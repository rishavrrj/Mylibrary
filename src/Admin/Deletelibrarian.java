package Admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Deletelibrarian
 */
@WebServlet("/Deletelibrarian")
public class Deletelibrarian extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String Userid=request.getParameter("id");
		String Name=request.getParameter("name");
		String Password=request.getParameter("pass");
		String Email=request.getParameter("email");
		try
		{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle");
		 
		Statement s=c.createStatement();
		
		s.executeUpdate("delete from addlibrarian WHERE Userid='"+Userid+"'");
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		response.sendRedirect("Viewlibrarian");
	}

}
