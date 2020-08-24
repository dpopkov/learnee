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
import java.sql.SQLException;
import java.util.List;

@WebServlet("/students")
public class StudentControllerServlet extends HttpServlet {
    private StudentDbUtil studentDbUtil;

    @Override
    public void init() throws ServletException {
        try {
            Context ctx = new InitialContext();
            DataSource dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/jsj2b_student_tracker");
            studentDbUtil = new StudentDbUtil(dataSource);
        } catch (NamingException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        listStudents(req, resp);
    }

    private void listStudents(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Student> students;
        try {
            students = studentDbUtil.getStudents();
        } catch (SQLException e) {
            throw new ServletException(e);
        }
        req.setAttribute("studentsList", students);
        req.getRequestDispatcher("/list-students.jsp").forward(req, resp);
    }
}
