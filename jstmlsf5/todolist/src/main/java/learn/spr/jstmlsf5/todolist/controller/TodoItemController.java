package learn.spr.jstmlsf5.todolist.controller;

import learn.spr.jstmlsf5.todolist.model.TodoData;
import learn.spr.jstmlsf5.todolist.util.Mappings;
import learn.spr.jstmlsf5.todolist.util.ViewNames;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class TodoItemController {
    @ModelAttribute
    public TodoData todoData() {
        return new TodoData();
    }

    /* Handler methods */

    // http://localhost:8080/todolist/items
    @GetMapping(Mappings.ITEMS)
    public String items() {
        return ViewNames.ITEMS_LIST;
    }
}
