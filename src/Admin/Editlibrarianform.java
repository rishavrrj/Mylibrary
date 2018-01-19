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

/**
 * Servlet implementation class Editlibrarianform
 */
@WebServlet("/Editlibrarianform")
public class Editlibrarianform extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>Edit Librarian Form</title>");
		out.println("</head>");
		out.println("<body>");
		String sid=request.getParameter("id");
		
		try
		{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle");
	    Statement s=conn.createStatement();
	   ResultSet  r=s.executeQuery("select * from addlibrarian where userid='"+sid+"'");
	   String n=null,e=null,p=null; 
	  while(r.next())
	  {
	    	n=r.getString(2);
	    	e=r.getString(3);
	    	p=r.getString(4);
	    
	  }
	   
	   out.println("<h2 align='center'>Edit Librarian</h2>");
	    out.println("<form method='post' action='Editlibrarian' >");
	    out.println("User Id:<input type='text' value='"+sid+"' name='id' readonly>");
	    out.println("<br><br>Name:<input type='text' value='"+n+"' name='name'>");
	    out.println("<br><br>Email:<input type='email' value='"+e+"' name='email'>");
	    out.println("<br><br>Password:<input type='password' value='"+p+"' name='pass'>");
	    out.println("<br><br><input type='submit' value='Update'>");
		}
	    
		catch(Exception e)
		{
			e.printStackTrace();
		}
	  
	    out.println("</form>");
		out.println("</body></html>");
		
	}

}
