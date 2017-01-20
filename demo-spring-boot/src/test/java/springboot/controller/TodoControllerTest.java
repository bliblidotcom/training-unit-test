package springboot.controller;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.http.ContentType;
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

import java.io.IOException;
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
      .body(containsString(NAME)).body(containsString(PRIORITY.name()))
      .statusCode(200);

    verify(todoService).getAll();
  }

  @Test
  public void insertTest(){
    CreateTodoRequest todoRequest = new CreateTodoRequest();
    todoRequest.setName(NAME);
    todoRequest.setPriority(PRIORITY);

    ObjectMapper mapper = new ObjectMapper();
    String jsOnString = null;

    try{

      jsOnString = mapper.writeValueAsString(todoRequest);

    }catch (JsonGenerationException e) {
      e.printStackTrace();
    } catch (JsonMappingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }


    when(todoService.saveTodo(todoRequest.getName(), todoRequest.getPriority())).thenReturn(true);


    given()
            .contentType(ContentType.JSON)
            .body(jsOnString)
            .when()
            .port(serverPort)
            .post("/todos")
            .then()
            .body(containsString("true"))
            .statusCode(200);

    verify(todoService).saveTodo(todoRequest.getName(), todoRequest.getPriority());
  }

  @After
  public void tearDown() {
    verifyNoMoreInteractions(this.todoService);
  }

}
