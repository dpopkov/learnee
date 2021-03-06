package learn.ee.pj4wcustsupport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import learn.ee.pj4wcustsupport.chat.ChatEndpoint;
import org.apache.commons.lang3.math.NumberUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Manages the listing, creating and joining of chat sessions.
 */
@WebServlet(
        name = "chatServlet",
        urlPatterns = "/chat"
)
public class ChatServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if("list".equals(action)) {
            log.debug("Listing pending support chats.");
            req.setAttribute("sessions", ChatEndpoint.pendingSessions);
            req.getRequestDispatcher("/WEB-INF/jsp/view/chat/list.jsp")
                    .forward(req, resp);
        } else {
            log.info("Rejected chat servlet GET request with invalid action [{}].", action);
            resp.sendRedirect("tickets");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Expires", "Thu, 1 Jan 1970 12:00:00 GMT");
        resp.setHeader("Cache-Control", "max-age=0, must-revalidate, no-cache");

        String action = req.getParameter("action");
        String view = null;
        switch (action) {
            case "new":
                log.debug("Accepted new chat request.");
                req.setAttribute("chatSessionId", 0);
                view = "chat";
                break;
            case "join":
                String id = req.getParameter("chatSessionId");
                if (!NumberUtils.isDigits(id)) {
                    resp.sendRedirect("chat?list");
                } else {
                    log.debug("Pending chat request joined.");
                    req.setAttribute("chatSessionId", Long.parseLong(id));
                    view = "chat";
                }
                break;
            default:
                resp.sendRedirect("tickets");
                break;
        }

        if (view != null) {
            req.getRequestDispatcher("/WEB-INF/jsp/view/chat/" + view + ".jsp").forward(req, resp);
        }
    }
}
