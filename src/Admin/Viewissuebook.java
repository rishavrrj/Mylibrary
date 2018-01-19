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
 * Servlet implementation class Viewissuebook
 */
@WebServlet("/Viewissuebook")
public class Viewissuebook extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{	
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head><style>table,th,td{border:1px solid black;border-collapse:collapse}</style>");
		
		out.println("<title>Issued Books</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h2 align=center>Issued Books</h2>");
		out.println("<table >");
		out.println("<tr><th>Serial No</th><th>Student Id </th><th>Student Name </th><th>Book Name</th><th>Issued Date</th><th>Return Status</th></tr>");
		try
		{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle");
	    Statement s=conn.createStatement();
	    ResultSet r=s.executeQuery("select * from issuebook");
	    while(r.next())
	    {
	    	String sid=r.getString(1);
	    	String sidd=r.getString(2);
	    	String sname=r.getString(3);
	    	String bname=r.getString(4);
	    	String date=r.getString(5);
	    	String status=r.getString(6);
	    	
	    	out.println("<tr><td>"+sid+"</td><td>"+sidd+"</td><td>"+sname+"</td><td>"+bname+"</td><td>"+date+"</td><td>"+status+"</td></tr>");
	    }
	    conn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		out.println("</table></body></html>");
	    out.close();
	}

}
