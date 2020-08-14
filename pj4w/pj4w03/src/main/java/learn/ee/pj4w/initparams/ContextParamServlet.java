package learn.ee.pj4w.initparams;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        name = "contextParamServlet",
        urlPatterns = {"/contextParams"}
)
public class ContextParamServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ServletContext context = getServletContext();
        PrintWriter writer = resp.getWriter();
        writer.append("<html>")
                .append("settingOne: ").append(context.getInitParameter("settingOne"))
                .append("<br>settingTwo: ").append(context.getInitParameter("settingTwo"))
                .append("</html>");
    }
}
