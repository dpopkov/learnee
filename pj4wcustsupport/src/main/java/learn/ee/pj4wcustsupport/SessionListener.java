package learn.ee.pj4wcustsupport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionIdListener;
import javax.servlet.http.HttpSessionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebListener
public class SessionListener implements HttpSessionListener, HttpSessionIdListener {
    private static final Logger log = LogManager.getLogger();

    private final SimpleDateFormat formatter = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");

    @Override
    public void sessionIdChanged(HttpSessionEvent event, String oldSessionId) {
        SessionRegistry.updateId(event.getSession(), oldSessionId);
        log.info("{}: Session ID {} changed to {}.", date(), oldSessionId, event.getSession().getId());
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        SessionRegistry.add(se.getSession());
        log.info("{}: Session {} created.", date(), se.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        SessionRegistry.remove(se.getSession());
        log.info("{}: Session {} destroyed.", date(), se.getSession().getId());
    }

    private String date() {
        return formatter.format(new Date());
    }
}
