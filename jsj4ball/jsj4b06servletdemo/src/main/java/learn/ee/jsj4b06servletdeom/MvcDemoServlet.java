package learn.ee.jsj4b06servletdeom;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/mvcDemo")
public class MvcDemoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] data = {"Alice", "Michael", "Bobby"};
        req.setAttribute("student_list", data);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/view-students.jsp");
        dispatcher.forward(req, resp);
    }
}
