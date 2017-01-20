/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springboot.repository;

import java.util.List;
import javax.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import springboot.model.Todo;
import springboot.service.TodoService;

/**
 *
 * @author Sofrie
 */
public class TodoRepositoryImpl implements TodoRepositoryCustom {

    private static final Logger LOG = LoggerFactory.getLogger(TodoService.class);
    
    @Autowired
    private EntityManager entityManager;

    @Autowired
    TodoRepository todoRepository;

    @Override
    public List<Todo> getAll() {
        LOG.debug("getAll...");
        //List<Todo> result = this.jdbcTemplate.queryForList("select name,priority from Todo", Todo.class);
        List<Todo> result=entityManager.createNativeQuery("select id,name,priority FROM Todo",Todo.class).getResultList();
        LOG.debug("result:{}", result);
        return result;
    }

    @Override
    public boolean store(Todo todo) {
       // todoRepository.save(todo);
       LOG.debug("store....");
       int a= todo.getPriority().ordinal();
       //List<Todo> result2=entityManager.createNativeQuery("select id,name,priority FROM Todo",Todo.class).getResultList();
     
       Boolean result=false;
       try
       {
           entityManager.persist(todo);
           result=true;
       // Todo newTodo = todoRepository.findOne(todo.getId());
       }
       catch (Exception e)
        {

        }
       return result;
    //    return true;
    }
}
