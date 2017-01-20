package springboot.repository;

import springboot.model.Todo;
import springboot.model.constants.TodoPriority;

import java.util.List;

/**
 * Created by Elisabet Diana K S on 20/01/2017.
 */
public interface TodoRepositoryCustom {
    public boolean store(Todo todo);
    public List<Todo> getAll();
}
