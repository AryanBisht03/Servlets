package MyCalculator;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Calculator extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int num1=Integer.parseInt(req.getParameter("num1"));
		int num2=Integer.parseInt(req.getParameter("num2"));
		String operation=req.getParameter("operation");
		int result=0;
		if(operation.equals("add"))
		{
		  result=num1+num2;	
		}
		else if(operation.equals("sub"))
		{
			result=num1-num2;
		}
		else if(operation.equals("mul"))
		{
			result=num1*num2;
		}
		else if(operation.equals("div"))
		{
			result=num1/num2;
		}
		
		resp.setContentType("text/html");
		PrintWriter out=resp.getWriter();
		out.print("<h2>Result: "+result+"</h2>");
}
}