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
	
	private static final Logger LOG = LoggerFactory.getLogger(TodoServiceTest.class);

	/**
	 * system under test
	 */
	@InjectMocks // inject mock disini itu class yang kita test
	private TodoService todoService;
	
	/**
	 * dependency
	 */	
	@Mock // @Mock ini dependency dari inject mock tersebut
	private TodoRepository todoRepository;
	
	/**
	 * Before test method, initialize
	 */
	@Before
	public void setUp(){		
		LOG.debug("before called..");
		MockitoAnnotations.initMocks(this); // inisialisasi mock pada class ini sendiri
	};
	
	/**
	 * after test method
	 */
	@After
	public void tearDown(){
		LOG.debug("teardown called..");				
		Mockito.verifyNoMoreInteractions(this.todoRepository);				
	};
	
	@Test
	public void getAllTest() {

		//given				
		List<Todo> todo = new ArrayList<Todo>();
		todo.add(new Todo("todo1", TodoPriority.HIGH));
		todo.add(new Todo("todo2", TodoPriority.MEDIUM));		
		BDDMockito.given(this.todoService.getAll()).willReturn(todo);
		
		//when
		LOG.debug("getAllTest");		
		List<Todo> todoResult = this.todoService.getAll();

		//then	
		//Assert => for check
		//untuk cek apakah bener2 listnya match atau tidak ubah dikit gan
		//Assert.assertThat(todoResult, Matchers.equalTo(todo));
		Assert.assertThat(todoResult == todo, Matchers.equalTo(true));
		
		
		//verify
			// di cek bahwa method getAll harus di panggil 1x
		BDDMockito.then(this.todoRepository).should( BDDMockito.times(1) ).getAll();
	}
	
	@Test
	public void saveToDoTest(){

		//given
		//initialize
		String name = "todo";
		TodoPriority priority = TodoPriority.HIGH;
		
		boolean response = true;		
		BDDMockito.given(this.todoService.saveTodo(name, priority)).willReturn(response);

		//when
		boolean resultResponse = this.todoService.saveTodo(name, priority);
		
		
		//then		
		Assert.assertThat(response == resultResponse,Matchers.equalTo(true));
		
		//verify
		BDDMockito.then(this.todoRepository).should( BDDMockito.times(1) ).store(
				new Todo(name, priority));
	}
	
}
