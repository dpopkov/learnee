package learn.spr.jstmlsf5.core.config;

import learn.spr.jstmlsf5.core.GuessCount;
import learn.spr.jstmlsf5.core.MaxNumber;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GameConfig {

    private final int maxNumber = 12;

    private final int guessCount = 8;

    /* Bean methods */
    @Bean
    @MaxNumber
    public int maxNumber() {
        return maxNumber;
    }

    @Bean
    @GuessCount
    public int guessCount() {
        return guessCount;
    }
}
