package learn.ee.pj4w.pj4w12javaconfig.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(
        basePackages = "learn.ee.pj4w.pj4w12javaconfig.site",
        excludeFilters =  @ComponentScan.Filter(Controller.class)
)
public class RootContextConfiguration {
}
