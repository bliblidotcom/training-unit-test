package springboot.repository;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arielchristianto on 1/20/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Configuration.class})
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class, TransactionalTestExecutionListener.class})
@Transactional(readOnly = false)
public class TodoRepositoryImplTest {
    @Autowired
    private TodoRepository todoRepository;

    @Before
    public void setUp(){}

    @After
    public void tearDown(){}

    @Test
    public void getAllTest() {
        //given
        Todo mockTodo = new Todo("todo1", TodoPriority.HIGH);
        List<Todo> mockTodoList = new ArrayList<Todo>();
        mockTodoList.add(mockTodo);
        todoRepository.save(mockTodoList);

        ///when
        List<Todo> result = todoRepository.getAll();

        //assert
        Assert.assertEquals(result, mockTodoList);
    }
}
