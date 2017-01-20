package springboot.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import springboot.model.Todo;

public interface TodoRepositoryCustom{
	public boolean store(Todo todo);
	public List<Todo> getAll();
}
