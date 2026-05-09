package viewer;

import java.io.*;
import java.sql.*;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class MyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
                         throws ServletException, IOException {

        resp.setContentType("text/html");

        PrintWriter out = resp.getWriter();

        out.println("<html><body>");

        try {

            out.println("<h1>Servlet Started</h1>");

            // Load Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connection
            String url =
            "jdbc:mysql://localhost:3306/college_db";

            String user = "root";
            String password = "root";

            Connection con =
            DriverManager.getConnection(
            url,user,password);

            // Query
            PreparedStatement ps =
            con.prepareStatement(
            "select * from students");

            ResultSet rs =
            ps.executeQuery();

            out.println("<h1>Student Data</h1>");

            out.println("<table border='1'>");

            out.println(
            "<tr>" +
            "<th>ID</th>" +
            "<th>Name</th>" +
            "<th>Email</th>" +
            "<th>City</th>" +
            "</tr>");

            while(rs.next()) {

                out.println("<tr>");

                out.println(
                "<td>" +
                rs.getInt("id") +
                "</td>");

                out.println(
                "<td>" +
                rs.getString("name") +
                "</td>");

                out.println(
                "<td>" +
                rs.getString("email") +
                "</td>");

                out.println(
                "<td>" +
                rs.getString("city") +
                "</td>");

                out.println("</tr>");
            }

            out.println("</table>");

            con.close();
        }

        catch(Exception e) {

            out.println("<h1>Error Occurred</h1>");

            out.println(e);
        }

        out.println("</body></html>");
    }
}