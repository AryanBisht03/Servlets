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

public class StudentRegistrationServlet extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String name=req.getParameter("uname");
		String email=req.getParameter("uemail");
		String password=req.getParameter("upassword");
		String phone=req.getParameter("uphone");
		String city=req.getParameter("ucity");
		resp.setContentType("text/html");
		PrintWriter out=resp.getWriter();
		
		try
		{
			// getting the driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			//getting the connection
			String url="jdbc:mysql://localhost:3306/library";
			String db="root";
			String password2="root";
			Connection con=DriverManager.getConnection(url,db,password2);
			// statements
			PreparedStatement ps=con.prepareStatement("insert into students(name,email,password,phone,city)values(?,?,?,?,?)");
			ps.setString(1,name);
			ps.setString(2,email);
			ps.setString(3,password);
			ps.setString(4,phone);
			ps.setString(5,city);
			
			int x=ps.executeUpdate();
			if(x>0)
			{
				out.print("<h2>Registered Successfully</h2>");
			}
			else
			{
				out.print("<h2>Not Registered Successfully</h2>");
			}
			ps.close();
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}