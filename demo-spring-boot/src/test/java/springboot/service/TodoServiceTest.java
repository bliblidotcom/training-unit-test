package springboot.service;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import org.hamcrest.Matchers;
import java.util.ArrayList;
import java.util.List;

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
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(TodoServiceTest.class);

	// system under test
	@InjectMocks
	private TodoService todoService;

	/*
	 * dependency
	 */

	@Mock
	private TodoRepository todorepository;

	/*
	 * before each method
	 */

	@Before
	public void setUp() {
		LOG.debug("set up...");
		MockitoAnnotations.initMocks(this);
	}

	/*
	 * after each method
	 */
	@After
	public void tearDown() {
		LOG.debug("tear down...");
		Mockito.verifyNoMoreInteractions(this.todorepository);
	}

	// method yang akan ditest
	@Test
	public void getAllText() {
		LOG.debug("getAllText....");
		// given
		List<Todo> todoList = new ArrayList<Todo>();
		todoList.add(new Todo("one", TodoPriority.HIGH));
		todoList.add(new Todo("two", TodoPriority.MEDIUM));
		BDDMockito.given(this.todorepository.getAll()).willReturn(todoList);

		// when
		List<Todo> testResult = this.todoService.getAll();

		// then

		// assert
		// Assert.assertThat(testResult == todoList, Matchers.equalTo(false));
		Assert.assertThat(testResult, Matchers.equalTo(todoList));

		// verify
		BDDMockito.then(this.todorepository).should(BDDMockito.times(1)).getAll();
	}

	@Test
	public void saveTodoTest() {
		LOG.debug("saveTodoTest...");

		// give
		Todo todo = new Todo("1", TodoPriority.HIGH);
		BDDMockito.given(this.todorepository.store(todo)).willReturn(true);

		// when
		boolean testResult = this.todoService.saveTodo("1", TodoPriority.HIGH);

		// then

		// assert
		Assert.assertThat(testResult, Matchers.equalTo(true));

		// verify
		BDDMockito.then(this.todorepository).should(BDDMockito.times(1)).store(todo);

	}
}
