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
 * Servlet implementation class Issuebook
 */
@WebServlet("/Issuebook")
public class Issuebook extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String serial=request.getParameter("serial");
		String sid=request.getParameter("sid");
		String sname=request.getParameter("sname");
		String bname=request.getParameter("bname");
		try
		{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle");
	    Statement s=conn.createStatement();
	    long millis=System.currentTimeMillis();  
        java.sql.Date date=new java.sql.Date(millis);  
	    ResultSet r=s.executeQuery("select * from book where SERIAL_NO='"+serial+"' ");
	   int q=0,i=0;
	    if(r.next())
	    {
	     q=r.getInt(4);
	     i=r.getInt(5);
	     if(q<=i)
		    {
		    	out.println("<h3>Book Can not be issued</h3>");
		    	request.getRequestDispatcher("/issuebookform.html").include(request, response);
		    }
	     else
	     {
	    	  int x=s.executeUpdate("insert into issuebook values('"+serial+"','"+sid+"','"+sname+"','"+bname+"','"+date+"','No')");
	          if(x>0)
	        	out.println("<h3>Book issued succesfully</h3>");
	          i=i+1;
	          s.executeUpdate("update book set issued='"+i+"' where SERIAL_NO='"+serial+"'");
	          request.getRequestDispatcher("/Welcomelibrarian.html").include(request, response); 
	     }
	    }
	    else
	    {
	    	out.println("<h3>Book Not Present-Invalid Serial No</h3>");
	    	request.getRequestDispatcher("/issuebookform.html").include(request, response);
	    }
	 
	  
	     conn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		out.close();
	}

}
