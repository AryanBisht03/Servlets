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
import java.sql.ResultSet;

public class ViewBooksServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String title=req.getParameter("utitle");
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
    	PreparedStatement ps=con.prepareStatement("select * from books");
    	ResultSet rs=ps.executeQuery();
    	
    	out.print("<table border='1'>");
    	out.print("<tr>");
    	out.print("<th>"+"ID"+"</th>");
    	out.print("<th>"+"Title"+"</th>");
    	out.print("<th>"+"Author Name"+"</th>");
    	out.print("<th>"+"Catagory Id"+"</th>");
    	out.print("<th>"+"Quantity"+"</th>");
    	out.print("<th>"+"Price"+"</th>");
    	out.print("</tr>");
    	
    	while(rs.next())
    	{
    		out.print("<tr>");
    		out.print("<td>"+rs.getInt("id")+"</td>");
    		out.print("<td>"+rs.getString("title")+"</td>");
    		out.print("<td>"+rs.getString("author")+"</td>");
    		out.print("<td>"+rs.getInt("catagory_id")+"</td>");
    		out.print("<td>"+rs.getInt("quantity")+"</td>");
    		out.print("<td>"+rs.getFloat("price")+"</td>");
    		out.print("</tr>");
    	}
    	out.print("<table border='1'>");
    	ps.close();
    	rs.close();
    	con.close();
    }
    catch(Exception e)
    {
    	e.printStackTrace();
    }
	}
}