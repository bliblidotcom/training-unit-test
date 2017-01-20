package springboot.repository;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
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
import springboot.repository.TodoRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Juan on 1/20/17.
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
    public void setUp() {
        LOG.debug("setup");
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() {
        LOG.debug("teardown");
        // Verify
//        BDDMockito.then(this.todoRepository).shouldHaveNoMoreInteractions();
    }

    @Test
    public void getAll() {
        List<Todo> todoListAwal = new ArrayList<Todo>();

        todoListAwal.add( todoRepository.save(new Todo("lala", TodoPriority.HIGH)));

        //when
        List<Todo> todoList = todoRepository.getAll();

        // Then
        Assert.assertEquals(todoList, todoListAwal);

        // Verify
//        Mockito.verify(this.todoRepository, Mockito.times(1))
//                .getAll();
    }
}
