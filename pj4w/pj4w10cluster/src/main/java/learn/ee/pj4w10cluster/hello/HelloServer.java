package learn.ee.pj4w10cluster.hello;

import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/helloServer")
public class HelloServer {
    @OnMessage
    public void processGreeting(String message, Session session) {
        System.out.println("HelloServer: Message received: " + message);
    }
}
