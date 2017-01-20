package springboot.service;

import java.util.List;

import springboot.model.Todo;
import springboot.model.constants.TodoPriority;

public interface TodoEntityRepositoryCustom {
	
	public List<Todo> getAll();
	public boolean saveTodo(String name, TodoPriority priority) ;
}
