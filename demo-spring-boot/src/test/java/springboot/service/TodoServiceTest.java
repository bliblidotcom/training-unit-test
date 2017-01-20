package springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.model.Todo;
import springboot.model.constants.TodoPriority;
import springboot.repository.TodoRepository;

import org.hamcrest.Matchers;
import java.util.List;
import java.util.ArrayList;
import junit.framework.TestResult;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by indra.e.prasetya on 1/18/2017.
 */
//bisa dibaca di: bddmockito javadoc io

//How to create unit test
//5. Tambahkan anotasi @InjectMocks, @Mocks, @Before, dan @After pada lokasi yg disesuaikan
//6. Buat method testing
//7. Buat log
//8. jalankan mvn clean test (sama seperti cara menjalanan clean install)
    //jalannya test dilakukan secara acak
//9. memanggil method dari kelas yang akan ditest
//10. tambahkan log pada kelas todoservice dan todorepository
//11. jalankan lagi (todoRepository tidak dipanggil meski di kelas todoService dipanggil)
//12. Selanjutnya buat implementasi palsu untuk repository
//13. pengecekan yang dilakukan mencakup pada:
    //hasil
    //pemanggilan objek
    //proses
public class TodoServiceTest {
    
    //1. declare class yg ingin dites (System under test)
    //@InjectMocks: yang dimasukkan adalah todoservice objek palsu
    @InjectMocks
    private TodoService todoService;
    //2. dependency
    //@Mock: yang dimasukkan adalah todorepository objek palsu
    @Mock
    private TodoRepository todoRepository;
    
    //Logger
    private static final Logger LOG=LoggerFactory.getLogger(TodoServiceTest.class);
    
    //3. set yg hendak ditest (before test)
    @Before
    public void setUp(){
        LOG.debug("setUp...");
        //
        MockitoAnnotations.initMocks(this);
    }
    //4. aksi setelah test selesai (after test)
    @After
    public void tearDown(){
        LOG.debug("tearDown...");
        Mockito.verifyNoMoreInteractions(this.todoRepository);
    }

    //penamaan method yang akan ditest
    @Test
    public void getAllTest() {
        LOG.debug("getAllTest...");
        //given
        List<Todo> todoList=new ArrayList<Todo>();
        todoList.add(new Todo("bebek",TodoPriority.MEDIUM));
        BDDMockito.given(this.todoRepository.getAll()).willReturn(todoList);
        
        //when
        List<Todo> testResult=this.todoService.getAll();
        
        //then
        //assert
        Assert.assertThat(testResult, Matchers.equalTo(todoList));
        //verify
        BDDMockito.then(this.todoRepository).should(BDDMockito.times(1)).getAll();
    }
    @Test
    public void saveTodoTest() {
        LOG.debug("saveTodoTest...");
        Todo todo=new Todo("ayam",TodoPriority.HIGH);
        
        BDDMockito.given(this.todoRepository.store(todo)).willReturn(true);
        boolean hasil=this.todoService.saveTodo("ayam", TodoPriority.HIGH);
        
        Assert.assertThat(hasil, Matchers.equalTo(true));
        
        BDDMockito.then(this.todoRepository).should(BDDMockito.times(1)).store(todo);
    }
}
