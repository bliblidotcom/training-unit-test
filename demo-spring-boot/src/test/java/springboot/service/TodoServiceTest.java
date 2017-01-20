package springboot.service;


import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.BDDAssertions;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.exceptions.verification.WantedButNotInvoked;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import springboot.model.Todo;
import springboot.model.constants.TodoPriority;
import springboot.repository.TodoRepository;

/**
 * Created by indra.e.prasetya on 1/18/2017.
 */

public class TodoServiceTest {
    
    //buat nyatet log nya yg di test
    private static final Logger LOG=LoggerFactory.getLogger(TodoServiceTest.class);
    
    //system under test
    //yg mau di test TodoService aja
    @InjectMocks //buat test, jadi yg dimasukkin ke test bukan todoservice beneran, tapi buat test aja
    private TodoService todoService;
    
    //klo ada dependency baru di Mock, klo ngga ada ngga usah dibuat MOck nya
    //dependency
    @Mock
    private TodoRepository todoRepository;
    
    //sebelum tiap method unit test
    @Before
    public void setUp()
    {
        LOG.debug("before (SetUp)....");
        //membuat class apa aja yg mau di mock
        MockitoAnnotations.initMocks(this);
        
    }
    
    //setelah tiap method unit test
    @After
    public void teartDown()
    {
        LOG.debug("after...");
        Mockito.verifyNoMoreInteractions(this.todoRepository);
       
    }
    
    @Test //method yg di test
    public void getAllTest() 
    {
        LOG.debug("get all test");
        
        //given (kondisinya waktu implementasi boongannya)
        List<Todo> todoList=new ArrayList<Todo>();
        todoList.add(new Todo( "one",TodoPriority.HIGH));
        todoList.add(new Todo( "two",TodoPriority.MEDIUM));        
        BDDMockito.given(this.todoRepository.getAll()).willReturn(todoList);
        
        //when (waktu eksekusinya)        
        List<Todo> testResult=this.todoService.getAll();
        
        //then (verifikasi bener apa ngga)        
        Assert.assertThat(testResult == todoList,Matchers.equalTo(true));
        Assert.assertThat(testResult,Matchers.equalTo(todoList));        
        
        
        //vefify (buat mastiin method nya beneran dipanggil sebanyak 1 kali ngga berkali2)
        BDDMockito.then(this.todoRepository).should(BDDMockito.times(1)).getAll();
        //BDDMockito.then(this.todoRepository.should(BDDMockito))
        // this.todoService.getAll();
    }
    
    @Test
    public void saveTodo() 
    {
        LOG.debug("save Todo ...");
        LOG.debug("get all test");
        
        //given (kondisinya waktu implementasi boongannya)
        Todo todo=new Todo("todoBaru",TodoPriority.LOW);        
        BDDMockito.given(this.todoRepository.store(todo)).willReturn(true);
        
        //when (waktu eksekusinya)        
        Boolean testResult=this.todoService.saveTodo(todo.getName(), todo.getPriority());
        
        //then (verifikasi bener apa ngga)        
        Assert.assertThat(testResult == true,Matchers.equalTo(true));
        Assert.assertThat(testResult,Matchers.equalTo(true));        
        
        //vefify (buat mastiin method nya beneran dipanggil sebanyak 1 kali ngga berkali2)
        BDDMockito.then(this.todoRepository).should(BDDMockito.times(1)).store(todo);
        //BDDMockito.then(this.todoRepository.should(BDDMockito))
        // this.todoService.getAll();
    }
}
