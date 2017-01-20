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
 * Created by Juan on 1/20/17.
 */
public class TodoServiceTest {

    private static final Logger LOG = LoggerFactory.getLogger(TodoServiceTest.class);

    //System under Test
    @InjectMocks
    private TodoService todoService;


    //dependency
    @Mock
    private TodoRepository todoRepository;


    //before each test method
    @Before
    public void setUp(){
        LOG.debug("setup...");
        MockitoAnnotations.initMocks(this);
    }

    //after each test method
    @After
    public void tearDown(){
        LOG.debug("tearDown...");

        //then
        //verify
        Mockito.verifyNoMoreInteractions(this.todoRepository);

    }


    @Test
    public void getAllTest(){
        LOG.debug("getAllTest");

        //given
        List<Todo> todoList = new ArrayList<Todo>();
        todoList.add(new Todo("One", TodoPriority.HIGH));
        todoList.add(new Todo("Two", TodoPriority.MEDIUM));
        BDDMockito.given(this.todoRepository.getAll()).willReturn(todoList);

        //when
        List<Todo> testResult = this.todoService.getAll();


        //then
        //assert
//        Assert.assertThat(testResult, Matchers.equalTo(todoList));
        Assert.assertThat(testResult == todoList, Matchers.equalTo(true));

        //verify
        BDDMockito.then(this.todoRepository).should(BDDMockito.times(1)).getAll();
//        Mockito.verifyNoMoreInteractions(this.todoRepository);

    }


    @Test
    public void saveTodoTest(){
        LOG.debug("saveTodoTest...");


        //given
        Todo todo = new Todo("one", TodoPriority.HIGH);
        BDDMockito.given(this.todoRepository.store(todo)).willReturn(true);

        //when
        boolean testResult = this.todoService.saveTodo("one", TodoPriority.HIGH);


        //assert
        Assert.assertThat(testResult, Matchers.equalTo(true));

        //verify
        BDDMockito.then(this.todoRepository).should(BDDMockito.times(1)).store(todo);

    }

}