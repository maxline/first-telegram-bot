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
    private long lastId;

    public InMemoryTaskRepository() {
        init();
    }

    @PostConstruct
    private void init() {
        save(new Task(null, "Открыть окно", "Backlog")); // TODO move to property file
        save(new Task(null, "Зарядить телефон", "Backlog"));
        save(new Task(null, "Лечь спать", "Backlog"));
        save(new Task(null, "Сделать ДЗ", "InProgress"));
    }

    @Nullable
    @Override
    public List<Task> getAll() {
        return new ArrayList(tasks.values());
    }

    @Override
    public boolean save(@Nonnull Task task) {
        //put - replace existing task or add new task
        task.setId(validateTaskId(task) ? task.getId() : getLastId() + 1);

        tasks.put(task.getId(), task);
        setLastId(getLastId() > task.getId() ? getLastId() : task.getId());
        return true;
    }

    private boolean validateTaskId(@Nonnull Task task) {
        return task.getId()!= null && task.getId() > 0;
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

    public long getLastId() {
        return lastId;
    }

    public void setLastId(long lastId) {
        this.lastId = lastId;
    }
}
