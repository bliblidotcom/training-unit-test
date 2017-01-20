package springboot.repository;

import java.util.List;

import springboot.model.Todo;

public interface TodoH2RepositoryCustom {
	List<Todo> getAll();

	Todo store(Todo todo);
}
