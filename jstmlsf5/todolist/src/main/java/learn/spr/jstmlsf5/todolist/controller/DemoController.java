package learn.spr.jstmlsf5.todolist.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class DemoController {
    // http://localhost:8080/todolist/hello
    @ResponseBody
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    // http://localhost:8080/todolist/welcome
    // prefix + name + suffix = /WEB-INF/view/welcome.jsp
    @GetMapping("welcome")
    public String welcome(Model model) {
        model.addAttribute("user", "Alice");
        log.info("model={}", model);
        return "welcome";
    }

    @ModelAttribute("welcomeMessage")
    public String welcomeMessage() {
        log.info("welcomeMessage() called");
        return "Welcome to this Demo application";
    }
}
