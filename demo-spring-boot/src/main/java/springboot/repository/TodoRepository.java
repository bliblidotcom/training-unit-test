package springboot.repository;

import org.springframework.stereotype.Service;
import springboot.model.Todo;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import springboot.model.constants.TodoPriority;

/**
 * Created by indra.e.prasetya on 1/18/2017.
 */
@Service
public class TodoRepository {

  private static final Logger LOG = LoggerFactory.getLogger(TodoRepository.class); //import yang slf4j
  
  private final List<Todo> todos = new ArrayList<Todo>();

  public boolean store(Todo todo) {
    LOG.debug("store...");
    todos.add(todo);

    return true;
  }

  public List<Todo> getAll() {
    LOG.debug("getall Todo...");
//    List<Todo> result = new ArrayList<Todo>(todos);
//    LOG.debug("result:{}",result);
        List<Todo> todoList = new ArrayList<Todo>();
        todoList.add(new Todo("one",TodoPriority.HIGH));
        todoList.add(new Todo("two",TodoPriority.MEDIUM));
        todoList.add(new Todo("three",TodoPriority.LOW));
    return todoList;
  }
}
