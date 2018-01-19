package Admin;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Wrapper;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.sql.*;
/**
 * Servlet implementation class Viewlibrarian
 */
@WebServlet("/Viewlibrarian")
public class Viewlibrarian extends HttpServlet {
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
		out.println("<title>View Librarian</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h2 align=center>Details of Librarian</h2>");
		out.println("<table>");
		out.println("<tr><th>User Id</th><th>Name</th><th>Email   </th><th>Password    </th><th>Edit   </th><th>Delete</th></tr>");
	   out.println("<a href=Welcomeadmin.html>Home</a>");
		try
		{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle");
	    Statement s=conn.createStatement();
	    ResultSet r=s.executeQuery("select * from addlibrarian");
	    while(r.next())
	    {
	    	String i=r.getString(1);
	    	String nam=r.getString(2);
	    	String emai=r.getString(3);
	    	String pas=r.getString(4);
	    	out.println("<tr><td>"+i+"</td><td>"+nam+"</td><td>"+emai+"</td><td>"+pas+"</td><td><a href='Editlibrarianform?id="+i+"'>Edit</a></td><td><a href='Deletelibrarian?id="+i+"'>Delete</a></td></tr>");
	      
	    }
	    conn.close();
		}
		catch(Exception e)
		{}
		
		out.println("</html></body>");
		request.getRequestDispatcher("/footer.html").include(request, response);
		out.close();
		
	

}
}
