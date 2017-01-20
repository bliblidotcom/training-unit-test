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
  //todoService depends ke todoRepository
  @Autowired
  private TodoRepository todoRepository;
  private static final Logger LOG=LoggerFactory.getLogger(TodoService.class);
  
  public boolean saveTodo(String name, TodoPriority priority) {
    Todo todo = new Todo(name, priority);
    LOG.debug("saveTodo...");
    return todoRepository.store(todo);
  }

  //saat unit test penyimpanan dan pengaksesan data hanya dilakukan dengan manipulasi
  public List<Todo> getAll() {
      LOG.debug("getAll...");
      List<Todo> result=todoRepository.getAll();
      LOG.debug("result:{}",result);
      
    return result;
  }

}
