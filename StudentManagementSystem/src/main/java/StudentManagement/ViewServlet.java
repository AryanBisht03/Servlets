package StudentManagement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ViewServlet extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	resp.setContentType("text/html");
	PrintWriter out=resp.getWriter();
	try
	{
		// adding driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		// getting the connection
		String url="jdbc:mysql://localhost:3306/jdbc_db";
		String user="root";
		String password="root";
		Connection con=DriverManager.getConnection(url,user,password);
		// getting the prepared statement
		PreparedStatement ps=con.prepareStatement("select * from students");
		ResultSet rs=ps.executeQuery();
		out.print("<h1>Student Data</h1>");
		out.print("<table border='1'>");
		out.print("<tr>\r\n"
				+ "	<th>ID</th>"
				+ "	<th>Name</th>"
				+ "	<th>Email</th>"
				+ "	<th>City</th>"
				+ "	</tr>");
		while(rs.next())
		{
			out.print("<tr>");
			out.print("<td>"+rs.getString("id")+"</td>");
			out.print("<td>"+rs.getString("name")+"</td>");
			out.print("<td>"+rs.getString("email")+"</td>");
			out.print("<td>"+rs.getString("city")+"</td>");
			out.print("</tr>");
		}
		out.print("</table>");
		//closing the connection
		con.close();
	}
	catch(Exception e)
	{
		out.print("<h2>Error Occurred</h2>");
	    out.print(e.getMessage());
		e.printStackTrace();
	}
	}
}