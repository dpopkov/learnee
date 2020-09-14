package learn.spr.jstmlsf5.console;

import learn.spr.jstmlsf5.core.AppConfig;
import learn.spr.jstmlsf5.core.MessageGenerator;
import learn.spr.jstmlsf5.core.NumberGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        log.info("In module 'console'");
        log.info("Guess The Number Game");

        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        NumberGenerator numberGenerator = context.getBean(NumberGenerator.class);
        int number = numberGenerator.next();
        log.info("number = {}", number);

        MessageGenerator messageGenerator = context.getBean(MessageGenerator.class);
        log.info("getMainMessage(): {}", messageGenerator.getMainMessage());
        log.info("getResultMessage(): {}", messageGenerator.getResultMessage());

        context.close();
    }
}
