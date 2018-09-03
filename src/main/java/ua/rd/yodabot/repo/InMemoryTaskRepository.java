package ua.rd.yodabot.repo;

import ua.rd.yodabot.domain.Task;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTaskRepository implements TaskRepository {

    private final Map<Long, Task> tasks = new HashMap<>();

    public InMemoryTaskRepository() {
        init();
    }

    @PostConstruct
    private void init() {
        tasks.put(1L, new Task(1L, "Открыть окно", "Backlog"));
        tasks.put(2L, new Task(2L, "Зарядить телефон", "Backlog"));
        tasks.put(3L, new Task(3L, "Лечь спать", "Backlog"));
        tasks.put(4L, new Task(3L, "Сделать ДЗ", "InProgress"));
    }

    @Nullable
    @Override
    public List<Task> getAll() {
        return new ArrayList(tasks.values());
    }

    @Override
    public boolean save(@Nonnull Task task) {
        //put - replace existing task or add new task
        tasks.put(task.getId(), task);
        return true;
    }

    @Override
    public boolean removeById(@Nonnull Long id) {
        return tasks.remove(id) != null ? true : false;
    }

    @Nullable
    @Override
    public Task getById(@Nonnull Long id) {
        return tasks.get(id);
    }
}
