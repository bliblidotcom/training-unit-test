package springboot.controller;

import com.google.gson.Gson;
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
import springboot.service.TodoService;
import springboot.model.request.CreateTodoRequest;

import java.util.Arrays;

import static com.jayway.restassured.RestAssured.given;
import com.jayway.restassured.http.ContentType;
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

    private Gson gson ;

    @Test
    public void insert() {

        CreateTodoRequest todo = new CreateTodoRequest();
        todo.setName("namalll");
        todo.setPriority(TodoPriority.HIGH);
        String json = "{\"name\":\"" + todo.getName() + "\",\"priority\":\"" + todo.getPriority() + "\"}";
        when(todoService.saveTodo(todo.getName(), todo.getPriority())).thenReturn(true);

        given()
                .contentType(ContentType.JSON)
                .content(json)
                .when()
                .port(serverPort)
                .post("/todos")
                .then()
                .body(containsString("true"))
                .body(containsString("value"))
                .statusCode(200);
        verify(todoService).saveTodo(todo.getName(), todo.getPriority());
    }

    @After
    public void tearDown() {
        verifyNoMoreInteractions(this.todoService);
    }

}
