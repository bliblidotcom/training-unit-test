package springboot.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import springboot.model.Todo;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by indra.e.prasetya on 1/18/2017.
 */
@Service
public class TodoRepositoryImpl implements TodoRepositoryCustom{

  private static final Logger LOG = LoggerFactory.
          getLogger(TodoRepositoryImpl.class);

  private final List<Todo> todos = new ArrayList<Todo>();
  
  @Autowired
  private EntityManager entityTemplate;
  
  @Override
  public boolean store(Todo todo) {
    LOG.debug("store...");
    this.entityTemplate.persist(todo);
    this.entityTemplate.flush();
//    this.entityTemplate
//            .createNativeQuery("INSERT INTO todo(name,priority) VALUES('"
//                    + todo.getName()+"',"+todo.getPriority()+")");
//    this.entityTemplate
//            .createNativeQuery("INSERT INTO todo(id,name,priority) VALUES("
//                    + todo.getId()+","
//                    + "'"
//                    +todo.getName()+"',"+todo.getPriority()+")");
    return true;
  }

  @Override
  public List<Todo> getAll() {
    LOG.debug("getAll...");
    List<Todo> result = this.entityTemplate.
            createNativeQuery("SELECT id,name,priority from todo t ",Todo.class)
            .getResultList();
    LOG.debug("result:{}", result);
    return result;
  }
}
