package springboot.service;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import springboot.repository.TodoRepository;

public class TodoServiceTest {

    private static final Logger LOG = LoggerFactory.getLogger(TodoServiceTest.class);

    /*
    * System under test
    */
    @InjectMocks //dari library mockito
    private TodoService todoService;

    /*dependency*/
    @Mock //dari library mockito
    private TodoRepository todoRepository;

    /*before each test method*/
    @Before //dari library junit
    public void setUp(){
        LOG.debug("setUp.....");
        MockitoAnnotations.initMocks(this);
    }

    /*after each test method*/
    @After //dari library junit
    public void tearDown(){
        LOG.debug("tearDown.....");
    }

    @Test
    public void getAllTest(){
        LOG.debug("getAllTest.....");
        this.todoService.getAll();
    }

    @Test
    public void saveTodoTest(){
        LOG.debug("saveTodoTest.....");
        this.todoService.saveTodo(null, null);
    }
}
