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

    //buat nyatet log nya yg di test
    private static final Logger LOG=LoggerFactory.getLogger(TodoService.class);
    
    
  @Autowired
  private TodoRepository todoRepository;

  public boolean saveTodo(String name, TodoPriority priority) {
    LOG.debug("saveTodo....");
    Todo todo = new Todo(name, priority);    
    LOG.debug(todo.getName(),todo.getPriority());
    return todoRepository.store(todo);
  }

  public List<Todo> getAll() {
      //getall
    LOG.debug("getAll..");
    List<Todo> result=todoRepository.getAll();
    //todoRepository.store(null);
    LOG.debug("result:{}",result);
    return result;
  }

}
