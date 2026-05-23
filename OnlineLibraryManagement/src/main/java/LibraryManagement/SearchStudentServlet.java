package LibraryManagement;

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
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class SearchStudentServlet extends HttpServlet
{
	private static final Logger logger=
			Logger.getLogger(SearchStudentServlet.class.getName());
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email=req.getParameter("uemail");
		resp.setContentType("text/html");
		PrintWriter out=resp.getWriter();
		try
		{
		    FileHandler fh=
		    new FileHandler("C:\\logs\\library.log",true);

		    fh.setFormatter(new SimpleFormatter());

		    logger.addHandler(fh);
		}
		catch(Exception e)
		{
		    logger.severe(e.toString());

		    e.printStackTrace();

		    out.print("Something went wrong");
		}
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/library";
			String db="root";
			String password2="root2";
			Connection con=DriverManager.getConnection(url,db,password2);
			PreparedStatement ps=con.prepareStatement("select * from students where email=?");
			ps.setString(1, email);
			ResultSet rs=ps.executeQuery();
			out.print("<table border='1'>");
			out.print("<tr>");
			out.print("<th>Id</th>");
			out.print("<th>Name</th>");
			out.print("<th>Email</th>");
			out.print("<th>Password</th>");
			out.print("<th>Phone</th>");
			out.print("<th>City</th>");
			out.print("<th>Created_at</th>");
			out.print("</tr>");
			
			while(rs.next())
			{
				out.print("<tr>");
				out.print("<td>"+rs.getInt("id")+"</td>");
				out.print("<td>"+rs.getString("name")+"</td>");
				out.print("<td>"+rs.getString("email")+"</td>");
				out.print("<td>"+rs.getString("password")+"</td>");
				out.print("<td>"+rs.getString("phone")+"</td>");
				out.print("<td>"+rs.getString("city")+"</td>");
				out.print("<td>"+rs.getString("created_at")+"</td>");
				out.print("</tr>");
			}
			out.print("</table>");
			rs.close();
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}
}