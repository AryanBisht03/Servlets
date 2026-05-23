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

public class ViewStudentsServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		int id=Integer.parseInt(req.getParameter("uid"));
//		String name=req.getParameter("uname");
//		String email=req.getParameter("uemail");
//		String password=req.getParameter("upassword");
//		String phone=req.getParameter("uphone");
//		String city=req.getParameter("ucity");
//		String created_at=req.getParameter("ucreated_at");
//		resp.setContentType("text/html");
		PrintWriter out=resp.getWriter();
		
		try
		{
			//loading the driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			//getting the connection
			String url="jdbc:mysql://localhost:3306/library";
			String db="root";
			String password2="root";
			Connection con=DriverManager.getConnection(url,db,password2);
			//statements
			PreparedStatement ps=con.prepareStatement("select * from students");
			ResultSet rs=ps.executeQuery();
			
				out.print("<table border='1'>");
				
				out.print("<tr>"+"<th>ID</th>"+"<th>Name</th>"+"<th>Email</th>"+"<th>Password</th>"+"<th>Phone No.</th>"+"<th>City</th>"+"<th>Registered At</th>"+"</tr>");
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
			ps.close();
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
}