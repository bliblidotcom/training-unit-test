package springboot.repository;

import org.springframework.stereotype.Service;
import springboot.model.Todo;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by indra.e.prasetya on 1/18/2017.
 */
@Service
public class TodoRepository {
    
    //buat nyatet log nya yg di test
    private static final Logger LOG=LoggerFactory.getLogger(TodoRepository.class);
    

  private final List<Todo> todos = new ArrayList<Todo>();

  public boolean store(Todo todo) {
    LOG.debug("store...");
    todos.add(todo);
    LOG.debug(todo.toString());
    return true;
  }

  public List<Todo> getAll() {
      LOG.debug("get All ...");
      List<Todo> result=new ArrayList<Todo>(todos);
      LOG.debug ("result :{}",result);
      return result;
        //return new ArrayList<Todo>(todos);
  }
}
