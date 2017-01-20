/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springboot.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties.Template;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import springboot.model.Todo;

/**
 *
 * @author Amesa
 */

public class TodoRepositoryImpl implements TodoRepositoryCustom {

    private static final Logger LOG = LoggerFactory.getLogger(TodoRepository.class);
    private final List<Todo> todos = new ArrayList<Todo>();

    
    @Autowired
    private EntityManager entityManager;
    @Override
    public boolean store(Todo todo) {
        LOG.debug("store...");
        todos.add(todo);

        return true;
    }

    @Override
    public List<Todo> getAll() {
        LOG.debug("getAll...");
        List<Todo> result = this.entityManager.createNativeQuery ("Select id, name, priority from todo", Todo.class).getResultList();
        LOG.debug("result:{}", result);
        return result;
    }

}
