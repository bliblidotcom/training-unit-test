package springboot.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import springboot.model.Todo;
import springboot.model.TodoNew;
import springboot.model.constants.TodoPriority;
import springboot.repository.TodoRepo;
import springboot.repository.TodoRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dias on 1/20/2017.
 */
public class TodoServTest {

    // The System Under Test (SUT)
    @InjectMocks
    private TodoServ todoService;

    // Dependency
    @Mock
    private TodoRepo todoRepository;

    private static final Logger LOG = LoggerFactory.getLogger(TodoServiceTest.class);

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() {
        // Verify
        BDDMockito.then(this.todoRepository).shouldHaveNoMoreInteractions();
    }

    //@Test
    public void getAllTest() {
        // Given
        List<TodoNew> todoList = new ArrayList<TodoNew>();
        todoList.add(new TodoNew("one", TodoPriority.HIGH));
        todoList.add(new TodoNew("two", TodoPriority.HIGH));
        BDDMockito.given(this.todoRepository.findAll()).willReturn(todoList);

        // When
        List<TodoNew> result = todoService.getAll();

        // Then
        Assert.assertEquals(todoList, result);

        // Verify
        Mockito.verify(this.todoRepository, Mockito.times(1))
                .findAll();
    }

    //@Test
    public void saveTodoTest() {
        // Given
        TodoNew newTodo = new TodoNew("Test insert new todo item.", TodoPriority.LOW);
        BDDMockito.given(this.todoRepository.store(newTodo)).willReturn(true);

        // When
        boolean success = todoService.saveTodo("Test insert new todo item.", TodoPriority.LOW);

        // Then
        Assert.assertTrue(success);

        // Verify
        Mockito.verify(this.todoRepository, Mockito.times(1))
                .save(newTodo);
    }
}
