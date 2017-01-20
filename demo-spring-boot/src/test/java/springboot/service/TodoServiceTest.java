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
        // verify
        Mockito.verifyNoMoreInteractions(this.todoRepository);
    }

    @Test
    public void getAllTest(){
        LOG.debug("getAllTest.....");

        //given
        List<Todo> todoList = new ArrayList<Todo>();
        todoList.add(new Todo("one", TodoPriority.HIGH));
        todoList.add(new Todo("two", TodoPriority.MEDIUM));
        todoList.add(new Todo("three", TodoPriority.LOW));
        BDDMockito.given(this.todoRepository.getAll()).willReturn(todoList);

        //when
        List<Todo> testResult = this.todoService.getAll();

        //then
        //assert buat ngecek value
        //cek objek yang sama. yang dibandingin hash memorynya
        Assert.assertThat(testResult == todoList, Matchers.equalTo(true));
        //cek objek yang sama. yang dibandingin valuenya.
        Assert.assertThat(testResult, Matchers.equalTo(todoList));

        //verify
        BDDMockito.then(this.todoRepository).should(BDDMockito.times(1)).getAll();
    }

    @Test
    public void saveTodoTest(){
        LOG.debug("saveTodoTest.....");
//        this.todoService.saveTodo(null, null);

        //given
        boolean check = true;
        Todo todo1 = new Todo("todo1", TodoPriority.HIGH);
//        boolean testResult = this.todoRepository.store(todo1);
        BDDMockito.given(this.todoRepository.store(todo1)).willReturn(check);


        //when
        boolean testResult = this.todoRepository.store(todo1);

        //then
//        Assert.assertThat(testResult == true, Matchers.equalTo(true));
        Assert.assertThat(testResult, Matchers.equalTo(check));

        //verify
        BDDMockito.then(this.todoRepository).should(BDDMockito.times(1)).store(todo1);
    }
}
