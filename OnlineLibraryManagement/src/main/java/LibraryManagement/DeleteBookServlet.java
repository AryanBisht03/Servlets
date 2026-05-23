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

public class DeleteBookServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	int id=Integer.parseInt(req.getParameter("uid"));
    resp.setContentType("text/html");
    PrintWriter out=resp.getWriter();
    try
    {
    	Class.forName("com.mysql.cj.jdbc.Driver");
    	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","root");
    	PreparedStatement ps=con.prepareStatement("delete from books where id=?");
    	ps.setInt(1, id);
    	int x=ps.executeUpdate();
    	if(x>0)
    	{
    		out.print("<h2>Deleted book successfully</h2>");
    	}
    	else
    	{
    		out.print("<h2>Invalid Book Id</h2>");
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