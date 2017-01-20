package springboot.service;

import org.hamcrest.Matchers;
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

/**
 * Created by arielchristianto on 1/20/17.
 */
public class ToDoServiceTest {
    private static final Logger LOG = LoggerFactory.getLogger(ToDoServiceTest.class);

    @InjectMocks
    private TodoService todoService;

    @Mock
    private TodoRepository todoRepository;

    @Before
    public void setUp() {
        LOG.debug("before(setUp)...");
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown(){
        Mockito.verifyNoMoreInteractions(todoRepository);
        LOG.debug("after(tearDown)...");
    }

    @Test
    public void getAllTest() {
        LOG.debug("getAllTest");

        //given
        List<Todo> todoList = new ArrayList<Todo>();
        todoList.add(new Todo("todo1", TodoPriority.MEDIUM));
        todoList.add(new Todo("todo2", TodoPriority.HIGH));
        BDDMockito.given(this.todoRepository.getAll()).willReturn(todoList);

        //when
        List<Todo> testResult = this.todoService.getAll();

        //then
        //assert
        Assert.assertThat(testResult, Matchers.equalTo(todoList)); //check value
        Assert.assertThat(testResult == todoList, Matchers.equalTo(true)); //check object

        //verify
        BDDMockito.then(this.todoRepository).should(BDDMockito.times(1)).getAll();
    }

    @Test
    public void saveToDoTest() {
        LOG.debug("Test: Test.saveToDoTest running...");

        //given
        Todo mockTodo = new Todo("todo3", TodoPriority.LOW);
        BDDMockito.given(this.todoRepository.store(mockTodo)).willReturn(true);

        //when
        boolean result = this.todoService.saveTodo("todo3", TodoPriority.LOW);

        //then
        //assert
        Assert.assertThat(result, Matchers.equalTo(true));
        //verify
        BDDMockito.then(this.todoRepository).should(BDDMockito.times(1)).store(mockTodo);
    }
}
