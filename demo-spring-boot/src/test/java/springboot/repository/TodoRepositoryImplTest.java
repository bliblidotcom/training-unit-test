/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springboot.repository;

import java.util.List;
import javax.persistence.EntityManager;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
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

/**
 *
 * @author Amesa
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Configuration.class})
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class, TransactionalTestExecutionListener.class})
@Transactional(readOnly = false)


public class TodoRepositoryImplTest {
    
    @Autowired
    private EntityManager entityManager;
    
    @Autowired
    TodoRepository todoRepository;
    private static final Logger LOG = LoggerFactory.getLogger(TodoRepository.class);
    @After
    public void tearDown() throws Exception{
        LOG.debug("===before (Setup)============");
}
    
    
     @Before
    public void setUp() throws Exception { //sebelum method unit test
        LOG.debug("===before (Setup)============");
      
    }
        
    @Test
    public void getAll() throws Exception{
        //given
        Todo todoOne = this.todoRepository.save(new Todo("one", TodoPriority.HIGH));
        //when
        List<Todo> todoList = this.todoRepository.getAll();
        LOG.debug("todoLitst:{}", todoList);
        
        //then
        Assert.assertThat(todoList.isEmpty(), Matchers.equalTo(false));
        Assert.assertThat(todoList.get(0), Matchers.equalTo(todoOne));//mastiin objeknya persis sama
    
    }
        
    
    @Test
    public void saveTodo() {
        LOG.debug("====Save to Do===========");

        //Todo todo = new Todo("1", TodoPriority.HIGH);
        //given
        Todo todo1 = this.todoRepository.save(new Todo("satu",TodoPriority.MEDIUM));
        
        //when
        //Boolean testResult = this.todoRepository.saveTodo(todo.getName(), todo.getPriority());
        Boolean testResult = this.todoRepository.store(todo1);
        //then

        //asert
        Todo todo = (Todo) this.entityManager.createNativeQuery(
                "select id, name, priority from todo where name = '"+todo1.getName()+"' and priority ='"+todo1.getPriority().ordinal()+"'", Todo.class)
                .getSingleResult();
        Assert.assertThat(todo1, Matchers.equalTo(todo));
        //verify
        //BDDMockito.then(this.todoRepository).should(BDDMockito.times(1)).store(todo);
    }
}
