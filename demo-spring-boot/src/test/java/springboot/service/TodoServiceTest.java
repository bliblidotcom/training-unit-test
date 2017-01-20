package springboot.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import springboot.model.Todo;
import springboot.model.constants.TodoPriority;
import springboot.repository.TodoRepository;

import java.util.ArrayList;
import java.util.List;

public class TodoServiceTest {
    // The System Under Test (SUT)
    @InjectMocks
    private TodoService todoService;

    // Dependency
    @Mock
    private TodoRepository todoRepository;

    private static final Logger LOG = LoggerFactory.getLogger(TodoServiceTest.class);

    @Before
    public void setUp() {
        LOG.debug("setup");
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() {
        LOG.debug("teardown");
        // Verify
      BDDMockito.then(this.todoRepository).shouldHaveNoMoreInteractions();
    }

    @Test
    public void getAllTest() {
        LOG.debug("getAllTest");
        // Given
        List<Todo> todoList = new ArrayList<Todo>();
        todoList.add(new Todo("Test a todo content.", TodoPriority.HIGH));
        todoList.add(new Todo("Another task we must do later.", TodoPriority.LOW));
        BDDMockito.given(this.todoRepository.getAll()).willReturn(todoList);

        // When
        List<Todo> result = todoService.getAll();

        // Then
        Assert.assertEquals(todoList, result);

        // Verify
        Mockito.verify(this.todoRepository, Mockito.times(1))
                .getAll();
    }

    @Test
    public void saveTodoTest() {
        LOG.debug("save to Do");
        // Given
        Todo newTodo = new Todo("Test insert new todo item.", TodoPriority.LOW);
        BDDMockito.given(this.todoRepository.save(newTodo)).willReturn(newTodo);

        // When
        Todo success = todoService.saveTodo(newTodo.getName(), newTodo.getPriority());

        // Then
        Assert.assertThat(newTodo == success, org.hamcrest.Matchers.equalTo(true));

        // Verify
        Mockito.verify(this.todoRepository, Mockito.times(1))
                .save(newTodo);
    }

}
