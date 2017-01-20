package springboot.service;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.model.Todo;
import springboot.model.constants.TodoPriority;
import springboot.repository.TodoRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by indra.e.prasetya on 1/18/2017.
 */
public class TodoServiceTest {

  private static final Logger LOG= LoggerFactory.getLogger(TodoServiceTest.class);

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

  /*
  before each test method
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
    // then

    // verify


  }

  @Test
  public void getAllTest() {
    LOG.debug("getAllTest...");

    // given
    List<Todo> todoList = new ArrayList<Todo>();
    todoList.add(new Todo("one", TodoPriority.HIGH));
    todoList.add(new Todo("two", TodoPriority.MEDIUM));
    todoList.add(new Todo("three", TodoPriority.LOW));
    BDDMockito.given(this.todoRepository.getAll()).willReturn(todoList);

    // when
    List<Todo> testResult = this.todoService.getAll();

    // then
    // assert
    Assert.assertThat(testResult, Matchers.equalTo(todoList));
    // verify
    BDDMockito.then(this.todoRepository).should(BDDMockito.times(1)).getAll();

    this.todoService.getAll();
    //Mockito.verifyNoMoreInteractions(this.todoRepository);

  }

  @Test
  public void saveTodoTest() {
    LOG.debug("saveTodoTest...");

    //given
    Todo todo = new Todo("halo", TodoPriority.HIGH);
    BDDMockito.given(this.todoRepository.store(todo)).willReturn(true);
    //when
   Boolean testResult = this.todoService.saveTodo("halo", TodoPriority.HIGH);
    //then
    //assert
    Assert.assertThat(testResult, Matchers.equalTo(true));
    //verify
    BDDMockito.then(this.todoRepository).should(BDDMockito.times(1)).store(todo);
    this.todoService.saveTodo(null, null);
    //Mockito.verifyNoMoreInteractions(this.todoService);
  }
}
