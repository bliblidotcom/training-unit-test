package springboot.service;

import java.util.List;

import springboot.model.Todo;
import springboot.model.constants.TodoPriority;

public interface TodoServiceInterface {
	public boolean saveTodo(String name, TodoPriority priority);
	public List<Todo> getAll();
}
