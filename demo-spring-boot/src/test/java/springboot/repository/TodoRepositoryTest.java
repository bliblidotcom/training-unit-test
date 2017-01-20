package springboot.repository;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import springboot.service.TodoService;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by Elisabet Diana K S on 20/01/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Configuration.class})
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class, TransactionalTestExecutionListener.class})
@Transactional(readOnly = false)
public class TodoRepositoryTest {
    private static final Logger LOG = LoggerFactory.getLogger(TodoService.class);
    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    EntityManager entityManager;

    @Before
    public void setUp() throws Exception{

    }

    @After
    public void tearDown() throws Exception{

    }

    @Test
    public void getAll() throws Exception{
        Todo todoOne = this.todoRepository.save(new Todo("ayam", TodoPriority.HIGH));
        List<Todo> todoList = this.todoRepository.getAll();
        Assert.assertThat(todoList.isEmpty(), Matchers.equalTo(false));
        Assert.assertThat(todoList.get(0), Matchers.equalTo(todoOne));
    }

    @Test
    public void store() throws Exception{
        Todo todo = new Todo("halo",TodoPriority.MEDIUM);
        boolean BOOLEAN = todoRepository.store(todo);
        Todo newTodo = (Todo) entityManager.createNativeQuery("select id,name,priority FROM Todo",Todo.class).getResultList().get(0);
        Assert.assertEquals(todo, newTodo);
        Assert.assertEquals(BOOLEAN,true);

    }
}
