package learn.ee.pj4w10cluster.hello;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@WebServlet(
        name = "greetingServlet",
        urlPatterns = "/sayHello"
)
@ClientEndpoint
public class GreetingServlet extends HttpServlet {
    private int doGetCount;
    private Session webSocketSession;

    @Override
    public void init() throws ServletException {
        final String path = getServletContext().getContextPath() + "/helloServer";
        try {
            URI uri = new URI("ws", "localhost:8080", path, null, null);
            webSocketSession = ContainerProvider.getWebSocketContainer().connectToServer(this, uri);
        } catch (URISyntaxException | DeploymentException | IOException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        webSocketSession.getBasicRemote().sendText("Hi, this is GreetingServlet! doGetCount=" + doGetCount++);
    }

    @Override
    public void destroy() {
        try {
            webSocketSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnMessage
    public void processMessageFromServer(String message, Session session) {
        System.out.println("Message came from the server ! " + message);
    }
}
