package ua.rd.yodabot.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.rd.yodabot.domain.Task;
import ua.rd.yodabot.repo.TaskRepository;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SimpleTaskServiceTest {

    public static final String TEST_TASK_DESCRIPTION = "test task description";

    private SimpleTaskService testInstance;

    @Mock
    TaskRepository taskRepositoryMock;

    @Before
    public void setup(){
        testInstance = new SimpleTaskService(taskRepositoryMock);
    }

    @Test
    public void add_ShouldCreateNewTaskWithIdAndReturnIt() throws Exception {

        Task createdTask = new Task(null, TEST_TASK_DESCRIPTION, "");
        Task expected = new Task(1L, TEST_TASK_DESCRIPTION, "");
        when(taskRepositoryMock.save(createdTask)).thenReturn(expected);
        Task actual = testInstance.add(TEST_TASK_DESCRIPTION);

        assertThat(actual, is(expected));
    }

    @Test
    public void add_ShouldSaveCreatedTaskToRepo() throws Exception {

        Task createdTask = new Task(null, TEST_TASK_DESCRIPTION, "");
        testInstance.add(TEST_TASK_DESCRIPTION);
        verify(taskRepositoryMock).save(createdTask);
    }

}