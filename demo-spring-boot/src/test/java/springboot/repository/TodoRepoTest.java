package springboot.repository;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import springboot.Configuration;
import springboot.model.Todo;
import springboot.model.TodoNew;
import springboot.model.constants.TodoPriority;
import springboot.service.TodoServiceTest;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dias on 1/20/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Configuration.class})
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class, TransactionalTestExecutionListener.class})
@Transactional(readOnly = false)
public class TodoRepoTest {

    @Autowired
    TodoRepo todoRepo;

    @Autowired
    EntityManager entityManager;

    private static final Logger LOG = LoggerFactory.getLogger(TodoServiceTest.class);

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {

    }

    @Test
    public void getAllTest() {
        TodoNew todo = this.todoRepo.save(new TodoNew("one", TodoPriority.HIGH));
        
        List<TodoNew> todoList = this.todoRepo.getAll();
        LOG.debug("todoList:{}", todoList);
        
        Assert.assertThat(todoList.isEmpty(), Matchers.equalTo(false));
        Assert.assertThat(todoList.get(0), Matchers.equalTo(todo));
    }

    @Test
    public void storeTest() {
        TodoNew todo  = this.todoRepo.save(new TodoNew("one", TodoPriority.HIGH));

        boolean cek = this.todoRepo.store(todo);

        TodoNew cekAda = (TodoNew) this.entityManager.createNativeQuery(
                "SELECT ID, NAME, PRIORITY FROM TodoNew WHERE NAME='one' AND PRIORITY="+todo.getPriority().ordinal(), TodoNew.class).getSingleResult();

        Assert.assertTrue(cek);
        Assert.assertThat(cekAda.getName(), Matchers.equalTo(todo.getName()));
        Assert.assertThat(cekAda.getPriority(), Matchers.equalTo(todo.getPriority()));
    }
}
