package springboot.repository;

import java.util.List;

import springboot.model.Todo;

public interface TokoRepositoryCustom {
	public boolean store(Todo todo);

	public List<Todo> getAll();
}
