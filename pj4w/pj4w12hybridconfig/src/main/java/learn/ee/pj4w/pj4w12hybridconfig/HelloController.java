package learn.ee.pj4w.pj4w12hybridconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    private GreetingService greetingService;

    @Autowired
    public void setGreetingService(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @SuppressWarnings("SameReturnValue")
    @ResponseBody
    @RequestMapping("/")
    public String helloWorld() {
        return "Hello, World!";
    }

    @ResponseBody
    @RequestMapping(value = "/", params = {"name"})
    public String helloName(@RequestParam("name") String name) {
        return greetingService.getGreeting(name);
    }
}
