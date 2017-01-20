package springboot.service;

import javax.persistence.EntityManager;

import org.hamcrest.Matchers;

//import javax.transaction.Transactional;
import java.util.List;
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
import springboot.repository.TodoRepository;
import springboot.repository.TodoRepositoryImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Configuration.class})
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class, TransactionalTestExecutionListener.class})
@Transactional(readOnly = false)

public class TodoRepositoryImplTest {
	
	  private static final Logger LOG = LoggerFactory.getLogger(TodoRepository.class);
	  
	  	@Autowired
	  	private EntityManager entityManager;
	    
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
	    public void store(){
	    	LOG.debug("Storee...");
	    	 Todo todoOne = new Todo("Adin", TodoPriority.HIGH);
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
	        
	        Assert.assertThat(todoList.isEmpty(),Matchers.equalTo(false));
	        Assert.assertThat(todoList.get(0),Matchers.equalTo(todoOne));
	    }
}
