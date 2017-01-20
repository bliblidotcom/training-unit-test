package springboot.service;

import jdk.nashorn.internal.objects.annotations.Getter;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import springboot.model.Todo;
import springboot.model.constants.TodoPriority;
import springboot.repository.TodoRepository;

import java.util.Arrays;
import java.util.List;

/**
 * Created by indra.e.prasetya on 1/18/2017.
 */
public class TodoServiceTest {

  @InjectMocks
  private TodoService todoService;

  @Mock
  private TodoRepository todoRepository;

  private static final String NAME = "Todo1";
  private static final TodoPriority PRIORITY = TodoPriority.HIGH;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void saveTodo() {
    Mockito.when(todoRepository.store(new Todo(NAME, PRIORITY))).thenReturn(true);

    boolean resp = todoService.saveTodo(NAME, PRIORITY);
    Assert.assertTrue(resp);

    Mockito.verify(todoRepository).store(new Todo(NAME, PRIORITY));
  }

  @Test
  public void getAll() {
    Mockito.when(todoRepository.getAll()).thenReturn(Arrays.asList(new Todo(NAME, PRIORITY)));

    List<Todo> resp = todoService.getAll();
    Assert.assertEquals(1, resp.size());
    Assert.assertEquals(NAME, resp.get(0).getName());
    Assert.assertEquals(PRIORITY, resp.get(0).getPriority());

    Mockito.verify(todoRepository).getAll();
  }




  @After
  public void tearDown() {
    Mockito.verifyNoMoreInteractions(this.todoRepository);
  }

}
