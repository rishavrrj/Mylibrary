package Admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Editlibrarian
 */
@WebServlet("/Editlibrarian")
public class Editlibrarian extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
		PrintWriter out =response.getWriter();
		
		 String Userid=request.getParameter("id");
		String Nam=request.getParameter("name");
		String Pass=request.getParameter("pass");
		String Emai=request.getParameter("email");
		
		try
		{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle");
		 
		Statement s=c.createStatement();
		String sql="update addlibrarian set name='"+Nam+"',email='"+Emai+"',password='"+Pass+"' WHERE userid='"+Userid+"'";
		s.executeUpdate(sql);
		out.println("Sucess");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		response.sendRedirect("Viewlibrarian");
		
		out.close();
	}

}