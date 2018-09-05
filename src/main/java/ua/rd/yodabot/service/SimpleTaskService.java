package ua.rd.yodabot.service;

import ua.rd.yodabot.domain.Task;
import ua.rd.yodabot.repo.TaskRepository;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;

public class SimpleTaskService implements TaskService {

    private TaskRepository taskRepository;

    public SimpleTaskService() {
    }

    public SimpleTaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Nullable
    @Override
    public Task getById(@Nonnull Long id) {
        return taskRepository.getById(id);
    }

    @Override
    public Task save(@Nonnull Task task) {
        return taskRepository.save(task) ? task : null;
    }

    @Override
    public void remove(@Nonnull Task task) {
        taskRepository.removeById(task.getId());
    }

    @Nonnull
    @Override
    public Collection<Task> getAll() {
        return taskRepository.getAll();
    }

    public TaskRepository getTaskRepository() {
        return taskRepository;
    }

    public void setTaskRepository(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task add(@Nonnull String description) {
        Task task = new Task(null, description, ""); //TODO add listName
        taskRepository.save(task);
        return task;
    }
}
