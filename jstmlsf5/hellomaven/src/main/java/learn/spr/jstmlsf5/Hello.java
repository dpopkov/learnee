package learn.spr.jstmlsf5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hello {
    private final static Logger log = LoggerFactory.getLogger(Hello.class);

    public static void main(String[] args) {
        String course = "Java Spring Tutorial Masterclass - Learn Spring Framework 5";
        log.trace("Hello {}", course);
        log.debug("Hello {}", course);
        log.info("Hello {}", course);
        log.warn("Hello {}", course);
        log.error("Hello {}", course);
    }
}
