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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import springboot.Configuration;
import springboot.model.Todo;
import springboot.model.constants.TodoPriority;
import springboot.service.TodoServiceTest;


/**
 *
 * @author ALz
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Configuration.class})
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class, TransactionalTestExecutionListener.class})
@Transactional(readOnly = false)
public class TodoRepositoryImplTest {
    private static final Logger LOG = LoggerFactory.getLogger(TodoServiceTest.class);
    
    @Autowired
    private TodoRepository todoRepository ;
    
    @Before
    public void setUp() throws Exception{
        LOG.debug("Starting test...");
    }  
    
    @After
    public void tearDown() throws Exception{
        LOG.debug("Stopping test...");
    }
    
    @Test
    public void store(){
        //given
        Todo todoSave = new Todo("dataMasuk", TodoPriority.HIGH);
        //when
        this.todoRepository.store(todoSave);
         List<Todo> result = this.todoRepository.getAll();
        LOG.debug("data tersimpan : {}",todoSave);
        //then
        Assert.assertThat(result.get(0),Matchers.equalTo(todoSave));
    }
    
    @Test
    public void getAll() throws Exception{
        //given
        Todo todoOne = 
                new Todo("one", TodoPriority.HIGH);
        todoRepository.save(todoOne);
        //when
        List<Todo> todoList=this.todoRepository.getAll();
        LOG.debug("todoList:{}",todoList);
        //then
        Assert.assertThat(todoList.isEmpty(),Matchers.equalTo(false));
        Assert.assertThat(todoList.get(0),Matchers.equalTo(todoOne));
    }
}
