package Admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;
/**
 * Servlet implementation class Addinglibrarian
 */
@WebServlet("/Addinglibrarian")
public class Addinglibrarian extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();

		String id=request.getParameter("userid");
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String pass=request.getParameter("password");
		
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle");
	         Statement s=conn.createStatement();
	         
	       int x=s.executeUpdate("insert into addlibrarian values('"+id+"','"+name+"','"+email+"','"+pass+"')");
	         if(x>0)
	       	out.println("<h3>Librarian added succesfully</h3>");
		     conn.close();
		} 
		
		catch (ClassNotFoundException | SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		request.getRequestDispatcher("/Addlibrarian.html").include(request, response);
		out.close();
		
		
	}

}
