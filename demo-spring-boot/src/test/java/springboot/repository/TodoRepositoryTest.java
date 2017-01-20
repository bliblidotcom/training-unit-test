package springboot.repository;


import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
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

/**
 * Created by dhika on 20/01/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Configuration.class})
@Transactional(readOnly = false)
@EnableAutoConfiguration
@SpringBootTest
public class TodoRepositoryTest {

    private static final Logger LOG = LoggerFactory.getLogger(TodoRepositoryTest.class);

    @Autowired
    TodoRepository todoRepository;

    @Test
    public void findAllTest() {
        Todo todo1 = new Todo("Test 1", TodoPriority.MEDIUM);
        Todo todo2 = new Todo("Test 2", TodoPriority.LOW);

        long initialCount = todoRepository.count();
        todoRepository.save(todo1);
        todoRepository.save(todo2);

        long countAfterInsertion = todoRepository.count();
        Assert.assertThat(countAfterInsertion, Matchers.equalTo(initialCount+2));
        Assert.assertThat(todoRepository.findAll().get(0), Matchers.equalTo(todo1));
    }

    @Test
    public void saveTest() {
        Todo todo = new Todo("Test saving a todo to database", TodoPriority.HIGH);
        LOG.info("todo : {}", todo.toString());

        Todo savedTodo = todoRepository.save(todo);

        Assert.assertThat(savedTodo, Matchers.equalTo(todo));
    }
}
