package learn.ee.pj4w.pj4w12javaconfig.site;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;

@Controller
public class HelloController {
    private static final Logger log = LogManager.getLogger();

    private GreetingService greetingService;

    @Inject
    public void setGreetingService(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @SuppressWarnings("SameReturnValue")
    @ResponseBody
    @RequestMapping("/")
    public String helloWorld() {
        log.traceEntry();
        return log.traceExit("Hello, World!");
    }

    @ResponseBody
    @RequestMapping(value = "/", params = {"name"})
    public String helloName(@RequestParam("name") String name) {
        log.traceEntry("name={}", name);
        String greeting = greetingService.getGreeting(name);
        return log.traceExit(greeting);
    }
}
