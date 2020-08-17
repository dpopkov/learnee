package learn.ee.pj4wcustsupport;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionIdListener;
import javax.servlet.http.HttpSessionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebListener
public class SessionListener implements HttpSessionListener, HttpSessionIdListener {
    private final SimpleDateFormat formatter = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");

    @Override
    public void sessionIdChanged(HttpSessionEvent event, String oldSessionId) {
        SessionRegistry.updateId(event.getSession(), oldSessionId);
        System.out.println(date() + ": Session ID " + oldSessionId + " changed to " + event.getSession().getId());
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        SessionRegistry.add(se.getSession());
        System.out.println(date() + ": Session " + se.getSession().getId() + " created");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        SessionRegistry.remove(se.getSession());
        System.out.println(date() + ": Session " + se.getSession().getId() + " destroyed");
    }

    private String date() {
        return formatter.format(new Date());
    }
}
