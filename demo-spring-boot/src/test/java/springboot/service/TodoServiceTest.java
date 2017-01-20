package springboot.service;

import org.hamcrest.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.internal.BooleanSupplier;
import org.mockito.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.model.Todo;
import springboot.model.constants.TodoPriority;
import springboot.repository.TodoRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by indra.e.prasetya on 1/18/2017.
 */

//ini namanya system under test

public class TodoServiceTest {

    private static final Logger LOG = LoggerFactory.getLogger(TodoServiceTest.class);
    //ini dependencinya

    @InjectMocks
    private TodoService todoService;

    @Mock
    private TodoRepository todoRepository;



    @Before
    public  void setUp(){
        LOG.debug("setUp...");
        MockitoAnnotations.initMocks(this) ;
    }
    //dipanggil selalu sebelum tesnya mulai



    @After
    public void tearDown() {
        LOG.debug("tearDown...");

        Mockito.verifyNoMoreInteractions(this.todoRepository);
    }
    //dipanggil  kalau tesnya udh selese


    @Test
    public void getAllTest() {
        LOG.debug("getAllTest...") ;
        //given
        List<Todo> todoList = new ArrayList();
        todoList.add(new Todo ("name" , TodoPriority.HIGH));
        todoList.add(new Todo ( "name" , TodoPriority.MEDIUM));
        BDDMockito.given(this.todoRepository.getAll()).willReturn(todoList);
        //when
        List<Todo > testResult = this.todoService.getAll();

        //this.todoService.getAll();
        //then
        Assert.assertThat(testResult, org.hamcrest.Matchers.equalTo(todoList ));

        //verify
//        BDDMockito.then(this.todoRepository)
        BDDMockito.then(this.todoRepository).should(BDDMockito.times(1)).getAll();    }


    @Test
    public void saveTodoTest() {
        //yeah
        //given
        boolean check = true;
        Todo todo1 = new Todo("todo", TodoPriority.HIGH );

        BDDMockito.given(this.todoRepository.store(todo1)).willReturn(check);
        //when
        Boolean testResult = this.todoRepository.store(todo1);


        //then
        Assert.assertThat(testResult, org.hamcrest.Matchers.equalTo(check ));

        //verify
//        BDDMockito.then(this.todoRepository)
        BDDMockito.then(this.todoRepository).should(BDDMockito.times(1)).store(todo1);

        LOG.debug("saveTodoTest...") ;
    }


    }



