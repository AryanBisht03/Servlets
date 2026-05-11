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

public class DeleteServlet extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		int id=Integer.parseInt(req.getParameter("uid"));
		resp.setContentType("text/html");
		PrintWriter out=resp.getWriter();
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/jdbc_db";
			String user="root";
			String password="root";
			Connection con=DriverManager.getConnection(url,user,password);
			PreparedStatement ps=con.prepareStatement("delete from students where id=?");
			ps.setInt(1,id);
			int x=ps.executeUpdate();
			if(x>0)
				out.print("Student deleted");
			else
				out.print("Student not deleted");
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