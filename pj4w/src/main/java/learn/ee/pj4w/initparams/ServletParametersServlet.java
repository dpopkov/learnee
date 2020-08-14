package learn.ee.pj4w.initparams;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ServletParametersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ServletConfig config = getServletConfig();
        PrintWriter writer = resp.getWriter();
        writer.append("<html>")
                .append("database: ").append(config.getInitParameter("database")).append("<br>")
                .append("server: ").append(config.getInitParameter("server"))
                .append("</html>");
    }
}
