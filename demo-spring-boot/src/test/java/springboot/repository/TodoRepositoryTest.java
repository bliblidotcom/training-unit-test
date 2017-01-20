/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springboot.repository;

import springboot.Configuration;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
import springboot.model.Todo;
import springboot.model.constants.TodoPriority;
import springboot.service.TodoService;
import java.util.List;
import org.hamcrest.Matchers;

/**
 *
 * @author Marlina
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Configuration.class})
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class,DirtiesContextTestExecutionListener.class, TransactionalTestExecutionListener.class})
@Transactional(readOnly = false)
public class TodoRepositoryTest {
    private static final Logger LOG=LoggerFactory.getLogger(TodoService.class);
    @Autowired
    private TodoRepository todoRepository;
    @Before
    public void setUp() throws Exception {
        
    }
    @After
    public void tearDown() throws Exception{
        
    }
    @Test
    public void getAll() throws Exception{
        //given
        Todo todoOne=this.todoRepository.save(new Todo("ayam",TodoPriority.HIGH));
        
        //when
        List<Todo> todoList=this.todoRepository.getAll();
        LOG.debug("todoList: {}",todoList);
        
        //then
        Assert.assertThat(todoList.isEmpty(), Matchers.equalTo(false));
        Assert.assertThat(todoList.get(0), Matchers.equalTo(todoOne));
    }
    @Test
    public void store() throws Exception{
        //given
        Todo todoOne=this.todoRepository.save(new Todo("ayam",TodoPriority.HIGH));
        
        //when
        boolean hasil=this.todoRepository.store(todoOne);
        LOG.debug("hasil: {}",hasil);
        
        //then
        Assert.assertThat(hasil, Matchers.equalTo(true));
        
    }
}
