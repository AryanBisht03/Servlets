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

public class AddBookServlet extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	int id=Integer.parseInt(req.getParameter("uid"));
	String title=req.getParameter("utitle");
	String author=req.getParameter("uauthor");
	int catagory_id=Integer.parseInt(req.getParameter("ucatagory_id"));
	int quantity=Integer.parseInt(req.getParameter("uquantity"));
	float price=Float.parseFloat(req.getParameter("uprice"));
	resp.setContentType("text/html");
	PrintWriter out=resp.getWriter();
	try
	{
		// loading the driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		// getting the connection
		String url="jdbc:mysql://localhost:3306/library";
		String db="root";
		String password="root";
		Connection con=DriverManager.getConnection(url,db,password);
		//statement
		PreparedStatement ps=con.prepareStatement("insert into books(id,title,author,catagory_id,quantity,price)values(?,?,?,?,?,?)");
		ps.setString(1, String.valueOf(id));
		ps.setString(2, title);
		ps.setString(3, author);
		ps.setString(4, String.valueOf(catagory_id));
		ps.setString(5, String.valueOf(quantity));
		ps.setString(6, String.valueOf(price));
		int x=ps.executeUpdate();
		if(x>0)
		{
			out.print("Book is added successfully");
		}
		else
		{
			out.print("Book is not added");
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