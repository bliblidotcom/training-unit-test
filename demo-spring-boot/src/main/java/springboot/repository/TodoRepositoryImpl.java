package springboot.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import springboot.model.Todo;
import springboot.model.constants.TodoPriority;
import springboot.service.TodoService;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by Elisabet Diana K S on 20/01/2017.
 */

@Repository
public class TodoRepositoryImpl implements TodoRepositoryCustom {

    private static final Logger LOG = LoggerFactory.getLogger(TodoService.class);
    @Autowired
    EntityManager entityManager;

    public boolean store(Todo todo){
        try{
            entityManager.persist(todo);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }


    public List<Todo> getAll() {
        LOG.debug("getAll...");
        //List<Todo> result = this.jdbcTemplate.queryForList("select name,priority from Todo", Todo.class);
        List<Todo> result = entityManager.createNativeQuery("select id,name,priority FROM Todo",Todo.class).getResultList();
        LOG.debug("result:{}", result);
        return result;
    }



}
