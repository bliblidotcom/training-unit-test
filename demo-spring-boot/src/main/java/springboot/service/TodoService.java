package springboot.service;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.model.Todo;
import springboot.model.constants.TodoPriority;
import springboot.repository.TodoRepository;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by indra.e.prasetya on 1/18/2017.
 */
@Service
public class TodoService {

  private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(TodoService.class);

  @Autowired
  private TodoRepository todoRepository;

  public boolean saveTodo(String name, TodoPriority priority) {
    Todo todo = new Todo(name, priority);
    LOG.debug("saveTodo...");
    LOG.debug(todo.getName());
    return todoRepository.store(todo);
  }

  public List<Todo> getAll() {
    LOG.debug("servicegetAll...");
    List<Todo> result = todoRepository.getAll();
    LOG.debug("result:{}",result);
    return result;
  }

}
