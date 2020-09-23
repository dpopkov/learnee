package learn.spr.jstmlsf5.todolist.service;

import learn.spr.jstmlsf5.todolist.model.TodoData;
import learn.spr.jstmlsf5.todolist.model.TodoItem;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
public class TodoItemServiceImpl implements TodoItemService {
    @Getter
    private final TodoData data = new TodoData();

    @Override
    public void add(TodoItem item) {
        data.add(item);
    }

    @Override
    public void remove(int id) {
        data.remove(id);
    }

    @Override
    public TodoItem get(int id) {
        return data.getById(id);
    }

    @Override
    public void update(TodoItem item) {
        data.update(item);
    }
}
