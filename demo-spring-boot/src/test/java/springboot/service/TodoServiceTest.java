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
 * Created by indra.e.prasetya on 1/18/2017.
 */
public class TodoServiceTest {

    private static final Logger LOG = LoggerFactory.getLogger(TodoServiceTest.class);   //logger slf4j

    //system yang di test
    @InjectMocks                                //inject mock todoRepository yang ada dibawah
    private TodoService todoService;

    //dependency
    @Mock                                       //mock yang diinject
    private TodoRepository todoRepository;

    //before test method
    @Before
    public void setUp() {
        LOG.debug("setUp...");
        MockitoAnnotations.initMocks(this);
    }

    //after test method
    @After
    public void tearDown() {
        LOG.debug("tearDown...");
    }

    //method test
    @Test
    public void getAllTest() {
        LOG.debug("getAllTest...");

        //given
        List<Todo> todoList = new ArrayList<Todo>();
        todoList.add(new Todo("one", TodoPriority.HIGH));
        todoList.add(new Todo("two", TodoPriority.MEDIUM));
        BDDMockito.given(this.todoRepository.getAll()).willReturn(todoList);

        //when
        List<Todo> testResult = this.todoService.getAll();

        //then  (ada 2, assert sama verifiy)
        //assert (buat ngecek value)
        Assert.assertThat(testResult == todoList, Matchers.equalTo(true));  //ngecek sampe objeknya
        //Assert.assertThat(testResult, Matchers.equalTo(todoList));          //ngecek valuenya doang

        //verify
        BDDMockito.then(this.todoRepository).should(BDDMockito.times(1)).getAll();  //untuk memastikan supaya data tidak bs ditembak langsung
        Mockito.verifyNoMoreInteractions(this.todoRepository);              //untuk memastikan test ini tidak memanggil kelas selain todoservice yang ditest
    }

    //method test
    @Test
    public void saveTodoTest() {
        LOG.debug("saveTodoTest...");

        //given
        Boolean cek = true;
        BDDMockito.given(this.todoRepository.store(new Todo("one", TodoPriority.HIGH))).willReturn(cek);

        //when
        Boolean cekResult = todoService.saveTodo("one", TodoPriority.HIGH);

        //then
        //assert
        Assert.assertThat(cek, Matchers.equalTo(cekResult));

        //verify
        BDDMockito.then(this.todoRepository).should(BDDMockito.times(1)).store(new Todo("one", TodoPriority.HIGH));
        Mockito.verifyNoMoreInteractions(this.todoRepository);
    }
}
