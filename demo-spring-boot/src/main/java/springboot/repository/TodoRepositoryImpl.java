package springboot.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import springboot.model.Todo;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by indra.e.prasetya on 1/18/2017.
 */

public class TodoRepositoryImpl {

  private static final Logger LOG = LoggerFactory.getLogger(TodoRepositoryImpl.class);

  private final List<Todo> todos = new ArrayList<Todo>();

  @Autowired
  private EntityManager entityManager;

  public boolean store(Todo todo) {
    LOG.debug("store...");
    boolean result;

    try {
      entityManager.persist(todo);
      result = true;
    } catch(Exception e) {
      result = false;
    }

    return result;
  }

  public List<Todo> getAll() {
    LOG.debug("getAll...");
    List<Todo> result = entityManager.createNativeQuery("Select id, name, priority from todo", Todo.class).getResultList();
    LOG.debug("result:{}", result);
    return result;
  }
}
