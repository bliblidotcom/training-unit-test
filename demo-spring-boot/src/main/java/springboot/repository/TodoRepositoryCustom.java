package springboot.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import springboot.model.Todo;

public interface TodoRepositoryCustom  {
	public List<Todo> getAll();
	public boolean store (Todo todo);
}
