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
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import springboot.model.Todo;
import springboot.model.constants.TodoPriority;
import springboot.repository.TodoRepository;

public class TodoServiceTest {

	private static final Logger LOG =  LoggerFactory.getLogger(TodoServiceTest.class);
	
	/**
	 * System under test
	 */
	@InjectMocks
	private TodoService todoService;
	
	/**
	 * dependency
	 */
	@Mock
	private TodoRepository todoRepository;

	/*=================================================	BATAS BATAS BATAS ==========================================================*/
	
	/**
	 * before each test method
	 */
	@Before
	public void setUp() {
		LOG.debug("setup test..");
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * after each test method
	 */
	@After
	public void tearDown() {
		LOG.debug("Tear down test..");		
//		Mockito.verifyNoMoreInteractions(this.todoRepository);
	}

	@Test
	public void getAllTest(){
		LOG.debug("get all test ..");
		
		// waktu kita bikin implementasi bohongan
		//given
		List<Todo> todoList = new ArrayList<Todo>();
		todoList.add(new Todo("todo", TodoPriority.HIGH));
		todoList.add(new Todo("todo", TodoPriority.MEDIUM));
		todoList.add(new Todo("todo", TodoPriority.LOW));
		BDDMockito.given(this.todoRepository.getAll()).willReturn(todoList);
		
		// waktu saat dipanggil	
		//when
		List<Todo> testResult = this.todoService.getAll();		
		
		//then
			// assert
		//cek apakah objek memiliki nilai sama toString
		Assert.assertThat(testResult, Matchers.equalTo(todoList));
		// cek objek apakah sama
		Assert.assertThat(testResult == todoList, Matchers.equalTo(true));
		
			// verify
		// verifikasi apakah method dipanggil 1 kali 
		BDDMockito.then(this.todoRepository).should(BDDMockito.times(1)).getAll();	
		
	}
	
	@Test
	public void saveTodoTest(){
		LOG.debug("save todo test ..");
		//given
		String name = "todo1";
		TodoPriority priority = TodoPriority.HIGH;
		Todo todo = new Todo(name, priority);		
		boolean response = true;
		BDDMockito.given(this.todoRepository.store(todo)).willReturn(response);
		
		//when		
		boolean testResult = this.todoService.saveTodo(name, priority);
		
		
		//then
			//assert
		Assert.assertThat(testResult==response, Matchers.equalTo(true));		
			//verify
		BDDMockito.then(this.todoRepository).should(BDDMockito.times(1)).store(todo);
	}
}
