package springboot.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.util.Arrays;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.*;
import org.springframework.web.bind.annotation.RequestBody;
import springboot.model.request.CreateTodoRequest;

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
        //methodnya ngga langsung dipanggil
        given()//buat bikin requestnya apa aja
                .contentType("application/json")
                .when()
                .port(serverPort)
                .get("/todos")//get soalnya cuma buat getAll
                .then()
                .body(containsString("value"))
                .body(containsString(NAME))
                .statusCode(200);

        verify(todoService).getAll();
    }
//harus ada responsebody

    @After
    public void tearDown() {
        verifyNoMoreInteractions(this.todoService);
    }

    @Test
    public void insert() throws JsonProcessingException {

        //methodnya ngga langsung dipanggil
        ObjectMapper mapper = new ObjectMapper();
        CreateTodoRequest create = new CreateTodoRequest();
        Todo obj = new Todo("satuu", PRIORITY.HIGH);
        create.setName(obj.getName());
        create.setPriority(obj.getPriority());
        String jsonInString = mapper.writeValueAsString(create);
        when(todoService.saveTodo(obj.getName(), obj.getPriority())).thenReturn(true);
        
        given()//buat bikin requestnya apa aja
                .contentType("application/json")
                .body(jsonInString)
                .when()
                .port(serverPort)
                .post("/todos")//get soalnya cuma buat getAll

                .then()
                .body(containsString("true"))
                .body(containsString("value"))
                .statusCode(200);

        verify(todoService).saveTodo(obj.getName(), obj.getPriority());
    }
}
