/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springboot.repository;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import static org.mockito.BDDMockito.willReturn;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import springboot.Configuration;
import springboot.model.Todo;
import springboot.model.constants.TodoPriority;
import springboot.service.TodoService;
import springboot.service.TodoServiceTest;

/**
 *
 * @author Sofrie
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Configuration.class})
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class, TransactionalTestExecutionListener.class})
@Transactional(readOnly = false)

public class TodoRepositoryTest {

// The System Under Test (SUT)
    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private EntityManager entityManager;

    
    private static final Logger LOG = LoggerFactory.getLogger(TodoServiceTest.class);

    @Before
    public void setUp() {
        LOG.debug("setUp...");
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() {
        LOG.debug("tearDown...");
        // Verify
        // BDDMockito.then(this.todoRepository).shouldHaveNoMoreInteractions();
    }

    @Test
    public void getAllTest() throws Exception {
        // Given
        Todo todoOne = this.todoRepository.save(new Todo("one", TodoPriority.MEDIUM));

        // When
        List<Todo> todoList = this.todoRepository.getAll();
        LOG.debug("todoList:{}", todoList);

        // Then
        Assert.assertThat(todoList.isEmpty(), Matchers.equalTo(false));
        Assert.assertThat(todoList.get(0), Matchers.equalTo(todoOne));

        // Verify
        //Mockito.verify(this.todoRepository, Mockito.times(1))
        //      .getAll();
    }

    @Test
    public void saveTodoTest() {
        // Given
        Todo newTodo = new Todo("testes.", TodoPriority.LOW);
       
        // When
        Boolean result=this.todoRepository.store(newTodo);

        // Then
        List<Todo> result2=this.todoRepository.findByName(newTodo.getName());
        LOG.debug(newTodo.getName());
        //Todo hasil=(Todo)entityManager.createNativeQuery("select id, name, priority FROM Todo WHERE name="+newTodo.getName()+" and priority="+newTodo.getPriority().ordinal()+"'",Todo.class).getResultList();
        
        Assert.assertThat(newTodo, Matchers.equalTo(result2.get(0)));
       // Assert.assertThat(todoList.get(0), Matchers.equalTo(todoOne));

        // Verify
        //  Mockito.verify(this.todoRepository, Mockito.times(1))
        //        .store(newTodo);
    }

}
