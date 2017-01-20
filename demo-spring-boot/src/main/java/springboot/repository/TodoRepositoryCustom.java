package springboot.repository;

import springboot.model.Todo;

import java.util.List;

/**
 * Created by The Frost on 20/01/2017.
 */
public interface TodoRepositoryCustom {

    public boolean store(Todo todo);
    public List<Todo> getAll();
}
