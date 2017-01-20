package springboot.repository;

import org.springframework.stereotype.Service;
import springboot.model.Todo;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import springboot.service.TodoService;

/**
 * Created by indra.e.prasetya on 1/18/2017.
 */
@Service
public class TodoRepository {

  private final List<Todo> todos = new ArrayList<Todo>();
  private static final Logger LOG=LoggerFactory.getLogger(TodoRepository.class);
  
  
  public boolean store(Todo todo) {
    todos.add(todo);
    LOG.debug("storeRepository()..");
    return true;
  }

  public List<Todo> getAll() {
      LOG.debug("getAllTodoRepository()..");
    return new ArrayList<Todo>(todos);
  }
}
