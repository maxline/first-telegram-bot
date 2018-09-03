package ua.rd.yodabot.repo;

import ua.rd.yodabot.domain.Task;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public interface TaskRepository {
    @Nullable
    Task getById(@Nonnull Long id);
    @Nullable
    List<Task> getAll();

    boolean save(@Nonnull Task task);
    boolean removeById(@Nonnull Long id);
}
