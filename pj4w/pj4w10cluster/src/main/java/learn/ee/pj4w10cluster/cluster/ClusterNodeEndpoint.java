package learn.ee.pj4w10cluster.cluster;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

@ServerEndpoint("/clusterNodeSocket/{nodeId}")
public class ClusterNodeEndpoint {
    private static final List<Session> connectedNodes = new ArrayList<>();

    @OnOpen
    public void opOpen(Session session, @PathParam("nodeId") String nodeId) {
        System.out.println("INFO: Node [" + nodeId + "] connected to cluster.");
        ClusterMessage message = new ClusterMessage(nodeId, "Joined the cluster");
        try {
            byte[] bytes = ClusterNodeEndpoint.toByteArray(message);
            for (Session node : connectedNodes) {
                node.getBasicRemote().sendBinary(ByteBuffer.wrap(bytes));
            }
        } catch (IOException e) {
            System.err.println("ERROR: Exception when notifying of new node");
            e.printStackTrace();
        }
        connectedNodes.add(session);
    }

    @OnMessage
    public void onMessage(Session session, byte[] message) {
        try {
            for (Session node : connectedNodes) {
                if (node != session) {
                    node.getBasicRemote().sendBinary(ByteBuffer.wrap(message));
                }
            }
        } catch (IOException e) {
            System.err.println("ERROR: Exception when handling message on server");
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose(Session session, @PathParam("nodeId") String nodeId) {
        System.out.println("INFO: Node [" + nodeId + "] disconnected.");
        connectedNodes.remove(session);
        ClusterMessage message = new ClusterMessage(nodeId, "Left the cluster.");
        try {
            byte[] bytes = ClusterNodeEndpoint.toByteArray(message);
            for(Session node : connectedNodes) {
                node.getBasicRemote().sendBinary(ByteBuffer.wrap(bytes));
            }
        } catch(IOException e) {
            System.err.println("ERROR: Exception when notifying of left node");
            e.printStackTrace();
        }
    }

    private static byte[] toByteArray(ClusterMessage message) throws IOException {
        try (ByteArrayOutputStream buffer = new ByteArrayOutputStream();
             ObjectOutputStream out = new ObjectOutputStream(buffer)) {
            out.writeObject(message);
            return buffer.toByteArray();
        }
    }
}
