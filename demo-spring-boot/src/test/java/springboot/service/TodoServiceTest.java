package springboot.service;

import org.hamcrest.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hamcrest.Matchers;
import springboot.model.Todo;
import springboot.model.constants.TodoPriority;
import springboot.repository.TodoRepository;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by ARDI on 1/20/2017.
 */
public class TodoServiceTest {

    private static final Logger LOG = LoggerFactory.getLogger(TodoServiceTest.class);

    /**
     * TODO system under test
     */
    @InjectMocks
    private TodoService todoService;

    /**
     * TODO dependency
     */
    @Mock
    private TodoRepository todoRepository;

    /**
     * TODO before each method
     */
    @Before
    public void setUp() {
        LOG.debug("setUp...");
        MockitoAnnotations.initMocks(this);
    }

    /**
     * TODO after each method
     */
    @After
    public void tearDown() {
        LOG.debug("tearDown...");
        Mockito.verifyNoMoreInteractions(this.todoRepository);
    }

    @Test
    public void getAllTest(){
        LOG.debug("getAllTest...");
        //TODO given
        List<Todo> todoList = new ArrayList<Todo>();
        todoList.add(new Todo("one" , TodoPriority.HIGH));
        todoList.add(new Todo("two" , TodoPriority.MEDIUM));
        BDDMockito.given(this.todoService.getAll()).willReturn(todoList);

        //TODO when
        List<Todo> testResult = this.todoService.getAll();

        //TODO then

        //TODO assert
        //Assert.assertThat(testResult == todoList , Matchers.equalTo(true));
        Assert.assertThat(testResult , Matchers.equalTo(todoList));

        //TODO verify
        BDDMockito.then(this.todoRepository).should(BDDMockito.times(1)).getAll();
    }
	
	@Test
	public void saveToDoTest(){
        LOG.debug("saveToDoTest...");

        Todo todo = new Todo("one" , TodoPriority.HIGH);
        BDDMockito.given(this.todoService.saveTodo("one" , TodoPriority.HIGH)).willReturn(true);

        Assert.assertThat(this.todoService.saveTodo("one",TodoPriority.HIGH) , Matchers.equalTo(true));

        BDDMockito.then(this.todoRepository).should(BDDMockito.times(1)).store(todo);
	}
}
