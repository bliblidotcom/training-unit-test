package springboot.service;

import org.hamcrest.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.Matchers;
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
import springboot.repository.TodoRepository;
import springboot.repository.TodoRepositoryImpl;
import sun.rmi.runtime.Log;


import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by The Frost on 20/01/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Configuration.class})
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class, TransactionalTestExecutionListener.class})
@Transactional(readOnly = false)
public class TodoRepositoryImplTest {

    private static final Logger LOG = LoggerFactory.getLogger(TodoRepository.class);

    @Autowired
    EntityManager entityManager;

    @Autowired
    private TodoRepository todoRepository ;


    @Before
    public void setUp() throws Exception{
        LOG.debug("Starting test...");
    }

    @After
    public void tearDown() throws Exception{
        LOG.debug("Stopping test...");
    }

    @Test
    public void store() {
        LOG.debug("testStore...");
        Todo todoOne = new Todo("Dito", TodoPriority.HIGH);
        boolean sukses = todoRepository.store(todoOne);

        Todo inputTodo =
                (Todo) this.entityManager.createNativeQuery("SELECT id,name,priority from todo", Todo.class).getResultList().get(0);

        Assert.assertEquals(sukses, true);
        Assert.assertEquals(todoOne, inputTodo);
    }

    @Test
    public void getAll() throws Exception{
        Todo todoOne =
                new Todo("one", TodoPriority.HIGH);
        todoRepository.save(todoOne);

        List<Todo> todoList=this.todoRepository.getAll();
        LOG.debug("todoList:{}",todoList);

        Assert.assertThat(todoList.isEmpty(), org.hamcrest.Matchers.equalTo(false));
        Assert.assertThat(todoList.get(0),org.hamcrest.Matchers.equalTo(todoOne));
    }
}
