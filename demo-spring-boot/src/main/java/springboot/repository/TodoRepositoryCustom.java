package springboot.repository;

import springboot.model.Todo;

import java.util.List;

/**
 * Created by Ryan Bagus Susilo on 1/20/2017.
 */
public interface TodoRepositoryCustom {
    public Todo store(Todo todo);
    public List getAll();
}
