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
 * Servlet implementation class Viewbook
 */
@WebServlet("/Viewbook")
public class Viewbook extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.print("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head><style>table,th,td{border:1px solid black;border-collapse:collapse}</style>");
		out.println("<title>View Books</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h2 align=center>View Books</h2>");
		out.println("<table>");
		out.println("<tr><th>Serial No</th><th>Name</th><th>Author </th><th>Quantity </th><th>Issued</th><th>Delete</th></tr>");
		try
		{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle");
	    Statement s=conn.createStatement();
	    ResultSet r=s.executeQuery("select * from book");
	    while(r.next())
	    {
	    	String sid=r.getString(1);
	    	String n=r.getString(2);
	    	String a=r.getString(3);
	    	String q=r.getString(4);
	    	int w=Integer.parseInt(q);
	    	String m=r.getString(5);
	    	int z=Integer.parseInt(m);
	    	out.println("<tr><td>"+sid+"</td><td>"+n+"</td><td>"+a+"</td><td>"+w+"</td><td>"+z+"</td><td><a href='Deletebook?id="+sid+"'>Delete</a></td></tr>");
	    }
	    conn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		out.println("</table></html></body>");
		request.getRequestDispatcher("/footer.html").include(request, response);
		out.close();
	}

}
