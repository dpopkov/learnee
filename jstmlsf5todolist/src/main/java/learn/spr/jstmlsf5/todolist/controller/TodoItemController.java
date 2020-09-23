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
import org.springframework.web.bind.annotation.RequestParam;

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
    public String addEditItem(@RequestParam(required = false, defaultValue = "-1") int id,
                              Model model) {
        TodoItem todoItem = service.get(id);
        if (todoItem == null) {
            log.info("Creating new TodoItem");
            todoItem = new TodoItem("", "", LocalDate.now());
        } else {
            log.info("Starting to edit TodoItem with id = {}.", todoItem.getId());
        }
        model.addAttribute(AttributeNames.TODO_ITEM, todoItem);
        return ViewNames.ADD_ITEM;
    }

    @SuppressWarnings("SpringMVCViewInspection")
    @PostMapping(Mappings.ADD_ITEM)
    public String processItem(@ModelAttribute(AttributeNames.TODO_ITEM) TodoItem todoItem) {
        if (todoItem.getId() == 0) {
            log.info("adding todoItem from form = {}", todoItem);
            service.add(todoItem);
            log.info("todoItem added = {}", todoItem);
        } else {
            service.update(todoItem);
            log.info("todoItem updated = {}", todoItem);
        }
        return "redirect:/" + Mappings.ITEMS;
    }

    @SuppressWarnings("SpringMVCViewInspection")
    @GetMapping(Mappings.DELETE_ITEM)
    public String deleteItem(@RequestParam int id) {
        log.info("starting to delete item with id = {}.", id);
        service.remove(id);
        log.info("deleted item with id = {}.", id);
        return "redirect:/" + Mappings.ITEMS;
    }

    @SuppressWarnings("SameReturnValue")
    @GetMapping(Mappings.VIEW_ITEM)
    public String viewItem(@RequestParam int id, Model model) {
        log.info("viewing an item with id = {}.", id);
        TodoItem todoItem = service.get(id);
        model.addAttribute(AttributeNames.TODO_ITEM, todoItem);
        return ViewNames.VIEW_ITEM;
    }
}
