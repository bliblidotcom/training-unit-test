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
import springboot.repository.TodoRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public class TodoServiceTest {

    // The System Under Test (SUT)
    @InjectMocks
    private TodoService todoService;

    // Dependency
    @Mock
    private TodoRepositoryImpl todoRepositoryImpl;

    private static final Logger LOG = LoggerFactory.getLogger(TodoServiceTest.class);

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() {
        // Verify
      BDDMockito.then(this.todoRepositoryImpl).shouldHaveNoMoreInteractions();
    }

    @Test
    public void getAllTest() {
        // Given
        List<Todo> todoList = new ArrayList<Todo>();
        todoList.add(new Todo("Test a todo content.", TodoPriority.HIGH));
        todoList.add(new Todo("Another task we must do later.", TodoPriority.LOW));
        BDDMockito.given(this.todoRepositoryImpl.getAll()).willReturn(todoList);

        // When
        List<Todo> result = todoService.getAll();

        // Then
        Assert.assertEquals(todoList, result);

        // Verify
        Mockito.verify(this.todoRepositoryImpl, Mockito.times(1))
                .getAll();
    }

    @Test
    public void saveTodoTest() {
        // Given
        Todo newTodo = new Todo("Test insert new todo item.", TodoPriority.LOW);
        BDDMockito.given(this.todoRepositoryImpl.store(newTodo)).willReturn(true);

        // When
        boolean success = todoService.saveTodo(newTodo.getName(), newTodo.getPriority());

        // Then
        Assert.assertTrue(success);

        // Verify
        Mockito.verify(this.todoRepositoryImpl, Mockito.times(1))
                .store(newTodo);
    }

}
