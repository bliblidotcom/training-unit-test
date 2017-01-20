/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springboot.service;

import java.util.ArrayList;
import java.util.List;
import org.hamcrest.Matcher;
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

/**
 *
 * @author ALz
 */
public class TodoServiceTest {

    private static final Logger LOG = LoggerFactory.getLogger(TodoServiceTest.class); //import yang slf4j

    @InjectMocks
    private TodoService todoService; //yang ditest

    @Mock
    private TodoRepository todoRepository; //keperluan dependency

    //method mengeset sebelum test
    @Before
    public void setUp() {
        LOG.debug("setUp...");
        MockitoAnnotations.initMocks(this); //menginisialisasi mock yang berisi kelas ini
    }

    //method mengeset aksi setelah test
    @After
    public void tearDown() {
        LOG.debug("tearDown...");
        Mockito.verifyNoMoreInteractions(this.todoRepository);
    }   

    @Test
    public void getAllTest() {
        LOG.debug("getAllTest...");
        //given
        List<Todo> todoList = new ArrayList<Todo>();
            todoList.add(new Todo("one",TodoPriority.HIGH));
            todoList.add(new Todo("two",TodoPriority.MEDIUM));
            todoList.add(new Todo("three",TodoPriority.LOW));
            
        BDDMockito.given(this.todoRepository.getAll()).willReturn(todoList);
        //when
        List<Todo> testResult = todoService.getAll();
        
        //then
        //assert
        Assert.assertThat(testResult,Matchers.equalTo(todoList));
        Assert.assertThat(testResult==todoList,Matchers.equalTo(true));
        //verify
        BDDMockito.then(todoRepository).should(
                BDDMockito.times(1)).getAll();
    }

    @Test
    public void saveTodoTest() {
        LOG.debug("saveTodoTest...");
        //given
        Todo todo = new Todo("test",TodoPriority.LOW);
        BDDMockito.given(todoRepository.store(todo))
                .willReturn(true);
        //when
        Boolean checkSave = todoService.saveTodo("test", TodoPriority.LOW);
        //then
        //assert
        Assert.assertThat(checkSave,Matchers.equalTo(true));
        //verify
         BDDMockito.then(todoRepository).should(
                BDDMockito.times(1)).store(todo);
    }
}
