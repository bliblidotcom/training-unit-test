package springboot.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
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
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() {
        // Verify
      BDDMockito.then(this.todoRepository).shouldHaveNoMoreInteractions();
    }

    @Test
    public void getAllTest() {
        // Given
        List<Todo> todoList = new ArrayList<Todo>();
        todoList.add(new Todo("Test a todo content.", TodoPriority.HIGH));
        todoList.add(new Todo("Another task we must do later.", TodoPriority.LOW));
        BDDMockito.given(this.todoRepository.getAll()).willReturn(todoList);

        // When
        List<Todo> result = todoService.getAll();

        // Then
//        Assert.assertEquals(todoList, result);
        Assert.assertThat(result,Matchers.equalTo(todoList));
        Assert.assertThat(result==todoList,Matchers.equalTo(true));
        // Verify
        Mockito.verify(this.todoRepository, Mockito.times(1))
                .getAll();
    }

    @Test
    public void saveTodoTest() {
        // Given
//        Todo newTodo = new Todo("Test insert new todo item.", TodoPriority.LOW);
        Todo todo = new Todo("test",TodoPriority.LOW);
         BDDMockito.given(todoRepository.store(todo))
                 .willReturn(true);

        // When
        boolean success = todoService.saveTodo("test", TodoPriority.LOW);

        // Then
//        Assert.assertTrue(success);        
        Assert.assertThat(success,Matchers.equalTo(true));
        // Verify
        Mockito.verify(this.todoRepository, Mockito.times(1))
                .store(todo);
    }

}
