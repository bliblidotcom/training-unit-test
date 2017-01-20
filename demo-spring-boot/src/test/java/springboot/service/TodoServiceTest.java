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
	@InjectMocks
	private TodoService todoService;
	/**
	 * dependency
	 */
	@Mock
	private TodoRepository todoRepository;

	/**
	 * before each test method
	 */
	@Before
	public void setUp() {
		LOG.debug("setUp...");
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * after each test method
	 */
	@After
	public void tearDown() {
		LOG.debug("tearDown...");
		Mockito.verifyNoMoreInteractions(this.todoRepository);
		//isinya semua dependency yg kita panggil
	}
	@Test
	public void getAllTest() {
		LOG.debug("getAllTest...");
		
		//given
		List<Todo> todoList = new ArrayList<Todo>();
		todoList.add(new Todo("one", TodoPriority.HIGH));
		todoList.add(new Todo("two", TodoPriority.MEDIUM));
		BDDMockito.given(this.todoRepository.getAll()).willReturn(todoList);
		//when
		List<Todo> testResult = this.todoService.getAll();
		
		//then
		//assert
		Assert.assertThat(testResult, Matchers.equalTo(todoList));
		
		//verify
		BDDMockito.then(this.todoRepository).should(BDDMockito.times(1)).getAll();
	}
	@Test
	public void saveTodoTest() {
		LOG.debug("saveTodoTest...");
		
		//given
		Todo todo = new Todo("one", TodoPriority.HIGH);
		BDDMockito.given(this.todoRepository.store(todo)).willReturn(true);
		
		//when
		boolean result = this.todoService.saveTodo("one", TodoPriority.HIGH);
		
		//then
		//assert
		Assert.assertThat(result, Matchers.equalTo(true));
		
		//verify
		BDDMockito.then(this.todoRepository).should(BDDMockito.times(1)).store(todo);
	}
}
