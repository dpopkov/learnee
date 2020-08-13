package learn.ee.pj4w;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        name = "helloServlet",
        urlPatterns = {"/greeting", "/hello", "/salutation", "/wazzup"},
        loadOnStartup = 1
)
public class HelloServlet extends HttpServlet {
    private static final String DEFAULT_USER = "Guest";

    @Override
    public void init() {
        System.out.println("Servlet " + this.getServletName() + " has started.");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String user = req.getParameter("user");
        if (user == null) {
            user = DEFAULT_USER;
        }
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.append("<!DOCTYPE html>\r\n")
                .append("<html>\r\n")
                .append("   <head>\r\n")
                .append("       <title>Hello User Application</title>\r\n")
                .append("   </head>\r\n")
                .append("   <body>\r\n")
                .append("       Hello, ").append(user).append("!<br/><br/>\r\n")
                .append("       <form action=\"greeting\" method=\"POST\">\r\n")
                .append("           Enter your name:<br/>\r\n")
                .append("           <input type=\"text\" name=\"user\"/><br/>\r\n")
                .append("           <input type=\"submit\" value=\"Submit\"/>\r\n")
                .append("       </form>\r\n")
                .append("   </body>\r\n")
                .append("</html>\r\n");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doGet(req, resp);
    }

    @Override
    public void destroy() {
        System.out.println("Servlet " + this.getServletName() + " has stopped");
    }
}
