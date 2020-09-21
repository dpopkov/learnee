package learn.spr.jstmlsf5.todolist.controller;

import learn.spr.jstmlsf5.todolist.model.TodoData;
import learn.spr.jstmlsf5.todolist.model.TodoItem;
import learn.spr.jstmlsf5.todolist.service.TodoItemService;
import learn.spr.jstmlsf5.todolist.util.AttributeNames;
import learn.spr.jstmlsf5.todolist.util.Mappings;
import learn.spr.jstmlsf5.todolist.util.ViewNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;

@Slf4j
@Controller
public class TodoItemController {
    private final TodoItemService service;

    @Autowired
    public TodoItemController(TodoItemService service) {
        this.service = service;
    }

    @ModelAttribute
    public TodoData todoData() {
        return service.getData();
    }

    /* Handler methods */

    // http://localhost:8080/todolist/items
    @SuppressWarnings("SameReturnValue")
    @GetMapping(Mappings.ITEMS)
    public String items() {
        return ViewNames.ITEMS_LIST;
    }

    @SuppressWarnings("SameReturnValue")
    @GetMapping(Mappings.ADD_ITEM)
    public String addItem(Model model) {
        TodoItem todoItem = new TodoItem("", "", LocalDate.now());
        model.addAttribute(AttributeNames.TODO_ITEM, todoItem);
        return ViewNames.ADD_ITEM;
    }

    @SuppressWarnings("SpringMVCViewInspection")
    @PostMapping(Mappings.ADD_ITEM)
    public String processItem(@ModelAttribute(AttributeNames.TODO_ITEM) TodoItem todoItem) {
        log.info("todoItem from form = {}", todoItem);
        service.add(todoItem);
        return "redirect:/" + Mappings.ITEMS;
    }
}
