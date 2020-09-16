package learn.spr.jstmlsf5.core.config;

import learn.spr.jstmlsf5.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/** Config that is used to initialize the container. */
@Configuration
@Import(GameConfig.class)
@ComponentScan(basePackages = "learn.spr.jstmlsf5")
public class AppConfig {
    @Bean
    public NumberGenerator numberGenerator() {
        return new NumberGeneratorImpl();
    }

    @Bean
    public Game game() {
        return new GameImpl(numberGenerator());
    }

    @Bean
    public MessageGenerator messageGenerator() {
        return new MessageGeneratorImpl();
    }
}
