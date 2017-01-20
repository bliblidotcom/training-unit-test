package springboot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.model.Todo;
import springboot.repository.TodoH2Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by indra.e.prasetya on 1/18/2017.
 */
@Service
public class TodoService {

	private static final Logger LOG = LoggerFactory.getLogger(TodoService.class);

	@Autowired
	private TodoH2Repository todoRepository;

	public Todo saveTodo(Todo todo) {
		LOG.debug("saveTodo...");
		Todo tudu = todoRepository.store(todo);
		LOG.debug("result: "+ tudu.toString());
		return tudu;

	}

	public List<Todo> getAll() {
		List<Todo> todoList = new ArrayList<Todo>();
		LOG.debug("getAll...");
		for (Todo todo : todoRepository.getAll()) {
			todoList.add(todo);
		}
		LOG.debug("result : {}", todoList);
		return todoList;
	}

}
