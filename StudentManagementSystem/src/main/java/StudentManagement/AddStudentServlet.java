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

public class AddStudentServlet extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name=req.getParameter("uname");
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
			PreparedStatement ps=con.prepareStatement("insert into students(name,email,city)values(?,?,?)");
			ps.setString(1,name);
			ps.setString(2,email);
			ps.setString(3,city);
			int x=ps.executeUpdate();
			if(x>0)
			{
				out.print("updated succesfully");
			}
			else
			{
				out.print("Not updated successfully");
			}
			con.close();
	    }
	    catch(Exception e)
	    {
	    	 resp.setContentType("text/html");
	    	    out.print("<h2>Error Occurred</h2>");
	    	    out.print(e.getMessage());
	    	e.printStackTrace();
	    }
	}
}