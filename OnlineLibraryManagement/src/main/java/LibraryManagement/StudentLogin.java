package LibraryManagement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentLogin extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String email=req.getParameter("uemail");
		String password=req.getParameter("upassword");
		resp.setContentType("text/html");
		PrintWriter out=resp.getWriter();
		
		try
		{
			// loading driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			//getting connection
			String url="jdbc:mysql://localhost:3306/library";
			String db="root";
			String password2="root";
			Connection con=DriverManager.getConnection(url,db,password2);
			//getting statemenets
			PreparedStatement ps=con.prepareStatement("select email,password from students where email=? and password=?");
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				HttpSession session=req.getSession();
				session.setAttribute("student",email);
				out.print("<h2>Login successfull!</h2>");
				out.print("<a href='studentlogin.html'>Logout</a>");
			}
			else
			{
				out.print("<h2>Login failed!!</h2>");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}