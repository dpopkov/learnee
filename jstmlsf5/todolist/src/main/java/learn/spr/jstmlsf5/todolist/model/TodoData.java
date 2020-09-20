package learn.spr.jstmlsf5.todolist.model;

import lombok.NonNull;

import java.time.LocalDate;
import java.util.*;

/** Simulates in-memory database. */
public class TodoData {
    private static int nextId = 1;

    private final List<TodoItem> items = new ArrayList<>();

    public TodoData() {
        add(new TodoItem("first", "first details", LocalDate.now()));
        add(new TodoItem("second", "second details", LocalDate.now()));
        add(new TodoItem("third", "third details", LocalDate.now()));
    }

    public List<TodoItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void add(@NonNull TodoItem item) {
        int id;
        synchronized (TodoData.class) {
            id = nextId;
            nextId++;
        }
        item.setId(id);
        items.add(item);
    }

    public void remove(int id) {
        Iterator<TodoItem> iterator = items.iterator();
        while (iterator.hasNext()) {
            TodoItem item = iterator.next();
            if (item.getId() == id) {
                iterator.remove();
                break;
            }
        }
    }

    public TodoItem getById(int id) {
        for (TodoItem item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public void update(@NonNull TodoItem toUpdate) {
        ListIterator<TodoItem> iterator = items.listIterator();
        while (iterator.hasNext()) {
            TodoItem it = iterator.next();
            if (it.getId() == toUpdate.getId()) {
                iterator.set(toUpdate);
                break;
            }
        }
    }
}
