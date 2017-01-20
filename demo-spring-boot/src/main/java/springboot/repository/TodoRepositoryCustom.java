package springboot.repository;

import springboot.model.Todo;

import java.util.List;

/**
 * Created by isdzulqor on 1/20/17.
 */
public interface TodoRepositoryCustom {
    public boolean store(Todo todo);
    public List<Todo> getAll();
}
