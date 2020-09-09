package learn.ee.pj4w10game.servlets;

import learn.ee.pj4w10game.game.TicTacToe;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(
        name = "gameServlet",
        urlPatterns = "/ticTacToe"
)
public class GameServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(GameServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<Long, String> pendingGames = TicTacToe.getPendingGames();
        log.debug("pendingGames = {}", pendingGames);
        req.setAttribute("pendingGames", pendingGames);
        view("list", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        log.debug("action = {}", action);
        if("join".equalsIgnoreCase(action)) {
            String gameIdString = req.getParameter("gameId");
            log.debug("gameIdString = {}", gameIdString);
            String username = req.getParameter("username");
            log.debug("username = {}", username);
            if(username == null || !NumberUtils.isDigits(gameIdString)) {
                list(req, resp);
            } else {
                req.setAttribute("action", "join");
                req.setAttribute("username", username);
                req.setAttribute("gameId", Long.parseLong(gameIdString));
                view("game", req, resp);
            }
        } else if("start".equalsIgnoreCase(action)) {
            String username = req.getParameter("username");
            log.debug("username = {}", username);
            if(username == null) {
                list(req, resp);
            } else {
                req.setAttribute("action", "start");
                req.setAttribute("username", username);
                req.setAttribute("gameId", TicTacToe.queueGame(username));
                view("game", req, resp);
            }
        } else {
            list(req, resp);
        }
    }

    private void view(String view, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("view({})", view);
        req.getRequestDispatcher("/WEB-INF/jsp/view/ticTacToe/" + view + ".jsp").forward(req, resp);
    }

    private void list(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        log.debug("list()");
        resp.sendRedirect(resp.encodeRedirectURL(req.getContextPath() + "/ticTacToe"));
    }
}
