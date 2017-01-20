package springboot.repository;

import java.util.List;

import javax.persistence.EntityManager;

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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Configuration.class})
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class, TransactionalTestExecutionListener.class})
@Transactional(readOnly = false)
public class TodoRepositoryImplTest {
	private static final Logger LOG = LoggerFactory.getLogger(TodoRepository.class);
	@Autowired
	private EntityManager entitymanager;
	
	@Autowired
	private TodoRepository todorepository;
	
	@Before
	public void setUp() throws Exception{
		
	}
	
	@After
	public void tearDown() throws Exception{
		
	}
	
	@Test
	public void store() throws Exception{
		//given
		Todo todoOne = this.todorepository.save(new Todo("two", TodoPriority.MEDIUM));
		
		//when 
		boolean result = todorepository.store(todoOne);
		
		//then
		Todo todoTest = (Todo) this.entitymanager.createNativeQuery(
				"select id, name, priority from todo where name ='" +todoOne.getName()+ "' and priority ='" + todoOne.getPriority().ordinal()+"'", Todo.class)
				.getSingleResult();
		Assert.assertThat(todoOne, Matchers.equalTo(todoTest));
	}
	
	@Test
	public void getAll() throws Exception {
		//given
		Todo todoOne = this.todorepository.save(new Todo("one", TodoPriority.HIGH));
		
		//when
		List<Todo> todoList = this.todorepository.getAll();
		LOG.debug("todoList : {}" , todoList);
		
		//then
		Assert.assertThat(todoList.isEmpty(), Matchers.equalTo(false));
		Assert.assertThat(todoList.get(0), Matchers.equalTo(todoOne));
		
	}
	
	
}
