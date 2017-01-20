package springboot.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import springboot.model.Todo;
import springboot.service.TodoService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by indra.e.prasetya on 1/18/2017.
 */
@Service
public class TodoRepository {

	private static Logger LOG = LoggerFactory.getLogger(TodoService.class);

	private final List<Todo> todos = new ArrayList<Todo>();

	public boolean store(Todo todo) {
		LOG.debug("Repo save called");
		todos.add(todo);
		return true;
	}

	public List<Todo> getAll() {
		LOG.debug("Repo getAll called");		
		return new ArrayList<Todo>(todos);
	}
}
