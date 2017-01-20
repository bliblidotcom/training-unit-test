/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springboot.controller;

import static com.jayway.restassured.RestAssured.given;
import java.util.Arrays;
import static org.hamcrest.Matchers.containsString;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import springboot.model.Todo;
import springboot.model.constants.TodoPriority;
import springboot.service.TodoService;

/**
 *
 * @author Marlina
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class InsertControllerTest {
    @MockBean
  private TodoService todoService;

  private static final String NAME = "Todo1";
  private static final TodoPriority PRIORITY = TodoPriority.HIGH;

  private static final String TODO = String.format("{\"name\":\"%s\",\"priority\":\"%s\"}", NAME, PRIORITY);
  @Test
  public void insert() {
      Todo todo=new Todo("ayam",PRIORITY.HIGH);
      String json="{\"name\":\"ayam\",\"priority\":\"HIGH\"}";
    when(todoService.saveTodo("ayam", PRIORITY.HIGH)).thenReturn(true);

    given()
      .contentType("application/json")
      .body(json)
      .when()
      .post("/todos")
      .then()
      .body(containsString("true"))
      .statusCode(200);

    verify(todoService).saveTodo("ayam", PRIORITY.HIGH);
  }
  @After
  public void tearDown() {
    verifyNoMoreInteractions(this.todoService);
  }
}
