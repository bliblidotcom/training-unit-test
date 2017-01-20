package springboot.repository;

import springboot.model.Todo;

import java.util.List;

/**
 * Created by arielchristianto on 1/20/17.
 */
public interface TodoRepositoryCustom {
    boolean store(Todo todo);

    List<Todo> getAll();
}
