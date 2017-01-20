package springboot.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import springboot.model.TodoNew;
import springboot.service.TodoService;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by Dias on 1/20/2017.
 */
public class TodoRepoImpl implements TodoRepoCustom {

    @Autowired
    EntityManager entityManager;

    private static final Logger LOG = LoggerFactory.getLogger(TodoService.class);

    @Override
    public boolean store(TodoNew todo) {
        LOG.debug("store Repo Custom.....");
        this.entityManager.persist(todo);
        this.entityManager.flush();
        return true;
    }

    @Override
    public List<TodoNew> getAll() {
        LOG.debug("getAll Repo Custom.....");
        List<TodoNew> todoList = this.entityManager.createNativeQuery(
                "SELECT ID, NAME, PRIORITY FROM TodoNew", TodoNew.class).getResultList();
        LOG.debug("todoList: {}",todoList);
        return todoList;
    }
}
