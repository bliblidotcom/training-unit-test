/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springboot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
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
//import org.mockito.BDDMockito;
//import static org.mockito.BDDMockito.given;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
import org.slf4j.LoggerFactory;
import springboot.model.Todo;
import springboot.model.constants.TodoPriority;

import springboot.repository.TodoRepository;

/**
 *
 * @author Amesa
 */
public class TodoServiceTest {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(TodoServiceTest.class);

    @InjectMocks //add service, bikin obj baru
    private TodoService todoService;

    @Mock
    private TodoRepository todoRepository;

    @Before
    public void setUp() { //sebelum method unit test
        LOG.debug("===before (Setup)============");
        MockitoAnnotations.initMocks(this);

    }

    @After
    public void tearDown() { //setelah tiap method unit test
        LOG.debug("===After....============");
        Mockito.verifyNoMoreInteractions(this.todoRepository);
    }

    @Test
    public void getAllTest() {
        LOG.debug("===GET ALL TEST===========");

        //given
        List<Todo> todoList = new ArrayList<Todo>();
        todoList.add(new Todo("one", TodoPriority.HIGH));
        todoList.add(new Todo("two", TodoPriority.MEDIUM));
        BDDMockito.given(this.todoRepository.getAll()).willReturn(todoList);

        //when
        List<Todo> testResult = this.todoService.getAll();

        //then
        //asert
        Assert.assertThat(testResult == todoList, Matchers.equalTo(true));
        Assert.assertThat(testResult, Matchers.equalTo(todoList));//mastiin objeknya persis sama
        //verify
        BDDMockito.then(this.todoRepository).should(BDDMockito.times(1)).getAll();
        //Mockito.verifyNoMoreInteractions(this.todoRepository);
    }

    @Test
    public void saveTodo() {
        LOG.debug("====Save to Do===========");
        //this.todoService.saveTodo(null,null);

        Todo todo = new Todo("1", TodoPriority.HIGH);
        //given
        BDDMockito.given(this.todoRepository.store(todo)).willReturn(true);

        //when
        Boolean testResult = this.todoService.saveTodo(todo.getName(), todo.getPriority());
        //then

        //asert
        Assert.assertThat(testResult, Matchers.equalTo(true));
        //verify
        BDDMockito.then(this.todoRepository).should(BDDMockito.times(1)).store(todo);
    }

}
