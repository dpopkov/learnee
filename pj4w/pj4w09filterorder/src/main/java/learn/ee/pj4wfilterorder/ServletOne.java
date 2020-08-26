package learn.ee.pj4wfilterorder;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "servletOne",
        urlPatterns = "/servletOne"
)
public class ServletOne extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("Entering ServletOne.doGet()");
        resp.setContentType("text/plain");
        resp.getWriter().write("Servlet One");
        System.out.println("Leaving ServletOne.doGet()");
    }
}
