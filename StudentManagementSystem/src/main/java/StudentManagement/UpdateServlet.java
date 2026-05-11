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

public class UpdateServlet extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String email=req.getParameter("uemail");
	String city=req.getParameter("ucity");
	resp.setContentType("text/html");
	PrintWriter out=resp.getWriter();
	
	try
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url="jdbc:mysql://localhost:3306/jdbc_db";
		String user="root";
		String password="root";
		Connection con=DriverManager.getConnection(url,user,password);
		PreparedStatement ps=con.prepareStatement("update students set city=? where email=?");
		ps.setString(1,city);
		ps.setString(2,email);
		int x=ps.executeUpdate();
		if(x>0)
			out.print("City is updated");
		else
			out.print("City is not updated");
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