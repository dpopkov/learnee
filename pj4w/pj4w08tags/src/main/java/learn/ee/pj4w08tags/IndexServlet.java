package learn.ee.pj4w08tags;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

@WebServlet(
        name = "indexServlet",
        urlPatterns = "/index"
)
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String view = "hello";
        if (req.getParameter("dates") != null) {
            req.setAttribute("date", new Date());
            req.setAttribute("calendar", Calendar.getInstance());
            req.setAttribute("instant", Instant.now());
            view = "dates";
        }
        req.getRequestDispatcher("/WEB-INF/jsp/view/" + view + ".jsp").forward(req, resp);
    }
}
