package springboot.service;

import java.util.List;

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
import springboot.repository.TodoEntityRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Configuration.class})
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class,
DirtiesContextTestExecutionListener.class, TransactionalTestExecutionListener.class})
@Transactional(readOnly = false)
public class RepositoryTest {
	
	private static final Logger LOG = LoggerFactory.getLogger(RepositoryTest.class);
	
	@Autowired
	TodoEntityRepository entityRepository;

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
    	
    }

    @Test
    public void getAllTest() {
    	this.entityRepository.save(new Todo("todo1",TodoPriority.HIGH));
    	this.entityRepository.save(new Todo("todo2",TodoPriority.LOW));
    	
    	List<Todo> result = this.entityRepository.getAll();
    	
    	LOG.debug("result : {}",result);
    	
    	Assert.assertThat(result.isEmpty(), Matchers.equalTo(false));    	    	
    }

    @Test
    public void saveTodoTest() {
    	
    	String todo = "todo3";
    	TodoPriority priority = TodoPriority.HIGH;
    	boolean isSuccess = this.entityRepository.saveTodo(todo, priority);    	
    	List<Todo> resultTodoList = this.entityRepository.findByName(todo);
    	
    	Assert.assertThat(isSuccess, Matchers.equalTo(true));
    	Assert.assertThat(resultTodoList.isEmpty(), Matchers.equalTo(false));
    }
	
}
