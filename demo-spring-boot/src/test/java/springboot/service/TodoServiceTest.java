package springboot.service;


import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import springboot.model.Todo;
import springboot.model.constants.TodoPriority;
import springboot.repository.TodoRepository;

public class TodoServiceTest {
	
	private static final Logger LOG = LoggerFactory.getLogger(TodoServiceTest.class);
	
	/**System under test**/
	 //untuk bikin obj todoservice, akan bikin todoservice, jadi langsing diinject
	@InjectMocks
	private TodoService todoService;

	/**dependency**/
	@Mock
	private TodoRepository todoRepository;
	
	//set up data untuk settingnya
	//before each test method
	@Before
	public void setUp() {
		LOG.debug("setUp...");
		MockitoAnnotations.initMocks(this);
		
	}	
	//Waktu tes udah selesai mau ngapain
	//after each test method
	@After
	public void tearDown(){
		LOG.debug("tearDown...");
		
	}
	
	@Test
	public void getAllTest(){
		LOG.debug("getAllTest..");
		
		//given, when, then merupakan behaviour
		//given
		List<Todo> todoList = new ArrayList<Todo>();
		todoList.add(new Todo("one",TodoPriority.HIGH));
		todoList.add(new Todo("two",TodoPriority.MEDIUM));
		todoList.add(new Todo("three",TodoPriority.LOW));
		BDDMockito.given(this.todoRepository.getAll()).willReturn(todoList);
		//when
		List<Todo> testResult = this.todoService.getAll();
		//then
		Assert.assertThat(testResult, Matchers.equalTo(todoList));
		this.todoService.getAll();
		
	}
	
	@Test
	public void saveTodoTest(){
		LOG.debug("saveTodoTest..");
		
		this.todoService.saveTodo(null, null);
	}
}
