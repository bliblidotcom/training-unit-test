package springboot.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import springboot.model.Todo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by indra.e.prasetya on 1/18/2017.
 */
@Service
public class TodoRepository {
	
	private static final Logger LOG = LoggerFactory.getLogger(TodoRepository.class); 

  private final List<Todo> todos = new ArrayList<Todo>();

  public boolean store(Todo todo) {
	LOG.debug("Store...");
    todos.add(todo);

    return true;
  }

  public List<Todo> getAll() {
	LOG.debug("getAll...");
	List<Todo> result = new ArrayList<Todo>(todos);
    LOG.debug("Result...:{}",result);
    return result;
  }
}
