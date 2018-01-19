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
 * Servlet implementation class Returnbook
 */
@WebServlet("/Returnbook")
public class Returnbook extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String serial=request.getParameter("serial");
		String sid=request.getParameter("id");
	
		try
		{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle");
	    Statement s=conn.createStatement();
	     ResultSet r=s.executeQuery("select * from issuebook where serial_no='"+serial+"' AND student_id='"+sid+"' ");
	    if(r.next())
	    {
	        
	    	s.executeUpdate("update issuebook set return_status='Yes' where serial_no='"+serial+"' AND student_id='"+sid+"' ");
	    	ResultSet r1=s.executeQuery("select * from book where serial_no='"+serial+"' ");
	      if(r1.next())
	      {
	    	  int k=r1.getInt(5);
	    	  k=k-1;
	    	  s.executeUpdate(" update book set issued='"+k+"' where serial_no='"+serial+"'");
	    	  out.println("<a href='Welcomelibrarian.html'>Home</a>");
		      out.println("<br><br>Book returned sucessfully");
	      }
	
	     
	    }
	    else
	    {
	    	out.println("Incorrect input data");
	    	request.getRequestDispatcher("returnbookform.html").include(request,response);

	    }
	     out.close();
	     
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
