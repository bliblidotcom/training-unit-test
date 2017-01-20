package springboot.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import springboot.Configuration;
import springboot.model.Todo;
import springboot.model.constants.TodoPriority;
import springboot.repository.TodoRepository;
import springboot.repository.TodoRepositoryImpl;


import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Ryan Bagus Susilo on 1/20/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Configuration.class})
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class, TransactionalTestExecutionListener.class})
@Transactional(readOnly = false)


public class TodoRepositoryImplTest {

    @Autowired
    private TodoRepository todoRepository;



    private static final Logger LOG = LoggerFactory.getLogger(TodoRepositoryImplTest.class);



    @Before
    public void setUp()throws Exception {
    }

    @After
    public void tearDown() throws  Exception{
    }

    @Test
    public void getAllTest() throws  Exception{

    Todo todoOne =this.todoRepository.save(new Todo( 1L,"ryan",TodoPriority.HIGH));

    List<Todo> todoList=this.todoRepository.getAll();
    LOG.debug("todoList:{}",todoList);
    Assert.assertEquals(todoOne,todoList.get(0));


    }

    @Test
    public void saveTodoTest() {


        Todo t=new Todo();
        t.setName("ryan");
        t.setId(1L);
        t.setPriority(TodoPriority.HIGH);
       // em.createNativeQuery("INSERT INTO testtable ('column1','column2') VALUES ('test1','test2')").executeUpdate();
        Todo result =
                this.todoRepository.store(t);

        List<Todo> todo =todoRepository.findAll();

        Assert.assertEquals(Arrays.asList(t),todo);






//        em.getTransaction().begin();
//        if (!em.contains(t)) {
//            // persist object - add to entity manager
//            em.persist(t);
//            // flush em - save to DB
//            em.flush();
//        }
//        // commit transaction at all
//        em.getTransaction().commit();





    }


}


//  private JdbcTemplate jdbcTemplate;
//        // Given
//        List<Todo> todoList = new ArrayList<Todo>();
//        todoList.add(new Todo("1","Test a todo content.", TodoPriority.HIGH));
//        todoList.add(new Todo("2","Another task we must do later.", TodoPriority.LOW));
//        BDDMockito.given(this.todoRepositoryImpl.getAll()).willReturn(todoList);
//
//        // When
//        List<Todo> result = todoService.getAll();
//
//        // Then
//        Assert.assertEquals(todoList, result);
//
//                .getAll();



//        // Given
//        Todo newTodo = new Todo("3","Test insert new todo item.", TodoPriority.LOW);
//        BDDMockito.given(this.todoRepositoryImpl.store(newTodo)).willReturn(true);
//
//        // When
//        boolean success = todoService.saveTodo(newTodo.getName(), newTodo.getPriority());
//
//        // Then
//        Assert.assertTrue(success);
//
//        // Verify
//        Mockito.verify(this.todoRepositoryImpl, Mockito.times(1))
//                .store(newTodo);