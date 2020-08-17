package learn.ee.pj4wcustsupport;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionRegistry {
    private static final Map<String, HttpSession> SESSIONS = new ConcurrentHashMap<>();

    private SessionRegistry() { }

    public static void add(HttpSession session) {
        SESSIONS.put(session.getId(), session);
    }

    public static void updateId(HttpSession session, String oldSessionId) {
        synchronized (SESSIONS) {
            SESSIONS.remove(oldSessionId);
            add(session);
        }
    }

    public static void remove(HttpSession session) {
        SESSIONS.remove(session.getId());
    }

    public static List<HttpSession> getAll() {
        return new ArrayList<>(SESSIONS.values());
    }

    public static int size() {
        return SESSIONS.size();
    }
}
