package springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.model.Todo;
import springboot.model.constants.TodoPriority;

import java.util.List;

/**
 * Created by Juan on 1/20/17.
 */

public interface TodoRepository extends JpaRepository<Todo, Long>, TodoRepositoryCustom {
    Todo findById(Long id);
    List<Todo> findByPriority(TodoPriority todoPriority);
    List<Todo> findByName(String name);
}
