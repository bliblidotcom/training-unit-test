package springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.model.Todo;
import springboot.model.constants.TodoPriority;
import springboot.repository.TodoRepository;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by indra.e.prasetya on 1/18/2017.
 */
@Service
public class TodoService {

  private static final Logger LOG = LoggerFactory.getLogger(TodoService.class);
    
  @Autowired
  private TodoRepository todoRepository;

  public boolean saveTodo(String name, TodoPriority priority) {
    LOG.debug("save...");
    Todo todo = new Todo(name, priority);
    LOG.debug("save:{}",todo);
    boolean result = todoRepository.store(todo);
    return result;
  }

  public List<Todo> getAll() {
    LOG.debug("getting todo list");
    List<Todo> result = todoRepository.getAll();
    
    LOG.debug("result:{}",result);
    return result;
  }

}
