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

public class DeleteStudentServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String email=req.getParameter("uemail");
	resp.setContentType("text/html");
	PrintWriter out=resp.getWriter();
	
	try
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url="jdbc:mysql://localhost:3306/library";
		String db="root";
		String password="root";
		Connection con=DriverManager.getConnection(url,db,password);
		PreparedStatement ps=con.prepareStatement("delete from students where email=?");
		ps.setString(1, email);
		int x=ps.executeUpdate();
		if(x>0)
		{
			out.print("<h2>Student Removed</h2>");
		}
		else
		{
			out.print("<h2>Incorrect email id");
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