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
import springboot.configuration.Configuration;
import springboot.model.Todo;
import springboot.model.constants.TodoPriority;

import java.util.List;

/**
 * Created by isdzulqor on 1/20/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Configuration.class})
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class, TransactionalTestExecutionListener.class})
@Transactional(readOnly = false)
public class TodoRepositoryImplTest{

    private static final Logger LOG = LoggerFactory.getLogger(TodoRepositoryImplTest.class);

    @Autowired
    private TodoRepository todoRepository;

//    @Autowired
//    JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() throws Exception{

    }

    @After
    public void tearDown() throws Exception{
        // Verify
    }

    @Test
    public void getAll() throws Exception{
        //given
        Todo todoOne = this.todoRepository.save(new Todo("one", TodoPriority.HIGH));

        //when
        List<Todo> todoList = this.todoRepository.getAll();
        LOG.debug("todolist :{}", todoList);

        //then
        Assert.assertThat(todoList.isEmpty(), Matchers.equalTo(false));
        Assert.assertThat(todoList.get(0), Matchers.equalTo(todoOne));
    }

}
