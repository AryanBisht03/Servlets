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

public class AdminLoginServlet extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String username=req.getParameter("uname");
	String password=req.getParameter("upassword");
	resp.setContentType("text/html");
	PrintWriter out=resp.getWriter();
	
	try
	{
		//getting the driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		//getting the connection
		String url="jdbc:mysql://localhost:3306/library";
		String db="root";
		String password2="root";
		Connection con=DriverManager.getConnection(url,db,password2);
		
		// getting the statements
		PreparedStatement ps=con.prepareStatement("select * from admins where username=? and password=?");
		ps.setString(1, username);
		ps.setString(2, password);
		ResultSet rs=ps.executeQuery();
		if(rs.next())
		{
			HttpSession session=req.getSession();
			session.setAttribute("admin", username);
			out.print("<h2> Login Successful</h2>");
			out.print("<a href='adminlogout.html'>Logout</a>");
		}
		else
		{
			out.print("<h2>Invalid Credentials</h2>");
		}
	    rs.close();
	    ps.close();
	    con.close();
	}
	catch(Exception e)
	{
	    resp.setContentType("text/html");
	    out.println("<h1>ERROR:</h1>");
	    out.println(e);
	    e.printStackTrace();
	}
	}
}