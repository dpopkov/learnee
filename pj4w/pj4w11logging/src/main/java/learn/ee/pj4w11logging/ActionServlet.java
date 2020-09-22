package learn.ee.pj4w11logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.MarkerManager;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Adding ?action=readLicense reads the Tomcat license file back to the browser.
 * <br>
 * Change the action to readFoo and an error, complete with exception stack
 * trace, appears in the log file.
 * This time the error also appears in the console (debugger) output because
 * it included the Marker named PJ4W_CONSOLE.
 * Notice the fish tag for each message logged and how it
 * is the same for multiple messages logged in the same request.
 * @see <a href="../../../../resources/log4j2.xml">log4j2.xml</a>
 */
@WebServlet(
        name = "actionServlet",
        urlPatterns = "/files"
)
public class ActionServlet extends HttpServlet {
    public static final Logger log = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getParameter("action");
        if (action != null) {
            log.info("Received request with action {}.", action);
            String contents;
            switch (action) {
                case "readFoo":
                    contents = readFile("../foo.bar", true);
                    break;
                case "readLicense":
                    contents = readFile("../LICENSE", false);
                    break;
                default:
                    contents = "Bad action " + action + " specified.";
                    log.warn("Action {} not supported.", action);
                    break;
            }
            if (contents != null) {
                resp.getWriter().write(contents);
            }
        } else {
            log.error("No action specified");
            resp.getWriter().write("No action specified");
        }
    }

    private String readFile(String fileName, boolean deleteWhenDone) {
        log.traceEntry("readFile(fileName={}, deleteWhenDone={})", fileName, deleteWhenDone);
        Path path = Paths.get(fileName);
        try {
            String contents = Files.readString(path);
            log.info("Successfully read file {}.", fileName);
            return log.traceExit("readFile() returns result {}", contents);
        } catch (IOException e) {
            log.error(MarkerManager.getMarker("PJ4W_CONSOLE"), "Failed to read file {}.",  fileName, e);
            return null;
        }
    }
}
