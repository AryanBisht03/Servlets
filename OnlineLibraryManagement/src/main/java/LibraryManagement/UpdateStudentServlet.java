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

public class UpdateStudentServlet extends HttpServlet
{
	@Override
	// isme update karenge badme for checkbox ke hisab se updation dalenge.
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String city=req.getParameter("ucity");
	String phone=req.getParameter("uphone");
	String password=req.getParameter("upassword");
	String email=req.getParameter("uemail");
	resp.setContentType("text/html");
	PrintWriter out=resp.getWriter();
	try
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url="jdbc:mysql://localhost:3306/library";
		String db="root";
		String password2="root";
		Connection con=DriverManager.getConnection(url,db,password2);
		PreparedStatement ps=con.prepareStatement("update students set city=?,phone=?,password=? where email=?");
		ps.setString(1,city);
		ps.setString(2,phone);
		ps.setString(3,password);
		ps.setString(4,email);
		int x=ps.executeUpdate();
		if(x>0)
		{
			out.print("<h2>Details are updated successfully</h2>");
		}
		else
		{
			out.print("<h2>Not updated</h2>");
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