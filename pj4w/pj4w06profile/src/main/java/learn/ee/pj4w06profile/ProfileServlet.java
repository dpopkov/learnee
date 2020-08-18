package learn.ee.pj4w06profile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@WebServlet(
        name = "profileServlet",
        urlPatterns = "/profile"
)
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setUserId(12345L);
        user.setUserName("Coder42");
        user.setFirstName("Alice");
        user.setLastName("Smith");

        ConcurrentHashMap<String, Boolean> permissions = new ConcurrentHashMap<>();
        permissions.put("user", true);
        permissions.put("moderator", true);
        permissions.put("admin", false);
        user.setPermissions(permissions);

        req.setAttribute("user", user);
        req.getRequestDispatcher("/WEB-INF/jsp/view/profile.jsp").forward(req, resp);
    }
}
