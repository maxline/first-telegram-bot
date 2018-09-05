package ua.rd.yodabot.service;


import ua.rd.yodabot.domain.Task;

import javax.annotation.Nonnull;

public interface TaskService extends AbstractDomainObjectService<Task> {

    Task add(@Nonnull String description);
}
