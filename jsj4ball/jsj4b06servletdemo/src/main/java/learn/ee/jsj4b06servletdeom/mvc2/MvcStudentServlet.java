package learn.ee.jsj4b06servletdeom.mvc2;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/mvcStudent")
public class MvcStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Student> students = StudentDataUtil.getStudents();
        req.setAttribute("student_list", students);
        RequestDispatcher dispatcher = req.getRequestDispatcher("mvc-student-list.jsp");
        dispatcher.forward(req, resp);
    }
}
