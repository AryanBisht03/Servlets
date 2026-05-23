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

public class UpdateBookServlet extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	int quantity=Integer.parseInt(req.getParameter("uquantity"));
	float price=Float.parseFloat(req.getParameter("uprice"));
	int id=Integer.parseInt(req.getParameter("uid"));
	resp.setContentType("text/html");
	 PrintWriter out=resp.getWriter();
	    
	    try
	    {
	    	// getting the driver
	    	Class.forName("com.mysql.cj.jdbc.Driver");
	    	// connection
	    	String url="jdbc:mysql://localhost:3306/library";
	    	String db="root";
	    	String password="root";
	    	Connection con=DriverManager.getConnection(url,db,password);
	    	// getting statement
	    	PreparedStatement ps=con.prepareStatement("update books set quantity=?,title=? where id=?");
	    	ps.setInt(1, quantity);
	    	ps.setFloat(2, price);
	    	ps.setInt(3, id);
	    	int x=ps.executeUpdate();
	    	if(x>0)
	    	{
	    		out.print("<h2>Updated successfully</h2>");
	    	}
	    	else
	    	{
	    		out.print("<h2>Not updated successfully</h2>");
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