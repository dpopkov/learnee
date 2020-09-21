package learn.spr.jstmlsf5.todolist.service;

import learn.spr.jstmlsf5.todolist.model.TodoData;
import learn.spr.jstmlsf5.todolist.model.TodoItem;

public interface TodoItemService {

    void add(TodoItem item);

    void remove(int id);

    TodoItem get(int id);

    void update(TodoItem item);

    TodoData getData();
}
