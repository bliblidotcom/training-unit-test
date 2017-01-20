package springboot.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.stereotype.Service;
import springboot.model.Todo;
import springboot.model.constants.TodoPriority;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by indra.e.prasetya on 1/18/2017.
 */
@Service
public class TodoRepositoryImpl implements TodoRepositoryCustom {

  private static final Logger LOG = LoggerFactory.getLogger(TodoRepositoryImpl.class);
  @Autowired
  private EntityManager entityManager;


  private final List<Todo> todos = new ArrayList<Todo>();

  @Override
  public Todo store(Todo todo) {
//    LOG.debug("store...");
//    todos.add(todo);
//  //  EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
//   // return builder.setType(EmbeddedDatabaseType.HSQL).build();
//    Todo todoOne =this.todoRepository.save(new Todo("1","todo", TodoPriority.HIGH));

    String sql ="INSERt iNTO TODO(id,name,priority) values(:id,:nama,:prioritas);";
  // entityManager.createNativeQuery("INSERT INTO todo ('id','name','priority') VALUES ('"+todo.getId()+"','"+todo.getName()+"','"+todo.getPriority().ordinal()+"')").executeUpdate();
    entityManager.createNativeQuery(sql).setParameter("id",1)
            .setParameter("nama","ryan")
            .setParameter("prioritas",todo.getPriority().ordinal()).executeUpdate();
    return todo ;
  }

  public List<Todo> getAll() {
    LOG.debug("getAll...");
   // List<Todo> result = new ArrayList<Todo>(todos);
 //   LOG.debug("result:{}", result);
   // queryForList(String sql, Map<String,?> paramMap)

    List<Todo> result =
            this.entityManager.createNativeQuery("select id , name ,priority from todo",Todo.class).getResultList();
    return result;
  }
}
