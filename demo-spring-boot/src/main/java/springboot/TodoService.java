package springboot;


import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.model.Todo;
import springboot.model.constants.TodoPriority;
import springboot.repository.TodoRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by indra.e.prasetya on 1/18/2017.
 */
@Service
public class TodoService {

  private static final Logger LOG= org.slf4j.LoggerFactory.getLogger(TodoService.class);
  @Autowired
  private TodoRepository todoRepository;

  public boolean saveTodo(String name, TodoPriority priority) {
   LOG.debug("save todo ");
    Todo todo = new Todo(name, priority);

    return todoRepository.store(todo);
  }

  public List<Todo> getAll() {
    LOG.debug("GetAll");
    List<Todo> result=todoRepository.getAll();
     //todoRepository.store(null);
    LOG.debug("result : {}",result);
//    List<Todo> todoList= new ArrayList<Todo>();
//    todoList.add(new Todo("one", TodoPriority.HIGH));
//    todoList.add(new Todo("two",TodoPriority.MEDIUM));
    return result;
  }

}
