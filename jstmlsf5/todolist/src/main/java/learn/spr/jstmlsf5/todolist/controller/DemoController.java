package learn.spr.jstmlsf5.todolist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DemoController {
    // http://localhost:8080/todolist/hello
    @ResponseBody
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
