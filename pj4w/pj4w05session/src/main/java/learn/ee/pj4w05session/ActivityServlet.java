package learn.ee.pj4w05session;

import learn.ee.pj4w.PageVisit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@WebServlet(
        name = "storeServlet",
        urlPatterns = "/do/*"
)
public class ActivityServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        recordSessionActivity(req);
        viewSessionActivity(req, resp);
    }

    private void recordSessionActivity(HttpServletRequest req) {
        HttpSession session = req.getSession();
        if (session.getAttribute("activity") == null) {
            session.setAttribute("activity", Collections.synchronizedList(new ArrayList<PageVisit>()));
        }
        @SuppressWarnings("unchecked")
        List<PageVisit> visits = (List<PageVisit>) session.getAttribute("activity");
        if (!visits.isEmpty()) {
            PageVisit last = visits.get(visits.size() - 1);
            last.setLeftTimestamp(System.currentTimeMillis());
        }
        PageVisit now = new PageVisit();
        now.setEnteredTimestamp(System.currentTimeMillis());
        if (req.getQueryString() == null) {
            now.setRequest(req.getRequestURL().toString());
        } else {
            now.setRequest(req.getRequestURL() + "?" + req.getQueryString());
        }
        try {
            now.setIpAddress(InetAddress.getByName(req.getRemoteAddr()));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        visits.add(now);
    }

    private void viewSessionActivity(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/view/viewSessionActivity.jsp").forward(req, resp);
    }
}
