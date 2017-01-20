package springboot.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import springboot.model.Todo;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by indra.e.prasetya on 1/18/2017.
 */
@Service
public class TodoRepositoryImpl implements TodoRepositoryCustom{

  private static final Logger LOG = LoggerFactory.getLogger(TodoRepositoryImpl.class);

  @Autowired
  private EntityManager entityManager;

  public boolean store(Todo todo) {
    LOG.debug("store...");
    try {
      entityManager.persist(todo);
      return true;
    }catch (Exception e){
      return false;
    }
  }

  public List<Todo> getAll() {
    LOG.debug("getAll...");
    List<Todo> result = this.entityManager
            .createNativeQuery("SELECT id,name,priority from todo" , Todo.class)
            .getResultList();
    LOG.debug("result:{}", result);

    return result;
  }
}
