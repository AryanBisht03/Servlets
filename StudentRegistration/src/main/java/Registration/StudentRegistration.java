package Registration;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
public class StudentRegistration extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String name=req.getParameter("name");
	String email=req.getParameter("email");
	String city=req.getParameter("city");
	resp.setContentType("text/html");
	PrintWriter out=resp.getWriter();
	
	try
	{
		// loading the driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		//creating the connection
		String url="jdbc:mysql://localhost:3306/student_db";
		String db="root";
		String password="root";
		Connection con=DriverManager.getConnection(url,db,password);
		// prepared statement
		PreparedStatement ps=con.prepareStatement("insert into students(name,email,city) values(?,?,?)");
		ps.setString(1,name);
		ps.setString(2,email);
		ps.setString(3,city);
		int x=ps.executeUpdate();
		if(x>0)
		{
			out.print("<h2>Registration successfull!</h2>");
		}
		else
		{
			out.print("<h2>Registration failed</h2>");
		}
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


