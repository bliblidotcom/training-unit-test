package springboot.repository;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ARDI on 1/20/2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Configuration.class})
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class, TransactionalTestExecutionListener.class})
@Transactional(readOnly = false)
public class TodoRepositoryImplTest {

    @Autowired
    private TodoRepository todoRepository;

//    @Autowired
//    private TodoRepositoryImpl todoRepositoryImpl;

    public static final Logger LOG = LoggerFactory.getLogger(TodoRepositoryImplTest.class);

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
        // Verify

    }

    @Test
    public void getAllTest() {
        // Given
        Todo todoOne = this.todoRepository.save(new Todo("one" , TodoPriority.HIGH));

        // When
        List<Todo> todoList = this.todoRepository.getAll();

        // Then
        Assert.assertThat(todoList.isEmpty(), Matchers.equalTo(false));
        Assert.assertThat(todoList.get(0) , Matchers.equalTo(todoOne));

        // Verify
    }

    @Test
    public void saveTodoTest() {
        // Given
        Todo newTodo = new Todo("Test insert new todo item.", TodoPriority.LOW);

        // When
        boolean result = this.todoRepository.store(newTodo);

        // Then
        Assert.assertThat(result , Matchers.equalTo(true));

        // Verify
    }
}
