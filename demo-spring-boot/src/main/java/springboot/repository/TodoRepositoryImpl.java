/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springboot.repository;

import java.util.List;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import springboot.model.Todo;
import springboot.service.TodoService;
import javax.persistence.EntityManager;
import org.hibernate.Query;


/**
 *
 * @author Marlina
 */
@Repository
public class TodoRepositoryImpl implements TodoRepositoryCustom {

        private static final Logger LOG=LoggerFactory.getLogger(TodoRepository.class);
        private final List<Todo> todos=new ArrayList<Todo>();
        @Autowired
        private EntityManager em;
    @Override
    public boolean store(Todo todo) {
        LOG.debug("store...");
        String query="INSERT INTO Todo (id,name,priority) VALUES(?,?,?)";
        Integer t=this.em.createNativeQuery(query).setParameter(1, todo.getId())
                .setParameter(2, todo.getName()).setParameter(3, todo.getPriority().ordinal())
                .executeUpdate();
        LOG.debug("ID DATA : {}",t);
        return true;
    }

    @Override
    public List<Todo> getAll() {
        LOG.debug("getAll....");
        List<Todo> result=this.em.createNativeQuery("select id, name,priority from todo",Todo.class).getResultList();
        LOG.debug("result:{}",result);
        return result;
    }
    
}
