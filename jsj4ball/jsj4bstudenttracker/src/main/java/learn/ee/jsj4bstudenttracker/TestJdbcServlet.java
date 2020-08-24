package learn.ee.jsj4bstudenttracker;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/testJdbc")
public class TestJdbcServlet extends HttpServlet {
    private static int doGetSequence;
//    @Resource(name="java:comp/env/jdbc/jsj2b_student_tracker")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        try {
            Context ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/jsj2b_student_tracker");
        } catch (NamingException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        int currentCount;
        synchronized (this) {
            currentCount = ++doGetSequence;
        }
        out.println("<p>doGet count = " + currentCount + "</p>");

        String sql = "SELECT email FROM student";
        try (Connection conn = dataSource.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            out.println("<ol>");
            while (rs.next()) {
                String email = rs.getString("email");
                out.println("<li>" + email + "</li>");
            }
            out.println("</ol>");
        } catch (SQLException e) {
            out.println(e.getMessage());
            e.printStackTrace();
        }
        out.println("</body></html>");
    }
}
