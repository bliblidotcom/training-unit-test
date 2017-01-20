package springboot.service;

import org.apache.log4j.spi.LoggerFactory;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.slf4j.Logger;
import springboot.model.Todo;
import springboot.model.constants.TodoPriority;
import springboot.repository.TodoRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by indra.e.prasetya on 1/18/2017.
 */
public class TodoServiceTest {

    private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(TodoServiceTest.class);
    // System yang di tes
    // (1) Deklarasi class yg ingin di service
    // InjectMocks mirip @service tapi yg diinject mock todoRepo
    @InjectMocks
    private TodoService todoService;

    // Dependency nya
    @Mock
    private TodoRepository todoRepository;

    // Method untuk men-set-up sistem dan data
    // Dipanggil sebelum test method
    @Before
    public void seUp(){
        LOG.debug("setUp...");
        MockitoAnnotations.initMocks(this);
    }

    // Dipanggil sesudah test method
    @After
    public void tearDown(){
        LOG.info("tearDown()..");
        Mockito.verifyNoMoreInteractions(this.todoRepository);
    }

    // Method yang akan di test
    @Test
    public void getAllTest(){
        LOG.info("getAllTest..");

        // Behaviour Driven Development
        // Given
        List<Todo> todoList = new ArrayList<Todo>();
        todoList.add(new Todo("one", TodoPriority.HIGH));
        todoList.add(new Todo("two", TodoPriority.MEDIUM));
        // Jika fungsi todoRepo.getAll dipanggil maka akan mengembalikan variable todolist
        BDDMockito.given(this.todoRepository.getAll()).willReturn(todoList);

        // When
        List<Todo> testResult = this.todoService.getAll();

        // Then
        // Assert
        Assert.assertThat(testResult==todoList,Matchers.equalTo(Boolean.TRUE));
        Assert.assertThat(testResult, Matchers.equalTo(todoList));

        // Verify
        BDDMockito.then(this.todoRepository).should(BDDMockito.times(1)).getAll();

        //this.todoService.getAll();
    }

    @Test
    public void saveTodoTest(){
        LOG.info("saveTodoTest()");

        // Membuat model todo yang akan coba disimpan
        Todo todo = new Todo("Satu", TodoPriority.HIGH);

        // Untuk kondisi dimana fungsi store menyimpan todo, kembalikan nilai True (pengganti Repo)
        BDDMockito.given(this.todoRepository.store(todo)).willReturn(Boolean.TRUE);

        // Menyimpan keluaran dari fungsi todo.saveTodo ke variable boole
        boolean boole = todoService.saveTodo("Satu",TodoPriority.HIGH );

        //Cek apakah hasilnya true atau tidak
        Assert.assertEquals(true, boole);

        BDDMockito.then(this.todoRepository).should(BDDMockito.times(1)).store(todo);


        //this.todoService.saveTodo(null, null);
    }

}
