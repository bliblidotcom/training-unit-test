package springboot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger LOG = LoggerFactory.getLogger(TodoService.class);
	
  @Autowired
  private TodoRepository todoRepository;

  public boolean saveTodo(String name, TodoPriority priority) {
	LOG.debug("saveToDo...");
    Todo todo = new Todo(name, priority);

    return todoRepository.store(todo);
  }

  public List<Todo> getAll() {
	  LOG.debug("getAll...");
	  List<Todo> result = todoRepository.getAll();
	  LOG.debug("result :{}",result);
	  //todoRepository.store(todo);
	  //todo yang terpanggil 2 kali, bikin error
	  //wanted but not invoked
//	  List<Todo> todoList = new ArrayList<Todo>();
//		todoList.add(new Todo("one",TodoPriority.HIGH));
//		todoList.add(new Todo("two",TodoPriority.MEDIUM));
//		todoList.add(new Todo("three",TodoPriority.LOW));
	  return result;
  }
}
