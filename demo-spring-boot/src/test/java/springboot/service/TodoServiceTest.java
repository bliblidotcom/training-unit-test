package springboot.service;



import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import springboot.TodoService;
import springboot.model.Todo;
import springboot.model.constants.TodoPriority;
import springboot.repository.TodoRepository;

import java.util.ArrayList;
import java.util.List;


public class TodoServiceTest{
    private static final Logger LOG= LoggerFactory.getLogger(TodoServiceTest.class);
    @InjectMocks//kaya add services di dalam todoservice ada dependencies
    private TodoService todoService;
    //before each test method
    @Mock
    private TodoRepository todoRepository;

    @Before
    public void setUp(){
     //   System.out.println("before ....");
        LOG.debug("set Up test");
        MockitoAnnotations.initMocks(this);

    }
    //after each test method
    @After
    public void tearDown(){
        LOG.debug("tearDown");
        //verify
        Mockito.verifyNoMoreInteractions(this.todoRepository);
        //isinya semua deendency kita panggil disini
    }
    @Test
    public void getAllTest()
    {
        LOG.debug("get all test");
        //givern
        //implementasi boongan
        List<Todo> todoList= new ArrayList<Todo>();
        todoList.add(new Todo("one", TodoPriority.HIGH));
        todoList.add(new Todo("two",TodoPriority.MEDIUM));
        BDDMockito.given(this.todoRepository.getAll()).willReturn(todoList);
        //when
        List<Todo> testResult=this.todoService.getAll();
        // this.todoService.getAll();
        // then
        //assert
        Assert.assertThat(testResult, Matchers.equalTo(todoList));
        //verify
        BDDMockito.then(this.todoRepository).should(BDDMockito.times(1)).getAll();
        //   Mockito.verifyNoMoreInteractions(this.todoRepository);

    }
    @Test
    public void saveTodoTest()
    {
        LOG.debug("save todo test");
       //   this.todoService.saveTodo(null,null);
       //  Assert.assertThat(testResult, Matchers.equalTo(todoList));
        //implementasi boongan
        List<Todo> todoList= new ArrayList<Todo>();
        todoList.add(new Todo("one1", TodoPriority.HIGH));
        todoList.add(new Todo("two1",TodoPriority.MEDIUM));
        BDDMockito.given(this.todoRepository.getAll()).willReturn(todoList);
        //when
        List<Todo> testResult=this.todoService.getAll();
        // this.todoService.getAll();
        // then
        //assert
        Assert.assertThat(testResult, Matchers.equalTo(todoList));
        //verify
        BDDMockito.then(this.todoRepository).should(BDDMockito.times(1)).getAll();
        //   Mockito.verifyNoMoreInteractions(this.todoRepository);

    }

}
