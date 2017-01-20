package springboot.controller;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import springboot.model.Todo;
import springboot.model.constants.TodoPriority;
import springboot.model.request.CreateTodoRequest;
import springboot.service.TodoService;

import java.util.Arrays;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.*;

/**
 * Created by indra.e.prasetya on 1/18/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class TodoControllerTest {

  @MockBean
  private TodoService todoService;

  @LocalServerPort
  private int serverPort;

  private static final String NAME = "Todo1";
  private static final TodoPriority PRIORITY = TodoPriority.HIGH;

  private static final String TODO = String.format("{\"name\":\"%s\",\"priority\":\"%s\"}", NAME, PRIORITY);

  @Test
  public void all() {
    when(todoService.getAll()).thenReturn(Arrays.asList(new Todo(NAME, PRIORITY)));

    given()
      .contentType("application/json")
      .when()
      .port(serverPort)
      .get("/todos")
      .then()
      .body(containsString("value"))
      .body(containsString(NAME))
      .statusCode(200);

    verify(todoService).getAll();
  }

  @Test
  public void insert() {
    Todo todo = new Todo(NAME, PRIORITY);
    when(todoService.saveTodo(NAME, PRIORITY)).thenReturn(true);

    CreateTodoRequest request = new CreateTodoRequest();
    request.setName(NAME);
    request.setPriority(PRIORITY);

    given()
            .contentType("application/json")
            .content(request)
            .when()
            .port(serverPort)
            .post("/todos")
            .then()
            .body(containsString("value"))
            .body(containsString("true"))
            .statusCode(200);
    verify(todoService).saveTodo(NAME, PRIORITY);
  }

  @After
  public void tearDown() {
    verifyNoMoreInteractions(this.todoService);
  }

}
