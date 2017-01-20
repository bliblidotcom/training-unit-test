package springboot.repository;

import springboot.model.Todo;

import java.util.List;

/**
 * Created by ARDI on 1/20/2017.
 */
public interface TodoRepositoryCustom{

    public boolean store(Todo todo);
    public List<Todo> getAll();

}
